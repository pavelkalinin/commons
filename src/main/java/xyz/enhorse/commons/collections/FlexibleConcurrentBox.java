package xyz.enhorse.commons.collections;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         05/09/16
 */
public class FlexibleConcurrentBox<T> extends BasicFlexibleBox<T> {

    public FlexibleConcurrentBox() {
        super(new ConcurrentHashMap<>());
    }
}
