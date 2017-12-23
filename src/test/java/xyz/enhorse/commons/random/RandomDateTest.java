package xyz.enhorse.commons.random;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Tests for {@link RandomDate}
 * @author <a href="mailto:ext_pkalinin@rencredit.ru">Pavel Kalinin</a>
 * on 23/12/17.
 */
public class RandomDateTest {

    private static final int NUMBER_OF_GENERATION_CYCLES = 1000;
    private static final int YEARS_GAP = 1;
    private static final LocalDate NOW = LocalDate.now();


    @Test
    public void next() {
        next(NOW.minusYears(YEARS_GAP), NOW.plusYears(YEARS_GAP));
    }


    @Test
    public void nextForPastDates() {
        next(NOW.minusYears(YEARS_GAP), NOW);
    }


    @Test
    public void nextForFutureDates() {
        next(NOW, NOW.plusYears(YEARS_GAP));
    }


    @Test
    public void nextForTheOneDay() {
        next(NOW, NOW);
    }


    @Test
    public void nextWithDefaultConstructor() {
        assertNotNull(new RandomDate().next());
    }


    private void next(final LocalDate from, final LocalDate to) {
        final RandomGenerator<LocalDate> generator = new RandomDate(from, to);
        final String message = "Generated date %s " + String.format("is not in the range [%s,%s)", from, to);

        for (int i = 0; i < NUMBER_OF_GENERATION_CYCLES; i++) {
            final LocalDate actual = generator.next();
            assertTrue(String.format(message, actual), isInRange(actual, from, to));
        }
    }


    private boolean isInRange(final LocalDate date, final LocalDate past, final LocalDate future) {
        return date.isEqual(past) || (date.isAfter(past) && date.isBefore(future));
    }
}