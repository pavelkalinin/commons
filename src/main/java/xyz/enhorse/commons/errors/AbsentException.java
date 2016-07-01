package xyz.enhorse.commons.errors;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         23.06.2016.
 */
public class AbsentException extends RuntimeException {

    public AbsentException() {
        super();
    }


    public AbsentException(final String subject, final String value) {
        super(subject + " \'" + value + "\' doesn't exist");
    }


    public AbsentException(final Throwable cause) {
        super(cause);
    }


    public AbsentException(final String subject, final String value, Throwable cause) {
        super(subject + " \'" + value + "\' doesn't exist", cause);
    }
}
