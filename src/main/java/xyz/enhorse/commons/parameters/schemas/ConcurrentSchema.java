package xyz.enhorse.commons.parameters.schemas;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         06.09.2016
 */
public class ConcurrentSchema extends BasicSchema<ConcurrentHashMap> {

    public ConcurrentSchema() {
        super(ConcurrentHashMap.class);
    }
}
