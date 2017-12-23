package xyz.enhorse.commons;

import java.io.Serializable;
import java.util.Objects;

/**
 * An interval From-To
 * @param <T> a type of the interval bounds
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 * on 23/12/17.
 */
public final class Interval<T extends Serializable & Comparable<T>> implements Serializable {

    /** Serialization version */
    private static final long serialVersionUID = 42L;

    /** A pair of bounds */
    private final Pair<T> bounds;


    /**
     * Creates a new {@code Interval} with the given bounds.
     * @param from a bound
     * @param to   another bound
     */
    public Interval(final T from, final T to) {
        bounds = (isOrdered(from, to))
                ? new Pair<>(from, to)
                : new Pair<>(to, from);
    }


    /**
     * Returns a minimal bound
     * @return a minimal bound
     */
    public T from() {
        return bounds.a;
    }


    /**
     * Returns a maximum bound
     * @return a maximum bound
     */
    public T to() {
        return bounds.b;
    }


    /**
     * Checks whether the instance's bounds are the same
     * @return {@code true} if the instance's bounds are the same
     * and {@code false} otherwise
     */
    public boolean isDegenerated() {
        return Objects.equals(bounds.a, bounds.b);
    }


    /**
     * Checks whether the given bounds are ordered by increasing their values
     * @param from a first bound
     * @param to   a second bound
     * @return {@code true} if the given bounds are ordered
     * and {@code false} otherwise
     */
    private boolean isOrdered(final T from, final T to) {
        return to != null && to.compareTo(from) > 0;
    }


    /** ${@inheritDoc} */
    @Override
    public int hashCode() {
        return Objects.hash(bounds);
    }


    /** ${@inheritDoc} */
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Interval<?> interval = (Interval<?>) o;

        return Objects.equals(bounds, interval.bounds);
    }


    /** ${@inheritDoc} */
    @Override
    public String toString() {
        return bounds.toString();
    }
}
