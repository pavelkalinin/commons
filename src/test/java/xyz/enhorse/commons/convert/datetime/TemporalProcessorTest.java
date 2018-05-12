package xyz.enhorse.commons.convert.datetime;


import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static xyz.enhorse.commons.convert.datetime.TemporalTestData.ZONED_DATE_TIME;

/**
 * Tests for {@link TemporalProcessor}
 * @author Pavel Kalinin on 10.04.2018.
 */
public class TemporalProcessorTest {

    @Test
    public void testCreateWhenParameterIsNull() {
        new TemporalProcessor<>(null);
    }


    @Test
    public void testToWhenParameterIsNullString() {
        final Class<?> parameter = null;
        assertEquals(new TemporalProcessor<>(ZONED_DATE_TIME).to(parameter), Optional.empty());
    }


    @Test
    public void testToWhenParameterIsNullClass() {
        final String parameter = null;
        assertEquals(new TemporalProcessor<>(ZONED_DATE_TIME).to(parameter), Optional.empty());
    }
}