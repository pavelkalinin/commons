package xyz.enhorse.commons.converters.temporal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class LocalDateTimeToLocalDate extends AbstractTemporalConverter<LocalDateTime, LocalDate> {

    public LocalDateTimeToLocalDate(final ZoneId zoneId) {
        super(new LocalDateTimeConverter(zoneId), new LocalDateConverter(zoneId));
    }


    public LocalDateTimeToLocalDate() {
        this(null);
    }
}

