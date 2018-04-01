package xyz.enhorse.commons.converters.temporal;

import org.junit.Test;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static xyz.enhorse.commons.converters.temporal.TemporalData.*;

public class LocalDateConverterTest {

    @Test
    public void convertToZoned() {
        final ZonedDateTime actual = new LocalDateConverter(ZONE_ID).convert(LOCAL_DATE).orElse(null);
        assertNotNull(actual);
        assertEquals(ZONED.toInstant().truncatedTo(ChronoUnit.DAYS), actual.toInstant());
    }


    @Test
    public void convertFromZoned() {
        final LocalDate actual = new LocalDateConverter().convert(ZONED).orElse(null);
        assertNotNull(actual);
        assertEquals(LOCAL_DATE, actual);
    }
}