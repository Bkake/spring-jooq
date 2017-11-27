package fr.free.bkake.springjooq.converter;

import org.jooq.Converter;

import java.sql.Time;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

/**
 * Created by bangaly.kake on 06/06/2017.
 *
 * Converter jOOQ for LocalTime (java) <-> Time(db)
 */
public class LocalTimeConverter implements Converter<Time, LocalTime> {
    @Override
    public LocalTime from(Time dataBaseObject) {
        return dataBaseObject == null ? null : dataBaseObject.toLocalTime();
    }

    @Override
    public Time to(LocalTime userObject) {
        return userObject == null ? null :  Time.valueOf(userObject.truncatedTo(ChronoUnit.SECONDS));
    }

    @Override
    public Class<Time> fromType() {
        return Time.class;
    }

    @Override
    public Class<LocalTime> toType() {
        return LocalTime.class;
    }
}
