package xyz.enhorse.commons.converters.temporal;

import java.time.ZoneId;
import java.util.Date;

public class DateToEpoch extends AbstractTemporalConverter<Date, Long> {

    public DateToEpoch(final ZoneId zoneId) {
        super(new DateConverter(zoneId), new EpochConverter(zoneId));
    }


    public DateToEpoch() {
        this(null);
    }

}
