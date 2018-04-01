package xyz.enhorse.commons.converters.temporal;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static xyz.enhorse.commons.converters.temporal.TemporalData.*;

public class DateToEpochTest {

    @Test
    public void convert() {
        assertEquals(EPOCH, new DateToEpoch(ZONE_ID).convert(DATE).orElseThrow(IllegalStateException::new));
    }
}