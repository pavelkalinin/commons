package xyz.enhorse.commons.collections;

import java.util.LinkedHashMap;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         02.09.2016
 */
public class FlexibleLinkedHashMap<K, V> extends BasicFlexibleMap<K, V> {

    public FlexibleLinkedHashMap() {
        super(new LinkedHashMap<>());
    }
}
