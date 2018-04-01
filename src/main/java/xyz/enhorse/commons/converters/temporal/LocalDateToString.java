package xyz.enhorse.commons.converters.temporal;

import java.time.LocalDate;
import java.time.ZoneId;

public class LocalDateToString extends AbstractTemporalConverter<LocalDate, String> {

    public LocalDateToString(final ZoneId zoneId, final String format) {
        super(new LocalDateConverter(zoneId), new StringConverter(format));
    }


    public LocalDateToString(final String format) {
        this(null, format);
    }
}
