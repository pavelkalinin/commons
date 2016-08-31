package xyz.enhorse.commons.parameters;

import java.util.Collection;
import java.util.List;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         23.06.2016.
 */
public interface Parameters {

    Parameters put(final Parameter parameter);

    Parameters remove(final String name);

    Parameters clear();

    Parameter get(final String name);

    boolean isExists(final String name);

    boolean isEmpty();

    Parameters merge(final Collection<Parameter> parameters);

    int size();

    List<Parameter> list();
}
