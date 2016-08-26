package xyz.enhorse.commons.parameters;

import xyz.enhorse.commons.Validate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         17.08.2016
 */
public class TextFileLoader implements ParametersLoader {

    private final File input;
    private final Charset charset;


    public TextFileLoader(final File file, final Charset encoding) {
        input = validateFile(file);
        charset = Validate.defaultIfNull(encoding, Charset.defaultCharset());
    }


    private File validateFile(final File file) {
        if ((file == null) || !(file.exists()) || !(file.isFile())) {
            throw new IllegalArgumentException("Illegal input. \'" + file + "\' must be an existing file.");
        }

        return file;
    }


    @Override
    public Map<String, String> load(final LoaderCompanion companion) {
        try {
            InputStreamLoader loader =
                    new InputStreamLoader(new FileInputStream(input), charset, System.lineSeparator());
            return loader.load(companion);
        } catch (FileNotFoundException ex) {
            String message = "File \'" + input + "\' was not found";
            LOGGER.error(message, ex);
            throw new IllegalStateException(message, ex);
        }
    }


    @Override
    public String toString() {
        return input.toString() + '[' + charset + ']';
    }
}
