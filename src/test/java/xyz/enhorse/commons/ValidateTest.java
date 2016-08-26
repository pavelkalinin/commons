package xyz.enhorse.commons;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         18/08/16
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        ValidateOthersTest.class,
        ValidateDefaultProducerTest.class,
        ValidateByteComparisonTest.class,
        ValidateShortComparisonTest.class,
        ValidateIntComparisonTest.class,
        ValidateLongComparisonTest.class,
        ValidateFloatComparisonTest.class,
        ValidateDoubleComparisonTest.class
})
public class ValidateTest {

}
