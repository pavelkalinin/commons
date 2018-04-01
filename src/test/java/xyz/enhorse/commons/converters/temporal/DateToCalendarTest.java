package xyz.enhorse.commons.converters.temporal;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static xyz.enhorse.commons.converters.temporal.TemporalData.*;

public class DateToCalendarTest {

    @Test
    public void convert() {
        assertEquals(CALENDAR.toInstant(),
                new DateToCalendar(ZONE_ID).convert(DATE).orElseThrow(IllegalStateException::new).toInstant());
    }
}