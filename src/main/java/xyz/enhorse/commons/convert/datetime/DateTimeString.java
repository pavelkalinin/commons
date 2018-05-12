package xyz.enhorse.commons.convert.datetime;

/**
 * The auxiliary class - string representation of date-time value with the certain format
 * @author Pavel Kalinin on 09.04.2018.
 */
public class DateTimeString {

    /** The date-time representation string */
    private final String value;
    /** The format of date-time represented string */
    private final String format;


    private DateTimeString(final String value, final String format) {
        this.value = value;
        this.format = format;
    }


    /** Creates a date-time representation for the given string value */
    public DateTimeString(final String value) {
        this(value, null);
    }


    /**
     * Returns a temporal processor for the given string value
     * @param format the format to process with
     * @return a temporal processor
     */
    public TemporalProcessor<DateTimeString> with(final String format) {
        return new TemporalProcessor<>(new DateTimeString(value(), format));
    }


    /**
     * Returns the date-time representation string of this instance
     * @return a date-time representation string
     */
    public String value() {
        return value;
    }


    /**
     * Returns the format of date-time represented string of this instance
     * @return a format of date-time represented string
     */
    public String format() {
        return format;
    }
}
