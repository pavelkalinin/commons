package xyz.enhorse.commons;

import java.util.regex.Pattern;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         08.08.2016
 */
public final class Email {

    private static final Pattern PATTERN =
            Pattern.compile("^[-a-z0-9!#$%&'*+/=?^_`{|}~]+(?:\\.[-a-z0-9!#$%&'*+/=?^_`{|}~]+)*@(?:[a-z0-9]([-a-z0-9]{0,61}[a-z0-9])?\\.)*(?:[a-z0-9]{2,10})$");

    private final String address;


    private Email(final String email) {
        address = email;
    }


    public String address() {
        return Validate.defaultIfNull(address, "");
    }


    public String postbox() {
        int posOfAt = address().indexOf('@');
        return address().substring(0, posOfAt);
    }


    public String domain() {
        int posOfAt = address().indexOf('@');
        return address().substring(posOfAt + 1);
    }


    public String zone() {
        int lastDotPos = address().lastIndexOf('.');
        return (lastDotPos > 0)
                ? address().substring(lastDotPos + 1)
                : "";
    }


    @Override
    public int hashCode() {
        return address().hashCode();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Email email = (Email) o;

        return address().equals(email.address());
    }


    @Override
    public String toString() {
        return address();
    }


    public static Email parse(final String address) {
        if ((address != null) && (PATTERN.matcher(address).matches())) {
            return new Email(address);
        }
        String message = String.format("\"%s\" isn't a correct email address.", address);
        throw new IllegalArgumentException(message);
    }
}
