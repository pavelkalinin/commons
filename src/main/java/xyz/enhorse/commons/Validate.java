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


    public static <T> T defaultIfNull(T checkedValue, T defaultValue) {
        if (checkedValue == null) {
            return defaultValue;
        }

        return checkedValue;
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


    public static String defaultIfNullOrEmpty(String checkedValue, String defaultValue) {
        if ((checkedValue == null) || (checkedValue.trim().isEmpty())) {
            return defaultValue;
        }

        return checkedValue;
    }


    public static <T> T required(String parameter, T value) {
        if (value == null) {
            throw new IllegalStateException("\"" + parameter + "\" is required, but was not defined.");
        }

        return value;
    }


    public static byte isLess(String parameter, byte value, byte boundary) {
        if (value >= boundary) {
            throw new IllegalArgumentException("\"" + parameter + "\"=" + value + "=\"" + value + " is not less than " + boundary);
        }

        return value;
    }


    public static int isLess(String parameter, int value, int boundary) {
        if (value >= boundary) {
            throw new IllegalArgumentException("\"" + parameter + "\"=" + value + " is not less than " + boundary);
        }

        return value;
    }


    public static short isLess(String parameter, short value, short boundary) {
        if (value >= boundary) {
            throw new IllegalArgumentException("\"" + parameter + "\"=" + value + " is not less than " + boundary);
        }

        return value;
    }


    public static long isLess(String parameter, long value, long boundary) {
        if (value >= boundary) {
            throw new IllegalArgumentException("\"" + parameter + "\"=" + value + " is not less than " + boundary);
        }

        return value;
    }


    public static float isLess(String parameter, float value, float boundary) {
        if (Float.compare(value, boundary) >= 0) {
            throw new IllegalArgumentException("\"" + parameter + "\"=" + value + " is not less than " + boundary);
        }

        return value;
    }


    public static double isLess(String parameter, double value, double boundary) {
        if (Double.compare(value, boundary) >= 0) {
            throw new IllegalArgumentException("\"" + parameter + "\"=" + value + " is not less than " + boundary);
        }

        return value;
    }


    public static byte isGreater(String parameter, byte value, byte boundary) {
        if (value <= boundary) {
            throw new IllegalArgumentException("\"" + parameter + "\"=" + value + " is not greater than " + boundary);
        }

        return value;
    }


    public static int isGreater(String parameter, int value, int boundary) {
        if (value <= boundary) {
            throw new IllegalArgumentException("\"" + parameter + "\"=" + value + " is not greater than " + boundary);
        }

        return value;
    }


    public static short isGreater(String parameter, short value, short boundary) {
        if (value <= boundary) {
            throw new IllegalArgumentException("\"" + parameter + "\"=" + value + " is not greater than " + boundary);
        }

        return value;
    }


    public static long isGreater(String parameter, long value, long boundary) {
        if (value <= boundary) {
            throw new IllegalArgumentException("\"" + parameter + "\"=" + value + " is not greater than " + boundary);
        }

        return value;
    }


    public static double isGreater(String parameter, float value, float boundary) {
        if (Float.compare(value, boundary) <= 0) {
            throw new IllegalArgumentException("\"" + parameter + "\"=" + value + " is not greater than " + boundary);
        }

        return value;
    }


    public static double isGreater(String parameter, double value, double boundary) {
        if (Double.compare(value, boundary) <= 0) {
            throw new IllegalArgumentException("\"" + parameter + "\"=" + value + " is not greater than " + boundary);
        }

        return value;
    }


    public static byte isLessOrEqual(String parameter, byte value, byte boundary) {
        if (value > boundary) {
            throw new IllegalArgumentException("\"" + parameter + "\"=" + value + " is greater than " + boundary);
        }

        return value;
    }


    public static int isLessOrEqual(String parameter, int value, int boundary) {
        if (value > boundary) {
            throw new IllegalArgumentException("\"" + parameter + "\"=" + value + " is greater than " + boundary);
        }

        return value;
    }


    public static short isLessOrEqual(String parameter, short value, short boundary) {
        if (value > boundary) {
            throw new IllegalArgumentException("\"" + parameter + "\"=" + value + " is greater than " + boundary);
        }

        return value;
    }


    public static long isLessOrEqual(String parameter, long value, long boundary) {
        if (value > boundary) {
            throw new IllegalArgumentException("\"" + parameter + "\"=" + value + " is greater than " + boundary);
        }

        return value;
    }


    public static double isLessOrEqual(String parameter, float value, float boundary) {
        if (Float.compare(value, boundary) > 0) {
            throw new IllegalArgumentException("\"" + parameter + "\"=" + value + " is not greater than " + boundary);
        }

        return value;
    }


    public static double isLessOrEqual(String parameter, double value, double boundary) {
        if (Double.compare(value, boundary) > 0) {
            throw new IllegalArgumentException("\"" + parameter + "\"=" + value + " is not greater than " + boundary);
        }

        return value;
    }


    public static byte isGreaterOrEqual(String parameter, byte value, byte boundary) {
        if (value < boundary) {
            throw new IllegalArgumentException("\"" + parameter + "\"=" + value + " is less than " + boundary);
        }

        return value;
    }


    public static int isGreaterOrEqual(String parameter, int value, int boundary) {
        if (value < boundary) {
            throw new IllegalArgumentException("\"" + parameter + "\"=" + value + " is less than " + boundary);
        }

        return value;
    }


    public static short isGreaterOrEqual(String parameter, short value, short boundary) {
        if (value < boundary) {
            throw new IllegalArgumentException("\"" + parameter + "\"=" + value + " is less than " + boundary);
        }

        return value;
    }


    public static long isGreaterOrEqual(String parameter, long value, long boundary) {
        if (value < boundary) {
            throw new IllegalArgumentException("\"" + parameter + "\"=" + value + " is less than " + boundary);
        }

        return value;
    }


    public static double isGreaterOrEqual(String parameter, float value, float boundary) {
        if (Float.compare(value, boundary) < 0) {
            throw new IllegalArgumentException("\"" + parameter + "\"=" + value + " is not greater than " + boundary);
        }

        return value;
    }


    public static double isGreaterOrEqual(String parameter, double value, double boundary) {
        if (Double.compare(value, boundary) < 0) {
            throw new IllegalArgumentException("\"" + parameter + "\"=" + value + " is not greater than " + boundary);
        }

        return value;
    }
}
