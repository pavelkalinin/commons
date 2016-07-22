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
import static xyz.enhorse.commons.HandyPath.EXTENSION_SEPARATOR;
import static xyz.enhorse.commons.HandyPath.PATH_SEPARATOR;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         14/07/16
 */
public class HandyPathTest {
    public static final String CURRENT_DIRECTORY = System.getProperty("user.dir");

    private static final String name = "test";
    private static final String extension = "tst";
    private static final String filename = name + EXTENSION_SEPARATOR + extension;
    private static final String weird = "j ::: */ _%%%+\'\"??? ;^";


    @Rule
    public TemporaryFolder tmp = new TemporaryFolder();


/*
    Constructors
*/


    @Test
    public void createDefaultConstructor() throws Exception {
        assertNotNull(new HandyPath());
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
    public void createFromString_null() throws Exception {
        String string = null;
        assertNotNull(new HandyPath(string));
    }


    @Test
    public void createFromString_weird() throws Exception {
        assertNotNull(new HandyPath(weird));
    }


    @Test
    public void createDefaultConstructor_givesCurrentFolder() throws Exception {
        assertEquals(CURRENT_DIRECTORY, new HandyPath().toString());
    }


    @Test
    public void createFromString_nullGivesCurrentFolder() throws Exception {
        String string = null;
        assertEquals(CURRENT_DIRECTORY, new HandyPath(string).toString());
    }


    @Test
    public void createFromString_emptyGivesCurrentFolder() throws Exception {
        assertEquals(CURRENT_DIRECTORY, new HandyPath("").toString());
    }


    @Test
    public void createFromPath_nullGivesCurrentFolder() throws Exception {
        Path path = null;
        assertEquals(CURRENT_DIRECTORY, new HandyPath(path).toString());
    }


    @Test
    public void createFromFile_nullGivesCurrentFolder() throws Exception {
        File file = null;
        assertEquals(CURRENT_DIRECTORY, new HandyPath(file).toString());
    }


/*
    pathname()
*/


    @Test
    public void pathname_existingFile() throws Exception {
        assertEquals(existingFile().getParent() + PATH_SEPARATOR, new HandyPath(existingFile()).pathname());
    }


    @Test
    public void pathname_existingDirectory() throws Exception {
        assertEquals(existingDirectory().toString() + PATH_SEPARATOR, new HandyPath(existingDirectory()).pathname());
    }


    @Test
    public void pathname_absentFile() throws Exception {
        assertEquals(absentFile().getParent() + PATH_SEPARATOR, new HandyPath(absentFile()).pathname());
    }


    @Test
    public void pathname_absentDirectory() throws Exception {
        assertEquals(absentDirectory().getParent() + PATH_SEPARATOR, new HandyPath(absentDirectory()).pathname());
    }


    @Test
    public void pathname_absentSubDirectoryInAbsentDirectory() throws Exception {
        File file = absentSubDirectoryInAbsentDirectory();
        assertEquals(file.getParent() + PATH_SEPARATOR, new HandyPath(file).pathname());
    }


/*
    name()
*/


    @Test
    public void name_existingFile() throws Exception {
        assertEquals(name, new HandyPath(existingFile()).name());
    }


    @Test
    public void name_absentFile() throws Exception {
        assertEquals(name, new HandyPath(absentFile()).name());
    }


    @Test
    public void name_existingDirectory() throws Exception {
        assertEquals(existingDirectory().getName(), new HandyPath(existingDirectory()).name());
    }


    @Test
    public void name_absentDirectory() throws Exception {
        assertEquals(absentDirectory().getName(), new HandyPath(absentDirectory()).name());
    }


/*
    extension()
*/


    @Test
    public void extension_existingFile_withExtensionSeparator() throws Exception {
        assertEquals(".txt", new HandyPath(tmp.newFile("test_file.txt")).extension());
    }


    @Test
    public void extension_existingFile_withoutExtensionSeparator() throws Exception {
        assertEquals("", new HandyPath(tmp.newFile("test_file")).extension());
    }


    @Test
    public void extension_absentFile() throws Exception {
        assertEquals(EXTENSION_SEPARATOR + extension, new HandyPath(absentFile()).extension());
    }


    @Test
    public void extension_existingDirectory() throws Exception {
        assertEquals("", new HandyPath(existingDirectory()).extension());
    }


    @Test
    public void extension_existingDirectory_withExtensionSeparator() throws Exception {

        assertEquals("", new HandyPath(tmp.newFolder("test.folder")).extension());
    }


    @Test
    public void extension_absentDirectory() throws Exception {
        assertEquals("", new HandyPath(absentDirectory()).extension());
    }


/*
    filename()
*/


    @Test
    public void filename_existingFile() throws Exception {
        assertEquals(filename, new HandyPath(existingFile()).filename());
    }


    @Test
    public void filename_absentFile() throws Exception {
        assertEquals(filename, new HandyPath(absentFile()).filename());
    }


    @Test
    public void filename_existingDirectory() throws Exception {
        assertEquals("", new HandyPath(existingDirectory()).filename());
    }


    @Test
    public void filename_absentDirectory() throws Exception {
        assertEquals(absentDirectory().getName(), new HandyPath(absentDirectory()).filename());
    }


/*
    changeName()
*/


    @Test
    public void changeName() throws Exception {
        HandyPath original = new HandyPath(existingFile());
        String newName = "new" + name;

        assertEquals(newName, original.changeName(newName).name());
    }


    @Test
    public void changeName_immutable() throws Exception {
        HandyPath original = new HandyPath(existingFile());
        String newName = "new" + name;

        assertNotEquals(original.changeName(newName).name(), original.name());
    }


/*
    changeExtension()
*/


    @Test
    public void changeExtension_withSeparator() throws Exception {
        HandyPath original = new HandyPath(existingFile());
        String newExtension = EXTENSION_SEPARATOR + "new" + extension;

        assertEquals(newExtension, original.changeExtension(newExtension).extension());
    }


    @Test
    public void changeExtension_withSeparator_immutable() throws Exception {
        HandyPath original = new HandyPath(existingFile());
        String newExtension = EXTENSION_SEPARATOR + "new" + extension;

        assertNotEquals(original.changeExtension(newExtension).extension(), original.extension());
    }


    @Test
    public void changeExtension_withoutSeparator() throws Exception {
        HandyPath original = new HandyPath(existingFile());
        String newExtension = "new" + extension;

        assertEquals(EXTENSION_SEPARATOR + newExtension, original.changeExtension(newExtension).extension());
    }


    @Test
    public void changeExtension_withoutSeparator_immutable() throws Exception {
        HandyPath original = new HandyPath(existingFile());
        String newExtension = "new" + extension;

        assertNotEquals(original.changeExtension(newExtension).extension(), original.extension());
    }


/*
    changePathname()
*/


    @Test
    public void changePathname_withoutSeparator() throws Exception {
        HandyPath original = new HandyPath(existingFile());
        String newPathname = existingDirectory() + "new";

        assertEquals(newPathname + PATH_SEPARATOR, original.changePathname(newPathname).pathname());
    }


    @Test
    public void changePathname_withoutSeparator_immutable() throws Exception {
        HandyPath original = new HandyPath(existingFile());
        String newPathname = existingDirectory() + "new";

        assertNotEquals(original.changePathname(newPathname).pathname(), original.pathname());
    }


    @Test
    public void changePathname_withSeparator() throws Exception {
        HandyPath original = new HandyPath(existingFile());
        String newPathname = existingDirectory() + "new" + PATH_SEPARATOR;

        assertEquals(newPathname, original.changePathname(newPathname).pathname());
    }


    @Test
    public void changePathname_withSeparator_immutable() throws Exception {
        HandyPath original = new HandyPath(existingFile());
        String newPathname = existingDirectory() + "new";

        assertNotEquals(original.changePathname(newPathname + PATH_SEPARATOR).pathname(), original.pathname());
    }


/*
    isExisting()
*/


    @Test
    public void isExisting_existingFile() throws Exception {
        HandyPath file = new HandyPath(existingFile());
        assertTrue(file.isExisting());
    }


    @Test
    public void isExisting_absentFile() throws Exception {
        HandyPath file = new HandyPath(absentFile());
        assertFalse(file.isExisting());
    }


    @Test
    public void isExisting_existingDirectory() throws Exception {
        HandyPath directory = new HandyPath(existingDirectory());
        assertTrue(directory.isExisting());
    }


    @Test
    public void isExisting_absentDirectory() throws Exception {
        HandyPath directory = new HandyPath(absentDirectory());
        assertFalse(directory.isExisting());
    }


/*
    isFile()
*/


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


/*
    isDirectory()
*/


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


/*
    isSymlink()
*/


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


/*
    isExistingFile()
*/


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


/*
    isExistingDirectory()
*/


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


/*
    isWritable()
*/


    @Test
    public void isWritable_existingDirectory() throws Exception {
        HandyPath file = new HandyPath(existingDirectory());
        assertTrue(file.isWritable());
    }


    @Test
    public void isWritable_existingFile() throws Exception {
        HandyPath file = new HandyPath(existingFile());
        assertTrue(file.isWritable());
    }


/*
    isExecutable()
*/


    @Test
    public void isExecutable_existingDirectory() throws Exception {
        HandyPath file = new HandyPath(existingDirectory());
        assertTrue(file.isExecutable());
    }


    @Test
    public void isExecutable_existingFile() throws Exception {
        HandyPath file = new HandyPath(existingFile());
        assertTrue(file.isExecutable());
    }


/*
    isHidden()
*/


    @Test
    public void isHidden_existingDirectory() throws Exception {
        HandyPath file = new HandyPath(existingDirectory());
        assertFalse(file.isHidden());
    }


    @Test
    public void isHidden_existingFile() throws Exception {
        HandyPath file = new HandyPath(existingFile());
        assertFalse(file.isHidden());
    }


    public void isHidden_absentDirectory() throws Exception {
        HandyPath file = new HandyPath(absentDirectory());
        assertFalse(file.isHidden());
    }


    @Test
    public void isHidden_absentFile() throws Exception {
        HandyPath file = new HandyPath(absentFile());
        assertFalse(file.isHidden());
    }

/*
    isReadable()
*/


    @Test
    public void isReadable_existingDirectory() throws Exception {
        HandyPath file = new HandyPath(existingDirectory());
        assertTrue(file.isReadable());
    }


    @Test
    public void isReadable_existingFile() throws Exception {
        HandyPath file = new HandyPath(existingFile());
        assertTrue(file.isReadable());
    }


    @Test
    public void isReadable_absentDirectory() throws Exception {
        HandyPath file = new HandyPath(absentDirectory());
        assertFalse(file.isReadable());
    }


    @Test
    public void isReadable_absentFile() throws Exception {
        HandyPath file = new HandyPath(absentFile());
        assertFalse(file.isReadable());
    }


/*
        TEST SUPPORT
*/


    private File existingFile() {
        try {
            File result = new File(existingDirectory().toString() + PATH_SEPARATOR + filename);
            return (result.exists())
                    ? result
                    : tmp.newFile(filename);
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }


    private File absentFile() {
        try {
            File result = new File(existingDirectory().toString() + PATH_SEPARATOR + filename);
            Files.deleteIfExists(result.toPath());
            return result;
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }


    private File existingDirectory() {
        return new File(tmp.getRoot().toString());
    }


    private File absentDirectory() {
        try {
            String result = existingDirectory() + name;
            Files.deleteIfExists(Paths.get(result));
            return new File(result);
        } catch (IOException ex) {
            throw new IllegalStateException();
        }
    }


    private File absentSubDirectoryInAbsentDirectory() {
        try {
            String result = absentDirectory().toString() + PATH_SEPARATOR + name;
            Files.deleteIfExists(Paths.get(result));
            return new File(result);
        } catch (IOException ex) {
            throw new IllegalStateException();
        }
    }
}