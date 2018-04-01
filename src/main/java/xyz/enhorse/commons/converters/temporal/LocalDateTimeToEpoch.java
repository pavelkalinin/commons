package xyz.enhorse.commons.converters.temporal;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class LocalDateTimeToEpoch extends AbstractTemporalConverter<LocalDateTime, Long> {

    public LocalDateTimeToEpoch(final ZoneId zoneId) {
        super(new LocalDateTimeConverter(zoneId), new EpochConverter(zoneId));
    }


    public LocalDateTimeToEpoch() {
        this(null);
    }
}

