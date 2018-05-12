package xyz.enhorse.commons.convert.datetime.converters;


import xyz.enhorse.commons.convert.ConverterFor;
import xyz.enhorse.commons.convert.datetime.DateTimeString;
import xyz.enhorse.commons.convert.datetime.TemporalConverter;
import xyz.enhorse.commons.convert.datetime.TemporalParser;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * The converter for {@link DateTimeString}
 * @author Pavel Kalinin on 09.04.2018.
 */
@ConverterFor(DateTimeString.class)
@SuppressWarnings("unused")
public class DateTimeStringConverter implements TemporalConverter<DateTimeString> {

    /** {@inheritDoc} */
    @Override
    public Optional<DateTimeString> nominated(final ZonedDateTime value) {
        return Optional.ofNullable(value)
                .map(ZonedDateTime::toString)
                .map(DateTimeString::new);
    }


    /** {@inheritDoc} */
    @Override
    public Optional<ZonedDateTime> denominated(final DateTimeString value) {
        return Optional.ofNullable(value)
                .flatMap(v -> parse(v.value(), v.format()));
    }


    /**
     * Parses the given string value with the given date-time format
     * @param value  the string value
     * @param format the format
     */
    private Optional<ZonedDateTime> parse(final String value, final String format) {
        return Optional.ofNullable(format)
                .map(DateTimeFormatter::ofPattern)
                .map(TemporalParser::new)
                .flatMap(p -> p.parse(value));
    }


}
