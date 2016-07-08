package xyz.enhorse.commons.parameters;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         06.07.2016
 */
public class ConcurrentParametersTest {

    private static final String NAME = "parameter";
    private static final String VALUE = "value";
    private static final String ONE_PARAMETER = NAME + Parameters.PARAMETER_VALUE_SEPARATOR + VALUE;


    @Test
    public void createDefault() throws Exception {
        assertNotNull(new ConcurrentParameters());
    }


    @Test
    public void createDefault_isEmpty() throws Exception {
        Parameters parameters = new ConcurrentParameters();
        assertTrue(parameters.isEmpty());
    }


    @Test
    public void createFromString_emptyString() throws Exception {
        Parameters parameters = new ConcurrentParameters("");
        assertTrue(parameters.isEmpty());
    }


    @Test
    public void createFromString_nullString() throws Exception {
        String string = null;
        Parameters parameters = new ConcurrentParameters(string);
        assertTrue(parameters.isEmpty());
    }


    @Test
    public void createFromString_oneParameter() throws Exception {
        Parameters parameters = new ConcurrentParameters(ONE_PARAMETER);
        assertEquals(VALUE, parameters.get(NAME));
    }


    @Test
    public void createFromString_twoParameters_validSize() throws Exception {
        String parameter1 = NAME + '1' + Parameters.PARAMETER_VALUE_SEPARATOR + VALUE + '1';
        String parameter2 = NAME + '2' + Parameters.PARAMETER_VALUE_SEPARATOR + VALUE + '2';
        Parameters parameters = new ConcurrentParameters(parameter1 + Parameters.PARAMETERS_SEPARATOR + parameter2);

        assertEquals(2, parameters.size());
    }


    @Test
    public void createFromString_twoParameters_validValues() throws Exception {
        String parameter1 = NAME + '1' + Parameters.PARAMETER_VALUE_SEPARATOR + VALUE + '1';
        String parameter2 = NAME + '2' + Parameters.PARAMETER_VALUE_SEPARATOR + VALUE + '2';
        Parameters parameters = new ConcurrentParameters(parameter1 + Parameters.PARAMETERS_SEPARATOR + parameter2);

        assertEquals("first parameter is invalid", VALUE + '1', parameters.get(NAME + '1'));
        assertEquals("second parameter is invalid", VALUE + '2', parameters.get(NAME + '2'));
    }


    @Test
    public void createFromString_oneParameter_withoutValue() throws Exception {
        String string = NAME + Parameters.PARAMETER_VALUE_SEPARATOR;
        Parameters parameters = new ConcurrentParameters(string);

        assertEquals("", parameters.get(NAME));
    }


    @Test
    public void createFromString_oneParameter_withoutValueAndSeparator() throws Exception {
        String string = NAME;
        Parameters parameters = new ConcurrentParameters(string);

        assertEquals("", parameters.get(NAME));
    }


    @Test
    public void createFromString_oneParameter_withoutValue_twoSeparators() throws Exception {
        String string = NAME + Parameters.PARAMETER_VALUE_SEPARATOR + Parameters.PARAMETER_VALUE_SEPARATOR;
        Parameters parameters = new ConcurrentParameters(string);

        assertEquals(String.valueOf(Parameters.PARAMETER_VALUE_SEPARATOR), parameters.get(NAME));
    }


    @Test
    public void createFromString_oneParameter_withoutValue_threeSeparators() throws Exception {
        String input = NAME + Parameters.PARAMETER_VALUE_SEPARATOR
                + Parameters.PARAMETER_VALUE_SEPARATOR + Parameters.PARAMETER_VALUE_SEPARATOR;
        Parameters parameters = new ConcurrentParameters(input);

        assertEquals(String.valueOf(Parameters.PARAMETER_VALUE_SEPARATOR) + Parameters.PARAMETER_VALUE_SEPARATOR,
                parameters.get(NAME));
    }


    @Test
    public void createFromParameters_emptyParameters() throws Exception {
        Parameters parameters = new ConcurrentParameters(new ConcurrentParameters());
        assertTrue(parameters.isEmpty());
    }


    @Test
    public void createFromParameters_nullParameters() throws Exception {
        Parameters temp = null;
        Parameters parameters = new ConcurrentParameters(temp);

        assertTrue(parameters.isEmpty());
    }


    @Test
    public void createFromParameters_identicalSize() throws Exception {
        String parameter1 = NAME + '1' + Parameters.PARAMETER_VALUE_SEPARATOR + VALUE + '1';
        String parameter2 = NAME + '2' + Parameters.PARAMETER_VALUE_SEPARATOR + VALUE + '2';
        Parameters temp = new ConcurrentParameters(parameter1 + Parameters.PARAMETERS_SEPARATOR + parameter2);
        Parameters parameters = new ConcurrentParameters(temp);

        assertEquals(temp.size(), parameters.size());
    }


    @Test
    public void createFromParameters_identicalValues() throws Exception {
        String parameter1 = NAME + '1' + Parameters.PARAMETER_VALUE_SEPARATOR + VALUE + '1';
        String parameter2 = NAME + '2' + Parameters.PARAMETER_VALUE_SEPARATOR + VALUE + '2';
        Parameters temp = new ConcurrentParameters(parameter1 + Parameters.PARAMETERS_SEPARATOR + parameter2);
        Parameters parameters = new ConcurrentParameters(temp);

        assertEquals("first parameters aren't identical", temp.get(NAME + '1'), parameters.get(NAME + '1'));
        assertEquals("second parameters aren't identical", temp.get(NAME + '2'), parameters.get(NAME + '2'));
    }
}