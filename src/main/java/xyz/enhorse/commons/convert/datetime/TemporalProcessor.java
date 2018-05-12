package xyz.enhorse.commons.convert.datetime;


import xyz.enhorse.commons.convert.Converters;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * Conversion process from the type {@link T}
 * @param <T> the type to convert
 */
public class TemporalProcessor<T> {

    /** The value to convert */
    private final T value;


    TemporalProcessor(final T value) {
        this.value = value;
    }


    /**
     * Performs conversion process from the type {@link T} to the given type {@link E}
     * @param type the class of the type of convert to
     * @param <E>  the type of convert to
     * @return the converted value
     */
    public <E> Optional<E> to(final Class<E> type) {
        return Optional.ofNullable(type)
                .flatMap(this::convert);
    }


    /**
     * Performs conversion process from the type {@link T} to the string with the given format of date-time
     * representation
     * @param format the format of date-time representation
     * @return the converted string
     */
    public Optional<String> to(final String format) {
        return Optional.ofNullable(format)
                .flatMap(this::format);
    }


    @SuppressWarnings("unchecked")
    private <E> Optional<E> convert(final Class<E> type) {
        final TemporalConverter<T> from = (TemporalConverter<T>) Converters.TEMPORAL.getConverter(value);
        final TemporalConverter<E> to = (TemporalConverter<E>) Converters.TEMPORAL.getConverter(type);

        return Optional.ofNullable(value)
                .flatMap(from::denominated)
                .flatMap(to::nominated);
    }


    @SuppressWarnings("unchecked")
    private Optional<String> format(final String format) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        final TemporalConverter<T> from = (TemporalConverter<T>) Converters.TEMPORAL.getConverter(value);

        return Optional.ofNullable(value)
                .flatMap(from::denominated)
                .map(formatter::format);
    }
}
