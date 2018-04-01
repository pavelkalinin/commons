package xyz.enhorse.commons.converters.temporal;

import java.time.ZoneId;
import java.util.Calendar;

public class StringToCalendar extends AbstractTemporalConverter<String, Calendar> {

    public StringToCalendar(final String format, final ZoneId zoneId) {
        super(new StringConverter(format), new CalendarConverter(zoneId));
    }


    public StringToCalendar(final String format) {
        this(format, null);
    }
}
