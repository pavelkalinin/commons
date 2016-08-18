package xyz.enhorse.commons;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         17.08.2016
 */
public class StringPair {

    private static final String DEFAULT_LEADING = "";
    private static final String DEFAULT_TRAILING = "";

    private static final StringPair EMPTY = new StringPair(DEFAULT_LEADING, DEFAULT_TRAILING);

    private final String leading;
    private final String trailing;


    public StringPair(final String leading, final String trailing) {
        this.leading = Validate.defaultIfNull(leading, DEFAULT_LEADING);
        this.trailing = Validate.defaultIfNull(trailing, DEFAULT_TRAILING);
    }


    public String leading() {
        return leading;
    }


    public String trailing() {
        return trailing;
    }


    @Override
    public int hashCode() {
        int result = leading.hashCode();
        result = 31 * result + trailing.hashCode();
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

        return leading.equals(pair.leading)
                && trailing.equals(pair.trailing);

    }


    @Override
    public String toString() {
        return String.format("[%s[:]%s]", leading, trailing);
    }


    public static StringPair create(final String string, final String delimiter) {
        return new Splitter(delimiter).split(string);
    }


    public static StringPair create(final String string, final char delimiter) {
        return new Splitter(delimiter).split(string);
    }


    private static class Splitter {

        private final String delimiter;


        public Splitter(final String delimiter) {
            this.delimiter = Validate.defaultIfNull(delimiter, "");
        }


        public Splitter(final char delimiter) {
            this.delimiter = String.valueOf(delimiter);
        }


        public StringPair split(final String string) {
            if (string == null) {
                return EMPTY;
            }

            int position = !delimiter.isEmpty() ? string.indexOf(delimiter) : -1;

            String key;
            String value;

            if (position > -1) {
                key = string.substring(0, position);
                value = string.substring(position + delimiter.length());
            } else {
                key = string;
                value = DEFAULT_TRAILING;
            }

            return new StringPair(key, value);
        }
    }
}
