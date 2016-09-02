package xyz.enhorse.commons.maps;

import java.util.Map;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         01.09.2016
 */
public interface FlexibleMap<K, V> extends Iterable<Map.Entry<K, V>> {

    FlexibleMap<K, V> put(final K key, final V value);

    FlexibleMap<K, V> remove(final K key);

    FlexibleMap<K, V> replace(final K key, final V value);

    FlexibleMap<K, V> merge(final FlexibleMap<? extends K, ? extends V> map);

    FlexibleMap<K, V> subtract(final FlexibleMap<K, V> map);

    FlexibleMap<K, V> intersect(final FlexibleMap<K, V> map);

    FlexibleMap<K, V> clear();

    V get(final K key);

    boolean isEmpty();

    boolean containsKey(final K key);

    boolean containsValue(final V value);

    int size();

    Map<K, V> toMap();
}
