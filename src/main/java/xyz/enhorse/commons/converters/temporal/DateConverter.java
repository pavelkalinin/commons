package xyz.enhorse.commons.converters.temporal;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.chrono.ChronoZonedDateTime;
import java.util.Date;
import java.util.Optional;

public class DateConverter extends AbstractZonedConverter<Date> {

    public DateConverter(final ZoneId zoneId) {
        super(zoneId);
    }


    public DateConverter() {
    }


    @Override
    public Optional<Date> convert(final ZonedDateTime temporal) {
        return Optional.ofNullable(temporal)
                .map(ChronoZonedDateTime::toInstant)
                .map(Date::from);
    }


    @Override
    public Optional<ZonedDateTime> convert(final Date source) {
        return Optional.ofNullable(source)
                .map(Date::toInstant)
                .map(instant -> instant.atZone(zoneId()));
    }
}
