package xyz.enhorse.commons.parameters;

import org.apache.log4j.Level;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.Assert.*;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         17.08.2016
 */
public class TextFileLoaderTest {

    private static final String SEPARATOR = System.lineSeparator();

    private static final TemporaryFolder temp = new TemporaryFolder();
    private static Level LEVEL;


    @Test
    public void toString_output() throws Exception {
        File file = temp.newFile();
        String toString = new TextFileLoader(file, UTF_8).toString();

        assertTrue("doesn't contains filename", toString.contains(file.toString()));
        assertTrue("doesn't contains charset", toString.contains(UTF_8.name()));
    }


    @Test
    public void create() throws Exception {
        assertNotNull(new TextFileLoader(temp.newFile(), UTF_8));
    }


    @Test(expected = IllegalArgumentException.class)
    public void create_nullFile() throws Exception {
        new TextFileLoader(null, UTF_8);
    }


    @Test
    public void create_nullCharset() throws Exception {
        assertNotNull(new TextFileLoader(temp.newFile(), null));
    }


    @Test(expected = IllegalArgumentException.class)
    public void create_notExistingFile() throws Exception {
        File file = temp.newFile();
        assert file.delete();

        new TextFileLoader(file, UTF_8);
    }


    @Test(expected = IllegalArgumentException.class)
    public void create_fileIsDirectory() throws Exception {
        File file = temp.newFolder();

        new TextFileLoader(file, UTF_8);
    }


    @Test(expected = IllegalStateException.class)
    public void load_fileIsAbsent() throws Exception {
        File file = temp.newFile();
        ParametersLoader loader = new TextFileLoader(file, UTF_8);

        assert file.delete();
        loader.load();
    }


    @Test
    public void load() throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");

        Map<String, String> actual = new TextFileLoader(generateFile(map, UTF_8), UTF_8).load();

        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            assertTrue("doesn't contain the key \'" + key + "\'", actual.containsKey(key));
            assertEquals("incorrect value of the key \'" + key + "\'", value, actual.get(key));
        }
    }


    @Test
    public void load_empty() throws Exception {
        TextFileLoader file = new TextFileLoader(generateFile(new HashMap<>(), UTF_8), UTF_8);

        assertTrue(file.load().isEmpty());
    }


    @Test
    public void load_duplicateKeys() throws Exception {
        String key = "key1";
        String value1 = "value1";
        String value2 = "value2";

        String string = key + Parameters.PARAMETER_VALUE_SEPARATOR + value1 + SEPARATOR
                + key + Parameters.PARAMETER_VALUE_SEPARATOR + value2;

        Map<String, String> actual = new TextFileLoader(generateFile(string, UTF_8), UTF_8).load();

        assertTrue("incorrect size", actual.size() == 1);
        assertEquals("incorrect value", value2, actual.get(key));
    }


    @Test
    public void load_duplicateValues() throws Exception {
        String key1 = "key1";
        String key2 = "key2";
        String value = "value";

        Map<String, String> map = new HashMap<String, String>() {{
            put(key1, value);
            put(key2, value);
        }};

        Map<String, String> actual = new TextFileLoader(generateFile(map, UTF_8), UTF_8).load();

        assertTrue("incorrect size", actual.size() == map.size());
        assertEquals("incorrect value", actual.get(key1), actual.get(key2));
    }


    @Test
    public void load_unicodeKey() throws Exception {
        String key = "ключ";
        String value = "value";

        Map<String, String> map = new HashMap<String, String>() {{
            put(key, value);
        }};

        Map<String, String> actual = new TextFileLoader(generateFile(map, UTF_8), UTF_8).load();

        assertTrue(actual.containsKey(key));
    }


    @Test
    public void load_keySpacesTrimmed() throws Exception {
        String key = "k e y";
        String value = "value";

        Map<String, String> map = new HashMap<String, String>() {{
            put("   " + key + " ", value);
        }};

        Map<String, String> actual = new TextFileLoader(generateFile(map, UTF_8), UTF_8).load();

        assertTrue(actual.containsKey(key));
    }


    @Test
    public void load_keyEmpty() throws Exception {
        String key = "";
        String value = "value";

        String string = key + Parameters.PARAMETER_VALUE_SEPARATOR + value;

        Map<String, String> actual = new TextFileLoader(generateFile(string, UTF_8), UTF_8).load();

        assertTrue(actual.isEmpty());
    }


    @Test
    public void load_valueEmpty() throws Exception {
        String key = "key";
        String value = "";

        String string = key + Parameters.PARAMETER_VALUE_SEPARATOR + value;

        Map<String, String> actual = new TextFileLoader(generateFile(string, UTF_8), UTF_8).load();

        assertTrue(actual.get(key).isEmpty());
    }


    @Test
    public void load_unicodeValue() throws Exception {
        String key = "key";
        String value = "значение";

        Map<String, String> map = new HashMap<String, String>() {{
            put(key, value);
        }};

        Map<String, String> actual = new TextFileLoader(generateFile(map, UTF_8), UTF_8).load();

        assertEquals(map.get(key), actual.get(key));
    }


    @Test
    public void load_valueSpacesTrimmed() throws Exception {
        String key = "key";
        String value = "value";

        Map<String, String> map = new HashMap<String, String>() {{
            put(key, " " + value + "    ");
        }};

        Map<String, String> actual = new TextFileLoader(generateFile(map, UTF_8), UTF_8).load();

        assertEquals(value, actual.get(key));
    }


    @Test
    public void load_valueQuotesTrimmed() throws Exception {
        String key = "key";
        String value = "value";

        Map<String, String> map = new HashMap<String, String>() {{
            put(key, "\"" + value + "\"");
        }};

        Map<String, String> actual = new TextFileLoader(generateFile(map, UTF_8), UTF_8).load();

        assertEquals(value, actual.get(key));
    }


    @Test
    public void load_valueSpaceAfterQuotesRemained() throws Exception {
        String key = "key";
        String value = " value ";

        Map<String, String> map = new HashMap<String, String>() {{
            put(key, "\"" + value + "\"");
        }};

        Map<String, String> actual = new TextFileLoader(generateFile(map, UTF_8), UTF_8).load();

        assertEquals(value, actual.get(key));
    }


    private static File generateFile(final String string, final Charset charset) throws Exception {
        File file = temp.newFile();

        if (file != null) {
            try (BufferedWriter writer = new BufferedWriter
                    (new OutputStreamWriter(new FileOutputStream(file, false), charset != null ? charset : UTF_8))) {

                writer.write(string);

            } catch (IOException ex) {
                throw new IllegalStateException("Error saving the file \'" + file + "\'", ex);
            }
        }

        return file;
    }


    private static File generateFile(final Map<String, String> map, final Charset charset) throws Exception {
        return generateFile(generateContent(map), charset);
    }


    private static String generateContent(Map<String, String> map) {
        StringJoiner joiner = new StringJoiner(String.valueOf(SEPARATOR));

        for (Map.Entry<String, String> entry : map.entrySet()) {
            joiner.add(entry.getKey() + Parameters.PARAMETER_VALUE_SEPARATOR + entry.getValue());
        }

        return joiner.toString();
    }


    @BeforeClass
    public static void setUp() throws Exception {
        temp.create();
        LEVEL = ParametersLoader.LOGGER.getLevel();
        ParametersLoader.LOGGER.setLevel(Level.FATAL);
    }


    @AfterClass
    public static void tearDown() throws Exception {
        temp.delete();
        ParametersLoader.LOGGER.setLevel(LEVEL);
    }
}