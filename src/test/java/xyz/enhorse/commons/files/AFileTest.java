package xyz.enhorse.commons.files;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         14/07/16
 */
public class AFileTest {

    private static final String name = "test";
    private static final String extension = "tst";
    private static final String filename = name + AFile.EXTENSION_SEPARATOR + extension;

    @Rule
    public TemporaryFolder tmp = new TemporaryFolder();


    private File file() {
        try {
            File result = new File(path() + filename);
            return (result.exists()) ? result : tmp.newFile(name + AFile.EXTENSION_SEPARATOR + extension);
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }


    private String path() {
        return tmp.getRoot().toString() + AFile.PATH_SEPARATOR;
    }


    @Test
    public void createFromFile() throws Exception {
        assertNotNull(new AFile(file()));
    }


    @Test
    public void createFromPath() throws Exception {
        assertNotNull(new AFile(file().toPath()));
    }


    @Test
    public void createFromString() throws Exception {
        assertNotNull(new AFile(file().getAbsoluteFile()));
    }


    @Test
    public void createFromAFile() throws Exception {
        assertNotNull(new AFile(new AFile(file())));
    }


    @Test
    public void pathTo() throws Exception {
        assertEquals(path(), new AFile(file()).pathTo());
    }


    @Test
    public void name() throws Exception {
        assertEquals(name, new AFile(file()).name());
    }


    @Test
    public void extension() throws Exception {
        assertEquals(extension, new AFile(file()).extension());
    }


    @Test
    public void filename() throws Exception {
        assertEquals(filename, new AFile(file()).filename());
    }
}