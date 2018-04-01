package xyz.enhorse.commons.converters.temporal;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static xyz.enhorse.commons.converters.temporal.TemporalData.*;

public class DateToStringTest {

    @Test
    public void convert() {
        assertEquals(TIMESTAMP,
                new DateToString(ZONE_ID, PATTERN).convert(DATE).orElseThrow(IllegalStateException::new));
    }
}