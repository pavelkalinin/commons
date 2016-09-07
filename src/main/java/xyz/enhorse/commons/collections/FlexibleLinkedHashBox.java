package xyz.enhorse.commons.collections;

import java.util.LinkedHashMap;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         05/09/16
 */
public class FlexibleLinkedHashBox<T> extends BasicFlexibleBox<T> {

    public FlexibleLinkedHashBox() {
        super(new LinkedHashMap<>());
    }
}