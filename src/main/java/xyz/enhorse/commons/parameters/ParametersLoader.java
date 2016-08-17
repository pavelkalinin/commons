package xyz.enhorse.commons.parameters;

import xyz.enhorse.commons.StringPair;
import xyz.enhorse.commons.Validate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         17.08.2016
 */
public class ParametersLoader {

    private final InputStream input;


    ParametersLoader(final InputStream in) {
        input = Validate.notNull("input stream", in);
    }


    public List<StringPair> load() {
        List<StringPair> list = new ArrayList<>();

        String line;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(input, Charset.forName("UTF-8")))) {
            while ((line = reader.readLine()) != null) {
                line = trimHead(line);
                if (!line.startsWith(Parameters.COMMENT_MARK)) {
                    StringPair pair = StringPair.parse(line, Parameters.PARAMETER_VALUE_SEPARATOR);
                    list.add(new StringPair(pair.key().trim().toLowerCase(), trimHead(pair.value())));
                }
            }
        } catch (IOException ex) {
            throw new IllegalStateException("Error loading parameters from " + input, ex);
        }

        return list;
    }


    private String trimHead(final String string) {
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
}
