package xyz.enhorse.commons;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         21.07.2016
 */
public class OperatingSystemTest {

    private static final String OS = System.getProperty("os.name");


    @Test
    public void isWindows() throws Exception {
        if (OS.startsWith("Windows")) {
            assertTrue(OperatingSystem.isWindows());
        } else {
            assertFalse(OperatingSystem.isWindows());
        }
    }


    @Test
    public void isOSX() throws Exception {
        if (OS.equals("Mac OS X")) {
            assertTrue(OperatingSystem.isOSX());
        } else {
            assertFalse(OperatingSystem.isOSX());
        }
    }

}