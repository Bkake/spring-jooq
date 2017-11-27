/**
 * This class is generated by jOOQ
 */
package fr.free.bkake.springjooq.domain.tables.pojos;


import java.io.Serializable;
import java.time.LocalDate;

import javax.annotation.Generated;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.8.6"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Author implements Serializable {

    private static final long serialVersionUID = 1755278976;

    private Integer   id;
    private String    firstName;
    private String    lastName;
    private LocalDate dateOfBirth;
    private Integer   yearOfBirth;

    public Author() {}

    public Author(Author value) {
        this.id = value.id;
        this.firstName = value.firstName;
        this.lastName = value.lastName;
        this.dateOfBirth = value.dateOfBirth;
        this.yearOfBirth = value.yearOfBirth;
    }

    public Author(
        Integer   id,
        String    firstName,
        String    lastName,
        LocalDate dateOfBirth,
        Integer   yearOfBirth
    ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.yearOfBirth = yearOfBirth;
    }

    public Integer getId() {
        return this.id;
    }

    public Author setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public Author setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return this.lastName;
    }

    public Author setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public LocalDate getDateOfBirth() {
        return this.dateOfBirth;
    }

    public Author setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public Integer getYearOfBirth() {
        return this.yearOfBirth;
    }

    public Author setYearOfBirth(Integer yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Author (");

        sb.append(id);
        sb.append(", ").append(firstName);
        sb.append(", ").append(lastName);
        sb.append(", ").append(dateOfBirth);
        sb.append(", ").append(yearOfBirth);

        sb.append(")");
        return sb.toString();
    }
}
