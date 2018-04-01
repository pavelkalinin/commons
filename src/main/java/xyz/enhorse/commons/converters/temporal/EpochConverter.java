package xyz.enhorse.commons.converters.temporal;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.chrono.ChronoZonedDateTime;
import java.util.Optional;

public class EpochConverter extends AbstractZonedConverter<Long> {

    public EpochConverter(final ZoneId zoneId) {
        super(zoneId);
    }


    public EpochConverter() {
    }


    @Override
    public Optional<Long> convert(final ZonedDateTime temporal) {
        return Optional.ofNullable(temporal)
                .map(ChronoZonedDateTime::toEpochSecond);
    }


    @Override
    public Optional<ZonedDateTime> convert(final Long source) {
        return Optional.ofNullable(source)
                .map(Instant::ofEpochSecond)
                .map(instant -> ZonedDateTime.ofInstant(instant, zoneId()));
    }
}
