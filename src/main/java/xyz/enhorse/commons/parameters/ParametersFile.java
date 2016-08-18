package xyz.enhorse.commons.parameters;

import xyz.enhorse.commons.Check;
import xyz.enhorse.commons.StringPair;
import xyz.enhorse.commons.Validate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         17.08.2016
 */
public class ParametersFile implements ParametersLoader {

    public static final String COMMENT_MARK = "//";

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
    public List<StringPair> load() {
        List<StringPair> list = new ArrayList<>();

        String line;
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(new FileInputStream(input), encoding.name()))) {
            while ((line = in.readLine()) != null) {
                line = trimLeadingSpaces(line);
                if (!line.startsWith(COMMENT_MARK)) {
                    StringPair pair = StringPair.create(line, Parameters.PARAMETER_VALUE_SEPARATOR);
                    String key = pair.leading().trim();
                    String value = trimLeadingSpaces(pair.trailing());
                    if (Check.isUrlSafe(key)) {
                        list.add(new StringPair(key, value));
                    }
                }
            }
        } catch (IOException ex) {
            throw new IllegalStateException("Error loading parameters from " + input, ex);
        }

        return list;
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
