package xyz.enhorse.commons.parameters;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         06.07.2016
 */
public class ConcurrentParametersTest {
    @Test
    public void createDefault() throws Exception {
        assertNotNull(new ConcurrentParameters());
    }


    @Test
    public void createDefault_isEmpty() throws Exception {
        Parameters parameters = new ConcurrentParameters();
        assertTrue(parameters.isEmpty());
    }
}