package xyz.enhorse.commons.parameters;

import java.util.List;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         23.06.2016.
 */
public interface Parameters extends Iterable<String> {

    char PARAMETERS_SEPARATOR = '&';
    char PARAMETER_VALUE_SEPARATOR = '=';

    Parameters add(final String parameter, final Object value);


    Parameters put(final String parameter, final Object value);


    Parameters replace(final String parameter, final Object newValue);


    Parameters delete(final String parameter);


    Object get(final String parameter);


    boolean isExists(final String parameter);


    boolean isEmpty();


    int size();


    List<String> list();
}
