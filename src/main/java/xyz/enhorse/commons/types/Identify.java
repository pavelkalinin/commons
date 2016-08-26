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

        String in = string.trim();

        if (isBoolean(string)) {
            return BOOLEAN;
        }

        int length = in.length();
        boolean isFloat = false;
        int i = -1;
        char[] chars = in.toCharArray();

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

            Long number = Long.parseLong(in);
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
