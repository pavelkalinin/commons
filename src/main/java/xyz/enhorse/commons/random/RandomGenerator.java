package xyz.enhorse.commons.random;

/**
 * A generator of pseudo random values
 * @param <T> a type of objects that this object will generate
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 * on 23/12/17.
 */
public interface RandomGenerator<T> {


    /**
     * Returns a next pseudo random value
     * @return a pseudorandom {@code T} value
     */
    T next();
}
