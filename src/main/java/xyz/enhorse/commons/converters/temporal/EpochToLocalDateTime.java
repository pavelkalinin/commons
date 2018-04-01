package xyz.enhorse.commons.converters.temporal;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class EpochToLocalDateTime extends AbstractTemporalConverter<Long, LocalDateTime> {


    public EpochToLocalDateTime(final ZoneId zoneId) {
        super(new EpochConverter(zoneId), new LocalDateTimeConverter(zoneId));
    }


    public EpochToLocalDateTime() {
        this(null);
    }
}
