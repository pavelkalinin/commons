package xyz.enhorse.commons.collections;

import java.util.TreeMap;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         05/09/16
 */
public class FlexibleTreeBox<T> extends BasicFlexibleBox<T> {

    public FlexibleTreeBox() {
        super(new TreeMap<>());
    }
}
