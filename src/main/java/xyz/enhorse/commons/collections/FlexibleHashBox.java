package xyz.enhorse.commons.collections;

import java.util.HashMap;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         05/09/16
 */
public class FlexibleHashBox<T> extends BasicFlexibleBox<T> {

    public FlexibleHashBox() {
        super(new HashMap<>());
    }
}
