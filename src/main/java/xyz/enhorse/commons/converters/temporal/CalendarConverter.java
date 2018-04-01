package xyz.enhorse.commons.converters.temporal;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Optional;

public class CalendarConverter extends AbstractZonedConverter<Calendar> {

    public CalendarConverter(final ZoneId zoneId) {
        super(zoneId);
    }


    public CalendarConverter() {
    }


    @Override
    public Optional<Calendar> convert(final ZonedDateTime temporal) {
        return Optional.ofNullable(temporal)
                .map(GregorianCalendar::from);
    }


    @Override
    public Optional<ZonedDateTime> convert(final Calendar source) {
        return Optional.ofNullable(source)
                .map(calendar -> calendar.toInstant().atZone(zoneId()));
    }
}
