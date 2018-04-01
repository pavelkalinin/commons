package xyz.enhorse.commons.converters.temporal;

import org.junit.Test;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static xyz.enhorse.commons.converters.temporal.TemporalData.*;

public class EpochConverterTest {

    @Test
    public void convertToZoned() {
        final ZonedDateTime actual = new EpochConverter(ZONE_ID).convert(EPOCH).orElse(null);
        assertNotNull(actual);
        assertEquals(ZONED.toInstant().truncatedTo(ChronoUnit.SECONDS), actual.toInstant());
    }


    @Test
    public void convertFromZoned() {
        final Long actual = new EpochConverter().convert(ZONED).orElse(null);
        assertNotNull(actual);
        assertEquals(EPOCH, actual);
    }
}