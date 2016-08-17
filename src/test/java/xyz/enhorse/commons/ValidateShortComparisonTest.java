package xyz.enhorse.commons;

import org.junit.Test;

import static org.junit.Assert.*;
import static xyz.enhorse.commons.Validate.*;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         11.07.2016
 */
public class ValidateShortComparisonTest {


    // less


    @Test
    public void isLess_positive() throws Exception {
        short value = 1;
        short boundary = 2;
        assertEquals(value, less("test value", value, boundary));
    }


    @Test
    public void isLess_negative() throws Exception {
        short value = -2;
        short boundary = -1;
        assertEquals(value, less("test value", value, boundary));
    }


    @Test
    public void isLess_zero() throws Exception {
        short value = 0;
        short boundary = 1;
        assertEquals(value, less("test value", value, boundary));
    }


    @Test
    public void isLess_maximumBoundary() throws Exception {
        short value = 0;
        short boundary = Short.MAX_VALUE;
        assertEquals(value, less("test value", value, boundary));
    }


    @Test
    public void isLess_minimum() throws Exception {
        short value = Short.MIN_VALUE;
        short boundary = 0;
        assertEquals(value, less("test value", value, boundary));
    }


    @Test(expected = IllegalArgumentException.class)
    public void isLess_greater() throws Exception {
        short value = 1;
        short boundary = 0;
        less("test value", value, boundary);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isLess_equalsToBoundary() throws Exception {
        short value = 1;
        short boundary = 1;
        less("test value", value, boundary);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isLess_minimums() throws Exception {
        short value = Short.MIN_VALUE;
        short boundary = Short.MIN_VALUE;
        less("test value", value, boundary);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isLess_maximums() throws Exception {
        short value = Short.MAX_VALUE;
        short boundary = Short.MAX_VALUE;
        less("test value", value, boundary);
    }


    // lessOrEquals


    @Test
    public void isLessOrEquals_positive() throws Exception {
        short value = 1;
        short boundary = 2;
        assertEquals(value, lessOrEquals("test value", value, boundary));
    }


    @Test
    public void isLessOrEquals_negative() throws Exception {
        short value = -2;
        short boundary = -1;
        assertEquals(value, lessOrEquals("test value", value, boundary));
    }


    @Test
    public void isLessOrEquals_zero() throws Exception {
        short value = 0;
        short boundary = 1;
        assertEquals(value, lessOrEquals("test value", value, boundary));
    }


    @Test
    public void isLessOrEquals_maximumBoundary() throws Exception {
        short value = 0;
        short boundary = Short.MAX_VALUE;
        assertEquals(value, lessOrEquals("test value", value, boundary));
    }


    @Test
    public void isLessOrEquals_minimum() throws Exception {
        short value = Short.MIN_VALUE;
        short boundary = 0;
        assertEquals(value, lessOrEquals("test value", value, boundary));
    }


    @Test(expected = IllegalArgumentException.class)
    public void isLessOrEquals_greater() throws Exception {
        short value = 1;
        short boundary = 0;
        lessOrEquals("test value", value, boundary);
    }


    @Test
    public void isLessOrEqual_equals() throws Exception {
        short value = 1;
        short boundary = 1;
        lessOrEquals("test value", value, boundary);
    }


    @Test
    public void isLessOrEquals_equalsMinimums() throws Exception {
        short value = Short.MIN_VALUE;
        short boundary = Short.MIN_VALUE;
        greaterOrEquals("test value", value, boundary);
    }


    @Test
    public void isLessOrEquals_equalsMaximums() throws Exception {
        short value = Short.MAX_VALUE;
        short boundary = Short.MAX_VALUE;
        greaterOrEquals("test value", value, boundary);
    }


    // greater


    @Test
    public void isGreater_positive() throws Exception {
        short value = 2;
        short boundary = 1;
        assertEquals(value, greater("test value", value, boundary));
    }


    @Test
    public void isGreater_negative() throws Exception {
        short value = -1;
        short boundary = -2;
        assertEquals(value, greater("test value", value, boundary));
    }


    @Test
    public void isGreater_zero() throws Exception {
        short value = 1;
        short boundary = 0;
        assertEquals(value, greater("test value", value, boundary));
    }


    @Test
    public void isGreater_maximumBoundary() throws Exception {
        short value = 0;
        short boundary = Short.MIN_VALUE;
        assertEquals(value, greater("test value", value, boundary));
    }


    @Test
    public void isGreater_maximum() throws Exception {
        short value = Short.MAX_VALUE;
        short boundary = 0;
        assertEquals(value, greater("test value", value, boundary));
    }


    @Test(expected = IllegalArgumentException.class)
    public void isGreater_less() throws Exception {
        short value = 0;
        short boundary = 1;
        greater("test value", value, boundary);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isGreater_equals() throws Exception {
        short value = 1;
        short boundary = 1;
        greater("test value", value, boundary);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isGreater_minimums() throws Exception {
        short value = Short.MIN_VALUE;
        short boundary = Short.MIN_VALUE;
        less("test value", value, boundary);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isGreater_maximums() throws Exception {
        short value = Short.MAX_VALUE;
        short boundary = Short.MAX_VALUE;
        less("test value", value, boundary);
    }


    // greaterOrEquals


    @Test
    public void isGreaterOrEquals_positive() throws Exception {
        short value = 2;
        short boundary = 1;
        assertEquals(value, greaterOrEquals("test value", value, boundary));
    }


    @Test
    public void isGreaterOrEquals_negative() throws Exception {
        short value = -1;
        short boundary = -2;
        assertEquals(value, greaterOrEquals("test value", value, boundary));
    }


    @Test
    public void isGreaterOrEquals_zero() throws Exception {
        short value = 1;
        short boundary = 0;
        assertEquals(value, greaterOrEquals("test value", value, boundary));
    }


    @Test
    public void isGreaterOrEquals_minimumBoundary() throws Exception {
        short value = 0;
        short boundary = Short.MIN_VALUE;
        assertEquals(value, greaterOrEquals("test value", value, boundary));
    }


    @Test
    public void isGreaterOrEquals_maximum() throws Exception {
        short value = Short.MAX_VALUE;
        short boundary = 0;
        assertEquals(value, greaterOrEquals("test value", value, boundary));
    }


    @Test(expected = IllegalArgumentException.class)
    public void isGreaterOrEquals_greater() throws Exception {
        short value = 0;
        short boundary = 1;
        greaterOrEquals("test value", value, boundary);
    }


    @Test
    public void isGreaterOrEquals_equals() throws Exception {
        short value = 1;
        short boundary = 1;
        greaterOrEquals("test value", value, boundary);
    }


    @Test
    public void isGreaterOrEquals_minimums() throws Exception {
        short value = Short.MIN_VALUE;
        short boundary = Short.MIN_VALUE;
        greaterOrEquals("test value", value, boundary);
    }


    @Test
    public void isGreaterOrEquals_maximums() throws Exception {
        short value = Short.MAX_VALUE;
        short boundary = Short.MAX_VALUE;
        greaterOrEquals("test value", value, boundary);
    }


    // inRangeExclusive


    @Test
    public void isBetween_positive() throws Exception {
        short value = 2;
        short min = 1;
        short max = 3;
        assertEquals(value, inRangeExclusive("test value", value, min, max));
    }


    @Test
    public void isBetween_negative() throws Exception {
        short value = -2;
        short min = -3;
        short max = -1;
        assertEquals(value, inRangeExclusive("test value", value, min, max));
    }


    @Test
    public void isBetween_zero() throws Exception {
        short value = 0;
        short min = -1;
        short max = 1;
        assertEquals(value, inRangeExclusive("test value", value, min, max));
    }


    @Test
    public void isBetween_minIsZero() throws Exception {
        short value = 1;
        short min = 0;
        short max = 2;
        assertEquals(value, inRangeExclusive("test value", value, min, max));
    }


    @Test
    public void isBetween_maxIsZero() throws Exception {
        short value = -1;
        short min = -2;
        short max = 0;
        assertEquals(value, inRangeExclusive("test value", value, min, max));
    }


    @Test
    public void isBetween_cornerBoundaries() throws Exception {
        short value = 0;
        short min = Short.MIN_VALUE;
        short max = Short.MAX_VALUE;
        assertEquals(value, inRangeExclusive("test value", value, min, max));
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_greaterThanMax() throws Exception {
        short value = 3;
        short min = 1;
        short max = 2;
        inRangeExclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_equalMax() throws Exception {
        short value = 2;
        short min = 1;
        short max = 2;
        inRangeExclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_lessThanMin() throws Exception {
        short value = 3;
        short min = 1;
        short max = 2;
        inRangeExclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_equalMin() throws Exception {
        short value = 1;
        short min = 1;
        short max = 2;
        inRangeExclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_equalMinimalMin() throws Exception {
        short value = Short.MIN_VALUE;
        short min = Short.MIN_VALUE;
        short max = 0;
        inRangeExclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_equalMaximalMax() throws Exception {
        short value = Short.MAX_VALUE;
        short min = 0;
        short max = Short.MAX_VALUE;
        inRangeExclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_maxEqualsMin() throws Exception {
        short value = 0;
        short min = 1;
        short max = 1;
        inRangeExclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_maxLessThanMin() throws Exception {
        short value = 0;
        short min = 2;
        short max = 1;
        inRangeExclusive("test value", value, min, max);
    }

    // inRangeInclusive


    @Test
    public void isBetweenOrEquals_positive() throws Exception {
        short value = 2;
        short min = 1;
        short max = 3;
        assertEquals(value, inRangeInclusive("test value", value, min, max));
    }


    @Test
    public void isBetweenOrEquals_negative() throws Exception {
        short value = -2;
        short min = -3;
        short max = -1;
        assertEquals(value, inRangeInclusive("test value", value, min, max));
    }


    @Test
    public void isBetweenOrEquals_zero() throws Exception {
        short value = 0;
        short min = -1;
        short max = 1;
        assertEquals(value, inRangeInclusive("test value", value, min, max));
    }


    @Test
    public void isBetweenOrEquals_minIsZero() throws Exception {
        short value = 1;
        short min = 0;
        short max = 2;
        assertEquals(value, inRangeInclusive("test value", value, min, max));
    }


    @Test
    public void isBetweenOrEquals_maxIsZero() throws Exception {
        short value = -1;
        short min = -2;
        short max = 0;
        assertEquals(value, inRangeInclusive("test value", value, min, max));
    }


    @Test
    public void isBetweenOrEquals_cornerBoundaries() throws Exception {
        short value = 0;
        short min = Short.MIN_VALUE;
        short max = Short.MAX_VALUE;
        assertEquals(value, inRangeInclusive("test value", value, min, max));
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetweenOrEquals_greaterThanMax() throws Exception {
        short value = 3;
        short min = 1;
        short max = 2;
        inRangeInclusive("test value", value, min, max);
    }


    @Test
    public void isBetweenOrEquals_equalsMax() throws Exception {
        short value = 2;
        short min = 1;
        short max = 2;
        inRangeInclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetweenOrEquals_lessThanMin() throws Exception {
        short value = 3;
        short min = 1;
        short max = 2;
        inRangeInclusive("test value", value, min, max);
    }


    @Test
    public void isBetweenOrEquals_equalsMin() throws Exception {
        short value = 1;
        short min = 1;
        short max = 2;
        inRangeInclusive("test value", value, min, max);
    }


    @Test
    public void isBetweenOrEquals_equalsMinimalMin() throws Exception {
        short value = Short.MIN_VALUE;
        short min = Short.MIN_VALUE;
        short max = 0;
        inRangeInclusive("test value", value, min, max);
    }


    @Test
    public void isBetweenOrEquals_equalsMaximalMax() throws Exception {
        short value = Short.MAX_VALUE;
        short min = 0;
        short max = Short.MAX_VALUE;
        inRangeInclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetweenOrEquals_maxEqualsMin() throws Exception {
        short value = 0;
        short min = 1;
        short max = 1;
        inRangeInclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetweenOrEquals_maxLessThanMin() throws Exception {
        short value = 0;
        short min = 2;
        short max = 1;
        inRangeInclusive("test value", value, min, max);
    }


    @Test
    public void isBetweenOrEquals_equalsBoundaries() throws Exception {
        short value = 1;
        short min = 1;
        short max = 1;
        inRangeInclusive("test value", value, min, max);
    }
}