package xyz.enhorse.commons.converters.temporal;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;

public class CalendarToLocalDate extends AbstractTemporalConverter<Calendar, LocalDate> {

    public CalendarToLocalDate(final ZoneId zoneId) {
        super(new CalendarConverter(zoneId), new LocalDateConverter(zoneId));
    }


    public CalendarToLocalDate() {
        this(null);
    }

}
