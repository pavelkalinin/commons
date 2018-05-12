package xyz.enhorse.commons.convert.datetime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static xyz.enhorse.commons.convert.datetime.TemporalTestData.*;

/**
 * Tests for {@link Convert}
 * @author Pavel Kalinin on 10.04.2018.
 */
@RunWith(value = Parameterized.class)
public class ConvertTest {

    private final Object object;


    public ConvertTest(final Object object) {
        this.object = object;
    }


    @Test
    public void testFromWhenStringParameterIsNull() {
        final String parameter = null;
        assertNotNull(Convert.from(parameter));
    }


    @Test
    public void testFromWhenClassParameterIsNull() {
        final Class parameter = null;
        assertNotNull(Convert.from(parameter));
    }


    @Test
    public void testFrom() {
        Convert.from(object)
                .to(object.getClass())
                .ifPresent(actual -> assertEquals(object, actual));
    }


    @Test
    public void testFromStringToString() {
        Convert.from(object)
                .to(PATTERN)
                .map(Convert::from)
                .map(s -> s.with(PATTERN))
                .flatMap(s -> s.to(object.getClass()))
                .ifPresent(actual -> assertEquals(object, actual));
    }


    @Parameters
    public static Object[] temporalObjects() {
        return new Object[]
                {
                        ZONED_DATE_TIME,
                        LOCAL_DATE_TIME,
                        LOCAL_DATE,
                        CALENDAR,
                        DATE,
                        EPOCH
                };
    }
}