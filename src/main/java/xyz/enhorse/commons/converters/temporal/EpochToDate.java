package xyz.enhorse.commons.converters.temporal;

import java.time.ZoneId;
import java.util.Date;

public class EpochToDate extends AbstractTemporalConverter<Long, Date> {


    public EpochToDate(final ZoneId zoneId) {
        super(new EpochConverter(zoneId), new DateConverter(zoneId));
    }


    public EpochToDate() {
        this(null);
    }
}
