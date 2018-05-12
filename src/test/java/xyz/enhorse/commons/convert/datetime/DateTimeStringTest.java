package xyz.enhorse.commons.convert.datetime;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static xyz.enhorse.commons.convert.datetime.TemporalTestData.TEMPORAL_STRING;

/**
 * Tests for {@link DateTimeString}
 * @author Pavel Kalinin on 10.04.2018.
 */
public class DateTimeStringTest {

    @Test
    public void testCreateWhenParameterIsNull() {
        new DateTimeString(null).value();
    }


    @Test
    public void testWithWhenParameterIsNull() {
        assertNotNull(new DateTimeString(TEMPORAL_STRING).with(null));
    }
}