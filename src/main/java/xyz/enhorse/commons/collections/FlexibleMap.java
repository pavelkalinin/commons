package xyz.enhorse.commons.collections;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         01.09.2016
 */
public interface FlexibleMap<K, V> extends Iterable<Map.Entry<K, V>> {

    FlexibleMap<K, V> put(final K key, final V value);

    FlexibleMap<K, V> remove(final K key);

    FlexibleMap<K, V> replace(final K key, final V value);

    FlexibleMap<K, V> merge(final Map<? extends K, ? extends V> map);

    FlexibleMap<K, V> subtract(final Map<K, V> map);

    FlexibleMap<K, V> intersect(final Map<K, V> map);

    FlexibleMap<K, V> clear();

    V get(final K key);

    boolean isEmpty();

    boolean containsKey(final K key);

    boolean containsValue(final V value);

    int size();

    Map<K, V> toMap();

    Set<K> keys();

    Collection<V> values();
}
