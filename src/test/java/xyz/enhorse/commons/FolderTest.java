package xyz.enhorse.commons;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

import static org.junit.Assert.assertEquals;


/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         16/07/16
 */
public class FolderTest {

    @ClassRule
    public static final TemporaryFolder TEMP = new TemporaryFolder();

    private static final Random RANDOM = new Random(10000);

    private static final Path CURRENT_DIRECTORY = new PathEx();
    private static final String NAME = "file";
    private static final String TYPE_1 = ".java";
    private static final String TYPE_2 = ".tmp";

    private static final String TYPE_ABSENT = ".txt";
    private static final int NUMBER_OF_FILES_TYPE_1 = Math.abs(RANDOM.nextInt(50) + 1);
    private static final int NUMBER_OF_FILES_TYPE_2 = Math.abs(RANDOM.nextInt(10) + 1);
    private static final int NUMBER_OF_DIRECTORIES = Math.abs(RANDOM.nextInt(10) + 1);

    private static String[] FILES = new String[NUMBER_OF_FILES_TYPE_1 + NUMBER_OF_FILES_TYPE_2];
    /*
            Constructors
    */
    private static String DIRECTORY;


    @Test
    public void createString_emptyString() throws Exception {
        assertEquals(CURRENT_DIRECTORY.toString(), new Folder("").path().toString());
    }


    @Test
    public void createString_existingDirectory() throws Exception {
        assertEquals(Paths.get(DIRECTORY), new Folder(DIRECTORY).path());
    }


    @Test
    public void createString_existingFile() throws Exception {
        assertEquals(Paths.get(DIRECTORY), new Folder(DIRECTORY + FILES[0]).path());
    }


    @Test
    public void createString_absent() throws Exception {
        String name = DIRECTORY + FILES[0] + TYPE_1;
        assert !(new File(name).exists());

        assertEquals(Paths.get(name), new Folder(name).path());
    }


    @Test
    public void createString_null() throws Exception {
        String name = null;

        assertEquals(CURRENT_DIRECTORY.toString(), new Folder(name).toString());
    }


    @Test
    public void createPath_existingDirectory() throws Exception {
        Path path = Paths.get(DIRECTORY);

        assertEquals(path, new Folder(path).path());
    }


    @Test
    public void createPath_existingFile() throws Exception {
        Path path = Paths.get(DIRECTORY + FILES[0]);

        assertEquals(Paths.get(DIRECTORY), new Folder(path).path());
    }


    @Test
    public void createPath_absent() throws Exception {
        String name = DIRECTORY + FILES[0] + TYPE_1;
        assert !(new File(name).exists());
        Path path = Paths.get(name);

        assertEquals(Paths.get(name), new Folder(path).path());
    }


    @Test
    public void createPath_null() throws Exception {
        Path path = null;

        assertEquals(CURRENT_DIRECTORY.toString(), new Folder(path).toString());
    }


    @Test
    public void createFile_existingDirectory() throws Exception {
        File file = new File(DIRECTORY);

        assertEquals(file.toPath(), new Folder(file).path());
    }


    @Test
    public void createFile_existingFile() throws Exception {
        File file = new File(DIRECTORY + FILES[0]);

        assertEquals(Paths.get(DIRECTORY), new Folder(file).path());
    }


    @Test
    public void createFile_absent() throws Exception {
        String name = DIRECTORY + FILES[0] + TYPE_1;
        File file = new File(name);
        assert !(file.exists());

        assertEquals(file.toPath(), new Folder(file).path());
    }


    @Test
    public void createFile_null() throws Exception {
        File file = null;

        assertEquals(CURRENT_DIRECTORY.toString(), new Folder(file).toString());
    }


    @Test
    public void createHandyPath_existingDirectory() throws Exception {
        PathEx path = new PathEx(DIRECTORY);

        assertEquals(path.toString(), new Folder(path).path().toString());
    }


    @Test
    public void createHandyPath_existingFile() throws Exception {
        PathEx path = new PathEx(DIRECTORY + FILES[0]);

        assertEquals(path.getParent(), new Folder(path).path());
    }


    @Test
    public void createHAndyPath_absent() throws Exception {
        String name = DIRECTORY + FILES[0] + TYPE_1;
        PathEx path = new PathEx(name);
        assert !(path.isExisting());

        assertEquals(path.toString(), new Folder(path).path().toString());
    }


    @Test
    public void createHandyPath_null() throws Exception {
        PathEx path = null;

        assertEquals(CURRENT_DIRECTORY.toString(), new Folder(path).path().toString());
    }


/*
    listFiles()
*/


    @Test
    public void createDefaultConstructor() throws Exception {
        assertEquals(CURRENT_DIRECTORY.toString(), new Folder().toString());
    }


    @Test
    public void listFiles_empty() throws Exception {
        Folder folder = new Folder(DIRECTORY);

        assertEquals(FILES.length, folder.listFiles().size());
    }


    @Test
    public void listFiles_emptyString() throws Exception {
        Folder folder = new Folder(DIRECTORY);

        assertEquals(FILES.length, folder.listFiles("").size());
    }


    @Test
    public void listFiles_null() throws Exception {
        Folder folder = new Folder(DIRECTORY);
        String[] extensions = null;

        assertEquals(FILES.length, folder.listFiles(extensions).size());
    }


    @Test
    public void listFiles_asterisk() throws Exception {
        Folder folder = new Folder(DIRECTORY);

        assertEquals(FILES.length, folder.listFiles("*").size());
    }


    @Test
    public void listFiles_onlySpaces() throws Exception {
        Folder folder = new Folder(DIRECTORY);

        assertEquals(FILES.length, folder.listFiles("     ").size());
    }


    @Test
    public void listFiles_onlyOneSpace() throws Exception {
        Folder folder = new Folder(DIRECTORY);
        String extension = " ";
        assertEquals(FILES.length, folder.listFiles(extension).size());
    }


    @Test
    public void listFiles_absentExtension() throws Exception {
        Folder folder = new Folder(DIRECTORY);
        assertEquals(0, folder.listFiles(TYPE_ABSENT).size());
    }


    @Test
    public void listFiles_existing_oneType() throws Exception {
        Folder folder = new Folder(DIRECTORY);
        assertEquals(NUMBER_OF_FILES_TYPE_1, folder.listFiles(TYPE_1).size());
    }


    @Test
    public void listFiles_existing_twoTypes() throws Exception {
        Folder folder = new Folder(DIRECTORY);
        assertEquals(FILES.length, folder.listFiles(TYPE_1, TYPE_2).size());
    }


    @Test
    public void listFiles_absentDirectory() throws Exception {
        Folder folder = new Folder(absentDirectory());
        assertEquals(0, folder.listFiles(TYPE_1, TYPE_2).size());
    }


/*
    listFolders()
*/


    @Test
    public void listFolders_existing() throws Exception {
        Folder folder = new Folder(DIRECTORY);
        assertEquals(NUMBER_OF_DIRECTORIES, folder.listFolders().size());
    }


    @Test
    public void listFolders_absent() throws Exception {
        Folder folder = new Folder(absentDirectory());
        assertEquals(0, folder.listFolders().size());
    }


/*
    list()
*/


    @Test
    public void list_existing() throws Exception {
        Folder folder = new Folder(DIRECTORY);
        assertEquals(FILES.length + NUMBER_OF_DIRECTORIES, folder.list().size());
    }


    @Test
    public void list_absentDirectory() throws Exception {
        Folder folder = new Folder(absentDirectory());
        assertEquals(0, folder.list().size());
    }


    /*
        TEST SUPPORT
    */
    @BeforeClass
    public static void init() throws Exception {
        TEMP.create();
        DIRECTORY = TEMP.getRoot().toString() + File.separator;

        for (String file : generateFilenames()) {
            createFileIfNotExists(DIRECTORY + file);
        }

        for (int i = 0; i < NUMBER_OF_DIRECTORIES; i++) {
            createDirectoryIfNotExists(DIRECTORY + "_" + FILES[i]);
        }
    }


    @AfterClass
    public static void clear() throws Exception {
        TEMP.delete();
    }


    private static String[] generateFilenames() {
        for (int i = 0; i < NUMBER_OF_FILES_TYPE_1; i++) {
            FILES[i] = NAME + i + randomizeLettersCase(TYPE_1);
        }

        for (int i = NUMBER_OF_FILES_TYPE_1; i < FILES.length; i++) {
            FILES[i] = NAME + i + randomizeLettersCase(TYPE_2);
        }

        return FILES;
    }


    private static String randomizeLettersCase(final String string) {
        StringBuilder builder = new StringBuilder();

        for (char ch : string.toCharArray()) {
            builder.append(convertToRandomCase(ch));
        }

        return builder.toString();
    }


    private static char convertToRandomCase(final char ch) {
        return (new Random().nextInt(8)) > 4
                ? Character.toLowerCase(ch)
                : Character.toUpperCase(ch);
    }


    private static void createFileIfNotExists(final String filename) throws IOException {
        Path path = Paths.get(filename);
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
    }


    private static void createDirectoryIfNotExists(final String directory) throws IOException {
        Path sub = Paths.get(directory);
        if (!Files.exists(sub)) {
            Files.createDirectory(sub);
        }
    }


    private static String absentDirectory() throws Exception {
        Path directory = Paths.get("absent");
        assert !(directory.toFile().exists());

        return directory.toString() + File.separator + "absent" + File.separator + "absent";
    }
}