package xyz.enhorse.commons.parameters;

import xyz.enhorse.commons.Validate;
import xyz.enhorse.commons.errors.AbsentException;
import xyz.enhorse.commons.errors.DuplicateException;

import java.util.*;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         23.06.2016.
 */
public abstract class AbstractParameters<T extends Map> implements Parameters {

    private final Map<String, Object> content;


    public AbstractParameters(Class<T> map) {
        content = createInstance(map);
    }


    public AbstractParameters(Class<T> map, String parameters) {
        this(map);
        parseParameters(parameters);
    }


    public AbstractParameters(Class<T> map, Parameters parameters) {
        this(map);
        copyParameters(parameters);
    }


    @Override
    public Parameters add(final String parameter, final Object value) {
        if (isExists(parameter)) {
            throw new DuplicateException("Parameter", parameter);
        }

        content.put(parameter, value);
        return this;
    }


    @Override
    public Parameters put(final String parameter, final Object value) {
        content.put(Validate.isIdentifier("parameter", parameter), value);
        return this;
    }


    @Override
    public Parameters replace(final String parameter, final Object newValue) {
        return delete(parameter).add(parameter, newValue);
    }


    @Override
    public Parameters remove(final String parameter) {
        content.remove(Validate.isIdentifier("parameter", parameter));
        return this;
    }


    @Override
    public Parameters delete(final String parameter) {
        if (!isExists(parameter)) {
            throw new AbsentException("Parameter", parameter);
        }

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
        return content.containsKey(Validate.isIdentifier("parameter", parameter));
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
    public Map toMap() {
        return new HashMap<>(content);
    }


    @Override
    public Iterator<String> iterator() {
        return content.keySet().iterator();
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
        if (isEmpty()) {
            return "";
        }

        StringBuilder options = new StringBuilder();
        for (Map.Entry<String, Object> entry : content.entrySet()) {
            options.append(entry.getKey()).append('=').append(entry.getValue()).append('&');
        }
        options.setLength(options.length() - 1);

        return options.toString();
    }


    private void copyParameters(final Parameters parameters) {
        if (parameters != null) {
            for (String parameter : parameters) {
                add(parameter, parameters.get(parameter));
            }
        }
    }


    private void parseParameters(final String parameters) {
        if ((parameters != null) && (!parameters.trim().isEmpty())) {
            for (String parameter : parameters.split(String.valueOf(PARAMETERS_SEPARATOR))) {
                String name = extractParameterName(parameter);
                if (!name.trim().isEmpty()) {
                    put(extractParameterName(parameter), extractParameterValue(parameter));
                }
            }
        }
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


    private static String extractParameterName(final String parameter) {
        String temp = Validate.defaultIfNull(parameter, "");

        int delimiter = temp.indexOf(PARAMETER_VALUE_SEPARATOR);
        return delimiter > -1 ? temp.substring(0, delimiter) : temp;
    }


    private static String extractParameterValue(final String parameter) {
        String temp = Validate.defaultIfNull(parameter, "");

        int delimiter = temp.indexOf(PARAMETER_VALUE_SEPARATOR);
        return delimiter > -1 ? temp.substring(delimiter + 1) : "";
    }
}
