package xyz.enhorse.commons.converters;

import java.util.Optional;

/**
 * Преобразование значения из одного типа в значения другого типа
 * @param <SOURCE_TYPE>      - тип для преобразования
 * @param <DESTINATION_TYPE> - целевой тип
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 * 01.04.2018
 */
@FunctionalInterface
public interface Converter<SOURCE_TYPE, DESTINATION_TYPE> {

    /**
     * Выполняет преобразование значения из одного типа в значения другого типа
     * @param source - значение для преобразования
     * @return преобразованное значение
     */
    Optional<DESTINATION_TYPE> convert(final SOURCE_TYPE source);
}
