package xyz.enhorse.commons.converters.temporal;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static xyz.enhorse.commons.converters.temporal.TemporalData.*;

public class DateToLocalDateTest {

    @Test
    public void convert() {
        assertEquals(LOCAL_DATE, new DateToLocalDate(ZONE_ID).convert(DATE).orElseThrow(IllegalStateException::new));
    }

}