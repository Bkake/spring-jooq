package fr.free.bkake.springjooq.utils;

import java.time.LocalDate;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * Created by bangaly.kake on 06/06/2017.
 */
public class PredicateUtils {

    public static final Predicate<Optional<String>> strIsPresent = getGenericPredicate(Optional::isPresent);
    public static final Predicate<Optional<Integer>> intIsPresent = getGenericPredicate(Optional::isPresent);
    public static final Predicate<Optional<LocalDate>> dateIsPresent = getGenericPredicate(Optional::isPresent);

    private static <T> Predicate<T> getGenericPredicate(Predicate<T> p) {
        return p;
    }
}
