package xyz.enhorse.commons;

import org.junit.Test;

import static org.junit.Assert.*;
import static xyz.enhorse.commons.Validate.*;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         11.07.2016
 */
public class ValidateFloatComparisonTest {
    private static final double PRECISION = 0.00001;

    // less


    @Test
    public void isLess_positive() throws Exception {
        float value = 1;
        float boundary = 2;
        assertEquals(value, less("test value", value, boundary), PRECISION);
    }


    @Test
    public void isLess_negative() throws Exception {
        float value = -2;
        float boundary = -1;
        assertEquals(value, less("test value", value, boundary), PRECISION);
    }


    @Test
    public void isLess_zero() throws Exception {
        float value = 0;
        float boundary = 1;
        assertEquals(value, less("test value", value, boundary), PRECISION);
    }


    @Test
    public void isLess_maximumBoundary() throws Exception {
        float value = 0;
        float boundary = Float.MAX_VALUE;
        assertEquals(value, less("test value", value, boundary), PRECISION);
    }


    @Test
    public void isLess_minimum() throws Exception {
        float value = Float.MAX_VALUE * -1;
        float boundary = 0;
        assertEquals(value, less("test value", value, boundary), PRECISION);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isLess_greater() throws Exception {
        float value = 1;
        float boundary = 0;
        less("test value", value, boundary);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isLess_equalsToBoundary() throws Exception {
        float value = 1;
        float boundary = 1;
        less("test value", value, boundary);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isLess_minimums() throws Exception {
        float value = Float.MIN_VALUE;
        float boundary = Float.MIN_VALUE;
        less("test value", value, boundary);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isLess_maximums() throws Exception {
        float value = Float.MAX_VALUE;
        float boundary = Float.MAX_VALUE;
        less("test value", value, boundary);
    }


    // lessOrEquals


    @Test
    public void isLessOrEquals_positive() throws Exception {
        float value = 1;
        float boundary = 2;
        assertEquals(value, lessOrEquals("test value", value, boundary), PRECISION);
    }


    @Test
    public void isLessOrEquals_negative() throws Exception {
        float value = -2;
        float boundary = -1;
        assertEquals(value, lessOrEquals("test value", value, boundary), PRECISION);
    }


    @Test
    public void isLessOrEquals_zero() throws Exception {
        float value = 0;
        float boundary = 1;
        assertEquals(value, lessOrEquals("test value", value, boundary), PRECISION);
    }


    @Test
    public void isLessOrEquals_maximumBoundary() throws Exception {
        float value = 0;
        float boundary = Float.MAX_VALUE;
        assertEquals(value, lessOrEquals("test value", value, boundary), PRECISION);
    }


    @Test
    public void isLessOrEquals_minimum() throws Exception {
        float value = Float.MAX_VALUE * -1;
        float boundary = 0;
        assertEquals(value, lessOrEquals("test value", value, boundary), PRECISION);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isLessOrEquals_greater() throws Exception {
        float value = 1;
        float boundary = 0;
        lessOrEquals("test value", value, boundary);
    }


    @Test
    public void isLessOrEqual_equals() throws Exception {
        float value = 1;
        float boundary = 1;
        lessOrEquals("test value", value, boundary);
    }


    @Test
    public void isLessOrEquals_equalsMinimums() throws Exception {
        float value = Float.MIN_VALUE;
        float boundary = Float.MIN_VALUE;
        greaterOrEquals("test value", value, boundary);
    }


    @Test
    public void isLessOrEquals_equalsMaximums() throws Exception {
        float value = Float.MAX_VALUE;
        float boundary = Float.MAX_VALUE;
        greaterOrEquals("test value", value, boundary);
    }


    // greater


    @Test
    public void isGreater_positive() throws Exception {
        float value = 2;
        float boundary = 1;
        assertEquals(value, greater("test value", value, boundary), PRECISION);
    }


    @Test
    public void isGreater_negative() throws Exception {
        float value = -1;
        float boundary = -2;
        assertEquals(value, greater("test value", value, boundary), PRECISION);
    }


    @Test
    public void isGreater_zero() throws Exception {
        float value = 1;
        float boundary = 0;
        assertEquals(value, greater("test value", value, boundary), PRECISION);
    }


    @Test
    public void isGreater_minimumBoundary() throws Exception {
        float value = 0;
        float boundary = Float.MAX_VALUE * -1;
        assertEquals(value, greater("test value", value, boundary), PRECISION);
    }


    @Test
    public void isGreater_maximum() throws Exception {
        float value = Float.MAX_VALUE;
        float boundary = 0;
        assertEquals(value, greater("test value", value, boundary), PRECISION);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isGreater_less() throws Exception {
        float value = 0;
        float boundary = 1;
        greater("test value", value, boundary);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isGreater_equals() throws Exception {
        float value = 1;
        float boundary = 1;
        greater("test value", value, boundary);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isGreater_minimums() throws Exception {
        float value = Float.MIN_VALUE;
        float boundary = Float.MIN_VALUE;
        less("test value", value, boundary);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isGreater_maximums() throws Exception {
        float value = Float.MAX_VALUE;
        float boundary = Float.MAX_VALUE;
        less("test value", value, boundary);
    }


    // greaterOrEquals


    @Test
    public void isGreaterOrEquals_positive() throws Exception {
        float value = 2;
        float boundary = 1;
        assertEquals(value, greaterOrEquals("test value", value, boundary), PRECISION);
    }


    @Test
    public void isGreaterOrEquals_negative() throws Exception {
        float value = -1;
        float boundary = -2;
        assertEquals(value, greaterOrEquals("test value", value, boundary), PRECISION);
    }


    @Test
    public void isGreaterOrEquals_zero() throws Exception {
        float value = 1;
        float boundary = 0;
        assertEquals(value, greaterOrEquals("test value", value, boundary), PRECISION);
    }


    @Test
    public void isGreaterOrEquals_minimumBoundary() throws Exception {
        float value = 0;
        float boundary = Float.MAX_VALUE * -1;
        assertEquals(value, greaterOrEquals("test value", value, boundary), PRECISION);
    }


    @Test
    public void isGreaterOrEquals_maximum() throws Exception {
        float value = Float.MAX_VALUE;
        float boundary = 0;
        assertEquals(value, greaterOrEquals("test value", value, boundary), PRECISION);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isGreaterOrEquals_greater() throws Exception {
        float value = 0;
        float boundary = 1;
        greaterOrEquals("test value", value, boundary);
    }


    @Test
    public void isGreaterOrEquals_equals() throws Exception {
        float value = 1;
        float boundary = 1;
        greaterOrEquals("test value", value, boundary);
    }


    @Test
    public void isGreaterOrEquals_minimums() throws Exception {
        float value = Float.MIN_VALUE;
        float boundary = Float.MIN_VALUE;
        greaterOrEquals("test value", value, boundary);
    }


    @Test
    public void isGreaterOrEquals_maximums() throws Exception {
        float value = Float.MAX_VALUE;
        float boundary = Float.MAX_VALUE;
        greaterOrEquals("test value", value, boundary);
    }


    // inRangeExclusive


    @Test
    public void isBetween_positive() throws Exception {
        float value = 2;
        float min = 1;
        float max = 3;
        assertEquals(value, inRangeExclusive("test value", value, min, max), PRECISION);
    }


    @Test
    public void isBetween_negative() throws Exception {
        float value = -2;
        float min = -3;
        float max = -1;
        assertEquals(value, inRangeExclusive("test value", value, min, max), PRECISION);
    }


    @Test
    public void isBetween_zero() throws Exception {
        float value = 0;
        float min = -1;
        float max = 1;
        assertEquals(value, inRangeExclusive("test value", value, min, max), PRECISION);
    }


    @Test
    public void isBetween_minIsZero() throws Exception {
        float value = 1;
        float min = 0;
        float max = 2;
        assertEquals(value, inRangeExclusive("test value", value, min, max), PRECISION);
    }


    @Test
    public void isBetween_maxIsZero() throws Exception {
        float value = -1;
        float min = -2;
        float max = 0;
        assertEquals(value, inRangeExclusive("test value", value, min, max), PRECISION);
    }


    @Test
    public void isBetween_cornerBoundaries() throws Exception {
        float value = 0;
        float min = Float.MAX_VALUE * -1 ;
        float max = Float.MAX_VALUE;
        assertEquals(value, inRangeExclusive("test value", value, min, max), PRECISION);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_greaterThanMax() throws Exception {
        float value = 3;
        float min = 1;
        float max = 2;
        inRangeExclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_equalMax() throws Exception {
        float value = 2;
        float min = 1;
        float max = 2;
        inRangeExclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_lessThanMin() throws Exception {
        float value = 3;
        float min = 1;
        float max = 2;
        inRangeExclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_equalMin() throws Exception {
        float value = 1;
        float min = 1;
        float max = 2;
        inRangeExclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_equalMinimalMin() throws Exception {
        float value = Float.MIN_VALUE;
        float min = Float.MIN_VALUE;
        float max = 0;
        inRangeExclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_equalMaximalMax() throws Exception {
        float value = Float.MAX_VALUE;
        float min = 0;
        float max = Float.MAX_VALUE;
        inRangeExclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_maxEqualsMin() throws Exception {
        float value = 0;
        float min = 1;
        float max = 1;
        inRangeExclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_maxLessThanMin() throws Exception {
        float value = 0;
        float min = 2;
        float max = 1;
        inRangeExclusive("test value", value, min, max);
    }

    // inRangeInclusive


    @Test
    public void isBetweenOrEquals_positive() throws Exception {
        float value = 2;
        float min = 1;
        float max = 3;
        assertEquals(value, inRangeInclusive("test value", value, min, max), PRECISION);
    }


    @Test
    public void isBetweenOrEquals_negative() throws Exception {
        float value = -2;
        float min = -3;
        float max = -1;
        assertEquals(value, inRangeInclusive("test value", value, min, max), PRECISION);
    }


    @Test
    public void isBetweenOrEquals_zero() throws Exception {
        float value = 0;
        float min = -1;
        float max = 1;
        assertEquals(value, inRangeInclusive("test value", value, min, max), PRECISION);
    }


    @Test
    public void isBetweenOrEquals_minIsZero() throws Exception {
        float value = 1;
        float min = 0;
        float max = 2;
        assertEquals(value, inRangeInclusive("test value", value, min, max), PRECISION);
    }


    @Test
    public void isBetweenOrEquals_maxIsZero() throws Exception {
        float value = -1;
        float min = -2;
        float max = 0;
        assertEquals(value, inRangeInclusive("test value", value, min, max), PRECISION);
    }


    @Test
    public void isBetweenOrEquals_cornerBoundaries() throws Exception {
        float value = 0;
        float min = Float.MAX_VALUE * -1;
        float max = Float.MAX_VALUE;
        assertEquals(value, inRangeInclusive("test value", value, min, max), PRECISION);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetweenOrEquals_greaterThanMax() throws Exception {
        float value = 3;
        float min = 1;
        float max = 2;
        inRangeInclusive("test value", value, min, max);
    }


    @Test
    public void isBetweenOrEquals_equalsMax() throws Exception {
        float value = 2;
        float min = 1;
        float max = 2;
        inRangeInclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetweenOrEquals_lessThanMin() throws Exception {
        float value = 3;
        float min = 1;
        float max = 2;
        inRangeInclusive("test value", value, min, max);
    }


    @Test
    public void isBetweenOrEquals_equalsMin() throws Exception {
        float value = 1;
        float min = 1;
        float max = 2;
        inRangeInclusive("test value", value, min, max);
    }


    @Test
    public void isBetweenOrEquals_equalsMinimalMin() throws Exception {
        float value = Float.MAX_VALUE * -1;
        float min = Float.MAX_VALUE * -1;
        float max = 0;
        inRangeInclusive("test value", value, min, max);
    }


    @Test
    public void isBetweenOrEquals_equalsMaximalMax() throws Exception {
        float value = Float.MAX_VALUE;
        float min = 0;
        float max = Float.MAX_VALUE;
        inRangeInclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetweenOrEquals_maxEqualsMin() throws Exception {
        float value = 0;
        float min = 1;
        float max = 1;
        inRangeInclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetweenOrEquals_maxLessThanMin() throws Exception {
        float value = 0;
        float min = 2;
        float max = 1;
        inRangeInclusive("test value", value, min, max);
    }


    @Test
    public void isBetweenOrEquals_equalsBoundaries() throws Exception {
        float value = 1;
        float min = 1;
        float max = 1;
        inRangeInclusive("test value", value, min, max);
    }
}