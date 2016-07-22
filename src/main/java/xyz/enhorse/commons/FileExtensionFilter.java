package xyz.enhorse.commons;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.StringJoiner;

import static xyz.enhorse.commons.HandyPath.EXTENSION_SEPARATOR;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         20.07.2016
 */
public class FileExtensionFilter implements PathFilter {


    private final Set<String> extensions = new HashSet<>();


    public FileExtensionFilter(final String... extensions) {
        collect(extensions);
    }


    private void collect(final String... extension) {
        for (String ext : extension) {
            extensions.add(normalize(ext));
        }
    }


    private boolean isMatched(final HandyPath path) {
        return extensions.contains(normalize(path.extension()));
    }


    @Override
    public boolean accept(Path entry) throws IOException {
        HandyPath path = new HandyPath(entry);
        return path.isFile() && isMatched(path);
    }


    @Override
    public String toString() {
        StringJoiner result = new StringJoiner(",");

        extensions.forEach(result::add);

        return "[" + result.toString() + "]";
    }


    private static String normalize(final String string) {
        return (!string.isEmpty() && (string.charAt(0) == EXTENSION_SEPARATOR)
                ? string.substring(1)
                : string)
                .toLowerCase();
    }
}


