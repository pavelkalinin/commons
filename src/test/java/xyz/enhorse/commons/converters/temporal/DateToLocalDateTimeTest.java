package xyz.enhorse.commons.converters.temporal;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static xyz.enhorse.commons.converters.temporal.TemporalData.*;

public class DateToLocalDateTimeTest {

    @Test
    public void convert() {
        assertEquals(LOCAL_DATE_TIME,
                new DateToLocalDateTime(ZONE_ID).convert(DATE).orElseThrow(IllegalStateException::new));
    }
}