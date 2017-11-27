package fr.free.bkake.springjooq.converter;

import org.jooq.Converter;

import java.sql.Date;
import java.time.LocalDate;

/**
 * Created by bangaly.kake on 06/06/2017.
 *
 * Converter jooQ for LocalDate (java) -> Date (db)
 */
public class LocalDateConverter implements Converter<Date, LocalDate> {
    @Override
    public LocalDate from(Date dataBaseObject) {
        return dataBaseObject == null ? null : dataBaseObject.toLocalDate();
    }

    @Override
    public Date to(LocalDate userObject) {
        return userObject == null ? null : Date.valueOf(userObject);
    }

    @Override
    public Class<Date> fromType() {
        return Date.class;
    }

    @Override
    public Class<LocalDate> toType() {
        return LocalDate.class;
    }
}