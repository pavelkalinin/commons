package xyz.enhorse.commons.convert;

import java.util.Optional;

/**
 * A converter from the type {@link T} to the type {@link E} and backward
 * @param <T> the type that supports conversion from/to {@link E}
 * @param <E> the type that supports conversion from/to {@link T}
 * @author Pavel Kalinin on 06.04.2018
 */
public interface Converter<T, E> {

    /**
     * Converts an object of {@link T} to an object of {@link E}
     * @param value - the value to convert
     * @return - the converted value
     */
    Optional<E> nominated(final T value);

    /**
     * Converts an object of {@link E} to an object of {@link T}
     * @param value - the value to convert
     * @return - the converted value
     */
    Optional<T> denominated(final E value);
}
