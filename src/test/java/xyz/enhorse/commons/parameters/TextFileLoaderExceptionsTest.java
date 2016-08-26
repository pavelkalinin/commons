package xyz.enhorse.commons.parameters;

import org.apache.log4j.Level;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.File;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         24.08.2016
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({TextFileLoader.class})
public class TextFileLoaderExceptionsTest {

    private static final TemporaryFolder temp = new TemporaryFolder();

    private static Level LEVEL;


    @Test(expected = IllegalStateException.class)
    public void load_fileIsAbsent() throws Exception {
        File file = temp.newFile();
        ParametersLoader loader = new TextFileLoader(file, UTF_8);

        assert file.delete();
        loader.load();
    }


    @BeforeClass
    public static void setUp() throws Exception {
        temp.create();
        LEVEL = ParametersLoader.LOGGER.getLevel();
        ParametersLoader.LOGGER.setLevel(Level.FATAL);
    }


    @AfterClass
    public static void tearDown() throws Exception {
        temp.delete();
        ParametersLoader.LOGGER.setLevel(LEVEL);
    }
}