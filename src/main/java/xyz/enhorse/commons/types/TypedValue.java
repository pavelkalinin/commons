package xyz.enhorse.commons.types;


/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         26.08.2016
 */
public class TypedValue {

    private final Object value;


    TypedValue(final Object value) {
        this.value = value;
    }


    @SuppressWarnings("unchecked")
    public <T> T value() {
        return (T) value;
    }


    public Class<?> type() {
        return value.getClass();
    }
}
