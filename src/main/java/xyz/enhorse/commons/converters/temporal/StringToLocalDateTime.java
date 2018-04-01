package xyz.enhorse.commons.converters.temporal;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class StringToLocalDateTime extends AbstractTemporalConverter<String, LocalDateTime> {

    public StringToLocalDateTime(final String format, final ZoneId zoneId) {
        super(new StringConverter(format), new LocalDateTimeConverter(zoneId));
    }


    public StringToLocalDateTime(final String format) {
        this(format, null);
    }

}
