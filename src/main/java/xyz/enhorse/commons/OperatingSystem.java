package xyz.enhorse.commons;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         21.07.2016
 */
public class OperatingSystem {

    private static final String OS_NAME = removeSpaces(System.getProperty("os.name").toLowerCase());


    public static boolean isWindows() {
        return matches("Windows");
    }


    public static boolean isOSX() {
        return matches("OS X");
    }


    public static boolean isLinux() {
        return matches("Linux");
    }


    public static boolean isFreeBSD() {
        return matches("FreeDSD");
    }


    public static boolean isOpenBSD() {
        return matches("OpenBSD");
    }


    public static boolean isNetBSD() {
        return matches("NetBSD");
    }


    public static boolean isUnix() {
        return !isWindows();
    }


    private static boolean matches(final String string) {
        return OS_NAME.contains(removeSpaces(string.toLowerCase()));
    }


    public static String removeSpaces(final String string) {
        final StringBuilder builder = new StringBuilder();

        for (char c : string.toCharArray()) {
            if (c != ' ') {
                builder.append(c);
            }
        }

        return builder.toString();
    }
}
