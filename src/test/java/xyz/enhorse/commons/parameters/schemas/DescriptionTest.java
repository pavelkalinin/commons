package xyz.enhorse.commons.parameters.schemas;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static xyz.enhorse.commons.parameters.schemas.Constraints.LESS_OR_EQUAL;
import static xyz.enhorse.commons.parameters.schemas.PureTypes.INTEGER;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         02.09.2016
 */
public class DescriptionTest {
    @Test
    public void isApplicable() throws Exception {

        Description d1 = new Description<>(INTEGER, null, null);
        assertTrue(d1.isApplicable("9"));
        assertTrue(d1.isApplicable("string"));
        assertTrue(d1.isApplicable(null));

        Description d2 = new Description<>(INTEGER, () -> 9, null);
        assertTrue(d2.isApplicable("9"));
        assertFalse(d2.isApplicable("string"));
        assertTrue(d2.isApplicable(null));

        Description d3 = new Description<>(INTEGER, () -> 10, new Constraint<>(LESS_OR_EQUAL, 9));
        assertTrue(d3.isApplicable("9"));
        assertFalse(d3.isApplicable("string"));
        assertFalse(d3.isApplicable(null));
    }

}