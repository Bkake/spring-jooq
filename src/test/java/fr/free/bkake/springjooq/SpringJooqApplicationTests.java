package fr.free.bkake.springjooq;

import fr.free.bkake.springjooq.configuration.Config;
import org.jooq.DSLContext;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


@Ignore
@RunWith(SpringRunner.class)
//@SpringBootTest(classes = SpringJooqApplication.class)
@Transactional("transactionManager")
@ContextConfiguration(classes = Config.class)
public class SpringJooqApplicationTests {

    @Autowired
    private DSLContext dsl;

    @Test
    public void givenValidData_whenInserting_thenSucceed() {

      /*  dsl.insertInto(AUTHOR)
                .set(AUTHOR.ID, 10)
                .set(AUTHOR.FIRST_NAME, "Herbert")
                .set(AUTHOR.LAST_NAME, "Schildt")
                .execute();

        dsl.insertInto(BOOK)
				.set(BOOK.ID, 4)
				.set(BOOK.TITLE, "A Beginner's Guide")
				.set(BOOK.AUTHOR_ID, 4)
				.execute();

		dsl.insertInto(AUTHOR_BOOK)
				.set(AUTHOR_BOOK.AUTHOR_ID, 4)
				.set(AUTHOR_BOOK.BOOK_ID, 4)
				.execute();

		final Result<Record3<Integer, String, Integer>> result = dsl.select(AUTHOR.ID, AUTHOR.LAST_NAME, DSL.count())
				.from(AUTHOR).join(AUTHOR_BOOK).on(AUTHOR.ID.equal(AUTHOR_BOOK.AUTHOR_ID))
				.join(BOOK).on(AUTHOR_BOOK.BOOK_ID.equal(BOOK.ID))
				.groupBy(AUTHOR.LAST_NAME)
				.fetch();

		assertEquals(3, result.size());
		assertEquals("Sierra", result.getValue(0, AUTHOR.LAST_NAME));
		assertEquals(Integer.valueOf(2), result.getValue(0, DSL.count()));
		assertEquals("Schildt", result.getValue(2, AUTHOR.LAST_NAME));
		assertEquals(Integer.valueOf(1), result.getValue(2, DSL.count())); */
    }
}
