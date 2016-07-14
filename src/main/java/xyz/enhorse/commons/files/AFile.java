package xyz.enhorse.commons.files;

import xyz.enhorse.commons.Validate;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 09.12.2015.
 */
public class AFile {

    public static final char EXTENSION_SEPARATOR = '.';
    public static final char PATH_SEPARATOR = File.separatorChar;

    private static final String DEFAULT_FILENAME = "";

    private File source;


    public AFile(final File file) {
        source = Validate.defaultIfNull(file, new File(DEFAULT_FILENAME));
    }


    public AFile(String filename) {
        this(new File(Validate.defaultIfNull(filename, DEFAULT_FILENAME)));
    }


    public AFile(final Path path) {
        this(Validate.defaultIfNull(path, Paths.get(DEFAULT_FILENAME)).toFile());
    }


    public AFile(final AFile aFile) {
        this(Validate.defaultIfNull(aFile, new AFile(DEFAULT_FILENAME)).toFile());
    }


    private AFile(String path, String name, String extension) {
        this(path + name + extension);
    }


    public String name() {
        String filename = toFile().getName();
        int separator = filename.indexOf(EXTENSION_SEPARATOR);

        return (separator > 0)
                ? filename.substring(0, separator)
                : "";
    }


    public String extension() {
        String filename = toFile().getName();
        int separator = filename.indexOf(EXTENSION_SEPARATOR);

        return ((separator > 0) && (separator < filename.length() - 1))
                ? filename.substring(separator + 1)
                : "";
    }


    public String pathTo() {
        final File parent = toFile().getParentFile();

        return (parent != null)
                ? parent.getAbsolutePath() + File.separator
                : "";
    }


    public String filename() {
        return name() + EXTENSION_SEPARATOR + extension();
    }


    public File toFile() {
        return source;
    }


    public Path toPath() {
        return toFile().toPath();
    }


    @Override
    public String toString() {
        return toFile().toString();
    }
}
