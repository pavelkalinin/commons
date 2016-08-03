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
import static xyz.enhorse.commons.PathEx.EXTENSION_SEPARATOR;
import static xyz.enhorse.commons.PathEx.PATH_SEPARATOR;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         14/07/16
 */
public class PathExTest {

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
        assertNotNull(new PathEx());
    }


    @Test
    public void createFromFile() throws Exception {
        assertNotNull(new PathEx(existingFile()));
    }


    @Test
    public void createFromFile_null() throws Exception {
        File file = null;
        assertNotNull(new PathEx(file));
    }


    @Test
    public void createFromFile_weird() throws Exception {
        File file = new File(weird);
        assertNotNull(new PathEx(file));
    }


    @Test
    public void createFromPath() throws Exception {
        assertNotNull(new PathEx(existingFile().toPath()));
    }


    @Test
    public void createFromPath_null() throws Exception {
        Path path = null;
        assertNotNull(new PathEx(path));
    }


    @Test
    public void createFromString() throws Exception {
        assertNotNull(new PathEx(existingFile().getAbsoluteFile()));
    }


    @Test
    public void createFromString_null() throws Exception {
        String string = null;
        assertNotNull(new PathEx(string));
    }


    @Test
    public void createFromString_weird() throws Exception {
        assertNotNull(new PathEx(weird));
    }


    @Test
    public void createDefaultConstructor_givesCurrentFolder() throws Exception {
        assertEquals(CURRENT_DIRECTORY, new PathEx().toString());
    }


    @Test
    public void createFromString_nullGivesCurrentFolder() throws Exception {
        String string = null;
        assertEquals(CURRENT_DIRECTORY, new PathEx(string).toString());
    }


    @Test
    public void createFromString_emptyGivesCurrentFolder() throws Exception {
        assertEquals(CURRENT_DIRECTORY, new PathEx("").toString());
    }


    @Test
    public void createFromPath_nullGivesCurrentFolder() throws Exception {
        Path path = null;
        assertEquals(CURRENT_DIRECTORY, new PathEx(path).toString());
    }


    @Test
    public void createFromFile_nullGivesCurrentFolder() throws Exception {
        File file = null;
        assertEquals(CURRENT_DIRECTORY, new PathEx(file).toString());
    }


/*
    pathname()
*/


    @Test
    public void pathname_existingFile() throws Exception {
        assertEquals(existingFile().getParent() + PATH_SEPARATOR, new PathEx(existingFile()).pathname());
    }


    @Test
    public void pathname_existingDirectory() throws Exception {
        assertEquals(existingDirectory().toString() + PATH_SEPARATOR, new PathEx(existingDirectory()).pathname());
    }


    @Test
    public void pathname_absentFile() throws Exception {
        assertEquals(absentFile().getParent() + PATH_SEPARATOR, new PathEx(absentFile()).pathname());
    }


    @Test
    public void pathname_absentDirectory() throws Exception {
        assertEquals(absentDirectory().getParent() + PATH_SEPARATOR, new PathEx(absentDirectory()).pathname());
    }


    @Test
    public void pathname_absentSubDirectoryInAbsentDirectory() throws Exception {
        File file = absentSubDirectoryInAbsentDirectory();
        assertEquals(file.getParent() + PATH_SEPARATOR, new PathEx(file).pathname());
    }


/*
    name()
*/


    @Test
    public void name_existingFile() throws Exception {
        assertEquals(name, new PathEx(existingFile()).name());
    }


    @Test
    public void name_absentFile() throws Exception {
        assertEquals(name, new PathEx(absentFile()).name());
    }


    @Test
    public void name_existingDirectory() throws Exception {
        assertEquals(existingDirectory().getName(), new PathEx(existingDirectory()).name());
    }


    @Test
    public void name_absentDirectory() throws Exception {
        assertEquals(absentDirectory().getName(), new PathEx(absentDirectory()).name());
    }


/*
    extension()
*/


    @Test
    public void extension_existingFile_withExtensionSeparator() throws Exception {
        assertEquals(".txt", new PathEx(tmp.newFile("test_file.txt")).extension());
    }


    @Test
    public void extension_existingFile_withoutExtensionSeparator() throws Exception {
        assertEquals("", new PathEx(tmp.newFile("test_file")).extension());
    }


    @Test
    public void extension_absentFile() throws Exception {
        assertEquals(EXTENSION_SEPARATOR + extension, new PathEx(absentFile()).extension());
    }


    @Test
    public void extension_existingDirectory() throws Exception {
        assertEquals("", new PathEx(existingDirectory()).extension());
    }


    @Test
    public void extension_existingDirectory_withExtensionSeparator() throws Exception {

        assertEquals("", new PathEx(tmp.newFolder("test.folder")).extension());
    }


    @Test
    public void extension_absentDirectory() throws Exception {
        assertEquals("", new PathEx(absentDirectory()).extension());
    }


/*
    filename()
*/


    @Test
    public void filename_existingFile() throws Exception {
        assertEquals(filename, new PathEx(existingFile()).filename());
    }


    @Test
    public void filename_absentFile() throws Exception {
        assertEquals(filename, new PathEx(absentFile()).filename());
    }


    @Test
    public void filename_existingDirectory() throws Exception {
        assertEquals("", new PathEx(existingDirectory()).filename());
    }


    @Test
    public void filename_absentDirectory() throws Exception {
        assertEquals(absentDirectory().getName(), new PathEx(absentDirectory()).filename());
    }


/*
    changeName()
*/


    @Test
    public void changeName() throws Exception {
        PathEx original = new PathEx(existingFile());
        String newName = "new" + name;

        assertEquals(newName, original.changeName(newName).name());
    }


    @Test
    public void changeName_immutable() throws Exception {
        PathEx original = new PathEx(existingFile());
        String newName = "new" + name;

        assertNotEquals(original.changeName(newName).name(), original.name());
    }


/*
    changeExtension()
*/


    @Test
    public void changeExtension_withSeparator() throws Exception {
        PathEx original = new PathEx(existingFile());
        String newExtension = EXTENSION_SEPARATOR + "new" + extension;

        assertEquals(newExtension, original.changeExtension(newExtension).extension());
    }


    @Test
    public void changeExtension_withSeparator_immutable() throws Exception {
        PathEx original = new PathEx(existingFile());
        String newExtension = EXTENSION_SEPARATOR + "new" + extension;

        assertNotEquals(original.changeExtension(newExtension).extension(), original.extension());
    }


    @Test
    public void changeExtension_withoutSeparator() throws Exception {
        PathEx original = new PathEx(existingFile());
        String newExtension = "new" + extension;

        assertEquals(EXTENSION_SEPARATOR + newExtension, original.changeExtension(newExtension).extension());
    }


    @Test
    public void changeExtension_withoutSeparator_immutable() throws Exception {
        PathEx original = new PathEx(existingFile());
        String newExtension = "new" + extension;

        assertNotEquals(original.changeExtension(newExtension).extension(), original.extension());
    }


/*
    changePathname()
*/


    @Test
    public void changePathname_withoutSeparator() throws Exception {
        PathEx original = new PathEx(existingFile());
        String newPathname = existingDirectory() + "new";

        assertEquals(newPathname + PATH_SEPARATOR, original.changePathname(newPathname).pathname());
    }


    @Test
    public void changePathname_withoutSeparator_immutable() throws Exception {
        PathEx original = new PathEx(existingFile());
        String newPathname = existingDirectory() + "new";

        assertNotEquals(original.changePathname(newPathname).pathname(), original.pathname());
    }


    @Test
    public void changePathname_withSeparator() throws Exception {
        PathEx original = new PathEx(existingFile());
        String newPathname = existingDirectory() + "new" + PATH_SEPARATOR;

        assertEquals(newPathname, original.changePathname(newPathname).pathname());
    }


    @Test
    public void changePathname_withSeparator_immutable() throws Exception {
        PathEx original = new PathEx(existingFile());
        String newPathname = existingDirectory() + "new";

        assertNotEquals(original.changePathname(newPathname + PATH_SEPARATOR).pathname(), original.pathname());
    }


/*
    isExisting()
*/


    @Test
    public void isExisting_existingFile() throws Exception {
        PathEx file = new PathEx(existingFile());
        assertTrue(file.isExisting());
    }


    @Test
    public void isExisting_absentFile() throws Exception {
        PathEx file = new PathEx(absentFile());
        assertFalse(file.isExisting());
    }


    @Test
    public void isExisting_existingDirectory() throws Exception {
        PathEx directory = new PathEx(existingDirectory());
        assertTrue(directory.isExisting());
    }


    @Test
    public void isExisting_absentDirectory() throws Exception {
        PathEx directory = new PathEx(absentDirectory());
        assertFalse(directory.isExisting());
    }


/*
    isFile()
*/


    @Test
    public void isFile_existingFile() throws Exception {
        PathEx file = new PathEx(existingFile());
        assertTrue(file.isFile());
    }


    @Test
    public void isFile_absentFile() throws Exception {
        PathEx file = new PathEx(absentFile());
        assertFalse(file.isFile());
    }


    @Test
    public void isFile_existingDirectory() throws Exception {
        PathEx directory = new PathEx(existingDirectory());
        assertFalse(directory.isFile());
    }


    @Test
    public void isFile_absentDirectory() throws Exception {
        PathEx directory = new PathEx(absentDirectory());
        assertFalse(directory.isFile());
    }


/*
    isDirectory()
*/


    @Test
    public void isDirectory_existingDirectory() throws Exception {
        PathEx directory = new PathEx(existingDirectory());
        assertTrue(directory.isDirectory());
    }


    @Test
    public void isDirectory_absentDirectory() throws Exception {
        PathEx directory = new PathEx(absentDirectory());
        assertFalse(directory.isDirectory());
    }


    @Test
    public void isDirectory_existingFile() throws Exception {
        PathEx file = new PathEx(existingFile());
        assertFalse(file.isDirectory());
    }


    @Test
    public void isDirectory_absentFile() throws Exception {
        PathEx file = new PathEx(absentFile());
        assertFalse(file.isDirectory());
    }


/*
    isSymlink()
*/


    @Test
    public void isSymlink_existingFile() throws Exception {
        PathEx file = new PathEx(existingFile());
        assertFalse(file.isSymlink());
    }


    @Test
    public void isSymlink_absentFile() throws Exception {
        PathEx file = new PathEx(absentFile());
        assertFalse(file.isSymlink());
    }


/*
    isExistingFile()
*/


    @Test
    public void isExistingFile_existingFile() throws Exception {
        PathEx file = new PathEx(existingFile());
        assertTrue(file.isExistingFile());
    }


    @Test
    public void isExistingFile_absentFile() throws Exception {
        PathEx file = new PathEx(absentFile());
        assertFalse(file.isExistingFile());
    }


    @Test
    public void isExistingFile_existingDirectory() throws Exception {
        PathEx file = new PathEx(existingDirectory());
        assertFalse(file.isExistingFile());
    }


    @Test
    public void isExistingFile_absentDirectory() throws Exception {
        PathEx file = new PathEx(absentDirectory());
        assertFalse(file.isExistingFile());
    }


/*
    isExistingDirectory()
*/


    @Test
    public void isExistingDirectory_existingDirectory() throws Exception {
        PathEx file = new PathEx(existingDirectory());
        assertTrue(file.isExistingDirectory());
    }


    @Test
    public void isExistingDirectory_absentDirectory() throws Exception {
        PathEx file = new PathEx(absentDirectory());
        assertFalse(file.isExistingDirectory());
    }


    @Test
    public void isExistingDirectory_existingFile() throws Exception {
        PathEx file = new PathEx(existingFile());
        assertFalse(file.isExistingDirectory());
    }


    @Test
    public void isExistingDirectory_absentFile() throws Exception {
        PathEx file = new PathEx(absentFile());
        assertFalse(file.isExistingDirectory());
    }


/*
    isWritable()
*/


    @Test
    public void isWritable_existingDirectory() throws Exception {
        PathEx file = new PathEx(existingDirectory());
        assertTrue(file.isWritable());
    }


    @Test
    public void isWritable_existingFile() throws Exception {
        PathEx file = new PathEx(existingFile());
        assertTrue(file.isWritable());
    }


/*
    isExecutable()
*/


    @Test
    public void isExecutable_existingDirectory() throws Exception {
        PathEx file = new PathEx(existingDirectory());
        assertTrue(file.isExecutable());
    }


    @Test
    public void isExecutable_existingFile() throws Exception {
        PathEx file = new PathEx(existingFile());
        if (OperatingSystem.isWindows()) {
            assertTrue(file.isExecutable());
        } else {
            assertFalse(file.isExecutable());
        }
    }


/*
    isHidden()
*/


    @Test
    public void isHidden_existingDirectory() throws Exception {
        PathEx file = new PathEx(existingDirectory());
        assertFalse(file.isHidden());
    }


    @Test
    public void isHidden_existingFile() throws Exception {
        PathEx file = new PathEx(existingFile());
        assertFalse(file.isHidden());
    }


    public void isHidden_absentDirectory() throws Exception {
        PathEx file = new PathEx(absentDirectory());
        assertFalse(file.isHidden());
    }


    @Test
    public void isHidden_absentFile() throws Exception {
        PathEx file = new PathEx(absentFile());
        assertFalse(file.isHidden());
    }

/*
    isReadable()
*/


    @Test
    public void isReadable_existingDirectory() throws Exception {
        PathEx file = new PathEx(existingDirectory());
        assertTrue(file.isReadable());
    }


    @Test
    public void isReadable_existingFile() throws Exception {
        PathEx file = new PathEx(existingFile());
        assertTrue(file.isReadable());
    }


    @Test
    public void isReadable_absentDirectory() throws Exception {
        PathEx file = new PathEx(absentDirectory());
        assertFalse(file.isReadable());
    }


    @Test
    public void isReadable_absentFile() throws Exception {
        PathEx file = new PathEx(absentFile());
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