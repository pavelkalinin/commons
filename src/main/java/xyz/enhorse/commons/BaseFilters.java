package xyz.enhorse.commons;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         20.07.2016
 */
enum BaseFilters implements PathFilter {
    FILES_ONLY {
        @Override
        public boolean accept(Path entry) throws IOException {
            return !Files.isDirectory(entry);
        }


        @Override
        public String toString() {
            return "[files]";
        }
    },

    DIRECTORIES_ONLY {
        @Override
        public boolean accept(Path entry) throws IOException {
            return Files.isDirectory(entry);
        }


        @Override
        public String toString() {
            return "[directories]";
        }
    },

    ALL {
        @Override
        public boolean accept(Path entry) throws IOException {
            return (entry != null) && (Files.exists(entry));
        }


        @Override
        public String toString() {
            return "[all]";
        }
    }
}
