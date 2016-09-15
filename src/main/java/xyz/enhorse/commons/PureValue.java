package xyz.enhorse.commons;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         15/09/16
 */
public class PureValue {

    private final PureType<?> type;
    private final Object value;


    public PureValue(final Object value) {
        this.value = PureTypes.convert(value);
        this.type = PureTypes.identify(value);
    }


    public PureType<?> type() {
        return type;
    }


    public Object value() {
        return value;
    }


    @Override
    public int hashCode() {
        return 31 * value.hashCode();
    }


    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final PureValue that = (PureValue) o;

        return value.equals(that.value);

    }


    @Override
    public String toString() {
        String value = String.valueOf(value());

        if (type.equals(PureTypes.STRING)) {
            value = '\"' + value + '\"';
        } else {

            if (type.equals(PureTypes.CHAR)) {
                value = '\'' + value + '\'';
            }
        }

        return String.format("%s[%s]", value, type());
    }
}
