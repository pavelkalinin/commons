package xyz.enhorse.commons.converters.temporal;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;

public class LocalDateConverter extends AbstractZonedConverter<LocalDate> {

    public LocalDateConverter(final ZoneId zoneId) {
        super(zoneId);
    }


    public LocalDateConverter() {
    }


    @Override
    public Optional<LocalDate> convert(final ZonedDateTime temporal) {
        return Optional.ofNullable(temporal)
                .map(ZonedDateTime::toLocalDate);
    }


    @Override
    public Optional<ZonedDateTime> convert(final LocalDate source) {
        return Optional.ofNullable(source)
                .map(date -> date.atStartOfDay(zoneId()));
    }
}
