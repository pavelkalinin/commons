package xyz.enhorse.commons.collections;

import java.util.HashMap;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         02.09.2016
 */
public class FlexibleHashMap<K, V> extends BasicFlexibleMap<K, V> {

    public FlexibleHashMap() {
        super(new HashMap<>());
    }
}
