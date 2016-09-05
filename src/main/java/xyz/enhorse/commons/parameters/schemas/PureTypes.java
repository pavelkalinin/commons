package xyz.enhorse.commons.parameters.schemas;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         26.08.2016
 */
public enum PureTypes {
    ;
    public static final PureType<String> NULL = NullType.NULL;
    public static final PureType<String> STRING = StringType.STRING;
    public static final PureType<Character> CHAR = CharType.CHAR;
    public static final PureType<Integer> INTEGER = IntegerType.INTEGER;
    public static final PureType<Long> LONG = LongType.LONG;
    public static final PureType<Double> DOUBLE = DoubleType.DOUBLE;
    public static final PureType<Boolean> BOOLEAN = BooleanType.BOOLEAN;


    public static Object convert(final String string) {
        return (identify(string)).cast(string);
    }


    public static PureType<?> identify(final String string) {
        if (string == null) {
            return NULL;
        }

        if (isChar(string)) {
            return CHAR;
        }

        String input = string.trim();

        if (isBoolean(input)) {
            return BOOLEAN;
        }

        try {
            return isInt(Long.parseLong(input)) ? INTEGER : LONG;
        } catch (NumberFormatException notInteger) {
            try {
                return Double.isFinite(Double.parseDouble(input)) ? DOUBLE : STRING;
            } catch (NumberFormatException notFloat) {
                return STRING;
            }
        }
    }


    private static boolean isBoolean(final String in) {
        return (in.equalsIgnoreCase("true")) || (in.equalsIgnoreCase("false"));
    }


    private static boolean isChar(final String in) {
        return (in.length() == 1) && (!Character.isDigit(in.charAt(0)));
    }


    private static boolean isInt(final long number) {
        return ((number >= 0) && (number <= Integer.MAX_VALUE))
                || ((number < 0) && (number >= Integer.MIN_VALUE));
    }


    public enum NullType implements PureType<String> {
        NULL {
            @Override
            public String cast(final String string) {
                return null;
            }


            @Override
            public Class<String> type() {
                return null;
            }
        }
    }

    public enum BooleanType implements PureType<Boolean> {
        BOOLEAN {
            @Override
            public Boolean cast(final String string) {
                return Boolean.parseBoolean(string.trim());
            }


            @Override
            public Class<Boolean> type() {
                return boolean.class;
            }
        }
    }


    public enum IntegerType implements PureType<Integer> {
        INTEGER {
            @Override
            public Integer cast(final String string) {
                return Integer.parseInt(string);
            }


            @Override
            public Class<Integer> type() {
                return int.class;
            }
        }
    }


    public enum LongType implements PureType<Long> {
        LONG {
            @Override
            public Long cast(final String string) {
                return Long.parseLong(string.trim());
            }


            @Override
            public Class<Long> type() {
                return long.class;
            }
        }
    }


    public enum DoubleType implements PureType<Double> {
        DOUBLE {
            @Override
            public Double cast(final String string) {
                return Double.parseDouble(string.trim());
            }


            @Override
            public Class<Double> type() {
                return double.class;
            }
        }
    }


    public enum CharType implements PureType<Character> {
        CHAR {
            @Override
            public Character cast(final String string) {
                return string.charAt(0);
            }


            @Override
            public Class<Character> type() {
                return Character.class;
            }
        }
    }


    public enum StringType implements PureType<String> {
        STRING {
            @Override
            public String cast(final String string) {
                return string;
            }


            @Override
            public Class<String> type() {
                return String.class;
            }
        }
    }
}
