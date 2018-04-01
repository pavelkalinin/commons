package xyz.enhorse.commons.converters.temporal;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class StringConverter implements ZonedConverter<String> {

    private final DateTimeFormatter formatter;


    public StringConverter(final String format) {
        formatter = Optional.ofNullable(format)
                .filter(string -> !string.isEmpty())
                .map(DateTimeFormatter::ofPattern)
                .orElseThrow(() -> new IllegalArgumentException("Format must not be null or empty!"));
    }


    @Override
    public Optional<String> convert(final ZonedDateTime temporal) {
        return Optional.ofNullable(temporal)
                .map(formatter::format);
    }


    @Override
    public Optional<ZonedDateTime> convert(final String string) {
        return Optional.ofNullable(string)
                .map(s -> ZonedDateTime.parse(string, formatter));
    }
}
