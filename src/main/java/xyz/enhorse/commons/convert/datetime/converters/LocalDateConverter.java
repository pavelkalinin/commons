package xyz.enhorse.commons.convert.datetime.converters;


import xyz.enhorse.commons.convert.ConverterFor;
import xyz.enhorse.commons.convert.datetime.TemporalConverter;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Optional;

/**
 * The converter for {@link LocalDate}
 * @author Pavel Kalinin on 09.04.2018.
 */
@ConverterFor(LocalDate.class)
@SuppressWarnings("unused")
public class LocalDateConverter implements TemporalConverter<LocalDate> {

    /** {@inheritDoc} */
    @Override
    public Optional<LocalDate> nominated(final ZonedDateTime value) {
        return Optional.ofNullable(value)
                .map(ZonedDateTime::toLocalDate);
    }


    /** {@inheritDoc} */
    //java.time.LocalDate objects do not contain any timezone information by themselves so we use UTC
    @Override
    public Optional<ZonedDateTime> denominated(final LocalDate value) {
        return Optional.ofNullable(value)
                .map(date -> date.atStartOfDay(ZoneOffset.UTC));
    }
}
