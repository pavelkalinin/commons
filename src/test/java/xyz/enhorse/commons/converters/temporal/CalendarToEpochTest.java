package xyz.enhorse.commons.converters.temporal;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static xyz.enhorse.commons.converters.temporal.TemporalData.*;

public class CalendarToEpochTest {

    @Test
    public void convert() {
        assertEquals(EPOCH, new CalendarToEpoch(ZONE_ID).convert(CALENDAR).orElseThrow(IllegalStateException::new));
    }
}