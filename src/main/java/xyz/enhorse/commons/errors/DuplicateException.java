package xyz.enhorse.commons.errors;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         23.06.2016.
 */
public class DuplicateException extends RuntimeException {

    public DuplicateException() {
        super();
    }


    public DuplicateException(final String subject, final String value) {
        super(subject + " \'" + value + "\' already exists");
    }


    public DuplicateException(final Throwable cause) {
        super(cause);
    }


    public DuplicateException(final String subject, final String value, final Throwable source) {
        super(subject + " \'" + value + "\' already exists", source);
    }
}
