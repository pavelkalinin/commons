package xyz.enhorse.commons.convert;

import java.lang.reflect.Constructor;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Stream;

import static java.util.stream.Collector.Characteristics.IDENTITY_FINISH;
import static java.util.stream.Collector.Characteristics.UNORDERED;

/**
 * The collector which maps a converter type {@link T} to the pair from a type that it supports and its instance
 * @param <T> the type of a converter
 * @author Pavel Kalinin on 06.04.2018
 */
public class ConverterCollector<T extends Converter>
        implements Collector<Class<T>, Map<Class<?>, Converter>, Map<Class<?>, Converter>> {

    /** {@inheritDoc} */
    @Override
    public Supplier<Map<Class<?>, Converter>> supplier() {
        return HashMap::new;
    }


    /** {@inheritDoc} */
    @Override
    public BiConsumer<Map<Class<?>, Converter>, Class<T>> accumulator() {
        return (map, value) -> {
            final Entry<T> entry = new Entry<>(value);
            final Optional<Class<?>> type = entry.supportedType();
            final Optional<Converter> converter = entry.converter();
            if (type.isPresent() && converter.isPresent()) {
                map.put(type.get(), converter.get());
            }
        };
    }


    /** {@inheritDoc} */
    @Override
    public BinaryOperator<Map<Class<?>, Converter>> combiner() {
        return (left, right) -> {
            left.putAll(right);
            return left;
        };
    }


    /** {@inheritDoc} */
    @Override
    public Function<Map<Class<?>, Converter>, Map<Class<?>, Converter>> finisher() {
        return Function.identity();
    }


    /** {@inheritDoc} */
    @Override
    public Set<Characteristics> characteristics() {
        return EnumSet.of(IDENTITY_FINISH, UNORDERED);
    }


    /** The class to instrument getting information of a converter supported type and constructing its instance */
    private static class Entry<T extends Converter> {

        private final Class<T> type;


        Entry(final Class<T> type) {
            this.type = type;
        }


        Optional<Converter> converter() {
            return getDefaultConstructor()
                    .map(this::createObject);
        }


        Optional<Class<?>> supportedType() {
            return Optional.ofNullable(type)
                    .map(c -> c.getAnnotation(ConverterFor.class))
                    .map(ConverterFor::value);
        }


        private Optional<Constructor<?>> getDefaultConstructor() {
            return Optional.ofNullable(type)
                    .flatMap(type -> Stream.of(type.getDeclaredConstructors())
                            .filter(constructor -> constructor.getGenericParameterTypes().length == 0)
                            .findFirst());
        }


        private Converter createObject(final Constructor<?> constructor) {
            try {
                return (Converter) constructor.newInstance();
            } catch (Exception ex) {
                final String message = String.format(
                        "Cannot create an instance of the converter %s with the default constructor %s",
                        type,
                        constructor);
                throw new IllegalStateException(message);
            }
        }
    }
}
