package xyz.enhorse.commons.collections;

import java.util.TreeMap;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         02.09.2016
 */
public class FlexibleTreeMap<K, V> extends BasicFlexibleMap<K, V> {

    public FlexibleTreeMap() {
        super(new TreeMap<>());
    }
}
