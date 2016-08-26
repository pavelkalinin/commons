package xyz.enhorse.commons.parameters;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         18/08/16
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        StringLoaderTest.class,
        InputStreamLoaderTest.class,
        InputStreamLoaderExceptionsTest.class,
        TextFileLoaderTest.class,
        URLLoaderTest.class,
        CustomCompanionTest.class
})
public class ParametersLoaderTest {

}
