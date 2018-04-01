package xyz.enhorse.commons.converters.temporal;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class LocalDateTimeToString extends AbstractTemporalConverter<LocalDateTime, String> {

    public LocalDateTimeToString(final ZoneId zoneId, final String format) {
        super(new LocalDateTimeConverter(zoneId), new StringConverter(format));
    }


    public LocalDateTimeToString(final String format) {
        this(null, format);
    }
}
