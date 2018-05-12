package xyz.enhorse.commons.convert.datetime.converters;


import xyz.enhorse.commons.convert.ConverterFor;
import xyz.enhorse.commons.convert.datetime.TemporalConverter;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.chrono.ChronoZonedDateTime;
import java.util.Optional;

/**
 * The converter for Epoch time representation (in seconds that have elapsed since 00:00:00 UTC, 1 January 1970)
 * @author Pavel Kalinin on 09.04.2018.
 */
@ConverterFor(Long.class)
@SuppressWarnings("unused")
public class EpochConverter implements TemporalConverter<Long> {

    /** {@inheritDoc} */
    @Override
    public Optional<Long> nominated(final ZonedDateTime value) {
        return Optional.ofNullable(value)
                .map(ChronoZonedDateTime::toEpochSecond);
    }


    /** {@inheritDoc} */
    @Override
    public Optional<ZonedDateTime> denominated(final Long source) {
        return Optional.ofNullable(source)
                .map(Instant::ofEpochSecond)
                .map(instant -> ZonedDateTime.ofInstant(instant, ZoneOffset.UTC));
    }
}
