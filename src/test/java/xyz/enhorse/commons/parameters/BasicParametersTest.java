package xyz.enhorse.commons.parameters;

import org.junit.Test;
import xyz.enhorse.commons.errors.AbsentException;
import xyz.enhorse.commons.errors.DuplicateException;

import java.util.Collections;

import static org.junit.Assert.*;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         06.07.2016
 */
public class BasicParametersTest {

    private static final String NAME = "parameter";
    private static final String VALUE = "value";
    private static final String ONE_PARAMETER = NAME + Parameters.PARAMETER_VALUE_SEPARATOR + VALUE;
    private static final String WEIRD_STRING = "120ir9-e9888ey $%#@@12((()R%^$%YfDFG";


    @Test
    public void createDefault() throws Exception {
        assertNotNull(new BasicParameters());
    }


    @Test
    public void createDefault_isEmpty() throws Exception {
        Parameters parameters = new BasicParameters();
        assertTrue(parameters.isEmpty());
    }


    @Test
    public void createFromString_emptyString() throws Exception {
        Parameters parameters = new BasicParameters("");
        assertTrue(parameters.isEmpty());
    }


    @Test
    public void createFromString_nullString() throws Exception {
        String string = null;
        Parameters parameters = new BasicParameters(string);
        assertTrue(parameters.isEmpty());
    }


    @Test
    public void createFromString_oneParameter() throws Exception {
        Parameters parameters = new BasicParameters(ONE_PARAMETER);
        assertEquals(VALUE, parameters.get(NAME));
    }


    @Test
    public void createFromString_twoParameters_validSize() throws Exception {
        String parameter1 = NAME + '1' + Parameters.PARAMETER_VALUE_SEPARATOR + VALUE + '1';
        String parameter2 = NAME + '2' + Parameters.PARAMETER_VALUE_SEPARATOR + VALUE + '2';
        Parameters parameters = new BasicParameters(parameter1 + Parameters.PARAMETERS_SEPARATOR + parameter2);

        assertEquals(2, parameters.size());
    }


    @Test
    public void createFromString_twoParameters_validValues() throws Exception {
        String parameter1 = NAME + '1' + Parameters.PARAMETER_VALUE_SEPARATOR + VALUE + '1';
        String parameter2 = NAME + '2' + Parameters.PARAMETER_VALUE_SEPARATOR + VALUE + '2';
        Parameters parameters = new BasicParameters(parameter1 + Parameters.PARAMETERS_SEPARATOR + parameter2);

        assertEquals("first parameter is invalid", VALUE + '1', parameters.get(NAME + '1'));
        assertEquals("second parameter is invalid", VALUE + '2', parameters.get(NAME + '2'));
    }


    @Test
    public void createFromString_oneParameter_withoutValue() throws Exception {
        String string = NAME + Parameters.PARAMETER_VALUE_SEPARATOR;
        Parameters parameters = new BasicParameters(string);

        assertEquals("", parameters.get(NAME));
    }


    @Test
    public void createFromString_oneParameter_withoutValueAndSeparator() throws Exception {
        String string = NAME;
        Parameters parameters = new BasicParameters(string);

        assertEquals("", parameters.get(NAME));
    }


    @Test
    public void createFromString_oneParameter_withoutValue_twoSeparators() throws Exception {
        String string = NAME + Parameters.PARAMETER_VALUE_SEPARATOR + Parameters.PARAMETER_VALUE_SEPARATOR;
        Parameters parameters = new BasicParameters(string);

        assertEquals(String.valueOf(Parameters.PARAMETER_VALUE_SEPARATOR), parameters.get(NAME));
    }


    @Test
    public void createFromString_oneParameter_withoutValue_threeSeparators() throws Exception {
        String input = NAME + Parameters.PARAMETER_VALUE_SEPARATOR
                + Parameters.PARAMETER_VALUE_SEPARATOR + Parameters.PARAMETER_VALUE_SEPARATOR;
        Parameters parameters = new BasicParameters(input);

        assertEquals(String.valueOf(Parameters.PARAMETER_VALUE_SEPARATOR) + Parameters.PARAMETER_VALUE_SEPARATOR,
                parameters.get(NAME));
    }


    @Test
    public void createFromParameters_emptyParameters() throws Exception {
        Parameters parameters = new BasicParameters(new BasicParameters());
        assertTrue(parameters.isEmpty());
    }


    @Test
    public void createFromParameters_nullParameters() throws Exception {
        Parameters temp = null;
        Parameters parameters = new BasicParameters(temp);

        assertTrue(parameters.isEmpty());
    }


    @Test
    public void createFromParameters_identicalSize() throws Exception {
        String parameter1 = NAME + '1' + Parameters.PARAMETER_VALUE_SEPARATOR + VALUE + '1';
        String parameter2 = NAME + '2' + Parameters.PARAMETER_VALUE_SEPARATOR + VALUE + '2';
        Parameters temp = new BasicParameters(parameter1 + Parameters.PARAMETERS_SEPARATOR + parameter2);
        Parameters parameters = new BasicParameters(temp);

        assertEquals(temp.size(), parameters.size());
    }


    @Test
    public void createFromParameters_identicalValues() throws Exception {
        String parameter1 = NAME + '1' + Parameters.PARAMETER_VALUE_SEPARATOR + VALUE + '1';
        String parameter2 = NAME + '2' + Parameters.PARAMETER_VALUE_SEPARATOR + VALUE + '2';
        Parameters temp = new BasicParameters(parameter1 + Parameters.PARAMETERS_SEPARATOR + parameter2);
        Parameters parameters = new BasicParameters(temp);

        assertEquals("first parameters aren't identical", temp.get(NAME + '1'), parameters.get(NAME + '1'));
        assertEquals("second parameters aren't identical", temp.get(NAME + '2'), parameters.get(NAME + '2'));
    }


    @Test
    public void put_sizeIncreased() throws Exception {
        Parameters parameters = new BasicParameters();
        int initialSize = parameters.size();
        parameters.put(NAME, VALUE);
        int actualSize = parameters.size();

        assertEquals(initialSize + 1, actualSize);
    }


    @Test
    public void put_existingParameter_valueChanged() throws Exception {
        Parameters parameters = new BasicParameters();
        parameters.put(NAME, VALUE);
        parameters.put(NAME, VALUE + '1');

        assertEquals(VALUE + '1', parameters.get(NAME));
    }


    @Test
    public void put_existingParameter_sizeUnchanged() throws Exception {
        Parameters parameters = new BasicParameters();
        parameters.put(NAME, VALUE + '1');
        parameters.put(NAME, VALUE + '2');

        assertEquals(1, parameters.size());
    }


    @Test
    public void put_manyParameters() throws Exception {
        Parameters parameters = new BasicParameters();
        int quantity = 1000;
        for (int i = 0; i < quantity; i++) {
            parameters.put(NAME + i, i);
        }

        assertEquals(quantity, parameters.size());
    }


    @Test(expected = IllegalArgumentException.class)
    public void put_nullParameter() throws Exception {
        Parameters parameters = new BasicParameters();
        parameters.put(null, VALUE);
    }


    @Test
    public void put_nullValue() throws Exception {
        Parameters parameters = new BasicParameters();
        parameters.put(NAME, null);

        assertNull(parameters.get(NAME));
    }


    @Test(expected = IllegalArgumentException.class)
    public void put_illegalParameter() throws Exception {
        Parameters parameters = new BasicParameters();
        parameters.put(" ", null);
    }


    @Test
    public void put_weirdValue() throws Exception {
        Parameters parameters = new BasicParameters();
        parameters.put(NAME, WEIRD_STRING);

        assertEquals(WEIRD_STRING, parameters.get(NAME));
    }


    @Test
    public void add_sizeIncreased() throws Exception {
        Parameters parameters = new BasicParameters();
        int initialSize = parameters.size();
        parameters.add(NAME, VALUE);
        int actualSize = parameters.size();

        assertEquals(initialSize + 1, actualSize);
    }


    @Test(expected = DuplicateException.class)
    public void add_existingParameter() throws Exception {
        Parameters parameters = new BasicParameters();
        parameters.add(NAME, VALUE);
        parameters.add(NAME, VALUE + '1');
    }


    @Test
    public void add_manyParameters() throws Exception {
        Parameters parameters = new BasicParameters();
        int quantity = 1000;
        for (int i = 0; i < quantity; i++) {
            parameters.add(NAME + i, i);
        }

        assertEquals(quantity, parameters.size());
    }


    @Test(expected = IllegalArgumentException.class)
    public void add_nullParameter() throws Exception {
        Parameters parameters = new BasicParameters();
        parameters.add(null, VALUE);
    }


    @Test
    public void add_nullValue() throws Exception {
        Parameters parameters = new BasicParameters();
        parameters.add(NAME, null);

        assertNull(parameters.get(NAME));
    }


    @Test(expected = IllegalArgumentException.class)
    public void add_illegalParameter() throws Exception {
        Parameters parameters = new BasicParameters();
        parameters.add(WEIRD_STRING, null);
    }


    @Test
    public void add_weirdValue() throws Exception {
        Parameters parameters = new BasicParameters();
        parameters.add(NAME, WEIRD_STRING);

        assertEquals(WEIRD_STRING, parameters.get(NAME));
    }


    @Test
    public void replace_sizeUnchanged() throws Exception {
        Parameters parameters = new BasicParameters();
        parameters.put(NAME, VALUE);
        int size = parameters.size();
        parameters.replace(NAME, VALUE + '1');

        assertEquals(size, parameters.size());
    }


    @Test
    public void replace_valueChanged() throws Exception {
        Parameters parameters = new BasicParameters();
        parameters.put(NAME, VALUE);
        parameters.replace(NAME, VALUE + '1');

        assertEquals(VALUE + '1', parameters.get(NAME));
    }


    @Test(expected = AbsentException.class)
    public void replace_absentParameter() throws Exception {
        Parameters parameters = new BasicParameters();
        parameters.replace(NAME, VALUE);
    }


    @Test(expected = IllegalArgumentException.class)
    public void replace_nullParameter() throws Exception {
        Parameters parameters = new BasicParameters();
        parameters.replace(null, VALUE);
    }


    @Test
    public void replace_withNullValue() throws Exception {
        Parameters parameters = new BasicParameters();
        parameters.put(NAME, VALUE);
        parameters.replace(NAME, null);

        assertNull(parameters.get(NAME));
    }


    @Test(expected = IllegalArgumentException.class)
    public void replace_illegalParameter() throws Exception {
        Parameters parameters = new BasicParameters();
        parameters.replace(WEIRD_STRING, null);
    }


    @Test
    public void replace_weirdValue() throws Exception {
        Parameters parameters = new BasicParameters();
        parameters.put(NAME, VALUE);
        parameters.replace(NAME, WEIRD_STRING);

        assertEquals(WEIRD_STRING, parameters.get(NAME));
    }


    @Test
    public void get_existing() throws Exception {
        Parameters parameters = new BasicParameters(ONE_PARAMETER);
        assertEquals(VALUE, parameters.get(NAME));
    }


    @Test(expected = AbsentException.class)
    public void get_absent() throws Exception {
        Parameters parameters = new BasicParameters();
        parameters.get(NAME);
    }


    @Test(expected = IllegalArgumentException.class)
    public void get_null() throws Exception {
        Parameters parameters = new BasicParameters();
        parameters.get(null);
    }


    @Test(expected = IllegalArgumentException.class)
    public void get_invalid() throws Exception {
        Parameters parameters = new BasicParameters();
        parameters.get(WEIRD_STRING);
    }


    @Test
    public void isEmpty_empty() throws Exception {
        Parameters parameters = new BasicParameters();
        if (parameters.size() == 0) {
            assertTrue(parameters.isEmpty());
        }
    }


    @Test
    public void isEmpty_notEmpty() throws Exception {
        Parameters parameters = new BasicParameters(ONE_PARAMETER);
        if (parameters.size() > 0) {
            assertFalse(parameters.isEmpty());
        }
    }


    @Test
    public void size_empty() throws Exception {
        Parameters parameters = new BasicParameters();
        assertEquals(0, parameters.size());
    }


    @Test
    public void size_notEmpty() throws Exception {
        Parameters parameters = new BasicParameters(ONE_PARAMETER);
        assertEquals(1, parameters.size());
    }


    @Test
    public void isExists_existing() throws Exception {
        Parameters parameters = new BasicParameters(ONE_PARAMETER);
        assertTrue(parameters.isExists(NAME));
    }


    @Test
    public void isExists_absent() throws Exception {
        Parameters parameters = new BasicParameters(ONE_PARAMETER);
        assertFalse(parameters.isExists(NAME + '1'));
    }


    @Test(expected = IllegalArgumentException.class)
    public void isExists_null() throws Exception {
        Parameters parameters = new BasicParameters(ONE_PARAMETER);
        assertFalse(parameters.isExists(null));
    }


    @Test(expected = IllegalArgumentException.class)
    public void isExists_weird() throws Exception {
        Parameters parameters = new BasicParameters(ONE_PARAMETER);
        assertFalse(parameters.isExists(WEIRD_STRING));
    }


    @Test
    public void remove_existingParameter() throws Exception {
        Parameters parameters = new BasicParameters(ONE_PARAMETER);
        parameters.remove(NAME);

        assertTrue(parameters.isEmpty());
    }


    @Test
    public void remove_absentParameter() throws Exception {
        Parameters parameters = new BasicParameters(ONE_PARAMETER);
        parameters.remove(NAME + '1');

        assertFalse(parameters.isEmpty());
    }


    @Test(expected = IllegalArgumentException.class)
    public void remove_null() throws Exception {
        Parameters parameters = new BasicParameters(ONE_PARAMETER);
        parameters.remove(null);
    }


    @Test(expected = IllegalArgumentException.class)
    public void remove_weird() throws Exception {
        Parameters parameters = new BasicParameters(ONE_PARAMETER);
        parameters.remove(WEIRD_STRING);
    }


    @Test
    public void clear_notEmpty() throws Exception {
        Parameters parameters = new BasicParameters(ONE_PARAMETER);
        parameters.clear();

        assertTrue(parameters.isEmpty());
    }


    @Test
    public void clear_empty() throws Exception {
        Parameters parameters = new BasicParameters();
        parameters.clear();

        assertTrue(parameters.isEmpty());
    }


    @Test
    public void delete_existingParameter() throws Exception {
        Parameters parameters = new BasicParameters(ONE_PARAMETER);
        parameters.delete(NAME);

        assertTrue(parameters.isEmpty());
    }


    @Test(expected = AbsentException.class)
    public void delete_absentParameter() throws Exception {
        Parameters parameters = new BasicParameters(ONE_PARAMETER);
        parameters.delete(NAME + '1');

        assertFalse(parameters.isEmpty());
    }


    @Test(expected = IllegalArgumentException.class)
    public void delete_null() throws Exception {
        Parameters parameters = new BasicParameters(ONE_PARAMETER);
        parameters.delete(null);
    }


    @Test(expected = IllegalArgumentException.class)
    public void delete_weird() throws Exception {
        Parameters parameters = new BasicParameters(ONE_PARAMETER);
        parameters.delete(WEIRD_STRING);
    }


    @Test
    public void list_empty() throws Exception {
        Parameters parameters = new BasicParameters();
        assertNotNull(parameters.list());
    }


    @Test
    public void list_notEmpty() throws Exception {
        Parameters parameters = new BasicParameters(ONE_PARAMETER);
        assertEquals(1, parameters.list().size());
    }


    @Test
    public void iterator_empty() throws Exception {
        Parameters parameters = new BasicParameters();
        assertFalse(parameters.iterator().hasNext());
    }


    @Test
    public void iterator_notEmpty() throws Exception {
        Parameters parameters = new BasicParameters(ONE_PARAMETER);
        assertTrue(parameters.iterator().hasNext());
    }


    @Test
    public void toString_empty() throws Exception {
        Parameters parameters = new BasicParameters();
        assertEquals("", parameters.toString());
    }


    @Test
    public void toString_oneParameter() throws Exception {
        Parameters parameters = new BasicParameters(ONE_PARAMETER);
        assertEquals(ONE_PARAMETER, parameters.toString());
    }


    @Test
    public void toString_someParameters() throws Exception {
        String parameter1 = NAME + '1' + Parameters.PARAMETER_VALUE_SEPARATOR + VALUE + '1';
        String parameter2 = NAME + '2' + Parameters.PARAMETER_VALUE_SEPARATOR + VALUE + '2';
        String input = parameter1 + Parameters.PARAMETERS_SEPARATOR + parameter2;
        Parameters parameters = new BasicParameters(input);
        String string = parameters.toString();

        assertTrue(string.contains(parameter1)
                && string.contains(parameter2)
                && string.charAt(parameter1.length()) == Parameters.PARAMETERS_SEPARATOR
                && string.length() == input.length());
    }


    @Test
    public void toMap_empty() throws Exception {
        Parameters parameters = new BasicParameters();
        assertEquals(Collections.EMPTY_MAP, parameters.toMap());
    }


    @Test
    public void toMap_twoParameters_validSize() throws Exception {
        String parameter1 = NAME + '1' + Parameters.PARAMETER_VALUE_SEPARATOR + VALUE + '1';
        String parameter2 = NAME + '2' + Parameters.PARAMETER_VALUE_SEPARATOR + VALUE + '2';
        Parameters parameters = new BasicParameters(parameter1 + Parameters.PARAMETERS_SEPARATOR + parameter2);

        assertEquals(parameters.size(), parameters.toMap().size());
    }


    @Test
    public void toMap_twoParameters_validValues() throws Exception {
        String parameter1 = NAME + '1' + Parameters.PARAMETER_VALUE_SEPARATOR + VALUE + '1';
        String parameter2 = NAME + '2' + Parameters.PARAMETER_VALUE_SEPARATOR + VALUE + '2';
        Parameters parameters = new BasicParameters(parameter1 + Parameters.PARAMETERS_SEPARATOR + parameter2);

        assertEquals("first parameter is invalid", VALUE + '1', parameters.toMap().get(NAME + '1'));
        assertEquals("second parameter is invalid", VALUE + '2', parameters.toMap().get(NAME + '2'));
    }


    @Test
    public void hashCode_same_notEmpty() throws Exception {
        Parameters parameters = new BasicParameters(ONE_PARAMETER);
        assertEquals(parameters.hashCode(), parameters.hashCode());
    }


    @Test
    public void hashCode_same_empty() throws Exception {
        Parameters parameters = new BasicParameters();

        assertEquals(parameters.hashCode(), parameters.hashCode());
    }


    @Test
    public void hashCode_identical_empty() throws Exception {
        Parameters parameters1 = new BasicParameters();
        Parameters parameters2 = new BasicParameters();

        assertEquals(parameters1.hashCode(), parameters2.hashCode());
    }


    @Test
    public void hashCode_identical_notEmpty() throws Exception {
        Parameters parameters1 = new BasicParameters(ONE_PARAMETER);
        Parameters parameters2 = new BasicParameters(ONE_PARAMETER);

        assertEquals(parameters1.hashCode(), parameters2.hashCode());
    }


    @Test
    public void hashCode_different() throws Exception {
        Parameters parameters1 = new BasicParameters(ONE_PARAMETER);
        Parameters parameters2 = new BasicParameters();

        assertNotEquals(parameters1.hashCode(), parameters2.hashCode());
    }


    @Test
    public void equals_same_notEmpty() throws Exception {
        Parameters parameters = new BasicParameters(ONE_PARAMETER);
        assertEquals(parameters, parameters);
    }


    @Test
    public void equals_same_empty() throws Exception {
        Parameters parameters = new BasicParameters();

        assertEquals(parameters, parameters);
    }


    @Test
    public void equals_identical_empty() throws Exception {
        Parameters parameters1 = new BasicParameters();
        Parameters parameters2 = new BasicParameters();

        assertTrue(parameters1.equals(parameters2));
    }


    @Test
    public void equals_identical_notEmpty() throws Exception {
        Parameters parameters1 = new BasicParameters(ONE_PARAMETER);
        Parameters parameters2 = new BasicParameters(ONE_PARAMETER);

        assertTrue(parameters1.equals(parameters2));
    }


    @Test
    public void equals_different() throws Exception {
        Parameters parameters1 = new BasicParameters(ONE_PARAMETER);
        Parameters parameters2 = new BasicParameters();

        assertFalse(parameters1.equals(parameters2));
    }


    @Test
    @SuppressWarnings("always")
    public void equals_null() throws Exception {
        Parameters parameters1 = new BasicParameters(ONE_PARAMETER);
        Parameters parameters2 = null;

        assertFalse(parameters1.equals(parameters2));
    }


    @Test
    public void equals_differentClasses() throws Exception {
        Parameters parameters1 = new BasicParameters(ONE_PARAMETER);
        assertFalse(parameters1.equals(9));
    }
}