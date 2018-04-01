package xyz.enhorse.commons.converters.temporal;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static xyz.enhorse.commons.converters.temporal.TemporalData.*;

public class CalendarToLocalDateTest {


    @Test
    public void convert() {
        assertEquals(LOCAL_DATE,
                new CalendarToLocalDate(ZONE_ID).convert(CALENDAR).orElseThrow(IllegalStateException::new));
    }

}