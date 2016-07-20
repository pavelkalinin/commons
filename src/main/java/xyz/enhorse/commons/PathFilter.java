package xyz.enhorse.commons;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Path;


/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         20.07.2016
 */
public interface PathFilter extends DirectoryStream.Filter<Path> {

    /**
     * Decides if the given directory entry should be accepted or filtered.
     *
     * @param entry the directory entry to be tested
     * @return {@code true} if the directory entry should be accepted
     * @throws IOException If an I/O error occurs
     */
    default
    @Override
    boolean accept(Path entry) throws IOException {
        return true;
    }
}
