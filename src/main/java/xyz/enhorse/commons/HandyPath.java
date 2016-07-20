package xyz.enhorse.commons;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 09.12.2015.
 */
public class HandyPath extends AbstractHandyPath {

    public static final char EXTENSION_SEPARATOR = '.';
    public static final char PATH_SEPARATOR = File.separatorChar;

    private static final String DEFAULT = "";


    public HandyPath(final Path path) {
        super(Validate.defaultIfNull(path, Paths.get(DEFAULT)));
    }


    public HandyPath(final File file) {
        this(fileToPath(file));
    }


    public HandyPath(String filename) {
        this(stringToPath(filename));
    }


    private HandyPath(String path, String name, String extension) {
        this(normalizePathname(path) + name + normalizeExtension(extension));
    }


    public boolean exists() {
        return Files.exists(source());
    }


    public boolean isDirectory() {
        return Files.isDirectory(source());
    }


    public boolean isExistingDirectory() {
        return isDirectory() && exists();
    }


    public boolean isFile() {
        return Files.isRegularFile(source());
    }


    public boolean isExistingFile() {
        return isFile() && exists();
    }


    public boolean isSymlink() {
        return Files.isSymbolicLink(source());
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


    public String pathname() {
        final File parent = !isDirectory()
                ? toFile().getParentFile()
                : toFile();

        return (parent != null)
                ? parent.getAbsolutePath() + File.separator
                : "";
    }


    public String filename() {
        return name() + EXTENSION_SEPARATOR + extension();
    }


    public HandyPath changeName(final String name) {
        return new HandyPath(pathname(), validate(name), extension());
    }


    public HandyPath changePathname(final String pathname) {
        return new HandyPath(validate(pathname), name(), extension());
    }


    public HandyPath changeExtension(final String extension) {
        return new HandyPath(pathname(), name(), validate(extension));
    }


    private static String normalizeExtension(final String extension) {
        return extension.charAt(0) == EXTENSION_SEPARATOR
                ? extension
                : EXTENSION_SEPARATOR + extension;
    }


    private static String normalizePathname(final String path) {
        return path.charAt(path.length() - 1) == PATH_SEPARATOR
                ? path
                : path + PATH_SEPARATOR;
    }


    private static Path stringToPath(final String string) {
        try {
            return Paths.get(string);
        } catch (Exception ex) {
            return Paths.get(DEFAULT);
        }
    }


    private static Path fileToPath(final File file) {
        try {
            return Validate.notNull("file", file).toPath();
        } catch (Exception ex) {
            return Paths.get(DEFAULT);
        }
    }


    private static String validate(final String string) {
        try {
            return Paths.get(string).toString();
        } catch (Exception ex) {
            throw new IllegalArgumentException("Illegal value \'" + string + '\'');
        }
    }
}
