package xyz.enhorse.commons.converters.temporal;

import java.time.ZoneId;

public class EpochToString extends AbstractTemporalConverter<Long, String> {

    public EpochToString(final ZoneId zoneId, final String format) {
        super(new EpochConverter(zoneId), new StringConverter(format));
    }


    public EpochToString(final String format) {
        this(null, format);
    }
}
