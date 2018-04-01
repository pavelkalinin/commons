package xyz.enhorse.commons.converters.temporal;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;

public class CalendarToLocalDateTime extends AbstractTemporalConverter<Calendar, LocalDateTime> {

    public CalendarToLocalDateTime(final ZoneId zoneId) {
        super(new CalendarConverter(zoneId), new LocalDateTimeConverter(zoneId));
    }


    public CalendarToLocalDateTime() {
        this(null);
    }
}
