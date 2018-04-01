package xyz.enhorse.commons.converters.temporal;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateToLocalDateTime extends AbstractTemporalConverter<Date, LocalDateTime> {

    public DateToLocalDateTime(final ZoneId zoneId) {
        super(new DateConverter(zoneId), new LocalDateTimeConverter(zoneId));
    }


    public DateToLocalDateTime() {
        this(null);
    }

}
