package xyz.enhorse.commons.converters.temporal;

import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class CalendarToDate extends AbstractTemporalConverter<Calendar, Date> {

    public CalendarToDate(final ZoneId zoneId) {
        super(new CalendarConverter(zoneId), new DateConverter(zoneId));
    }


    public CalendarToDate() {
        this(null);
    }
}
