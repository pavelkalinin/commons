package xyz.enhorse.commons.convert.datetime.converters;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static xyz.enhorse.commons.convert.datetime.TemporalTestData.ZONED_DATE_TIME;

/**
 * Tests for {@link DateTimeStringConverter}
 * @author Pavel Kalinin on 10.04.2018.
 */
public class DateTimeStringConverterTest {

    @Test
    public void test() {
        new DateTimeStringConverter().nominated(ZONED_DATE_TIME)
                .flatMap(v -> new DateTimeStringConverter().denominated(v))
                .ifPresent(actual -> assertEquals(actual, ZONED_DATE_TIME));
    }
}