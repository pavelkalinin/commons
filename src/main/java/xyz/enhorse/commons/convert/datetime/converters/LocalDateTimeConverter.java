package xyz.enhorse.commons.convert.datetime.converters;


import xyz.enhorse.commons.convert.ConverterFor;
import xyz.enhorse.commons.convert.datetime.TemporalConverter;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Optional;

/**
 * The converter for {@link LocalDateTime}
 * @author Pavel Kalinin on 09.04.2018.
 */
@ConverterFor(LocalDateTime.class)
@SuppressWarnings("unused")
public class LocalDateTimeConverter implements TemporalConverter<LocalDateTime> {

    /** {@inheritDoc} */
    @Override
    public Optional<LocalDateTime> nominated(final ZonedDateTime value) {
        return Optional.ofNullable(value)
                .map(ZonedDateTime::toLocalDateTime);
    }


    /** {@inheritDoc} */
    @Override
    public Optional<ZonedDateTime> denominated(final LocalDateTime value) {
        //java.time.LocalDateTime objects do not contain any timezone information by themselves so we use UTC
        return Optional.ofNullable(value)
                .map(date -> date.atZone(ZoneOffset.UTC));
    }
}
