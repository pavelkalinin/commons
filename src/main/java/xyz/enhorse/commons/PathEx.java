package xyz.enhorse.commons;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 09.12.2015.
 */
public class PathEx extends AbstractPathEx {
    private static final String CURRENT = ".";

    public static final char EXTENSION_SEPARATOR = '.';
    public static final char PATH_SEPARATOR = File.separatorChar;
    public static final Path CURRENT_DIRECTORY = Paths.get(CURRENT).toAbsolutePath().normalize();


    public PathEx() {
        this(CURRENT_DIRECTORY);
    }


    public PathEx(final Path path) {
        super(Validate.defaultIfNull(path, CURRENT_DIRECTORY));
    }


    public PathEx(final File file) {
        this(fileToPath(file));
    }


    public PathEx(String filename) {
        this(stringToPath(filename));
    }


    private PathEx(String path, String name, String extension) {
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
                return filename.substring(separator);
            }
        }
        return "";
    }


    public String pathname() {
        final File parent = !isDirectory()
                ? toFile().getParentFile()
                : toFile();

        return (parent != null)
                ? parent.getAbsolutePath() + PATH_SEPARATOR
                : "";
    }


    public String filename() {
        return (!isDirectory())
                ? name() + normalizeExtension(extension())
                : "";
    }


    public PathEx changeName(final String name) {
        return new PathEx(pathname(), validate(name), extension());
    }


    public PathEx changePathname(final String pathname) {
        return new PathEx(validate(pathname), name(), extension());
    }


    public PathEx changeExtension(final String extension) {
        return new PathEx(pathname(), name(), validate(extension));
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
            return CURRENT_DIRECTORY;
        }
    }


    private static Path fileToPath(final File file) {
        try {
            return file.getAbsoluteFile().toPath();
        } catch (Exception ex) {
            return CURRENT_DIRECTORY;
        }
    }


    private static String validate(final String string) {
        try {
            return Paths.get(Validate.defaultIfNullOrEmpty(string, CURRENT)).toString();
        } catch (Exception ex) {
            throw new IllegalArgumentException("Illegal value \'" + string + '\'');
        }
    }
}
