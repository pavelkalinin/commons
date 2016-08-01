package xyz.enhorse.commons;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         29/07/16
 */
public class Pretty {

    public static final String KEY_VALUE_DELIMITER = "=";
    public static final String DELIMITER = "; ";
    public static final String GROUP_START = "[";
    public static final String GROUP_END = "]";


    private Pretty(){

    }


    public static String format(final Map<Object, Object> map) {
        StringBuilder builder = new StringBuilder();

        for (Map.Entry<Object, Object> entry : Validate.defaultIfNull(map, new HashMap<>()).entrySet()) {
            builder.append(entry.getKey())
                    .append(KEY_VALUE_DELIMITER);

            Object value = entry.getValue();
            builder.append(format(value))
                    .append(DELIMITER);
        }

        return "" + GROUP_START + trimTailDelimiter(builder) + GROUP_END;
    }


    public static String format(final Object object) {
        return (object instanceof String) ? String.format("\"%s\"", object) : String.valueOf(object);
    }


    private static StringBuilder trimTailDelimiter(StringBuilder builder) {
        int length = Validate.defaultIfNull(builder, new StringBuilder()).length();

        if (length >= DELIMITER.length()) {
            builder.setLength(length - DELIMITER.length());
        }

        return builder;
    }
}
