package xyz.enhorse.commons.parameters.schemas;

import xyz.enhorse.commons.collections.FlexibleBox;
import xyz.enhorse.commons.parameters.Parameters;

import java.util.Map;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         06.09.2016
 */
public interface Schema extends FlexibleBox<Description<?>> {

    Parameters process(Map<String, String> map);
}
