package xyz.enhorse.commons;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         17.08.2016
 */
public class StringPair {

    private final String key;
    private final String value;


    public StringPair(final String key, final String value) {
        this.key = Validate.notNullOrEmpty("pair's key", key);
        this.value = Validate.defaultIfNull(value, "");
    }


    public String key() {
        return key;
    }


    public String value() {
        return value;
    }


    @Override
    public int hashCode() {
        int result = key.hashCode();
        result = 31 * result + value.hashCode();
        return result;
    }


    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        StringPair pair = (StringPair) o;

        return key.equals(pair.key)
                && value.equals(pair.value);

    }


    @Override
    public String toString() {
        return String.format("[%s[:]%s]", key, value);
    }


    public static StringPair parse(final String string, final String delimiter) {
        return new Splitter(delimiter).split(string);
    }


    public static StringPair parse(final String string, final char delimiter) {
        return new Splitter(delimiter).split(string);
    }


    private static class Splitter {

        private final String delimiter;


        public Splitter(final String delimiter) {
            this.delimiter = Validate.notNull("splitter's delimiter", delimiter);
        }


        public Splitter(final char delimiter) {
            this.delimiter = String.valueOf(Validate.notNull("splitter's delimiter", delimiter));
        }


        public StringPair split(final String string) {
            Validate.notNullOrEmpty("string to split", string);
            int position = string.indexOf(delimiter);

            String key;
            String value;

            if (position > 0) {
                key = string.substring(0, position);
                value = string.substring(position + delimiter.length());
            } else {
                key = string;
                value = "";
            }

            return new StringPair(key, value);
        }
    }
}
