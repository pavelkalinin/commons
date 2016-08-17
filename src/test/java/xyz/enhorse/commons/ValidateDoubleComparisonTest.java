package xyz.enhorse.commons;

import org.junit.Test;

import static org.junit.Assert.*;
import static xyz.enhorse.commons.Validate.*;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         11.07.2016
 */
public class ValidateDoubleComparisonTest {
    private static final double PRECISION = 0.00001;

    // less


    @Test
    public void isLess_positive() throws Exception {
        double value = 1;
        double boundary = 2;
        assertEquals(value, less("test value", value, boundary), PRECISION);
    }


    @Test
    public void isLess_negative() throws Exception {
        double value = -2;
        double boundary = -1;
        assertEquals(value, less("test value", value, boundary), PRECISION);
    }


    @Test
    public void isLess_zero() throws Exception {
        double value = 0;
        double boundary = 1;
        assertEquals(value, less("test value", value, boundary), PRECISION);
    }


    @Test
    public void isLess_maximumBoundary() throws Exception {
        double value = 0;
        double boundary = Double.MAX_VALUE;
        assertEquals(value, less("test value", value, boundary), PRECISION);
    }


    @Test
    public void isLess_minimum() throws Exception {
        double value = Double.MAX_VALUE * -1;
        double boundary = 0;
        assertEquals(value, less("test value", value, boundary), PRECISION);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isLess_greater() throws Exception {
        double value = 1;
        double boundary = 0;
        less("test value", value, boundary);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isLess_equalsToBoundary() throws Exception {
        double value = 1;
        double boundary = 1;
        less("test value", value, boundary);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isLess_minimums() throws Exception {
        double value = Double.MIN_VALUE;
        double boundary = Double.MIN_VALUE;
        less("test value", value, boundary);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isLess_maximums() throws Exception {
        double value = Double.MAX_VALUE;
        double boundary = Double.MAX_VALUE;
        less("test value", value, boundary);
    }


    // lessOrEquals


    @Test
    public void isLessOrEquals_positive() throws Exception {
        double value = 1;
        double boundary = 2;
        assertEquals(value, lessOrEquals("test value", value, boundary), PRECISION);
    }


    @Test
    public void isLessOrEquals_negative() throws Exception {
        double value = -2;
        double boundary = -1;
        assertEquals(value, lessOrEquals("test value", value, boundary), PRECISION);
    }


    @Test
    public void isLessOrEquals_zero() throws Exception {
        double value = 0;
        double boundary = 1;
        assertEquals(value, lessOrEquals("test value", value, boundary), PRECISION);
    }


    @Test
    public void isLessOrEquals_maximumBoundary() throws Exception {
        double value = 0;
        double boundary = Double.MAX_VALUE;
        assertEquals(value, lessOrEquals("test value", value, boundary), PRECISION);
    }


    @Test
    public void isLessOrEquals_minimum() throws Exception {
        double value = Double.MAX_VALUE * -1;
        double boundary = 0;
        assertEquals(value, lessOrEquals("test value", value, boundary), PRECISION);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isLessOrEquals_greater() throws Exception {
        double value = 1;
        double boundary = 0;
        lessOrEquals("test value", value, boundary);
    }


    @Test
    public void isLessOrEqual_equals() throws Exception {
        double value = 1;
        double boundary = 1;
        lessOrEquals("test value", value, boundary);
    }


    @Test
    public void isLessOrEquals_equalsMinimums() throws Exception {
        double value = Double.MIN_VALUE;
        double boundary = Double.MIN_VALUE;
        greaterOrEquals("test value", value, boundary);
    }


    @Test
    public void isLessOrEquals_equalsMaximums() throws Exception {
        double value = Double.MAX_VALUE;
        double boundary = Double.MAX_VALUE;
        greaterOrEquals("test value", value, boundary);
    }


    // greater


    @Test
    public void isGreater_positive() throws Exception {
        double value = 2;
        double boundary = 1;
        assertEquals(value, greater("test value", value, boundary), PRECISION);
    }


    @Test
    public void isGreater_negative() throws Exception {
        double value = -1;
        double boundary = -2;
        assertEquals(value, greater("test value", value, boundary), PRECISION);
    }


    @Test
    public void isGreater_zero() throws Exception {
        double value = 1;
        double boundary = 0;
        assertEquals(value, greater("test value", value, boundary), PRECISION);
    }


    @Test
    public void isGreater_minimumBoundary() throws Exception {
        double value = 0;
        double boundary = Double.MAX_VALUE * -1;
        assertEquals(value, greater("test value", value, boundary), PRECISION);
    }


    @Test
    public void isGreater_maximum() throws Exception {
        double value = Double.MAX_VALUE;
        double boundary = 0;
        assertEquals(value, greater("test value", value, boundary), PRECISION);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isGreater_less() throws Exception {
        double value = 0;
        double boundary = 1;
        greater("test value", value, boundary);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isGreater_equals() throws Exception {
        double value = 1;
        double boundary = 1;
        greater("test value", value, boundary);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isGreater_minimums() throws Exception {
        double value = Double.MIN_VALUE;
        double boundary = Double.MIN_VALUE;
        less("test value", value, boundary);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isGreater_maximums() throws Exception {
        double value = Double.MAX_VALUE;
        double boundary = Double.MAX_VALUE;
        less("test value", value, boundary);
    }


    // greaterOrEquals


    @Test
    public void isGreaterOrEquals_positive() throws Exception {
        double value = 2;
        double boundary = 1;
        assertEquals(value, greaterOrEquals("test value", value, boundary), PRECISION);
    }


    @Test
    public void isGreaterOrEquals_negative() throws Exception {
        double value = -1;
        double boundary = -2;
        assertEquals(value, greaterOrEquals("test value", value, boundary), PRECISION);
    }


    @Test
    public void isGreaterOrEquals_zero() throws Exception {
        double value = 1;
        double boundary = 0;
        assertEquals(value, greaterOrEquals("test value", value, boundary), PRECISION);
    }


    @Test
    public void isGreaterOrEquals_minimumBoundary() throws Exception {
        double value = 0;
        double boundary = Double.MAX_VALUE * -1;
        assertEquals(value, greaterOrEquals("test value", value, boundary), PRECISION);
    }


    @Test
    public void isGreaterOrEquals_maximum() throws Exception {
        double value = Double.MAX_VALUE;
        double boundary = 0;
        assertEquals(value, greaterOrEquals("test value", value, boundary), PRECISION);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isGreaterOrEquals_greater() throws Exception {
        double value = 0;
        double boundary = 1;
        greaterOrEquals("test value", value, boundary);
    }


    @Test
    public void isGreaterOrEquals_equals() throws Exception {
        double value = 1;
        double boundary = 1;
        greaterOrEquals("test value", value, boundary);
    }


    @Test
    public void isGreaterOrEquals_minimums() throws Exception {
        double value = Double.MIN_VALUE;
        double boundary = Double.MIN_VALUE;
        greaterOrEquals("test value", value, boundary);
    }


    @Test
    public void isGreaterOrEquals_maximums() throws Exception {
        double value = Double.MAX_VALUE;
        double boundary = Double.MAX_VALUE;
        greaterOrEquals("test value", value, boundary);
    }


    // inRangeExclusive


    @Test
    public void isBetween_positive() throws Exception {
        double value = 2;
        double min = 1;
        double max = 3;
        assertEquals(value, inRangeExclusive("test value", value, min, max), PRECISION);
    }


    @Test
    public void isBetween_negative() throws Exception {
        double value = -2;
        double min = -3;
        double max = -1;
        assertEquals(value, inRangeExclusive("test value", value, min, max), PRECISION);
    }


    @Test
    public void isBetween_zero() throws Exception {
        double value = 0;
        double min = -1;
        double max = 1;
        assertEquals(value, inRangeExclusive("test value", value, min, max), PRECISION);
    }


    @Test
    public void isBetween_minIsZero() throws Exception {
        double value = 1;
        double min = 0;
        double max = 2;
        assertEquals(value, inRangeExclusive("test value", value, min, max), PRECISION);
    }


    @Test
    public void isBetween_maxIsZero() throws Exception {
        double value = -1;
        double min = -2;
        double max = 0;
        assertEquals(value, inRangeExclusive("test value", value, min, max), PRECISION);
    }


    @Test
    public void isBetween_cornerBoundaries() throws Exception {
        double value = 0;
        double min = Double.MAX_VALUE * -1 ;
        double max = Double.MAX_VALUE;
        assertEquals(value, inRangeExclusive("test value", value, min, max), PRECISION);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_greaterThanMax() throws Exception {
        double value = 3;
        double min = 1;
        double max = 2;
        inRangeExclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_equalMax() throws Exception {
        double value = 2;
        double min = 1;
        double max = 2;
        inRangeExclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_lessThanMin() throws Exception {
        double value = 3;
        double min = 1;
        double max = 2;
        inRangeExclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_equalMin() throws Exception {
        double value = 1;
        double min = 1;
        double max = 2;
        inRangeExclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_equalMinimalMin() throws Exception {
        double value = Double.MIN_VALUE;
        double min = Double.MIN_VALUE;
        double max = 0;
        inRangeExclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_equalMaximalMax() throws Exception {
        double value = Double.MAX_VALUE;
        double min = 0;
        double max = Double.MAX_VALUE;
        inRangeExclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_maxEqualsMin() throws Exception {
        double value = 0;
        double min = 1;
        double max = 1;
        inRangeExclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_maxLessThanMin() throws Exception {
        double value = 0;
        double min = 2;
        double max = 1;
        inRangeExclusive("test value", value, min, max);
    }

    // inRangeInclusive


    @Test
    public void isBetweenOrEquals_positive() throws Exception {
        double value = 2;
        double min = 1;
        double max = 3;
        assertEquals(value, inRangeInclusive("test value", value, min, max), PRECISION);
    }


    @Test
    public void isBetweenOrEquals_negative() throws Exception {
        double value = -2;
        double min = -3;
        double max = -1;
        assertEquals(value, inRangeInclusive("test value", value, min, max), PRECISION);
    }


    @Test
    public void isBetweenOrEquals_zero() throws Exception {
        double value = 0;
        double min = -1;
        double max = 1;
        assertEquals(value, inRangeInclusive("test value", value, min, max), PRECISION);
    }


    @Test
    public void isBetweenOrEquals_minIsZero() throws Exception {
        double value = 1;
        double min = 0;
        double max = 2;
        assertEquals(value, inRangeInclusive("test value", value, min, max), PRECISION);
    }


    @Test
    public void isBetweenOrEquals_maxIsZero() throws Exception {
        double value = -1;
        double min = -2;
        double max = 0;
        assertEquals(value, inRangeInclusive("test value", value, min, max), PRECISION);
    }


    @Test
    public void isBetweenOrEquals_cornerBoundaries() throws Exception {
        double value = 0;
        double min = Double.MAX_VALUE * -1;
        double max = Double.MAX_VALUE;
        assertEquals(value, inRangeInclusive("test value", value, min, max), PRECISION);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetweenOrEquals_greaterThanMax() throws Exception {
        double value = 3;
        double min = 1;
        double max = 2;
        inRangeInclusive("test value", value, min, max);
    }


    @Test
    public void isBetweenOrEquals_equalsMax() throws Exception {
        double value = 2;
        double min = 1;
        double max = 2;
        inRangeInclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetweenOrEquals_lessThanMin() throws Exception {
        double value = 3;
        double min = 1;
        double max = 2;
        inRangeInclusive("test value", value, min, max);
    }


    @Test
    public void isBetweenOrEquals_equalsMin() throws Exception {
        double value = 1;
        double min = 1;
        double max = 2;
        inRangeInclusive("test value", value, min, max);
    }


    @Test
    public void isBetweenOrEquals_equalsMinimalMin() throws Exception {
        double value = Double.MAX_VALUE * -1;
        double min = Double.MAX_VALUE * -1;
        double max = 0;
        inRangeInclusive("test value", value, min, max);
    }


    @Test
    public void isBetweenOrEquals_equalsMaximalMax() throws Exception {
        double value = Double.MAX_VALUE;
        double min = 0;
        double max = Double.MAX_VALUE;
        inRangeInclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetweenOrEquals_maxEqualsMin() throws Exception {
        double value = 0;
        double min = 1;
        double max = 1;
        inRangeInclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetweenOrEquals_maxLessThanMin() throws Exception {
        double value = 0;
        double min = 2;
        double max = 1;
        inRangeInclusive("test value", value, min, max);
    }


    @Test
    public void isBetweenOrEquals_equalsBoundaries() throws Exception {
        double value = 1;
        double min = 1;
        double max = 1;
        inRangeInclusive("test value", value, min, max);
    }
}