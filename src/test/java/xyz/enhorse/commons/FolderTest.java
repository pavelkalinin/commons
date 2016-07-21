package xyz.enhorse.commons;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;


/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         16/07/16
 */
public class FolderTest {

    private static final Path CURRENT_DIRECTORY = new HandyPath();

    private static final String TYPE_1 = ".java";
    private static final String TYPE_2 = ".tmp";
    private static final String TYPE_ABSENT = ".txt";

    private static final int NUMBER_OF_FILES_TYPE_1 = (int) (Math.random() * 100 + 1);
    private static final int NUMBER_OF_FILES_TYPE_2 = (int) (Math.random() * 10 + 1);
    private static final int NUMBER_OF_DIRECTORIES = (int) (Math.random() * NUMBER_OF_FILES_TYPE_1 + 1);

    private static final String[] FILES = new String[NUMBER_OF_FILES_TYPE_1 + NUMBER_OF_FILES_TYPE_2];

    private static String directory;


/*
        Constructors
*/


    @Test
    public void createString_emptyString() throws Exception {
        assertEquals(CURRENT_DIRECTORY.toString(), new Folder("").path().toString());
    }


    @Test
    public void createString_existingDirectory() throws Exception {
        assertEquals(Paths.get(directory), new Folder(directory).path());
    }


    @Test
    public void createString_existingFile() throws Exception {
        assertEquals(Paths.get(directory), new Folder(directory + FILES[0]).path());
    }


    @Test
    public void createString_absent() throws Exception {
        String name = directory + FILES[0] + TYPE_1;
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
        Path path = Paths.get(directory);

        assertEquals(path, new Folder(path).path());
    }


    @Test
    public void createPath_existingFile() throws Exception {
        Path path = Paths.get(directory + FILES[0]);

        assertEquals(Paths.get(directory), new Folder(path).path());
    }


    @Test
    public void createPath_absent() throws Exception {
        String name = directory + FILES[0] + TYPE_1;
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
        File file = new File(directory);

        assertEquals(file.toPath(), new Folder(file).path());
    }


    @Test
    public void createFile_existingFile() throws Exception {
        File file = new File(directory + FILES[0]);

        assertEquals(Paths.get(directory), new Folder(file).path());
    }


    @Test
    public void createFile_absent() throws Exception {
        String name = directory + FILES[0] + TYPE_1;
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
        HandyPath path = new HandyPath(directory);

        assertEquals(path.toString(), new Folder(path).path().toString());
    }


    @Test
    public void createHandyPath_existingFile() throws Exception {
        HandyPath path = new HandyPath(directory + FILES[0]);

        assertEquals(path.getParent(), new Folder(path).path());
    }


    @Test
    public void createHAndyPath_absent() throws Exception {
        String name = directory + FILES[0] + TYPE_1;
        HandyPath path = new HandyPath(name);
        assert !(path.isExisting());

        assertEquals(path.toString(), new Folder(path).path().toString());
    }


    @Test
    public void createHandyPath_null() throws Exception {
        HandyPath path = null;

        assertEquals(CURRENT_DIRECTORY.toString(), new Folder(path).path().toString());
    }


    @Test
    public void createDefaultConstructor() throws Exception {
        assertEquals(CURRENT_DIRECTORY.toString(), new Folder().toString());
    }


/*
    listFiles()
*/


    @Test
    public void listFiles_empty() throws Exception {
        Folder folder = new Folder(directory);

        assertEquals(FILES.length, folder.listFiles().size());
    }


    @Test
    public void listFiles_emptyString() throws Exception {
        Folder folder = new Folder(directory);

        assertEquals(FILES.length, folder.listFiles("").size());
    }


    @Test
    public void listFiles_null() throws Exception {
        Folder folder = new Folder(directory);

        assertEquals(FILES.length, folder.listFiles(null).size());
    }


    @Test
    public void listFiles_asterisk() throws Exception {
        Folder folder = new Folder(directory);

        assertEquals(FILES.length, folder.listFiles("*").size());
    }


    @Test
    public void listFiles_onlySpaces() throws Exception {
        Folder folder = new Folder(directory);

        assertEquals(FILES.length, folder.listFiles("     ").size());
    }


    @Test
    public void listFiles_onlyOneSpace() throws Exception {
        Folder folder = new Folder(directory);
        String extension = " ";
        assertEquals(FILES.length, folder.listFiles(extension).size());
    }


    @Test
    public void listFiles_absentExtension() throws Exception {
        Folder folder = new Folder(directory);
        assertEquals(0, folder.listFiles(TYPE_ABSENT).size());
    }


    @Test
    public void listFiles_existing_oneType() throws Exception {
        Folder folder = new Folder(directory);
        assertEquals(NUMBER_OF_FILES_TYPE_1, folder.listFiles(TYPE_1).size());
    }


    @Test
    public void listFiles_existing_twoTypes() throws Exception {
        Folder folder = new Folder(directory);
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
        Folder folder = new Folder(directory);
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
        Folder folder = new Folder(directory);
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
        generateFilenames();

        directory = Files.createTempDirectory("folder_test").toString() + File.separator;


        for (String file : FILES) {
            String filename = directory + file;
            Path path = Paths.get(filename);
            if (!Files.exists(path)) {
                Files.createFile(path);
            }
        }

        for (int i = 0; i < NUMBER_OF_DIRECTORIES; i++) {
            createDirectoryIfNotExists(directory + "_" + FILES[i]);
        }
    }


    private static void createDirectoryIfNotExists(final String directory) throws IOException {
        Path sub = Paths.get(directory);
        if (!Files.exists(sub)) {
            Files.createDirectory(sub);
        }
    }


    private static void generateFilenames() {
        for (int i = 0; i < NUMBER_OF_FILES_TYPE_1; i++) {
            FILES[i] = "file" + i + TYPE_1;
        }

        for (int i = NUMBER_OF_FILES_TYPE_1; i < FILES.length; i++) {
            FILES[i] = "file" + i + TYPE_2;
        }
    }


    private static String absentDirectory() throws Exception {
        Path directory = Files.createTempDirectory("absent");
        Files.deleteIfExists(directory);
        assert !(directory.toFile().exists());

        return directory.toString() + File.separator + "absent" + File.separator + "absent";
    }
}