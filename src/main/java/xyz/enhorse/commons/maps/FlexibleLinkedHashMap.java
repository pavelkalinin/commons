package xyz.enhorse.commons.maps;

import java.util.LinkedHashMap;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         02.09.2016
 */
public class FlexibleLinkedHashMap<K, V> extends AbstractFlexibleMap<K, V> {

    public FlexibleLinkedHashMap() {
        super(new LinkedHashMap<>());
    }
}
