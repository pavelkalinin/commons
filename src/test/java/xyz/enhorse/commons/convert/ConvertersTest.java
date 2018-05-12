package xyz.enhorse.commons.convert;


import org.junit.Test;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;


/**
 * Tests for {@link Converters}
 * @author Pavel Kalinin on 10.04.2018.
 */
public class ConvertersTest {

    @Test
    public void testGetConverterByValueWhenParameterIsNull() {
        final Object parameter = null;

        final long expected = Converters.values().length;
        final long actual = Stream.of(Converters.values())
                .map(converters -> {
                    try {
                        converters.getConverter(parameter);
                    } catch (IllegalArgumentException ex) {
                        return 1;
                    }
                    return 0;
                }).mapToInt(i -> i).sum();
        assertEquals(actual, expected);
    }


    @Test
    public void testGetConverterByClassWhenParameterIsNull() {
        testGetConverterByClass(null);
    }


    @Test
    public void testGetConverterByClassWhenParameterIsUnknownType() {
        testGetConverterByClass(this.getClass());
    }


    private void testGetConverterByClass(final Class<?> type) {
        final long expected = Converters.values().length;
        final long actual = Stream.of(Converters.values())
                .map(converters -> {
                    try {
                        converters.getConverter(type);
                    } catch (IllegalStateException ex) {
                        return 1;
                    }
                    return 0;
                }).mapToInt(i -> i).sum();
        assertEquals(actual, expected);
    }
}