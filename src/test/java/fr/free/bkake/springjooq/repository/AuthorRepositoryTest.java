package fr.free.bkake.springjooq.repository;

import fr.free.bkake.springjooq.configuration.Config;
import fr.free.bkake.springjooq.domain.tables.pojos.Author;
import fr.free.bkake.springjooq.utils.ImmutableReferences;
import fr.free.bkake.springjooq.utils.References;
import org.jooq.DSLContext;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static fr.free.bkake.springjooq.domain.tables.Author.AUTHOR;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by bangaly.kake on 06/06/2017.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Config.class)
@Transactional("transactionManager")
public class AuthorRepositoryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorRepositoryTest.class);

    @Inject
    private AuthorRepository authorRepository;

    @Inject
    private DSLContext dsl;


    @Test
    public void fetchAll_authors_thenSucceed() {
        List<Author> authors = authorRepository.fetchAll(Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty());
        assertThat(authors.size()).isNotZero();
        authors.stream().forEach(author -> LOGGER.info("fetchAll_authors_thenSucceed :" + author.toString()));
    }

    @Test
    public void fetchAll_authors_firstName_like_P_thenSucceed() {
        List<Author> authors = authorRepository.fetchAll(Optional.empty(), Optional.of("%o"), Optional.empty(), Optional.empty(), Optional.empty());
        assertThat(authors.size()).isNotZero();
        authors.stream().forEach(author -> LOGGER.info("fetchAll_authors_thenSucceed :" + author.toString()));
    }

    @Test
    public void insertAuthor() {
        int resultActual = authorRepository.insertAuthor(5, Optional.of("Kake"), "Bangaly", givenDateOfBirth());
        assertThat(resultActual).isEqualTo(1);
        printResult(5, "insertAuthor:");
    }

    @Test
    public void updateAuthor() {
        int resultActual = authorRepository.updateAuthor(2, "Toto", "Bangaly", givenDateOfBirth().get());
        assertThat(resultActual).isEqualTo(1);
        printResult(2, "updateAuthor:" );
    }


    @Test
    public void deleteAuthorById() {
        authorRepository.insertAuthor(5, Optional.of("Kake"), "Bangaly", givenDateOfBirth());
        int resultActual = authorRepository.deleteAuthorById(3);
        assertThat(resultActual).isEqualTo(1);
    }

    @Test
    public void fetchById() throws Exception {
        List<Author> authors = authorRepository.fetchById((Integer[]) Arrays.asList(1, 2).toArray());
        assertThat(authors.size()).isNotZero();
        authors.stream().forEachOrdered(author -> LOGGER.info("fetchById :" + author));
    }

    @Test
    public void fetchOneById() throws Exception {
        Optional<Author> author = authorRepository.fetchOneById(1);

        assertThat(author.isPresent()).isTrue();
        assertThat(author.get().getId()).isEqualTo(1);
        assertThat(author.get().getFirstName()).isEqualTo("George");
        assertThat(author.get().getLastName()).isEqualTo("Orwell");
        assertThat(author.get().getYearOfBirth()).isEqualTo(1903);

        printResult(1, "fetchOneById:");
    }

    @Test
    public void fetchByFirstName() throws Exception {
        List<Author> authors = authorRepository.fetchByFirstName((String[]) Arrays.asList("Paulo").toArray());
        assertThat(authors.size()).isNotZero();
        authors.stream().forEachOrdered(author -> LOGGER.info(author.toString()));
    }

    @Test
    public void fetchByLastName() throws Exception {
        List<Author> authors = authorRepository.fetchByLastName((String[]) Arrays.asList("Orwell").toArray());
        assertThat(authors.size()).isNotZero();
        authors.stream().forEachOrdered(author -> LOGGER.info(author.toString()));
    }

    @Test
    public void fetchByDateOfBirth() throws Exception {
        List<Author> authors = authorRepository.fetchByDateOfBirth((LocalDate[]) Arrays.asList(LocalDate.of(1983, 8, 24)).toArray());
        assertThat(authors.size()).isEqualTo(2);
        authors.stream().forEachOrdered(author -> LOGGER.info(author.toString()));
    }

    @Test
    public void fetchByYearOfBirth() throws Exception {
        List<Author> authors = authorRepository.fetchByYearOfBirth((Integer[]) Arrays.asList(1983).toArray());
        assertThat(authors.size()).isNotZero();
        authors.stream().forEachOrdered(author -> LOGGER.info(author.toString()));
    }

    private Optional<LocalDate> givenDateOfBirth() {
        return Optional.of(LocalDate.of(1990, 5, 2));
    }

    private void printResult(Integer value, String msg) {
        Optional<Author> author = dsl.selectFrom(AUTHOR)
                .where(AUTHOR.ID.eq(value))
                .fetchOptionalInto(Author.class);

        if (author.isPresent()) {
            LOGGER.info(msg + " " + author.get() );
        }
    }
}