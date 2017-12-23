package xyz.enhorse.commons.random;

import org.junit.Test;

import java.time.temporal.ChronoUnit;

/**
 * Tests for {@link RandomEnumValue}
 * @author <a href="mailto:ext_pkalinin@rencredit.ru">Pavel Kalinin</a>
 * on 23/12/17.
 */
public class RandomEnumValueTest {

    @Test(timeout = 100)
    public void next() {
        final RandomGenerator<ChronoUnit> generator = new RandomEnumValue<>(ChronoUnit.class);

        final ChronoUnit started = generator.next();

        ChronoUnit actual;
        do {
            actual = generator.next();
        } while (actual == started);
    }

}