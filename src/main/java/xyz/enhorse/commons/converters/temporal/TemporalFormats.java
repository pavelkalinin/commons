package xyz.enhorse.commons.converters.temporal;

import java.time.ZonedDateTime;
import java.util.Optional;

public enum TemporalFormats implements ZonedConverter<String> {

    ISO_8601_DATE_TIME("yyyy-MM-dd'T'HH:mm:ssZ"),
    ISO_8601("yyyy-MM-dd"),
    RUSSIAN_DOTTED("dd.MM.yyyy"),
    RUSSIAN_DASHED("dd-MM-yyyy");

    public static final ZonedConverter<String> DEFAULT = ISO_8601_DATE_TIME;
    private final StringConverter converter;
    private final String format;


    TemporalFormats(final String format) {
        this.format = format;
        converter = new StringConverter(format);
    }


    @Override
    public Optional<String> convert(final ZonedDateTime temporal) {
        return converter.convert(temporal);
    }


    @Override
    public Optional<ZonedDateTime> convert(final String string) {
        return converter.convert(string);
    }


    public String format() {
        return format;
    }
}
