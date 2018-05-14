package xyz.enhorse.commons.convert;


import xyz.enhorse.commons.PackageExplorer;
import xyz.enhorse.commons.convert.datetime.TemporalConverter;

import java.util.Map;
import java.util.Optional;

/**
 * The registry of existing converters grouping by its type
 * @author Pavel Kalinin on 06.04.2018
 */
public enum Converters {
    TEMPORAL(TemporalConverter.class);

    /** The base type of a converter group */
    private final Class<? extends Converter> type;
    /** All found converters as a map of a supported by the converter type to an instance of the converter */
    private final Map<Class<?>, Converter> converters;


    Converters(final Class<? extends Converter> type) {
        this.type = type;
        converters = registerConverters();
    }


    /** Returns the base type of a converter group */
    Class<? extends Converter> type() {
        return type;
    }


    /**
     * Builds the map of a supported type of converter to an instance of the converter.
     * @return the map of a supported type of converter to an instance of the converter
     * @see PackageExplorer
     */
    private Map<Class<?>, Converter> registerConverters() {
        return new PackageExplorer<>(type())
                .findAllInstantiableClasses().stream()
                .collect(new ConverterCollector<>());
    }


    /**
     * Returns the converter for the given object if it exists.
     * @param value the object
     * @param <T>   the type of the object
     * @return the converter for the given objects
     */
    @SuppressWarnings("unchecked")
    public <T> Converter<?, T> getConverter(final T value) {
        return Optional.ofNullable(value)
                .map(v -> (Class<T>) v.getClass())
                .map(this::getConverter)
                .orElseThrow(() -> new IllegalArgumentException("Value must not be null!"));
    }


    /**
     * Returns the converter for the given type if it exists.
     * @param type the class
     * @param <T>  the type of the class
     * @return the converter for the given type
     */
    @SuppressWarnings("unchecked")
    public <T> Converter<?, T> getConverter(final Class<T> type) {
        return Optional.ofNullable(type)
                .flatMap(clazz -> converters.keySet().stream()
                        .filter(converter -> converter.isAssignableFrom(clazz))
                        .findFirst()
                        .map(c -> (Converter<?, T>) converters.get(c)))
                .orElseThrow(() -> new IllegalStateException("Cannot obtain a suitable converter for " + type));
    }
}
