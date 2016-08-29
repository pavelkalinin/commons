package xyz.enhorse.commons.parameters;

import org.junit.Test;

import java.util.Map;
import java.util.StringJoiner;

import static org.junit.Assert.*;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         25.08.2016
 */
public class CustomCompanionTest {

    private static final String DELIMITER = "  ";
    private static final String KEY_PREFIX = "key.";


    @Test
    public void load_withCompanion() throws Exception {
        String key = "test";
        String value = "value";
        String cipheredValue = Cipher.crypt("value");
        String string = key + "  " + Parameters.PARAMETER_VALUE_SEPARATOR + '\"' + cipheredValue + "\"";

        ParametersLoader loader = new StringLoader(string, "%");
        Map<String, Object> map = loader.load(new CipherCompanion());

        assertFalse("value doesn't ciphered", string.contains(value));
        assertTrue("key not found", map.containsKey(KEY_PREFIX + key));
        assertEquals("incorrect value", value, map.get(KEY_PREFIX + key));
    }


    private static class CipherCompanion implements LoaderCompanion {

        @Override
        public String preProcessKey(final String key) {
            return key.trim();
        }


        @Override
        public String preProcessValue(final String value) {
            return value.substring(1, value.length() - 1);
        }


        @Override
        public String postProcessKey(final String key) {
            return KEY_PREFIX + key;
        }


        @Override
        public String postProcessValue(final String value) {
            return Decipher.decrypt(value);
        }
    }


    private static class Cipher {

        static String crypt(final String string) {
            StringJoiner result = new StringJoiner(DELIMITER);

            for (char symbol : string.toCharArray()) {
                result.add(String.format("%04x", (int) symbol));
            }

            return result.toString();
        }
    }

    private static class Decipher {

        static String decrypt(final String crypt) {
            StringBuilder result = new StringBuilder();

            for (String part : crypt.split(DELIMITER)) {
                result.append((char) Integer.parseInt(part, 16));
            }

            return result.toString();
        }
    }
}
