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

    private final HandyPath folder;


    public Folder(final String directory) {
        folder = validateDirectory(directory);
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


    private HandyPath validateDirectory(String path) {
        HandyPath result = new HandyPath(path);

        return (result.isDirectory())
                ? result
                : new HandyPath(result.pathname());
    }


    public Path path() {
        return Paths.get(folder.toUri());
    }


    private List<Path> list(DirectoryStream.Filter<? super Path> filter) {
        List<Path> result = new ArrayList<>();

        if (folder.exists()) {
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
        return folder.toString() + File.separator;
    }
}
