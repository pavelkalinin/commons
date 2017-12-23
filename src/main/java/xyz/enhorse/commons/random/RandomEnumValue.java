package xyz.enhorse.commons.random;

import xyz.enhorse.commons.Validate;

import java.util.concurrent.ThreadLocalRandom;

/**
 * The generator of pseudo random values of {@link Enum}
 * @param <E> the type of values to generate
 * @author <a href="mailto:ext_pkalinin@rencredit.ru">Pavel Kalinin</a>
 * on 23/12/17.
 */
public class RandomEnumValue<E extends Enum<?>> implements RandomGenerator<E> {

    /** The type of values to generate */
    private final Class<E> type;


    /**
     * Creates a new {@code RandomEnumValue}
     * @param type a type of values to generate
     */
    public RandomEnumValue(final Class<E> type) {
        this.type = Validate.notNull("Enum type", type);
    }


    /** ${@inheritDoc} */
    @Override
    public E next() {
        return type.getEnumConstants()[ThreadLocalRandom.current().nextInt(type.getEnumConstants().length)];
    }
}
