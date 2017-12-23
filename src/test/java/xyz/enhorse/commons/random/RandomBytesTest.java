package xyz.enhorse.commons.random;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Tests for {@link RandomBytes}
 * @author <a href="mailto:ext_pkalinin@rencredit.ru">Pavel Kalinin</a>
 * on 23/12/17.
 */
public class RandomBytesTest {

    @Test
    public void creatingWithNegativeSize() {
        final RandomGenerator<byte[]> generator = new RandomBytes(-100);
        assertNotNull(generator.next());
    }


    @Test
    public void creatingWithZeroSize() {
        final RandomGenerator<byte[]> generator = new RandomBytes(0);
        assertNotNull(generator.next());
    }


    @Test(timeout = 100)
    public void next() {
        final int expected = 1024;
        final RandomGenerator<byte[]> generator = new RandomBytes(expected);

        final byte[] started = generator.next();

        byte[] actual;
        do {
            actual = generator.next();
        } while (Arrays.equals(actual, started));

        assertEquals("Unexpected size of the generated array", expected, actual.length);
    }
}