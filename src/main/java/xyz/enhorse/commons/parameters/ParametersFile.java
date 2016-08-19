package xyz.enhorse.commons.parameters;

import xyz.enhorse.commons.StringPair;
import xyz.enhorse.commons.Validate;

import java.io.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         17.08.2016
 */
public class ParametersFile extends AbstractParametersLoader {

    private final File input;
    private final Charset encoding;


    public ParametersFile(final File file, Charset charset) {
        input = validateFile(file);
        encoding = Validate.defaultIfNull(charset, Charset.defaultCharset());
    }


    private File validateFile(final File file) {
        if ((file == null) || !(file.exists()) || !(file.isFile())) {
            throw new IllegalArgumentException("Illegal input file \'" + file + "\'.");
        }

        return file;
    }


    @Override
    public Map<String, String> load() {
        Map<String, String> map = new HashMap<>();

        String line;
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(new FileInputStream(input), encoding.name()))) {
            while ((line = in.readLine()) != null) {
                StringPair pair = super.processString(line, map);
                if (!StringPair.EMPTY.equals(pair)) {
                    map.put(pair.leading(), trimLeadingSpaces(pair.trailing()));
                }
            }
        } catch (IOException ex) {
            throw new IllegalStateException("Error loading parameters from " + input, ex);
        }

        return map;
    }


    private String trimLeadingSpaces(final String string) {
        int length = string.length();
        int current = 0;
        char[] chars = string.toCharArray();

        while ((current < length) && (chars[current] <= ' ')) {
            current++;
        }

        return ((current > 0) || (length < chars.length))
                ? string.substring(current, length)
                : string;
    }


    @Override
    public String toString() {
        return input
                + " ["
                + encoding
                + ']';
    }
}
