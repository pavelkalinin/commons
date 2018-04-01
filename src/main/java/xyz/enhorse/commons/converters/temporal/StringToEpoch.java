package xyz.enhorse.commons.converters.temporal;

import java.time.ZoneId;

public class StringToEpoch extends AbstractTemporalConverter<String, Long> {

    public StringToEpoch(final String format, final ZoneId zoneId) {
        super(new StringConverter(format), new EpochConverter(zoneId));
    }


    public StringToEpoch(final String format) {
        this(format, null);
    }
}
