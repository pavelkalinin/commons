package xyz.enhorse.commons;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         15.07.2016
 */
public class Folder {

    private final PathEx folder;


    public Folder() {
        folder = new PathEx();
    }


    public Folder(final Path path) {
        folder = normalize(path);
    }


    public Folder(final File file) {
        this(new PathEx(file));
    }


    public Folder(final String directory) {
        this(new PathEx(directory));
    }


    public Folder(final PathEx path) {
        this(Validate.defaultIfNull(path, new PathEx()).source());
    }


    public List<Path> list() {
        return list(BaseFilters.ALL);
    }


    public List<Path> listFolders() {
        return list(BaseFilters.DIRECTORIES_ONLY);
    }


    public List<Path> listFiles(final String... extensions) {
        if (!exists()) {
            return Collections.emptyList();
        }

        return isAnyFile(extensions)
                ? list(BaseFilters.FILES_ONLY)
                : list(new FileExtensionFilter(extensions));
    }


    private boolean isNullOrEmpty(final String[] array) {
        return array == null
                || (array.length == 0)
                || ((array.length == 1) && (array[0] == null));
    }


    private boolean isAnyFile(final String[] array) {
        boolean result = false;

        if (isNullOrEmpty(array)) {
            result = true;
        } else {
            if (array.length == 1) {
                String extension = array[0].trim();
                if (extension.isEmpty() || (extension.equals("*"))) {
                    result = true;
                }
            }
        }

        return result;
    }


    private PathEx normalize(Path path) {
        PathEx result = new PathEx(path);

        return (!result.isExisting()) || (result.isDirectory())
                ? result
                : new PathEx(result.pathname());
    }


    public Path path() {
        return Paths.get(folder.toString());
    }


    private List<Path> list(DirectoryStream.Filter<? super Path> filter) {
        List<Path> result = new ArrayList<>();

        if (folder.isExisting()) {
            try (DirectoryStream<Path> directoryStream = (filter == BaseFilters.ALL)
                    ? Files.newDirectoryStream(path())
                    : Files.newDirectoryStream(path(), filter)) {
                directoryStream.forEach(result::add);
            } catch (IOException ex) {
                throw new IllegalStateException("Listing error on the path \'" + path() + '\'' +
                        " with the filter \'" + filter + '\'', ex);
            }
        }

        return result;
    }


    public boolean exists() {
        return folder.toFile().exists();
    }


    @Override
    public String toString() {
        return folder.toString();
    }
}
