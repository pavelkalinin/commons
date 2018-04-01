package xyz.enhorse.commons.converters.temporal;

import xyz.enhorse.commons.converters.Converter;

import java.util.Optional;

public abstract class AbstractTemporalConverter<T, E> implements Converter<T, E> {

    private static final String ERROR = " type converter must not be null";
    private final ZonedConverter<T> sourceConverter;
    private final ZonedConverter<E> destinationConverter;


    AbstractTemporalConverter(final ZonedConverter<T> sourceConverter,
                              final ZonedConverter<E> destinationConverter) {
        this.sourceConverter = Optional.ofNullable(sourceConverter)
                .orElseThrow(() -> new IllegalArgumentException("Source" + ERROR));
        this.destinationConverter = Optional.ofNullable(destinationConverter)
                .orElseThrow(() -> new IllegalArgumentException("Destination" + ERROR));
    }


    @Override
    public Optional<E> convert(final T source) {
        return Optional.ofNullable(source)
                .flatMap(sourceConverter::convert)
                .flatMap(destinationConverter::convert);
    }
}
