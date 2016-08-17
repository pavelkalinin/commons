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


    public static <T> T defaultIfNull(T checkedValue, DefaultsProducer<T> producer) {
        if (checkedValue == null) {
            if (producer != null) {
                return producer.getDefault();
            } else {
                throw new IllegalArgumentException("Can't get a default value " +
                        "because a default values producer isn't defined.");
            }
        }

        return checkedValue;
    }


    public static String defaultIfNullOrEmpty(String checkedValue, String defaultValue) {
        if ((checkedValue == null) || (checkedValue.trim().isEmpty())) {
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
                        + "\" contains illegal symbol \'" + current + '\'');
            }
        }

        return content;
    }


    public static String isUrlSafe(String parameter, String value) {
        String content = notNullOrEmpty(parameter, value);

        for (int i = 0; i < content.length(); i++) {
            char current = content.charAt(i);

            if ((!Character.isLetterOrDigit(current)) && (".-_".indexOf(current) < 0)) {
                throw new IllegalArgumentException("\"" + parameter
                        + "\" contains a not URL safe symbol \'" + current + '\'');
            }
        }

        return content;
    }


    public static <T> T required(String parameter, T value) {
        if (value == null) {
            throw new IllegalStateException("\"" + parameter + "\" is required, but was not defined.");
        }

        return value;
    }


    //isLess
    public static byte isLess(String parameter, byte value, byte boundary) {
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


    public static int isLess(String parameter, int value, int boundary) {
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


    //isGreater
    public static byte isGreater(String parameter, byte value, byte boundary) {
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


    public static int isGreater(String parameter, int value, int boundary) {
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


    //isLessOrEquals
    public static byte isLessOrEquals(String parameter, byte value, byte boundary) {
        if (value > boundary) {
            throw new IllegalArgumentException("\"" + parameter + "\"=" + value + " is greater than " + boundary);
        }

        return value;
    }


    public static short isLessOrEquals(String parameter, short value, short boundary) {
        if (value > boundary) {
            throw new IllegalArgumentException("\"" + parameter + "\"=" + value + " is greater than " + boundary);
        }

        return value;
    }


    public static int isLessOrEquals(String parameter, int value, int boundary) {
        if (value > boundary) {
            throw new IllegalArgumentException("\"" + parameter + "\"=" + value + " is greater than " + boundary);
        }

        return value;
    }


    public static long isLessOrEquals(String parameter, long value, long boundary) {
        if (value > boundary) {
            throw new IllegalArgumentException("\"" + parameter + "\"=" + value + " is greater than " + boundary);
        }

        return value;
    }


    public static double isLessOrEquals(String parameter, float value, float boundary) {
        if (Float.compare(value, boundary) > 0) {
            throw new IllegalArgumentException("\"" + parameter + "\"=" + value + " is not greater than " + boundary);
        }

        return value;
    }


    public static double isLessOrEquals(String parameter, double value, double boundary) {
        if (Double.compare(value, boundary) > 0) {
            throw new IllegalArgumentException("\"" + parameter + "\"=" + value + " is not greater than " + boundary);
        }

        return value;
    }


    //isGreaterOrEquals
    public static byte isGreaterOrEquals(String parameter, byte value, byte boundary) {
        if (value < boundary) {
            throw new IllegalArgumentException("\"" + parameter + "\"=" + value + " is less than " + boundary);
        }

        return value;
    }


    public static short isGreaterOrEquals(String parameter, short value, short boundary) {
        if (value < boundary) {
            throw new IllegalArgumentException("\"" + parameter + "\"=" + value + " is less than " + boundary);
        }

        return value;
    }


    public static int isGreaterOrEquals(String parameter, int value, int boundary) {
        if (value < boundary) {
            throw new IllegalArgumentException("\"" + parameter + "\"=" + value + " is less than " + boundary);
        }

        return value;
    }


    public static long isGreaterOrEquals(String parameter, long value, long boundary) {
        if (value < boundary) {
            throw new IllegalArgumentException("\"" + parameter + "\"=" + value + " is less than " + boundary);
        }

        return value;
    }


    public static double isGreaterOrEquals(String parameter, float value, float boundary) {
        if (Float.compare(value, boundary) < 0) {
            throw new IllegalArgumentException("\"" + parameter + "\"=" + value + " is not greater than " + boundary);
        }

        return value;
    }


    public static double isGreaterOrEquals(String parameter, double value, double boundary) {
        if (Double.compare(value, boundary) < 0) {
            throw new IllegalArgumentException("\"" + parameter + "\"=" + value + " is not greater than " + boundary);
        }

        return value;
    }


    //isBetween
    public static byte isBetween(String parameter, byte value, byte min, byte max) {
        Validate.isLess(parameter, value, max);
        Validate.isGreater(parameter, value, min);

        return value;
    }


    public static short isBetween(String parameter, short value, short min, short max) {
        Validate.isLess(parameter, value, max);
        Validate.isGreater(parameter, value, min);

        return value;
    }


    public static int isBetween(String parameter, int value, int min, int max) {
        Validate.isLess(parameter, value, max);
        Validate.isGreater(parameter, value, min);

        return value;
    }


    public static long isBetween(String parameter, long value, long min, long max) {
        Validate.isLess(parameter, value, max);
        Validate.isGreater(parameter, value, min);

        return value;
    }


    public static double isBetween(String parameter, float value, float min, float max) {
        Validate.isLessOrEquals("minimum", min, max);
        Validate.isLess(parameter, value, max);
        Validate.isGreater(parameter, value, min);

        return value;
    }


    public static double isBetween(String parameter, double value, double min, double max) {
        Validate.isLess(parameter, value, max);
        Validate.isGreater(parameter, value, min);

        return value;
    }


    //isBetweenOrEqual
    public static byte isBetweenOrEquals(String parameter, byte value, byte min, byte max) {
        Validate.isLessOrEquals(parameter, value, max);
        Validate.isGreaterOrEquals(parameter, value, min);

        return value;
    }


    public static short isBetweenOrEquals(String parameter, short value, short min, short max) {
        Validate.isLessOrEquals(parameter, value, max);
        Validate.isGreaterOrEquals(parameter, value, min);

        return value;
    }


    public static int isBetweenOrEquals(String parameter, int value, int min, int max) {
        Validate.isLessOrEquals(parameter, value, max);
        Validate.isGreaterOrEquals(parameter, value, min);

        return value;
    }


    public static long isBetweenOrEquals(String parameter, long value, long min, long max) {
        Validate.isLessOrEquals(parameter, value, max);
        Validate.isGreaterOrEquals(parameter, value, min);

        return value;
    }


    public static double isBetweenOrEquals(String parameter, float value, float min, float max) {
        Validate.isLessOrEquals(parameter, value, max);
        Validate.isGreaterOrEquals(parameter, value, min);

        return value;
    }


    public static double isBetweenOrEquals(String parameter, double value, double min, double max) {
        Validate.isLessOrEquals(parameter, value, max);
        Validate.isGreaterOrEquals(parameter, value, min);

        return value;
    }
}
