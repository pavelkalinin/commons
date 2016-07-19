package xyz.enhorse.commons;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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


    private File existingFile() {
        try {
            File result = new File(existingDirectory() + filename);
            return (result.exists()) ? result : tmp.newFile(name + HandyPath.EXTENSION_SEPARATOR + extension);
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }


    private File absentFile() {
        try {
            File result = new File(existingDirectory() + filename);
            Files.deleteIfExists(result.toPath());
            return result;
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }


    private String existingDirectory() {
        return tmp.getRoot().toString() + HandyPath.PATH_SEPARATOR;
    }


    private String absentDirectory() {
        try {
            String result = existingDirectory() + name + HandyPath.PATH_SEPARATOR;
            Files.deleteIfExists(Paths.get(result));
            return result;
        } catch (IOException ex) {
            throw new IllegalStateException();
        }
    }


    @Test
    public void createFromFile() throws Exception {
        assertNotNull(new HandyPath(existingFile()));
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
        assertNotNull(new HandyPath(existingFile().toPath()));
    }


    @Test
    public void createFromPath_null() throws Exception {
        Path path = null;
        assertNotNull(new HandyPath(path));
    }


    @Test
    public void createFromString() throws Exception {
        assertNotNull(new HandyPath(existingFile().getAbsoluteFile()));
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
        assertEquals(existingDirectory(), new HandyPath(existingFile()).pathname());
    }


    @Test
    public void name() throws Exception {
        assertEquals(name, new HandyPath(existingFile()).name());
    }


    @Test
    public void extension() throws Exception {
        assertEquals(extension, new HandyPath(existingFile()).extension());
    }


    @Test
    public void filename() throws Exception {
        assertEquals(filename, new HandyPath(existingFile()).filename());
    }


    @Test
    public void changeName() throws Exception {
        HandyPath original = new HandyPath(existingFile());
        String newName = "new" + name;
        HandyPath changed = original.changeName(newName);

        assertEquals(newName, changed.name());
    }


    @Test
    public void changeName_immutable() throws Exception {
        HandyPath original = new HandyPath(existingFile());
        String newName = "new" + name;
        original.changeName(newName);

        assertEquals(name, original.name());
    }


    @Test
    public void changeExtension_withoutSeparator() throws Exception {
        HandyPath original = new HandyPath(existingFile());
        String newExtension = "new" + extension;
        HandyPath changed = original.changeExtension(newExtension);

        assertEquals(newExtension, changed.extension());
    }


    @Test
    public void changeExtension_withoutSeparator_immutable() throws Exception {
        HandyPath original = new HandyPath(existingFile());
        String newExtension = "new" + extension;
        original.changeExtension(newExtension);

        assertEquals(extension, original.extension());
    }


    @Test
    public void changePathname_withoutSeparator() throws Exception {
        HandyPath original = new HandyPath(existingFile());
        String newPathname = existingDirectory() + "new";
        HandyPath changed = original.changePathname(newPathname);

        assertEquals(newPathname + HandyPath.PATH_SEPARATOR, changed.pathname());
    }


    @Test
    public void changePathname_withoutSeparator_immutable() throws Exception {
        HandyPath original = new HandyPath(existingFile());
        String newPathname = existingDirectory() + "new";
        original.changePathname(newPathname);

        assertEquals(existingDirectory(), original.pathname());
    }


    @Test
    public void changeExtension_withSeparator() throws Exception {
        HandyPath original = new HandyPath(existingFile());
        String newExtension = HandyPath.EXTENSION_SEPARATOR + "new" + extension;
        HandyPath changed = original.changeExtension(newExtension);

        assertEquals(newExtension, HandyPath.EXTENSION_SEPARATOR + changed.extension());
    }


    @Test
    public void changeExtension_withSeparator_immutable() throws Exception {
        HandyPath original = new HandyPath(existingFile());
        String newExtension = HandyPath.EXTENSION_SEPARATOR + "new" + extension;
        original.changeExtension(newExtension);

        assertEquals(extension, original.extension());
    }


    @Test
    public void changePathname_withSeparator() throws Exception {
        HandyPath original = new HandyPath(existingFile());
        String newPathname = existingDirectory() + "new" + HandyPath.PATH_SEPARATOR;
        HandyPath changed = original.changePathname(newPathname);

        assertEquals(newPathname, changed.pathname());
    }


    @Test
    public void changePathname_withSeparator_immutable() throws Exception {
        HandyPath original = new HandyPath(existingFile());
        String newPathname = existingDirectory() + "new" + HandyPath.PATH_SEPARATOR;
        original.changePathname(newPathname);

        assertEquals(existingDirectory(), original.pathname());
    }


    @Test
    public void exists_existingFile() throws Exception {
        HandyPath file = new HandyPath(existingFile());
        assertTrue(file.exists());
    }


    @Test
    public void exists_absentFile() throws Exception {
        HandyPath file = new HandyPath(absentFile());
        assertFalse(file.exists());
    }


    @Test
    public void exists_existingDirectory() throws Exception {
        HandyPath directory = new HandyPath(existingDirectory());
        assertTrue(directory.exists());
    }


    @Test
    public void exists_absentDirectory() throws Exception {
        HandyPath directory = new HandyPath(absentDirectory());
        assertFalse(directory.exists());
    }


    @Test
    public void isFile_existingFile() throws Exception {
        HandyPath file = new HandyPath(existingFile());
        assertTrue(file.isFile());
    }


    @Test
    public void isFile_absentFile() throws Exception {
        HandyPath file = new HandyPath(absentFile());
        assertFalse(file.isFile());
    }


    @Test
    public void isFile_existingDirectory() throws Exception {
        HandyPath directory = new HandyPath(existingDirectory());
        assertFalse(directory.isFile());
    }


    @Test
    public void isFile_absentDirectory() throws Exception {
        HandyPath directory = new HandyPath(absentDirectory());
        assertFalse(directory.isFile());
    }


    @Test
    public void isDirectory_existingDirectory() throws Exception {
        HandyPath directory = new HandyPath(existingDirectory());
        assertTrue(directory.isDirectory());
    }


    @Test
    public void isDirectory_absentDirectory() throws Exception {
        HandyPath directory = new HandyPath(absentDirectory());
        assertFalse(directory.isDirectory());
    }


    @Test
    public void isDirectory_existingFile() throws Exception {
        HandyPath file = new HandyPath(existingFile());
        assertFalse(file.isDirectory());
    }


    @Test
    public void isDirectory_absentFile() throws Exception {
        HandyPath file = new HandyPath(absentFile());
        assertFalse(file.isDirectory());
    }


    @Test
    public void isSymlink_existingFile() throws Exception {
        HandyPath file = new HandyPath(existingFile());
        assertFalse(file.isSymlink());
    }


    @Test
    public void isSymlink_absentFile() throws Exception {
        HandyPath file = new HandyPath(absentFile());
        assertFalse(file.isSymlink());
    }


    @Test
    public void isExistingFile_existingFile() throws Exception {
        HandyPath file = new HandyPath(existingFile());
        assertTrue(file.isExistingFile());
    }


    @Test
    public void isExistingFile_absentFile() throws Exception {
        HandyPath file = new HandyPath(absentFile());
        assertFalse(file.isExistingFile());
    }


    @Test
    public void isExistingFile_existingDirectory() throws Exception {
        HandyPath file = new HandyPath(existingDirectory());
        assertFalse(file.isExistingFile());
    }


    @Test
    public void isExistingFile_absentDirectory() throws Exception {
        HandyPath file = new HandyPath(absentDirectory());
        assertFalse(file.isExistingFile());
    }


    @Test
    public void isExistingDirectory_existingDirectory() throws Exception {
        HandyPath file = new HandyPath(existingDirectory());
        assertTrue(file.isExistingDirectory());
    }


    @Test
    public void isExistingDirectory_absentDirectory() throws Exception {
        HandyPath file = new HandyPath(absentDirectory());
        assertFalse(file.isExistingDirectory());
    }


    @Test
    public void isExistingDirectory_existingFile() throws Exception {
        HandyPath file = new HandyPath(existingFile());
        assertFalse(file.isExistingDirectory());
    }


    @Test
    public void isExistingDirectory_absentFile() throws Exception {
        HandyPath file = new HandyPath(absentFile());
        assertFalse(file.isExistingDirectory());
    }
}