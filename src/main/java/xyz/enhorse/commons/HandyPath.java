package xyz.enhorse.commons;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 09.12.2015.
 */
public class HandyPath extends AbstractHandyPath {

    public static final char EXTENSION_SEPARATOR = '.';
    public static final char PATH_SEPARATOR = File.separatorChar;

    private static final Path DEFAULT_PATH = Paths.get(".").toAbsolutePath().normalize();


    public HandyPath() {
        this(DEFAULT_PATH);
    }


    public HandyPath(final Path path) {
        super(Validate.defaultIfNull(path, DEFAULT_PATH));
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


    public boolean isExisting() {
        return Files.exists(source());
    }


    public boolean isDirectory() {
        return Files.isDirectory(source());
    }


    public boolean isFile() {
        return Files.isRegularFile(source());
    }


    public boolean isExistingDirectory() {
        return isDirectory() && isExisting();
    }


    public boolean isExistingFile() {
        return isFile() && isExisting();
    }


    public boolean isSymlink() {
        return Files.isSymbolicLink(source());
    }


    public boolean isWritable() {
        return Files.isWritable(source());
    }


    public boolean isReadable() {
        return Files.isReadable(source());
    }


    public boolean isExecutable() {
        return Files.isExecutable(source());
    }


    public boolean isHidden() {
        try {
            return Files.isHidden(source());
        } catch (IOException ex) {
            return isExisting();
        }
    }


    public String name() {
        String filename = toFile().getName();
        int separator = filename.indexOf(EXTENSION_SEPARATOR);

        return (separator > 0)
                ? filename.substring(0, separator)
                : filename;
    }


    public String extension() {
        if (!isDirectory()) {
            String filename = toFile().getName();
            int separator = filename.indexOf(EXTENSION_SEPARATOR);
            if (separator > 0) {
                return filename.substring(separator + 1);
            }
        }
        return "";
    }


    public String pathname() {
        final File parent = !isDirectory()
                ? toFile().getParentFile()
                : toFile();

        return (parent != null)
                ? parent.getAbsolutePath()
                : "";
    }


    public String filename() {
        return (!isDirectory())
                ? name() + normalizeExtension(extension())
                : "";
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
        return (extension.length() == 0) || (extension.charAt(0) == EXTENSION_SEPARATOR)
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
            return Paths.get(string).toAbsolutePath().normalize();
        } catch (Exception ex) {
            return DEFAULT_PATH;
        }
    }


    private static Path fileToPath(final File file) {
        try {
            return file.toPath();
        } catch (Exception ex) {
            return DEFAULT_PATH;
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
