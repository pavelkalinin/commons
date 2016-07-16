package xyz.enhorse.commons;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static xyz.enhorse.commons.HandyPath.*;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         15.07.2016
 */
public class Folder {

    private final File folder;


    public Folder(final String directory) {
        folder = validateDirectory(directory);
    }


    public List<File> getContents(final String... extensions) {
        return (extensions != null)
                ? fileList(new FileFilter(extensions))
                : fileList(null);
    }


    private File validateDirectory(String path) {
        File result = null;

        if (path != null) {
            File temp = new File(path);
            if (temp.isDirectory()) {
                result = temp;
            }
        }

        return result != null
                ? result
                : currentDirectory();
    }


    public Path path() {
        return Paths.get(folder.getAbsolutePath());
    }


    private File currentDirectory() {
        return new File("");
    }


    private List<File> fileList(FileFilter filter) {
        List<File> result = new ArrayList<>();
        Path current = path();

        try (DirectoryStream<Path> directoryStream = (filter != null)
                ? Files.newDirectoryStream(current, filter)
                : Files.newDirectoryStream(current)) {
            for (Path path : directoryStream) {
                if (!Files.isDirectory(path))
                    result.add(path.toFile());
            }
        } catch (IOException ex) {
            throw new IllegalStateException("Listing error on the path \'" + path() + '\'', ex);
        }

        return result;
    }


    private class FileFilter implements DirectoryStream.Filter<Path> {

        private final Set<String> suitable = new HashSet<>();


        FileFilter(final String... extensions) {
            fillSuitable(extensions);
        }


        private void fillSuitable(final String... extensions) {
            for (String extension : extensions) {
                extension = extension.trim();
                if (!extension.isEmpty()) {
                    if (extension.charAt(0) == EXTENSION_SEPARATOR) {
                        extension = extension.replace(EXTENSION_SEPARATOR, '\0');
                    }
                    suitable.add(extension);
                }
            }
        }


        @Override
        public boolean accept(Path entry) throws IOException {
            return suitable.contains(new HandyPath(entry).extension());
        }
    }
}
