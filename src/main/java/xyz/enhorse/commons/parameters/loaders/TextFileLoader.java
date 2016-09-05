package xyz.enhorse.commons.parameters.loaders;

import xyz.enhorse.commons.Validate;

import java.io.*;
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


    public TextFileLoader(final String filename, final Charset encoding) {
        this(new File(Validate.notNull("filename", filename)), encoding);
    }


    private File validateFile(final File file) {
        if ((file == null) || !(file.exists()) || !(file.isFile())) {
            throw new IllegalArgumentException("Illegal input. \'" + file + "\' must be an existing file.");
        }

        return file;
    }


    @Override
    public Map<String, String> load(final LoaderCompanion companion) {
        try (InputStream stream = new FileInputStream(input)) {
            return (new InputStreamLoader(stream, charset, System.lineSeparator())).load(companion);
        } catch (FileNotFoundException ex) {
            String message = "File \'" + input + "\' was not found";
            LOGGER.error(message, ex);
            throw new IllegalStateException(message, ex);
        } catch (IOException ex) {
            String message = "Error reading \'" + input + "\'";
            LOGGER.error(message, ex);
            throw new IllegalStateException(message, ex);
        }
    }


    @Override
    public String toString() {
        return input.toString() + '[' + charset + ']';
    }
}
