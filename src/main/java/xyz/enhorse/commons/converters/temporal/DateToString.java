package xyz.enhorse.commons.converters.temporal;

import java.time.ZoneId;
import java.util.Date;

public class DateToString extends AbstractTemporalConverter<Date, String> {

    public DateToString(final ZoneId zoneId, final String format) {
        super(new DateConverter(zoneId), new StringConverter(format));
    }


    public DateToString(final String format) {
        this(null, format);
    }
}
