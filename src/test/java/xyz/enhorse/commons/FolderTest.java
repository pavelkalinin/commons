package xyz.enhorse.commons;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         16/07/16
 */
public class FolderTest {

    private static final String TYPE_1 = ".java";
    private static final String TYPE_2 = ".tmp";
    private static final String TYPE_ABSENT = ".txt";

    private static final int NUMBER_OF_FILES_TYPE_1 = (int) (Math.random() * 100 + 1);
    private static final int NUMBER_OF_FILES_TYPE_2 = (int) (Math.random() * 10 + 1);
    private static final int NUMBER_OF_DIRECTORIES = (int) (Math.random() * NUMBER_OF_FILES_TYPE_1 + 1);

    private static final String[] FILES = new String[NUMBER_OF_FILES_TYPE_1 + NUMBER_OF_FILES_TYPE_2];

    private static String directory;


    @Test
    public void create_emptyString() throws Exception {
        assertNotNull(new Folder(""));
    }


    @Test
    public void create_existingFolder() throws Exception {
        assertEquals(Paths.get(directory), new Folder(directory).path());
    }


    @Test
    public void create_existingFile() throws Exception {
        assertEquals(Paths.get(directory), new Folder(directory + FILES[0]).path());
    }


    @Test
    public void create_absentFile() throws Exception {
        assertEquals(Paths.get(directory), new Folder(directory + FILES[0] + TYPE_1).path());
    }


    @Test
    public void create_absentDirectory() throws Exception {
        assertNotNull(new Folder(absentDirectory()));
    }


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
    public void listFiles_onlySpace() throws Exception {
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