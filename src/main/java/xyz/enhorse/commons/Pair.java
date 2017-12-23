package xyz.enhorse.commons;

import java.io.Serializable;
import java.util.Objects;

/**
 * A pair of values
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 * on 23/12/17.
 */
public final class Pair<T extends Serializable> implements Serializable {

    /** Serialization version */
    private static final long serialVersionUID = 42L;

    /** The first value */
    public final T a;

    /** The second value */
    public final T b;


    /**
     * Creates a new {@code Pair} of the two linked values
     * @param a a first value
     * @param b a second value
     */
    public Pair(final T a, final T b) {
        this.a = a;
        this.b = b;
    }


    /**
     * Swaps linked values and returns a new {@code Pair}
     * @return a {@code Pair} with swapped values
     */
    public Pair<T> swap() {
        return new Pair<>(b, a);
    }


    /** ${@inheritDoc} */
    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }


    /** ${@inheritDoc} */
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pair<?> pair = (Pair<?>) o;

        return Objects.equals(a, pair.a) && Objects.equals(b, pair.b);
    }


    /** ${@inheritDoc} */
    @Override
    public String toString() {
        return "{" + a + ',' + b + '}';
    }
}
