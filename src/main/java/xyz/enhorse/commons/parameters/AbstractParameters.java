package xyz.enhorse.commons.parameters;

import xyz.enhorse.commons.Pretty;
import xyz.enhorse.commons.StringPair;
import xyz.enhorse.commons.URLString;
import xyz.enhorse.commons.Validate;
import xyz.enhorse.commons.errors.AbsentException;
import xyz.enhorse.commons.errors.DuplicateException;

import java.util.*;
import java.util.function.Consumer;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         23.06.2016.
 */
public abstract class AbstractParameters<T extends Map> implements Parameters {

    private final Map<String, Object> content;


    public AbstractParameters(Class<T> map) {
        content = createInstance(map);
    }


    @Override
    public Parameters append(final List<StringPair> list) {
        if (list != null) {
            for (StringPair pair : list) {
                put(pair.key(), pair.value());
            }
        }

        return this;
    }


    @Override
    public Parameters add(final String parameter, final Object value) {
        if (isExists(parameter)) {
            throw new DuplicateException("Parameter", parameter);
        }

        return put(parameter, value);
    }


    @Override
    public Parameters put(final String parameter, final Object value) {
        content.put(Validate.urlSafe("parameter", parameter), value);
        return this;
    }


    @Override
    public Parameters replace(final String parameter, final Object newValue) {
        if (!isExists(parameter)) {
            throw new AbsentException("Parameter", parameter);
        }
        return put(parameter, newValue);
    }


    @Override
    public Parameters remove(final String parameter) {
        content.remove(parameter);
        return this;
    }


    @Override
    public Parameters clear() {
        content.clear();
        return this;
    }


    @Override
    public Object get(final String parameter) {
        if (!isExists(parameter)) {
            throw new AbsentException("Parameter", parameter);
        }

        return content.get(parameter);
    }


    @Override
    public boolean isExists(final String parameter) {
        return content.containsKey(parameter);
    }


    @Override
    public boolean isEmpty() {
        return content.isEmpty();
    }


    @Override
    public int size() {
        return content.size();
    }


    @Override
    public List<String> list() {
        return new ArrayList<>(content.keySet());
    }


    @Override
    public Map asMap() {
        return new HashMap<>(content);
    }


    @Override
    public String asURLEncodedString() {
        final String parametersDelimiter = "&";
        final char valueDelimiter = '=';
        final char queryStart = '?';

        StringJoiner query = new StringJoiner(parametersDelimiter);

        for (Map.Entry<String, Object> entry : content.entrySet()) {
            query.add(entry.getKey() + valueDelimiter + URLString.encodeUTF(String.valueOf(entry.getValue())));
        }

        return queryStart + query.toString();
    }


    @Override
    public int hashCode() {
        return content.hashCode();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AbstractParameters other = (AbstractParameters) o;

        return content.equals(other.content);
    }


    @Override
    public String toString() {
        return Pretty.format(content);
    }


    @Override
    public Iterator<Map.Entry<String, Object>> iterator() {
        return content.entrySet().iterator();
    }


    @Override
    public void forEach(final Consumer<? super Map.Entry<String, Object>> action) {
        content.entrySet().forEach(action);
    }


    @Override
    public Spliterator<Map.Entry<String, Object>> spliterator() {
        return content.entrySet().spliterator();
    }


    @SuppressWarnings("unchecked")
    private static <T extends Map> Map<String, Object> createInstance(Class<T> map) {
        Validate.notNull("map class", map);

        try {
            return (Map<String, Object>) map.newInstance();
        } catch (Exception ex) {
            throw new IllegalStateException("Couldn't create an instance of " + map.toString(), ex);
        }
    }
}
