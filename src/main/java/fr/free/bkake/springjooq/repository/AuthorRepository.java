package fr.free.bkake.springjooq.repository;


import fr.free.bkake.springjooq.domain.tables.pojos.Author;
import org.apache.commons.lang3.StringUtils;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static fr.free.bkake.springjooq.domain.tables.Author.AUTHOR;
import static fr.free.bkake.springjooq.utils.ConstantsUtils._LIKEOPERATOR;
import static fr.free.bkake.springjooq.utils.PredicateUtils.dateIsPresent;
import static fr.free.bkake.springjooq.utils.PredicateUtils.intIsPresent;
import static fr.free.bkake.springjooq.utils.PredicateUtils.strIsPresent;

/**
 * Created by bangaly.kake on 06/06/2017.
 */
@Repository
public class AuthorRepository {

    private DSLContext dsl;

    @Inject
    public AuthorRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    public int insertAuthor(Integer id, Optional<String> firstName, String lastName, Optional<LocalDate> dateOfBirth) {
        return dsl.insertInto(AUTHOR)
                .set(AUTHOR.ID, id)
                .set(AUTHOR.FIRST_NAME, firstName.orElse(StringUtils.EMPTY))
                .set(AUTHOR.LAST_NAME, lastName)
                .set(AUTHOR.DATE_OF_BIRTH, dateOfBirth.get())
                .set(AUTHOR.YEAR_OF_BIRTH, dateOfBirth.isPresent() ? dateOfBirth.get().getYear() : null)
                .execute();
    }

    public int updateAuthor(Integer id, String firstName, String lastName, LocalDate dateOfBirth) {
        return dsl.update(AUTHOR)
                .set(AUTHOR.FIRST_NAME, firstName)
                .set(AUTHOR.LAST_NAME, lastName)
                .set(AUTHOR.DATE_OF_BIRTH, dateOfBirth)
                .set(AUTHOR.YEAR_OF_BIRTH, dateOfBirth.getYear())
                .where(AUTHOR.ID.equal(id))
                .execute();
    }

    public int deleteAuthorById(Integer id) {
        return dsl.delete(AUTHOR)
                .where(AUTHOR.ID.eq(id))
                .execute();
    }

    public List<Author> fetchById(Integer... values) {
        return dsl.selectFrom(AUTHOR)
                .where(AUTHOR.ID.in(values))
                .fetchInto(Author.class);
    }

    public Optional<Author> fetchOneById(Integer value) {
        return dsl.selectFrom(AUTHOR)
                .where(AUTHOR.ID.eq(value))
                .fetchOptionalInto(Author.class);
    }

    public List<Author> fetchByFirstName(String... values) {
        return dsl.selectFrom(AUTHOR)
                .where(AUTHOR.FIRST_NAME.in(values))
                .fetchInto(Author.class);
    }

    public List<Author> fetchByLastName(String... values) {
        return dsl.selectFrom(AUTHOR)
                .where(AUTHOR.LAST_NAME.in(values))
                .fetchInto(Author.class);
    }

    public List<Author> fetchByDateOfBirth(LocalDate... values) {
        return dsl.selectFrom(AUTHOR)
                .where(AUTHOR.DATE_OF_BIRTH.in(values))
                .fetchInto(Author.class);
    }


    public List<Author> fetchByYearOfBirth(Integer... values) {
        return dsl.selectFrom(AUTHOR)
                .where(AUTHOR.YEAR_OF_BIRTH.in(values))
                .fetchInto(Author.class);
    }

    public List<Author> fetchAll(Optional<Integer> id, Optional<String> firstName, Optional<String> lastName,
                                 Optional<LocalDate> dateOfBirth, Optional<Integer> yearOfBirth) {
        List<Condition> conditions = new ArrayList<>();

        if(intIsPresent.test(id)){
            conditions.add( AUTHOR.ID.eq(id.get()));
        }

        if (strIsPresent.test(firstName)){
            Condition likeFirstName = StringUtils.contains(firstName.get(), _LIKEOPERATOR) ?
                    AUTHOR.FIRST_NAME.like(firstName.get()) : AUTHOR.FIRST_NAME.eq(firstName.get());
            conditions.add(likeFirstName);
        }

        if (strIsPresent.test(lastName)){
            Condition likeLastName = StringUtils.contains(lastName.get(), _LIKEOPERATOR) ?
                    AUTHOR.LAST_NAME.like(lastName.get()) : AUTHOR.LAST_NAME.eq(lastName.get());
            conditions.add(likeLastName);
        }

        if (dateIsPresent.test(dateOfBirth)) {
            conditions.add(AUTHOR.DATE_OF_BIRTH.eq(dateOfBirth.get()));
        }

        if (intIsPresent.test(yearOfBirth)) {
            conditions.add(AUTHOR.YEAR_OF_BIRTH.eq(yearOfBirth.get()));
        }

        return dsl.selectFrom(AUTHOR)
                .where(DSL.and(conditions))
                .orderBy(AUTHOR.ID)
                .fetchInto(Author.class);
    }
}
