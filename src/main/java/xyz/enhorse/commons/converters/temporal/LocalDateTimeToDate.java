package xyz.enhorse.commons.converters.temporal;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class LocalDateTimeToDate extends AbstractTemporalConverter<LocalDateTime, Date> {

    public LocalDateTimeToDate(final ZoneId zoneId) {
        super(new LocalDateTimeConverter(zoneId), new DateConverter(zoneId));
    }


    public LocalDateTimeToDate() {
        this(null);
    }
}

