package xyz.enhorse.commons;

import java.util.Map;
import java.util.Properties;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         29/07/16
 */
public class PrettyProperties extends Properties {

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (Map.Entry<Object, Object> entry : super.entrySet()) {
            builder.append(entry.getKey())
                    .append('=');

            Object value = entry.getValue();
            builder.append((value instanceof String) ? String.format("\"%s\"", value) : value)
                    .append("; ");
        }

        builder.setLength(builder.length() - 2);

        return "[" + builder + "]";
    }
}
