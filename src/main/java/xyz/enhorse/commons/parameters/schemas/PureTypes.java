package xyz.enhorse.commons.parameters.schemas;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         26.08.2016
 */
public enum PureTypes implements PureType {
    NULL {
        @Override
        public String cast(final String string) {
            return null;
        }


        @Override
        public Class<?> type() {
            return null;
        }
    },
    BOOLEAN {
        @Override
        public Boolean cast(final String string) {
            return Boolean.parseBoolean(string.trim());
        }


        @Override
        public Class<?> type() {
            return Boolean.class;
        }
    },
    INTEGER {
        @Override
        public Integer cast(final String string) {
            return Integer.parseInt(string);
        }


        @Override
        public Class<?> type() {
            return Integer.class;
        }
    },
    LONG {
        @Override
        public Long cast(final String string) {
            return Long.parseLong(string.trim());
        }


        @Override
        public Class<?> type() {
            return Long.class;
        }
    },
    DOUBLE {
        @Override
        public Double cast(final String string) {
            return Double.parseDouble(string.trim());
        }


        @Override
        public Class<?> type() {
            return Double.class;
        }
    },
    CHAR {
        @Override
        public Character cast(final String string) {
            return string.charAt(0);
        }


        @Override
        public Class<?> type() {
            return Character.class;
        }
    },
    STRING {
        @Override
        public String cast(final String string) {
            return string;
        }


        @Override
        public Class<?> type() {
            return String.class;
        }
    };


    public static Object convert(final String string) {
        return (identify(string)).cast(string);
    }


    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static PureTypes identify(final String string) {
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
                Double.parseDouble(input);
                return DOUBLE;
            } catch (NumberFormatException notFloat) {
                return STRING;
            }
        }
    }


    public static PureTypes identify(final Object object) {
        return identify(String.valueOf(object));
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
}
