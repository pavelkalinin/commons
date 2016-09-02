package xyz.enhorse.commons.maps;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         02.09.2016
 */
public class FlexibleConcurrentHashMap<K, V> extends AbstractFlexibleMap<K, V> {

    public FlexibleConcurrentHashMap() {
        super(new ConcurrentHashMap<>());
    }
}
