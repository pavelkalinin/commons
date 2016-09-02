package xyz.enhorse.commons.maps;

import java.util.TreeMap;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         02.09.2016
 */
public class FlexibleTreeMap<K, V> extends AbstractFlexibleMap<K, V> {

    public FlexibleTreeMap() {
        super(new TreeMap<>());
    }
}
