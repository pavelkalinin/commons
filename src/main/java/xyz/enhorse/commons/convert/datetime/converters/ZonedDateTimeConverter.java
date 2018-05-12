package xyz.enhorse.commons.convert.datetime.converters;

import xyz.enhorse.commons.convert.ConverterFor;
import xyz.enhorse.commons.convert.datetime.TemporalConverter;

import java.time.ZonedDateTime;
import java.util.Optional;

/**
 * The converter for {@link ZonedDateTime}
 * @author Pavel Kalinin on 09.04.2018.
 */
@ConverterFor(ZonedDateTime.class)
@SuppressWarnings("unused")
public class ZonedDateTimeConverter implements TemporalConverter<ZonedDateTime> {

    /** {@inheritDoc} */
    @Override
    public Optional<ZonedDateTime> nominated(final ZonedDateTime value) {
        return Optional.ofNullable(value);
    }


    /** {@inheritDoc} */
    @Override
    public Optional<ZonedDateTime> denominated(final ZonedDateTime value) {
        return nominated(value);
    }
}
