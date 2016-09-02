package xyz.enhorse.commons.maps;

import java.util.HashMap;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         02.09.2016
 */
public class FlexibleHashMap<K, V> extends AbstractFlexibleMap<K, V> {

    public FlexibleHashMap() {
        super(new HashMap<>());
    }
}
