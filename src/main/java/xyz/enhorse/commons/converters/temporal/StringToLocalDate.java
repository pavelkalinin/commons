package xyz.enhorse.commons.converters.temporal;

import java.time.LocalDate;
import java.time.ZoneId;

public class StringToLocalDate extends AbstractTemporalConverter<String, LocalDate> {

    public StringToLocalDate(final String format, final ZoneId zoneId) {
        super(new StringConverter(format), new LocalDateConverter(zoneId));
    }


    public StringToLocalDate(final String format) {
        this(format, null);
    }
}
