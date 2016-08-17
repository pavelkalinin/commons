package xyz.enhorse.commons;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         24.03.2016
 */
public class ValidateDefaultProducerTest {

    @Test
    public void defaultIfNull() throws Exception {
        String defaultValue = "default";
        String value = "value";

        assertEquals(value, Validate.defaultIfNull(value, defaultValue));
    }


    @Test
    public void defaultIfNull_choiceFromTwo() throws Exception {
        String value1 = "value1";
        String value2 = "value2";
        boolean criteria = (new Date().getTime() % 2) == 0;

        String expected = criteria ? value1 : value2;
        String actual = Validate.defaultIfNull(null, () -> criteria ? value1 : value2);

        assertEquals(expected, actual);
    }


    @Test
    public void defaultIfNull_returnConstant() throws Exception {
        String defaultValue = "default";

        String actual = Validate.defaultIfNull(null, () -> defaultValue);

        assertEquals(defaultValue, actual);
    }


    @Test(expected = IllegalArgumentException.class)
    public void defaultIfNull_producerIsNull() throws Exception {
        String defaultValue = "default";
        DefaultsProducer<String> producer = null;

        String actual = Validate.defaultIfNull(null, producer);

        assertEquals(defaultValue, actual);
    }
}