package xyz.enhorse.commons.converters.temporal;

import java.time.ZoneId;
import java.util.Calendar;

public class CalendarToString extends AbstractTemporalConverter<Calendar, String> {

    public CalendarToString(final ZoneId zoneId, final String format) {
        super(new CalendarConverter(zoneId), new StringConverter(format));
    }


    public CalendarToString(final String format) {
        this(null, format);
    }
}
