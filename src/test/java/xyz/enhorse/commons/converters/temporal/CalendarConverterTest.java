package xyz.enhorse.commons.converters.temporal;

import org.junit.Test;

import java.time.ZonedDateTime;
import java.util.Calendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static xyz.enhorse.commons.converters.temporal.TemporalData.*;

public class CalendarConverterTest {

    @Test
    public void convertToZoned() {
        final ZonedDateTime actual = new CalendarConverter(ZONE_ID).convert(CALENDAR).orElse(null);
        assertNotNull(actual);
        assertEquals(ZONED.toInstant(), actual.toInstant());
    }


    @Test
    public void convertFromZoned() {
        final Calendar actual = new CalendarConverter().convert(ZONED).orElse(null);
        assertNotNull(actual);
        assertEquals(CALENDAR.toInstant(), actual.toInstant());
    }
}