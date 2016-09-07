package xyz.enhorse.commons.parameters;

import xyz.enhorse.commons.collections.FlexibleBox;

import java.util.List;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         23.06.2016.
 */
public interface Parameters extends FlexibleBox<ParameterValue<?>> {

    List<Parameter<?>> list();
}
