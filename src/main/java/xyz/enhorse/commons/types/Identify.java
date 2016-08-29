package xyz.enhorse.commons.types;

import static xyz.enhorse.commons.types.PureTypes.*;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         26.08.2016
 */
public enum Identify {
    ;


    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static PureTypes type(final String string) {
        if (string == null) {
            return NULL;
        }

        if (string.length() == 1) {
            if (Character.isDigit(string.charAt(0))) {
                return INTEGER;
            } else {
                return CHAR;
            }
        }

        String input = string.trim();

        if (isBoolean(input)) {
            return BOOLEAN;
        }

        try {
            Long number = Long.parseLong(input);
            if ((number >= 0) && (number <= Integer.MAX_VALUE)) {
                return INTEGER;
            }

            if ((number < 0) && (number >= Integer.MIN_VALUE)) {
                return INTEGER;
            }

            return LONG;
        } catch (NumberFormatException notInteger) {
            try {
                Double.parseDouble(input);
                return DOUBLE;
            } catch (NumberFormatException notFloat) {
                return STRING;
            }
        }
    }


    private static boolean isBoolean(final String in) {
        return (in.equalsIgnoreCase("true")) || (in.equalsIgnoreCase("false"));
    }
}
