package xyz.enhorse.commons.converters.temporal;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;

public class LocalDateToCalendar extends AbstractTemporalConverter<LocalDate, Calendar> {

    public LocalDateToCalendar(final ZoneId zoneId) {
        super(new LocalDateConverter(zoneId), new CalendarConverter(zoneId));
    }


    public LocalDateToCalendar() {
        this(null);
    }
}

