package xyz.enhorse.commons.converters.temporal;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;

public class LocalDateTimeToCalendar extends AbstractTemporalConverter<LocalDateTime, Calendar> {

    public LocalDateTimeToCalendar(final ZoneId zoneId) {
        super(new LocalDateTimeConverter(zoneId), new CalendarConverter(zoneId));
    }


    public LocalDateTimeToCalendar() {
        this(null);
    }
}

