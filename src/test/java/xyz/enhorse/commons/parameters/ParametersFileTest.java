package xyz.enhorse.commons.parameters;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.Assert.*;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         17.08.2016
 */
public class ParametersFileTest {

    private static final TemporaryFolder temp = new TemporaryFolder();


    @Test
    public void load() throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("key1", "value1");
        parameters.put("key2", "value2");
        parameters.put("key3", "value3");

        ParametersFile file = generateFile(parameters, UTF_8);

        for (Map.Entry<String, String> entry : file.load().entrySet()) {
            assertEquals(parameters.get(entry.getKey()), entry.getValue());
        }
    }


    @Test(expected = IllegalArgumentException.class)
    public void create_illegal_nullFile() throws Exception {
        assertNull(new ParametersFile(null, UTF_8));
    }


    @Test
    public void create_nullCharset() throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("key1", "value1");
        parameters.put("key2", "value2");
        parameters.put("key3", "value3");

        assertNotNull(generateFile(parameters, null));
    }


    @Test(expected = IllegalArgumentException.class)
    public void create_illegal_notExistingFile() throws Exception {
        File file = temp.newFile();
        assert file.delete();
        assertNotNull(new ParametersFile(file, UTF_8));
    }


    @Test(expected = IllegalArgumentException.class)
    public void create_illegal_fileIsDirectory() throws Exception {
        File file = temp.newFolder();
        assertNotNull(new ParametersFile(file, UTF_8));
    }



    @Test
    public void load_incorrectKey() throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("key1", "value1");
        parameters.put("key 2", "value2");
        parameters.put("key3", "value3");

        ParametersFile file = generateFile(parameters, UTF_8);

        assertEquals(parameters.size() - 1, file.load().size());
    }


    @Test
    public void load_keyBetweenSpaces() throws Exception {
        String key = "key";

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("  " + key + "  ", "value");

        ParametersFile file = generateFile(parameters, UTF_8);

        assertTrue(file.load().containsKey(key));
    }


    @Test
    public void load_duplicateKeys() throws Exception {
        String key = "key";
        String value1 = "value1";
        String value2 = "value2";
        Charset encoding = UTF_8;

        File file = temp.newFile();

        if (file != null) {
            try (BufferedWriter writer = new BufferedWriter
                    (new OutputStreamWriter(new FileOutputStream(file, false), encoding))) {
                writer.write(String.format("%s%c%s%n", key, Parameters.PARAMETER_VALUE_SEPARATOR, value1));
                writer.write(String.format("%s%c%s%n", key, Parameters.PARAMETER_VALUE_SEPARATOR, value2));
            } catch (IOException ex) {
                throw new IllegalStateException("Error saving the file \'" + file + "\'", ex);
            }
        }

        Map<String, String> parameters = new ParametersFile(file, encoding).load();

        assertEquals(value2, parameters.get(key));
    }


    @Test
    public void load_empty() throws Exception {
        ParametersFile file = generateFile(new HashMap<>(), UTF_8);

        assertEquals(0, file.load().size());
    }


    @Test
    public void load_trimLeadingSpacesFromValue() throws Exception {
        String key = "key";
        String value = "value";

        Map<String, Object> parameters = new HashMap<>();
        parameters.put(key, "   " + value);

        ParametersFile file = generateFile(parameters, UTF_8);

        assertEquals(value, file.load().get(key));
    }

    //TODO check with a different from UTF-8 encoding

    @Test
    public void toString_output() throws Exception {
        File file = temp.newFile();
        String toString = new ParametersFile(file, UTF_8).toString();

        assertTrue("doesn't contains filename", toString.contains(file.toString()));
        assertTrue("doesn't contains charset", toString.contains(UTF_8.name()));
    }


    @BeforeClass
    public static void setUp() throws Exception {
        temp.create();
    }


    @AfterClass
    public static void tearDown() throws Exception {
        temp.delete();
    }


    private static ParametersFile generateFile(final Map<String, Object> map, final Charset charset) throws Exception {
        File file = temp.newFile();

        if (file != null) {
            try (BufferedWriter writer = new BufferedWriter
                    (new OutputStreamWriter(new FileOutputStream(file, false), charset != null ? charset : UTF_8))) {
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    writer.write(String.format("%s%c%s%n",
                            entry.getKey(), Parameters.PARAMETER_VALUE_SEPARATOR, entry.getValue()));
                }
            } catch (IOException ex) {
                throw new IllegalStateException("Error saving the file \'" + file + "\'", ex);
            }
        }

        return new ParametersFile(file, charset);
    }
}