package xyz.enhorse.commons.types;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         26.08.2016
 */
public enum PureTypes {
    NULL {
        @Override
        Object cast(final String string) {
            return null;
        }
    },
    BOOLEAN {
        @Override
        Object cast(final String string) {
            return Boolean.parseBoolean(string.trim());
        }
    },
    INTEGER {
        @Override
        Object cast(final String string) {
            return Integer.parseInt(string.trim());
        }
    },
    LONG {
        @Override
        Object cast(final String string) {
            return Long.parseLong(string.trim());
        }
    },
    DOUBLE {
        @Override
        Object cast(final String string) {
            return Double.parseDouble(string.trim());
        }
    },
    CHAR {
        @Override
        Object cast(final String string) {
            return string.charAt(0);
        }
    },
    STRING {
        @Override
        Object cast(final String string) {
            return string;
        }
    };


    abstract Object cast(final String string);


    public static PureValue pureValueOf(final String string) {
        return new PureValue(convert(string));
    }


    public static Object convert(final String string) {
        return Identify.type(string).cast(string);
    }
}
