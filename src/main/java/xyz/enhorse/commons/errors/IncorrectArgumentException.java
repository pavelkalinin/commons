package xyz.enhorse.commons.errors;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         25/06/16
 */
public class IncorrectArgumentException extends RuntimeException {

    public IncorrectArgumentException() {
        super();
    }


    public IncorrectArgumentException(final String subject, final String value) {
        super(subject + " \'" + value + "\' is incorrect");
    }


    public IncorrectArgumentException(final Throwable cause) {
        super(cause);
    }


    public IncorrectArgumentException(final String subject, final String value, final Throwable source) {
        super(subject + " \'" + value + "\' is incorrect", source);
    }
}
