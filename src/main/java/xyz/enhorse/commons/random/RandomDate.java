package xyz.enhorse.commons.random;

import xyz.enhorse.commons.Interval;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

import static xyz.enhorse.commons.Validate.defaultIfNull;

/**
 * The generator of a pseudo random {@link LocalDate}
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 * on 23/12/17.
 */
public class RandomDate implements RandomGenerator<LocalDate> {

    /** The default value for a start date */
    private static final LocalDate FROM = LocalDate.MIN;
    /** The default value for an end date */
    private static final LocalDate TO = LocalDate.MAX;

    /** The date interval */
    private Interval<Long> interval;


    /** Creates a {@code RandomDate} */
    public RandomDate() {
        this(FROM, TO);
    }


    /**
     * Creates a {@code RandomDate} with the specified interval
     * @param from the start date (inclusive)
     * @param to   the end date (exclusive)
     */
    public RandomDate(final LocalDate from, final LocalDate to) {
        interval = new Interval<>(
                defaultIfNull(from, FROM).toEpochDay(),
                defaultIfNull(to, TO).toEpochDay());
    }


    /** ${@inheritDoc} */
    @Override
    public LocalDate next() {
        return LocalDate.ofEpochDay(interval.isDegenerated()
                ? interval.from()
                : ThreadLocalRandom.current().nextLong(interval.from(), interval.to()));
    }
}
