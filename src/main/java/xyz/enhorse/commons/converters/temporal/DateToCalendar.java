package xyz.enhorse.commons.converters.temporal;

import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class DateToCalendar extends AbstractTemporalConverter<Date, Calendar> {

    public DateToCalendar(final ZoneId zoneId) {
        super(new DateConverter(zoneId), new CalendarConverter(zoneId));
    }


    public DateToCalendar() {
        this(null);
    }

}
