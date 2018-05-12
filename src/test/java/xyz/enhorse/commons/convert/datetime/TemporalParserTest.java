package xyz.enhorse.commons.convert.datetime;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.junit.Assert.assertEquals;


/**
 * Tests for {@link TemporalParser}
 * @author Pavel Kalinin on 10.04.2018.
 */
@RunWith(value = Parameterized.class)
public class TemporalParserTest {

    private final String input;
    private final String format;


    public TemporalParserTest(final String input, final String format) {
        this.input = input;
        this.format = format;
    }


    @Test(expected = NullPointerException.class)
    public void testCreateInstanceWhenConstructorParameterIsNull() {
        new TemporalParser(null);
    }


    @Test
    public void testParseWhenParameterIsNull() {
        assertEquals(new TemporalParser(DateTimeFormatter.BASIC_ISO_DATE).parse(null), Optional.empty());
    }


    @Test
    public void testParse() {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        new TemporalParser(formatter)
                .parse(input)
                .ifPresent(actual -> assertEquals(actual.format(formatter), input));
    }


    @Parameters
    public static String[][] temporalObjects() {
        return new String[][]
                {
                        {"2010-11-12T13:14:15.678+00:00", "yyyy-MM-dd\'T\'HH:mm:ss.SSSxxx"},
                        {"2010-11-12T13:14:15.678", "yyyy-MM-dd\'T\'HH:mm:ss.SSS"},
                        {"2010-11-12T13:14:15", "yyyy-MM-dd\'T\'HH:mm:ss"},
                        {"2010-11-12", "yyyy-MM-dd"},
                        {"13:14:15", "HH:mm:ss"},
                };
    }
}