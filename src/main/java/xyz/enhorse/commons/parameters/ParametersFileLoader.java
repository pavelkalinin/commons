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
public class ParametersFileLoader implements ParametersLoader {

    private final File file;


    public ParametersFileLoader(final File file) {
        this.file = validateFile(file);
    }


    private File validateFile(final File file) {
        if ((file == null) || !(file.exists()) || !(file.isFile())) {
            throw new IllegalArgumentException("Illegal input file \'" + file + "\'.");
        }

        return file;
    }


    @Override
    public Map<String, String> load(final Charset encoding) {
        Charset charset = Validate.defaultIfNull(encoding, Charset.defaultCharset());

        try {
            ParametersInputStreamLoader loader =
                    new ParametersInputStreamLoader(new FileInputStream(file), System.lineSeparator());
            return loader.load(charset);
        } catch (FileNotFoundException ex) {
            String message = "File \'" + file + "\' not found";
            LOGGER.error(message, ex);
            throw new IllegalStateException(message, ex);
        }
    }


    @Override
    public String toString() {
        return file.toString();
    }
}
