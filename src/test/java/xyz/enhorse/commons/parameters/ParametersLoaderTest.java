package xyz.enhorse.commons.parameters;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         18/08/16
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        ParametersFileLoaderTest.class,
        ParametersURLLoaderTest.class
})
public class ParametersLoaderTest {

}
