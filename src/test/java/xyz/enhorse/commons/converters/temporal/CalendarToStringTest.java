package xyz.enhorse.commons.converters.temporal;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static xyz.enhorse.commons.converters.temporal.TemporalData.*;

public class CalendarToStringTest {

    @Test
    public void convert() {
        assertEquals(TIMESTAMP,
                new CalendarToString(ZONE_ID, PATTERN).convert(CALENDAR).orElseThrow(IllegalStateException::new));
    }
}