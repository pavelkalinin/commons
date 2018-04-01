package xyz.enhorse.commons.converters.temporal;

import java.time.ZonedDateTime;
import java.util.Optional;

/**
 * Преобразование значений между типом {@link T} и {@link ZonedDateTime}
 * @param <T> тип для преобразования
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 * 01.04.2018
 */
public interface ZonedConverter<T> {

    /**
     * Преобразовать значение типа {@link ZonedDateTime} в значение типа {@link T}
     * @param temporal - значение для преобразования
     * @return - преобразованное значение
     */
    Optional<T> convert(final ZonedDateTime temporal);

    /**
     * Преобразовать значение типа {@link T} в значение типа {@link ZonedDateTime}
     * @param temporal - значение для преобразования
     * @return - преобразованное значение
     */
    Optional<ZonedDateTime> convert(final T temporal);
}
