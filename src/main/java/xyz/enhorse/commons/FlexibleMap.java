package xyz.enhorse.commons;

import java.util.Map;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         01.09.2016
 */
public interface FlexibleMap<T extends Map<K, V>, K, V> {

    FlexibleMap<T, K, V> put(final K key, final V value);

    FlexibleMap<T, K, V> remove(final K key);

    FlexibleMap<T, K, V> replace(final K key, final V value);

    FlexibleMap<T, K, V> clear();

    V get(final K key);

    boolean isEmpty();

    boolean containsKey(final K key);

    boolean containsValue(final V value);


    Class<T> type();
}
