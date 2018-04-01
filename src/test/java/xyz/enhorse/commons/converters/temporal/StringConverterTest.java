package xyz.enhorse.commons.converters.temporal;

import org.junit.Test;

import java.time.ZonedDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static xyz.enhorse.commons.converters.temporal.TemporalData.*;

public class StringConverterTest {

    @Test
    public void convertToZoned() {
        final ZonedDateTime actual = new StringConverter(PATTERN).convert(TIMESTAMP).orElse(null);
        assertNotNull(actual);
        assertEquals(ZONED.toInstant(), actual.toInstant());
    }


    @Test
    public void convertFromZoned() {
        final String actual = new StringConverter(PATTERN).convert(ZONED).orElse(null);
        assertNotNull(actual);
        assertEquals(TIMESTAMP, actual);
    }
}