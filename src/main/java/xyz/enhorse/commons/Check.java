package xyz.enhorse.commons;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         17/08/16
 */
public class Check {

    private Check() {

    }


    public static boolean isNullOrEmpty(final String string) {
        return (string == null) || (string.trim().isEmpty());
    }


    public static boolean isIdentifier(final String string) {
        if (isNullOrEmpty(string)) {
            return false;
        }

        for (int i = 0; i < string.length(); i++) {
            char current = string.charAt(i);
            if (!Character.isJavaIdentifierPart(current)) {
                return false;
            }
        }

        return true;
    }


    public static boolean isUrlSafe(final String string) {
        if (isNullOrEmpty(string)) {
            return false;
        }

        for (char current : string.toCharArray()) {
            if ((!isLatinLetter(current)) && (!Character.isDigit(current)) && ("-._".indexOf(current) < 0)) {
                return false;
            }
        }

        return true;
    }


    public static boolean isLatinLetter(char c) {
        return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
    }
}
