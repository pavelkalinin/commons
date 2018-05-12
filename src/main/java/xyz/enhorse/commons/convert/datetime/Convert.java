package xyz.enhorse.commons.convert.datetime;

/**
 * The handy, user friendly interface over date-time conversions, the common start point of using the framework.
 * @author Pavel Kalinin on 09.04.2018.
 */
public class Convert {


    private Convert() {
        //the utility class
    }


    /**
     * Returns a temporal processor for the given value
     * @param value the value to convert
     * @param <T>   the type of the value
     * @return a temporal processor
     */
    public static <T> TemporalProcessor<T> from(final T value) {
        return new TemporalProcessor<>(value);
    }


    /**
     * Returns a date-time representation of the given string value
     * @param value the value to convert
     * @return a date-time representation
     */
    public static DateTimeString from(final String value) {
        return new DateTimeString(value);
    }
}
