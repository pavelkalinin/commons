package xyz.enhorse.commons;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import static org.junit.Assert.*;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         14/07/16
 */
public class HandyPathTest {

    private static final String name = "test";
    private static final String extension = "tst";
    private static final String filename = name + HandyPath.EXTENSION_SEPARATOR + extension;
    private static final String weird = "j ::: */ _%%%+\'\"??? ;^";

    @Rule
    public TemporaryFolder tmp = new TemporaryFolder();


    private File file() {
        try {
            File result = new File(path() + filename);
            return (result.exists()) ? result : tmp.newFile(name + HandyPath.EXTENSION_SEPARATOR + extension);
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }


    private String path() {
        return tmp.getRoot().toString() + HandyPath.PATH_SEPARATOR;
    }


    @Test
    public void createFromFile() throws Exception {
        assertNotNull(new HandyPath(file()));
    }


    @Test
    public void createFromFile_null() throws Exception {
        File file = null;
        assertNotNull(new HandyPath(file));
    }


    @Test
    public void createFromFile_weird() throws Exception {
        File file = new File(weird);
        assertNotNull(new HandyPath(file));
    }


    @Test
    public void createFromPath() throws Exception {
        assertNotNull(new HandyPath(file().toPath()));
    }


    @Test
    public void createFromPath_null() throws Exception {
        Path path = null;
        assertNotNull(new HandyPath(path));
    }


    @Test
    public void createFromString() throws Exception {
        assertNotNull(new HandyPath(file().getAbsoluteFile()));
    }


    @Test
    public void createFromString_string() throws Exception {
        String string = null;
        assertNotNull(new HandyPath(string));
    }


    @Test
    public void createFromString_weird() throws Exception {
        assertNotNull(new HandyPath(weird));
    }


    @Test
    public void pathname() throws Exception {
        assertEquals(path(), new HandyPath(file()).pathname());
    }


    @Test
    public void name() throws Exception {
        assertEquals(name, new HandyPath(file()).name());
    }


    @Test
    public void extension() throws Exception {
        assertEquals(extension, new HandyPath(file()).extension());
    }


    @Test
    public void filename() throws Exception {
        assertEquals(filename, new HandyPath(file()).filename());
    }


    @Test
    public void changeName() throws Exception {
        HandyPath original = new HandyPath(file());
        String newName = "new" + name;
        HandyPath changed = original.changeName(newName);

        assertEquals("name wasn't changed", newName, changed.name());
        assertEquals("immutability was broken", name, original.name());
    }


    @Test
    public void changeExtension_withoutSeparator() throws Exception {
        HandyPath original = new HandyPath(file());
        String newExtension = "new" + extension;
        HandyPath changed = original.changeExtension(newExtension);

        assertEquals("extension wasn't changed", newExtension, changed.extension());
        assertEquals("immutability was broken", extension, original.extension());
    }


    @Test
    public void changePathname_withoutSeparator() throws Exception {
        HandyPath original = new HandyPath(file());
        String newPathname = path() + "new";
        HandyPath changed = original.changePathname(newPathname);

        assertEquals("pathname wasn't changed", newPathname + HandyPath.PATH_SEPARATOR, changed.pathname());
        assertEquals("immutability was broken", path(), original.pathname());
    }


    @Test
    public void changeExtension_withSeparator() throws Exception {
        HandyPath original = new HandyPath(file());
        String newExtension = HandyPath.EXTENSION_SEPARATOR + "new" + extension;
        HandyPath changed = original.changeExtension(newExtension);

        assertEquals("extension wasn't changed", newExtension, HandyPath.EXTENSION_SEPARATOR + changed.extension());
        assertEquals("immutability was broken", extension, original.extension());
    }


    @Test
    public void changePathname_withSeparator() throws Exception {
        HandyPath original = new HandyPath(file());
        String newPathname = path() + "new" + HandyPath.PATH_SEPARATOR;
        HandyPath changed = original.changePathname(newPathname);

        assertEquals("pathname wasn't changed", newPathname, changed.pathname());
        assertEquals("immutability was broken", path(), original.pathname());
    }


    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void changeName_weird() throws Exception {
        HandyPath f = new HandyPath(file()).changeName(weird);
        System.out.println(f);
    }


    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void changeExtension_weird() throws Exception {
        HandyPath f = new HandyPath(file()).changeExtension(weird);
        System.out.println(f);
    }


    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void changePathname_weird() throws Exception {
        HandyPath f = new HandyPath(file()).changePathname(weird);
        System.out.println(f);
    }
}