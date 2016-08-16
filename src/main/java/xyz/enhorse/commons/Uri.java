package xyz.enhorse.commons;

import java.net.URI;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         16.08.2016
 */
public class Uri {

    private final URI address;


    public Uri(final URI uri) {
        address = Validate.notNull("URI", uri);
    }


    public URI toURI() {
        return parse(address.toString()).address;
    }


    @Override
    public int hashCode() {
        return address.hashCode();
    }


    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Uri other = (Uri) o;

        return address.equals(other.address);

    }


    @Override
    public String toString() {
        return address.toString();
    }


    public static Uri parse(final String address) {
        try {
            return new Uri(new URI(Validate.notNullOrEmpty("address", address)));
        } catch (Exception ex) {
            String message = String.format("\"%s\" isn't a correct URI.", address);
            throw new IllegalArgumentException(message);
        }
    }


    public static boolean isValid(final String address) {
        try {
            parse(address);
        } catch (Exception ex) {
            return false;
        }

        return true;
    }
}
