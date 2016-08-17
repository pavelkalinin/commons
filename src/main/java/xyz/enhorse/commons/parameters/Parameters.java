package xyz.enhorse.commons.parameters;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         23.06.2016.
 */
public interface Parameters extends Iterable<String> {

    char PARAMETERS_SEPARATOR = '&';
    char PARAMETER_VALUE_SEPARATOR = '=';
    String COMMENT_MARK = "//";


    Parameters load(final File file);


    Parameters append(final File file);


    Parameters add(final String parameter, final Object value);


    Parameters put(final String parameter, final Object value);


    Parameters replace(final String parameter, final Object newValue);


    Parameters remove(final String parameter);


    Parameters delete(final String parameter);


    Parameters clear();


    Object get(final String parameter);


    boolean isExists(final String parameter);


    boolean isEmpty();


    int size();


    List<String> list();


    String toString();


    Map toMap();
}
