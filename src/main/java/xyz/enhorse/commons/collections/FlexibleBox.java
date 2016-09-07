package xyz.enhorse.commons.collections;

import java.util.Map;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Talinin</a>
 *         05/09/16
 */
public interface FlexibleBox<T> extends Iterable<String> {

    FlexibleBox<T> put(final NamedValue<T> element);

    FlexibleBox<T> remove(final String name);

    FlexibleBox<T> replace(final NamedValue<T> element);

    FlexibleBox<T> merge(final FlexibleBox<? extends T> map);

    FlexibleBox<T> subtract(final FlexibleBox<T> map);

    FlexibleBox<T> intersect(final FlexibleBox<T> map);

    FlexibleBox<T> clear();

    T get(final String name);

    boolean isEmpty();

    boolean contains(final String name);

    int size();

    Map<String, T> toMap();
}
