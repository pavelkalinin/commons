package xyz.enhorse.commons.converters.temporal;

import java.time.LocalDate;
import java.time.ZoneId;

public class EpochToLocalDate extends AbstractTemporalConverter<Long, LocalDate> {


    public EpochToLocalDate(final ZoneId zoneId) {
        super(new EpochConverter(zoneId), new LocalDateConverter(zoneId));
    }


    public EpochToLocalDate() {
        this(null);
    }
}
