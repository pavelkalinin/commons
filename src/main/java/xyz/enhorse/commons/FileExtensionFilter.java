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


    private final Set<String> suitable = new HashSet<>();


    public FileExtensionFilter(final String... extensions) {
        fillSuitable(extensions);
    }


    private void fillSuitable(final String... extensions) {
        for (String extension : extensions) {
            suitable.add((extension.charAt(0) == EXTENSION_SEPARATOR)
                    ? extension.substring(1)
                    : extension);
        }
    }


    @Override
    public boolean accept(Path entry) throws IOException {
        return BaseFilters.FILES_ONLY.accept(entry) && suitable.contains(new HandyPath(entry).extension());
    }


    @Override
    public String toString() {
        StringJoiner result = new StringJoiner(",");

        suitable.forEach(result::add);

        return "[" + result.toString() + "]";
    }
}


