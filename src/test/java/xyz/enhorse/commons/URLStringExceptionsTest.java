package xyz.enhorse.commons;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.nio.charset.Charset;

import static org.powermock.api.mockito.PowerMockito.when;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         24.08.2016
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({URLString.class})
public class URLStringExceptionsTest {

    @Test(expected = IllegalStateException.class)
    public void encode_exception() throws Exception {
        Charset charset = PowerMockito.mock(Charset.class);
        when(charset.name()).thenReturn("mocked");

        URLString.encode("string", charset);
    }


    @Test(expected = IllegalStateException.class)
    public void decode_exception() throws Exception {
        Charset charset = PowerMockito.mock(Charset.class);
        when(charset.name()).thenReturn("");

        URLString.decode("string", charset);
    }
}