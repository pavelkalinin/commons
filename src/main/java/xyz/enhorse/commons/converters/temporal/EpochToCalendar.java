package xyz.enhorse.commons.converters.temporal;

import java.time.ZoneId;
import java.util.Calendar;

public class EpochToCalendar extends AbstractTemporalConverter<Long, Calendar> {


    public EpochToCalendar(final ZoneId zoneId) {
        super(new EpochConverter(zoneId), new CalendarConverter(zoneId));
    }


    public EpochToCalendar() {
        this(null);
    }
}
