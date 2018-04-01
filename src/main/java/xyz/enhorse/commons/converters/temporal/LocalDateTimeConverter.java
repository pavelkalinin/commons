package xyz.enhorse.commons.converters.temporal;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;

public class LocalDateTimeConverter extends AbstractZonedConverter<LocalDateTime> {

    public LocalDateTimeConverter(final ZoneId zoneId) {
        super(zoneId);
    }


    public LocalDateTimeConverter() {
    }


    @Override
    public Optional<LocalDateTime> convert(final ZonedDateTime temporal) {
        return Optional.ofNullable(temporal)
                .map(ZonedDateTime::toLocalDateTime);
    }


    @Override
    public Optional<ZonedDateTime> convert(final LocalDateTime source) {
        return Optional.ofNullable(source)
                .map(date -> date.atZone(zoneId()));
    }

}
