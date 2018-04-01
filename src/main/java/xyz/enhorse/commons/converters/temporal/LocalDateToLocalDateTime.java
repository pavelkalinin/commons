package xyz.enhorse.commons.converters.temporal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class LocalDateToLocalDateTime extends AbstractTemporalConverter<LocalDate, LocalDateTime> {

    public LocalDateToLocalDateTime(final ZoneId zoneId) {
        super(new LocalDateConverter(zoneId), new LocalDateTimeConverter(zoneId));
    }


    public LocalDateToLocalDateTime() {
        this(null);
    }
}

