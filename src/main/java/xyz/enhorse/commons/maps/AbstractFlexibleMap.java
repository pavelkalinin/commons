package xyz.enhorse.commons.maps;

import xyz.enhorse.commons.Pretty;
import xyz.enhorse.commons.Validate;

import java.util.Iterator;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         02.09.2016
 */
public abstract class AbstractFlexibleMap<K, V> implements FlexibleMap<K, V> {

    private final Map<K, V> content;


    public AbstractFlexibleMap(Map<K, V> map) {
        content = copy(Validate.notNull("map", map));
    }


    @Override
    public FlexibleMap<K, V> put(final K key, final V value) {
        content.put(key, value);

        return this;
    }


    @Override
    public FlexibleMap<K, V> remove(final K key) {
        content.remove(key);

        return this;
    }


    @Override
    public FlexibleMap<K, V> replace(final K key, final V value) {
        content.replace(key, value);

        return this;
    }


    @Override
    public FlexibleMap<K, V> merge(final FlexibleMap<? extends K, ? extends V> map) {
        if (map != null) {
            for (Map.Entry<? extends K, ? extends V> entry : map) {
                content.put(entry.getKey(), entry.getValue());
            }
        }

        return this;
    }


    @Override
    public FlexibleMap<K, V> subtract(final FlexibleMap<K, V> map) {
        if (map != null) {
            for (Map.Entry<? extends K, ? extends V> entry : map) {
                K key = entry.getKey();
                if (content.containsKey(key)) {
                    content.remove(key);
                }
            }
        }

        return this;
    }


    @Override
    public FlexibleMap<K, V> intersect(final FlexibleMap<K, V> map) {
        if (map != null) {
            for (Iterator<Map.Entry<K, V>> iterator = content.entrySet().iterator(); iterator.hasNext(); ) {
                K key = iterator.next().getKey();
                if (!map.containsKey(key)) {
                    iterator.remove();
                }
            }
        }

        return this;
    }


    @Override
    public FlexibleMap<K, V> clear() {
        content.clear();

        return this;
    }


    @Override
    public V get(final K key) {
        return content.get(key);
    }


    @Override
    public boolean isEmpty() {
        return content.isEmpty();
    }


    @Override
    public boolean containsKey(final K key) {
        return content.containsKey(key);
    }


    @Override
    public boolean containsValue(final V value) {
        return content.containsValue(value);
    }


    @Override
    public int size() {
        return content.size();
    }


    @Override
    public Map<K, V> toMap() {
        return copy(content);
    }


    @Override
    public Iterator<Map.Entry<K, V>> iterator() {
        return content.entrySet().iterator();
    }


    @Override
    public void forEach(final Consumer<? super Map.Entry<K, V>> action) {
        content.entrySet().forEach(action);
    }


    @Override
    public Spliterator<Map.Entry<K, V>> spliterator() {
        return content.entrySet().spliterator();
    }


    @Override
    public int hashCode() {
        return content.hashCode();
    }


    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractFlexibleMap<?, ?> that = (AbstractFlexibleMap<?, ?>) o;

        return content.equals(that.content);

    }


    @Override
    public String toString() {
        return Pretty.format(content);
    }


    @SuppressWarnings("unchecked")
    private Map<K, V> copy(Map<K, V> map) {
        try {
            Map<K, V> copy = (Map<K, V>) (map.getClass().newInstance());
            copy.putAll(map);
            return copy;
        } catch (Exception ex) {
            throw new IllegalStateException("Couldn't create an instance of " + map.toString(), ex);
        }
    }
}
