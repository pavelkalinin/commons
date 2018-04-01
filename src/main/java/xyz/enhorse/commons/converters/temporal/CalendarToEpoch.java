package xyz.enhorse.commons.converters.temporal;

import java.time.ZoneId;
import java.util.Calendar;

public class CalendarToEpoch extends AbstractTemporalConverter<Calendar, Long> {

    public CalendarToEpoch(final ZoneId zoneId) {
        super(new CalendarConverter(zoneId), new EpochConverter(zoneId));
    }


    public CalendarToEpoch() {
        this(null);
    }
}
