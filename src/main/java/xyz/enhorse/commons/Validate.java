package xyz.enhorse.commons;

/**
 * Utility class with methods to validate, set default values and etc.
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 * 21.01.2016
 */

@SuppressWarnings({"unused", "ResultOfMethodCallIgnored"})
public class Validate {


    private static final String IS_NOT_LESS_THAN = " is not less than ";
    private static final String IS_NOT_GREATER_THAN = " is not greater than ";
    private static final String IS_GREATER_THAN = " is greater than ";
    private static final String IS_LESS_THAN = " is less than ";


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
        if ((checkedValue == null) || (checkedValue.isEmpty())) {
            return defaultValue;
        }

        return checkedValue;
    }


    public static String notNullOrEmpty(String parameter, String value) {
        String content = notNull(parameter, value);

        if (content.isEmpty()) {
            throw new IllegalArgumentException("\"" + parameter + "\" is not allowed to be empty.");
        }

        return content;
    }


    public static String identifier(String parameter, String value) {
        String content = notNullOrEmpty(parameter, value);

        for (char current : value.toCharArray()) {
            if (!Character.isJavaIdentifierPart(current)) {
                throw new IllegalArgumentException("\"" + parameter
                        + "\" contains illegal symbol \'" + current + '\'');
            }
        }

        return content;
    }


    public static String urlSafe(String parameter, String value) {
        String content = notNullOrEmpty(parameter, value);

        for (char current : value.toCharArray()) {
            if ((!((current >= 'A' && current <= 'Z') || (current >= 'a' && current <= 'z')))
                    && (!Character.isDigit(current))
                    && ("-._".indexOf(current) < 0)) {
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


    //less
    public static byte less(String parameter, byte value, byte boundary) {
        if (value >= boundary) {
            throw new IllegalArgumentException("\"" + parameter + "\"=" + value + IS_NOT_LESS_THAN + boundary);
        }

        return value;
    }


    public static short less(String parameter, short value, short boundary) {
        if (value >= boundary) {
            throw new IllegalArgumentException("\"" + parameter + "\"=" + value + IS_NOT_LESS_THAN + boundary);
        }

        return value;
    }


    public static int less(String parameter, int value, int boundary) {
        if (value >= boundary) {
            throw new IllegalArgumentException("\"" + parameter + "\"=" + value + IS_NOT_LESS_THAN + boundary);
        }

        return value;
    }


    public static long less(String parameter, long value, long boundary) {
        if (value >= boundary) {
            throw new IllegalArgumentException("\"" + parameter + "\"=" + value + IS_NOT_LESS_THAN + boundary);
        }

        return value;
    }


    public static float less(String parameter, float value, float boundary) {
        if (Float.compare(value, boundary) >= 0) {
            throw new IllegalArgumentException("\"" + parameter + "\"=" + value + IS_NOT_LESS_THAN + boundary);
        }

        return value;
    }


    public static double less(String parameter, double value, double boundary) {
        if (Double.compare(value, boundary) >= 0) {
            throw new IllegalArgumentException("\"" + parameter + "\"=" + value + IS_NOT_LESS_THAN + boundary);
        }

        return value;
    }


    //greater
    public static byte greater(String parameter, byte value, byte boundary) {
        if (value <= boundary) {
            throw new IllegalArgumentException("\"" + parameter + "\"=" + value + IS_NOT_GREATER_THAN + boundary);
        }

        return value;
    }


    public static short greater(String parameter, short value, short boundary) {
        if (value <= boundary) {
            throw new IllegalArgumentException("\"" + parameter + "\"=" + value + IS_NOT_GREATER_THAN + boundary);
        }

        return value;
    }


    public static int greater(String parameter, int value, int boundary) {
        if (value <= boundary) {
            throw new IllegalArgumentException("\"" + parameter + "\"=" + value + IS_NOT_GREATER_THAN + boundary);
        }

        return value;
    }


    public static long greater(String parameter, long value, long boundary) {
        if (value <= boundary) {
            throw new IllegalArgumentException("\"" + parameter + "\"=" + value + IS_NOT_GREATER_THAN + boundary);
        }

        return value;
    }


    public static double greater(String parameter, float value, float boundary) {
        if (Float.compare(value, boundary) <= 0) {
            throw new IllegalArgumentException("\"" + parameter + "\"=" + value + IS_NOT_GREATER_THAN + boundary);
        }

        return value;
    }


    public static double greater(String parameter, double value, double boundary) {
        if (Double.compare(value, boundary) <= 0) {
            throw new IllegalArgumentException("\"" + parameter + "\"=" + value + IS_NOT_GREATER_THAN + boundary);
        }

        return value;
    }


    //lessOrEquals
    public static byte lessOrEquals(String parameter, byte value, byte boundary) {
        if (value > boundary) {
            throw new IllegalArgumentException("\"" + parameter + "\"=" + value + IS_GREATER_THAN + boundary);
        }

        return value;
    }


    public static short lessOrEquals(String parameter, short value, short boundary) {
        if (value > boundary) {
            throw new IllegalArgumentException("\"" + parameter + "\"=" + value + IS_GREATER_THAN + boundary);
        }

        return value;
    }


    public static int lessOrEquals(String parameter, int value, int boundary) {
        if (value > boundary) {
            throw new IllegalArgumentException("\"" + parameter + "\"=" + value + IS_GREATER_THAN + boundary);
        }

        return value;
    }


    public static long lessOrEquals(String parameter, long value, long boundary) {
        if (value > boundary) {
            throw new IllegalArgumentException("\"" + parameter + "\"=" + value + IS_GREATER_THAN + boundary);
        }

        return value;
    }


    public static double lessOrEquals(String parameter, float value, float boundary) {
        if (Float.compare(value, boundary) > 0) {
            throw new IllegalArgumentException("\"" + parameter + "\"=" + value + IS_NOT_GREATER_THAN + boundary);
        }

        return value;
    }


    public static double lessOrEquals(String parameter, double value, double boundary) {
        if (Double.compare(value, boundary) > 0) {
            throw new IllegalArgumentException("\"" + parameter + "\"=" + value + IS_NOT_GREATER_THAN + boundary);
        }

        return value;
    }


    //greaterOrEquals
    public static byte greaterOrEquals(String parameter, byte value, byte boundary) {
        if (value < boundary) {
            throw new IllegalArgumentException("\"" + parameter + "\"=" + value + IS_LESS_THAN + boundary);
        }

        return value;
    }


    public static short greaterOrEquals(String parameter, short value, short boundary) {
        if (value < boundary) {
            throw new IllegalArgumentException("\"" + parameter + "\"=" + value + IS_LESS_THAN + boundary);
        }

        return value;
    }


    public static int greaterOrEquals(String parameter, int value, int boundary) {
        if (value < boundary) {
            throw new IllegalArgumentException("\"" + parameter + "\"=" + value + IS_LESS_THAN + boundary);
        }

        return value;
    }


    public static long greaterOrEquals(String parameter, long value, long boundary) {
        if (value < boundary) {
            throw new IllegalArgumentException("\"" + parameter + "\"=" + value + IS_LESS_THAN + boundary);
        }

        return value;
    }


    public static double greaterOrEquals(String parameter, float value, float boundary) {
        if (Float.compare(value, boundary) < 0) {
            throw new IllegalArgumentException("\"" + parameter + "\"=" + value + IS_NOT_GREATER_THAN + boundary);
        }

        return value;
    }


    public static double greaterOrEquals(String parameter, double value, double boundary) {
        if (Double.compare(value, boundary) < 0) {
            throw new IllegalArgumentException("\"" + parameter + "\"=" + value + IS_NOT_GREATER_THAN + boundary);
        }

        return value;
    }


    //inRangeExclusive
    public static byte inRangeExclusive(String parameter, byte value, byte min, byte max) {
        Validate.less(parameter, value, max);
        Validate.greater(parameter, value, min);

        return value;
    }


    public static short inRangeExclusive(String parameter, short value, short min, short max) {
        Validate.less(parameter, value, max);
        Validate.greater(parameter, value, min);

        return value;
    }


    public static int inRangeExclusive(String parameter, int value, int min, int max) {
        Validate.less(parameter, value, max);
        Validate.greater(parameter, value, min);

        return value;
    }


    public static long inRangeExclusive(String parameter, long value, long min, long max) {
        Validate.less(parameter, value, max);
        Validate.greater(parameter, value, min);

        return value;
    }


    public static double inRangeExclusive(String parameter, float value, float min, float max) {
        Validate.lessOrEquals("minimum", min, max);
        Validate.less(parameter, value, max);
        Validate.greater(parameter, value, min);

        return value;
    }


    public static double inRangeExclusive(String parameter, double value, double min, double max) {
        Validate.less(parameter, value, max);
        Validate.greater(parameter, value, min);

        return value;
    }


    //inRangeInclusive
    public static byte inRangeInclusive(String parameter, byte value, byte min, byte max) {
        Validate.lessOrEquals(parameter, value, max);
        Validate.greaterOrEquals(parameter, value, min);

        return value;
    }


    public static short inRangeInclusive(String parameter, short value, short min, short max) {
        Validate.lessOrEquals(parameter, value, max);
        Validate.greaterOrEquals(parameter, value, min);

        return value;
    }


    public static int inRangeInclusive(String parameter, int value, int min, int max) {
        Validate.lessOrEquals(parameter, value, max);
        Validate.greaterOrEquals(parameter, value, min);

        return value;
    }


    public static long inRangeInclusive(String parameter, long value, long min, long max) {
        Validate.lessOrEquals(parameter, value, max);
        Validate.greaterOrEquals(parameter, value, min);

        return value;
    }


    public static double inRangeInclusive(String parameter, float value, float min, float max) {
        Validate.lessOrEquals(parameter, value, max);
        Validate.greaterOrEquals(parameter, value, min);

        return value;
    }


    public static double inRangeInclusive(String parameter, double value, double min, double max) {
        Validate.lessOrEquals(parameter, value, max);
        Validate.greaterOrEquals(parameter, value, min);

        return value;
    }


    //minimumIfLess
    public static byte minimumIfLess(byte value, byte boundary) {
        return (value > boundary) ? value : boundary;
    }


    public static short minimumIfLess(short value, short boundary) {
        return (value > boundary) ? value : boundary;
    }


    public static int minimumIfLess(int value, int boundary) {
        return (value > boundary) ? value : boundary;
    }


    public static long minimumIfLess(long value, long boundary) {
        return (value > boundary) ? value : boundary;
    }


    public static float minimumIfLess(float value, float boundary) {
        return (Float.compare(value, boundary) > 0) ? value : boundary;
    }


    public static double minimumIfLess(double value, double boundary) {
        return (Double.compare(value, boundary) > 0) ? value : boundary;
    }


    public static <T extends Comparable<T>> T minimumIfLess(T value, T boundary) {
        if (value == null) {
            return boundary;
        }

        if (boundary == null) {
            return value;
        }

        return (value.compareTo(boundary) > 0) ? value : boundary;
    }


    //maximumIfGreater
    public static byte maximumIfGreater(byte value, byte boundary) {
        return (value < boundary) ? value : boundary;
    }


    public static short maximumIfGreater(short value, short boundary) {
        return (value < boundary) ? value : boundary;
    }


    public static int maximumIfGreater(int value, int boundary) {
        return (value < boundary) ? value : boundary;
    }


    public static long maximumIfGreater(long value, long boundary) {
        return (value < boundary) ? value : boundary;
    }


    public static float maximumIfGreater(float value, float boundary) {
        return (Float.compare(value, boundary) < 0) ? value : boundary;
    }


    public static double maximumIfGreater(double value, double boundary) {
        return (Double.compare(value, boundary) < 0) ? value : boundary;
    }


    public static <T extends Comparable<T>> T maximumIfGreater(T value, T boundary) {
        if (value == null) {
            return boundary;
        }

        if (boundary == null) {
            return value;
        }

        return (value.compareTo(boundary) < 0) ? value : boundary;
    }
}
