package xyz.enhorse.commons.converters.temporal;

import java.time.ZoneId;
import java.util.Date;

public class StringToDate extends AbstractTemporalConverter<String, Date> {

    public StringToDate(final String format, final ZoneId zoneId) {
        super(new StringConverter(format), new DateConverter(zoneId));
    }


    public StringToDate(final String format) {
        this(format, null);
    }
}
