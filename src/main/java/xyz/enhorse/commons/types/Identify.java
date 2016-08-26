package xyz.enhorse.commons.types;

import static xyz.enhorse.commons.types.PureTypes.*;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         26.08.2016
 */
public enum Identify {
    ;


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

        if (isBoolean(string)) {
            return BOOLEAN;
        }


        char[] chars = input.toCharArray();
        int length = chars.length;
        boolean isFloat = false;
        int i = -1;

        while (i++ < length - 1) {
            if (!Character.isDigit(chars[i])) {
                if (isFloatingPoint(chars[i])) {
                    if (isFloat) {
                        break;
                    } else {
                        isFloat = true;
                    }
                } else {
                    break;
                }
            }
        }

        if (i == length) {
            if (isFloat) {
                return DOUBLE;
            }

            Long number = Long.parseLong(input);
            return number <= Integer.MAX_VALUE ? INTEGER : LONG;
        }

        return STRING;
    }


    private static boolean isFloatingPoint(final char c) {
        return (c == '.');
    }


    private static boolean isBoolean(final String in) {
        return (in.equalsIgnoreCase("true")) || (in.equalsIgnoreCase("false"));
    }
}
