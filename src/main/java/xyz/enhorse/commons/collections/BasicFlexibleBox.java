package xyz.enhorse.commons.collections;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Talinin</a>
 *         05/09/16
 */
public class BasicFlexibleBox<T> implements FlexibleBox<T> {

    private final FlexibleMap<String, T> content;


    public BasicFlexibleBox(final Map<String, T> content) {
        this.content = new BasicFlexibleMap<>(content);
    }


    @Override
    public FlexibleBox<T> put(final NamedValue<T> element) {
        content.put(element.name(), element.value());

        return this;
    }


    @Override
    public FlexibleBox<T> remove(final String name) {
        content.remove(name);

        return this;
    }


    @Override
    public FlexibleBox<T> replace(final NamedValue<T> element) {
        if (element != null) {
            content.replace(element.name(), element.value());
        }

        return this;
    }


    @Override
    public FlexibleBox<T> merge(final FlexibleBox<? extends T> box) {
        content.merge(box.toMap());

        return this;
    }


    @Override
    public FlexibleBox<T> subtract(final FlexibleBox<T> box) {
        content.subtract(box.toMap());

        return this;
    }


    @Override
    public FlexibleBox<T> intersect(final FlexibleBox<T> box) {
        content.intersect(box.toMap());

        return this;
    }


    @Override
    public FlexibleBox<T> clear() {
        content.clear();

        return this;
    }


    @Override
    public T get(final String name) {
        return content.get(name);
    }


    @Override
    public boolean isEmpty() {
        return content.isEmpty();
    }


    @Override
    public boolean contains(final String name) {
        return content.containsKey(name);
    }


    @Override
    public int size() {
        return content.size();
    }


    @Override
    public Map<String, T> toMap() {
        return new HashMap<>(content.toMap());
    }


    @Override
    public Iterator<String> iterator() {
        return content.keys().iterator();
    }


    @Override
    public void forEach(final Consumer<? super String> action) {
        content.keys().forEach(action);
    }


    @Override
    public Spliterator<String> spliterator() {
        return content.keys().spliterator();
    }


    @Override
    public String toString() {
        return content.toString();
    }
}
