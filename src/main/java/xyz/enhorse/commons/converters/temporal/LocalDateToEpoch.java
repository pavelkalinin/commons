package xyz.enhorse.commons.converters.temporal;

import java.time.LocalDate;
import java.time.ZoneId;

public class LocalDateToEpoch extends AbstractTemporalConverter<LocalDate, Long> {

    public LocalDateToEpoch(final ZoneId zoneId) {
        super(new LocalDateConverter(zoneId), new EpochConverter(zoneId));
    }


    public LocalDateToEpoch() {
        this(null);
    }
}

