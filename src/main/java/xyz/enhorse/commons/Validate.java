package xyz.enhorse.commons;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         21.01.2016
 */

@SuppressWarnings("unused")
public class Validate {
    private Validate() {
    }


    public static <T> T notNull(String parameter, T value) {
        if (value == null) {
            throw new IllegalArgumentException("\"" + parameter + "\" is not allowed to be null.");
        }
        return value;
    }


    public static String notNullOrEmpty(String parameter, String value) {
        String content = notNull(parameter, value);
        if (content.trim().isEmpty()) {
            throw new IllegalArgumentException("\"" + parameter + "\" is not allowed to be empty.");
        }
        return content;
    }


    public static String isIdentifier(String parameter, String value) {
        String content = notNullOrEmpty(parameter, value);
        for (int i = 0; i < content.length(); i++) {
            char current = content.charAt(i);
            if (!Character.isJavaIdentifierPart(current)) {
                throw new IllegalArgumentException("\"" + parameter
                        + "\" contains illegal symbol '" + current + '\'');
            }
        }
        return content;
    }


    public static <T> T defaultIfNull(T checkedValue, T defaultValue) {
        if (checkedValue == null) {
            return defaultValue;
        }
        return checkedValue;
    }


    public static String defaultIfNullOrEmpty(String checkedValue, String defaultValue) {
        if ((checkedValue == null) || (checkedValue.trim().isEmpty())) {
            return defaultValue;
        }
        return checkedValue;
    }


    public static void required(String parameter, Object value) {
        if (value == null) {
            throw new IllegalStateException("\"" + parameter + "\" is required, but was not defined.");
        }
    }
}
