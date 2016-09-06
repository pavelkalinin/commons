package xyz.enhorse.commons.collections;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         02.09.2016
 */
public class FlexibleConcurrentHashMap<K, V> extends BasicFlexibleMap<K, V> {

    public FlexibleConcurrentHashMap() {
        super(new ConcurrentHashMap<>());
    }
}
