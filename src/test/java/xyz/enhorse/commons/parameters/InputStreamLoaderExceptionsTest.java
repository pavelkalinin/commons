package xyz.enhorse.commons.parameters;

import org.apache.log4j.Level;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         24.08.2016
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({InputStreamLoader.class})
public class InputStreamLoaderExceptionsTest {

    private static final String SEPARATOR = "-";

    private static Level LEVEL;


    @Test(expected = IllegalStateException.class)
    public void load_exception_encoding() throws Exception {
        InputStream stream = new ByteArrayInputStream("".getBytes(UTF_8));
        Charset charset = PowerMockito.mock(Charset.class);
        ParametersLoader loader = new InputStreamLoader(stream, charset, SEPARATOR);

        when(charset.name()).thenReturn("mocked");

        assertNotNull(loader);
        loader.load();
    }


    @Test(expected = IllegalStateException.class)
    public void load_exception_reading() throws Exception {
        InputStream stream = mock(ByteArrayInputStream.class);
        ParametersLoader loader = new InputStreamLoader(stream, UTF_8, SEPARATOR);

        when(stream.read(Mockito.anyObject()))
                .thenThrow(new IOException("mocked"));

        assertNotNull(loader);
        loader.load();
    }


    @BeforeClass
    public static void setUp() throws Exception {
        LEVEL = ParametersLoader.LOGGER.getLevel();
        ParametersLoader.LOGGER.setLevel(Level.FATAL);
    }


    @AfterClass
    public static void tearDown() throws Exception {
        ParametersLoader.LOGGER.setLevel(LEVEL);
    }
}