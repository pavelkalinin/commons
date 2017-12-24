package xyz.enhorse.commons;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static xyz.enhorse.commons.Validate.greater;
import static xyz.enhorse.commons.Validate.greaterOrEquals;
import static xyz.enhorse.commons.Validate.inRangeExclusive;
import static xyz.enhorse.commons.Validate.inRangeInclusive;
import static xyz.enhorse.commons.Validate.less;
import static xyz.enhorse.commons.Validate.lessOrEquals;
import static xyz.enhorse.commons.Validate.maximumIfGreater;
import static xyz.enhorse.commons.Validate.minimumIfLess;

/**
 * Tests for {@link Validate}
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 * 11.07.2016
 */
@SuppressWarnings("ResultOfMethodCallIgnored")
public class ValidateShortComparisonTest {


    // less


    @Test
    public void isLess_positive() {
        short value = 1;
        short boundary = 2;
        assertEquals(value, less("test value", value, boundary));
    }


    @Test
    public void isLess_negative() {
        short value = -2;
        short boundary = -1;
        assertEquals(value, less("test value", value, boundary));
    }


    @Test
    public void isLess_zero() {
        short value = 0;
        short boundary = 1;
        assertEquals(value, less("test value", value, boundary));
    }


    @Test
    public void isLess_maximumBoundary() {
        short value = 0;
        short boundary = Short.MAX_VALUE;
        assertEquals(value, less("test value", value, boundary));
    }


    @Test
    public void isLess_minimum() {
        short value = Short.MIN_VALUE;
        short boundary = 0;
        assertEquals(value, less("test value", value, boundary));
    }


    @Test(expected = IllegalArgumentException.class)
    public void isLess_greater() {
        short value = 1;
        short boundary = 0;
        less("test value", value, boundary);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isLess_equalsToBoundary() {
        short value = 1;
        short boundary = 1;
        less("test value", value, boundary);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isLess_minimums() {
        short value = Short.MIN_VALUE;
        short boundary = Short.MIN_VALUE;
        less("test value", value, boundary);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isLess_maximums() {
        short value = Short.MAX_VALUE;
        short boundary = Short.MAX_VALUE;
        less("test value", value, boundary);
    }


    // lessOrEquals


    @Test
    public void isLessOrEquals_positive() {
        short value = 1;
        short boundary = 2;
        assertEquals(value, lessOrEquals("test value", value, boundary));
    }


    @Test
    public void isLessOrEquals_negative() {
        short value = -2;
        short boundary = -1;
        assertEquals(value, lessOrEquals("test value", value, boundary));
    }


    @Test
    public void isLessOrEquals_zero() {
        short value = 0;
        short boundary = 1;
        assertEquals(value, lessOrEquals("test value", value, boundary));
    }


    @Test
    public void isLessOrEquals_maximumBoundary() {
        short value = 0;
        short boundary = Short.MAX_VALUE;
        assertEquals(value, lessOrEquals("test value", value, boundary));
    }


    @Test
    public void isLessOrEquals_minimum() {
        short value = Short.MIN_VALUE;
        short boundary = 0;
        assertEquals(value, lessOrEquals("test value", value, boundary));
    }


    @Test(expected = IllegalArgumentException.class)
    public void isLessOrEquals_greater() {
        short value = 1;
        short boundary = 0;
        lessOrEquals("test value", value, boundary);
    }


    @Test
    public void isLessOrEqual_equals() {
        short value = 1;
        short boundary = 1;
        lessOrEquals("test value", value, boundary);
    }


    @Test
    public void isLessOrEquals_equalsMinimums() {
        short value = Short.MIN_VALUE;
        short boundary = Short.MIN_VALUE;
        greaterOrEquals("test value", value, boundary);
    }


    @Test
    public void isLessOrEquals_equalsMaximums() {
        short value = Short.MAX_VALUE;
        short boundary = Short.MAX_VALUE;
        greaterOrEquals("test value", value, boundary);
    }


    // greater


    @Test
    public void isGreater_positive() {
        short value = 2;
        short boundary = 1;
        assertEquals(value, greater("test value", value, boundary));
    }


    @Test
    public void isGreater_negative() {
        short value = -1;
        short boundary = -2;
        assertEquals(value, greater("test value", value, boundary));
    }


    @Test
    public void isGreater_zero() {
        short value = 1;
        short boundary = 0;
        assertEquals(value, greater("test value", value, boundary));
    }


    @Test
    public void isGreater_maximumBoundary() {
        short value = 0;
        short boundary = Short.MIN_VALUE;
        assertEquals(value, greater("test value", value, boundary));
    }


    @Test
    public void isGreater_maximum() {
        short value = Short.MAX_VALUE;
        short boundary = 0;
        assertEquals(value, greater("test value", value, boundary));
    }


    @Test(expected = IllegalArgumentException.class)
    public void isGreater_less() {
        short value = 0;
        short boundary = 1;
        greater("test value", value, boundary);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isGreater_equals() {
        short value = 1;
        short boundary = 1;
        greater("test value", value, boundary);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isGreater_minimums() {
        short value = Short.MIN_VALUE;
        short boundary = Short.MIN_VALUE;
        less("test value", value, boundary);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isGreater_maximums() {
        short value = Short.MAX_VALUE;
        short boundary = Short.MAX_VALUE;
        less("test value", value, boundary);
    }


    // greaterOrEquals


    @Test
    public void isGreaterOrEquals_positive() {
        short value = 2;
        short boundary = 1;
        assertEquals(value, greaterOrEquals("test value", value, boundary));
    }


    @Test
    public void isGreaterOrEquals_negative() {
        short value = -1;
        short boundary = -2;
        assertEquals(value, greaterOrEquals("test value", value, boundary));
    }


    @Test
    public void isGreaterOrEquals_zero() {
        short value = 1;
        short boundary = 0;
        assertEquals(value, greaterOrEquals("test value", value, boundary));
    }


    @Test
    public void isGreaterOrEquals_minimumBoundary() {
        short value = 0;
        short boundary = Short.MIN_VALUE;
        assertEquals(value, greaterOrEquals("test value", value, boundary));
    }


    @Test
    public void isGreaterOrEquals_maximum() {
        short value = Short.MAX_VALUE;
        short boundary = 0;
        assertEquals(value, greaterOrEquals("test value", value, boundary));
    }


    @Test(expected = IllegalArgumentException.class)
    public void isGreaterOrEquals_greater() {
        short value = 0;
        short boundary = 1;
        greaterOrEquals("test value", value, boundary);
    }


    @Test
    public void isGreaterOrEquals_equals() {
        short value = 1;
        short boundary = 1;
        greaterOrEquals("test value", value, boundary);
    }


    @Test
    public void isGreaterOrEquals_minimums() {
        short value = Short.MIN_VALUE;
        short boundary = Short.MIN_VALUE;
        greaterOrEquals("test value", value, boundary);
    }


    @Test
    public void isGreaterOrEquals_maximums() {
        short value = Short.MAX_VALUE;
        short boundary = Short.MAX_VALUE;
        greaterOrEquals("test value", value, boundary);
    }


    // inRangeExclusive


    @Test
    public void isBetween_positive() {
        short value = 2;
        short min = 1;
        short max = 3;
        assertEquals(value, inRangeExclusive("test value", value, min, max));
    }


    @Test
    public void isBetween_negative() {
        short value = -2;
        short min = -3;
        short max = -1;
        assertEquals(value, inRangeExclusive("test value", value, min, max));
    }


    @Test
    public void isBetween_zero() {
        short value = 0;
        short min = -1;
        short max = 1;
        assertEquals(value, inRangeExclusive("test value", value, min, max));
    }


    @Test
    public void isBetween_minIsZero() {
        short value = 1;
        short min = 0;
        short max = 2;
        assertEquals(value, inRangeExclusive("test value", value, min, max));
    }


    @Test
    public void isBetween_maxIsZero() {
        short value = -1;
        short min = -2;
        short max = 0;
        assertEquals(value, inRangeExclusive("test value", value, min, max));
    }


    @Test
    public void isBetween_cornerBoundaries() {
        short value = 0;
        short min = Short.MIN_VALUE;
        short max = Short.MAX_VALUE;
        assertEquals(value, inRangeExclusive("test value", value, min, max));
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_greaterThanMax() {
        short value = 3;
        short min = 1;
        short max = 2;
        inRangeExclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_equalMax() {
        short value = 2;
        short min = 1;
        short max = 2;
        inRangeExclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_lessThanMin() {
        short value = 3;
        short min = 1;
        short max = 2;
        inRangeExclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_equalMin() {
        short value = 1;
        short min = 1;
        short max = 2;
        inRangeExclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_equalMinimalMin() {
        short value = Short.MIN_VALUE;
        short min = Short.MIN_VALUE;
        short max = 0;
        inRangeExclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_equalMaximalMax() {
        short value = Short.MAX_VALUE;
        short min = 0;
        short max = Short.MAX_VALUE;
        inRangeExclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_maxEqualsMin() {
        short value = 0;
        short min = 1;
        short max = 1;
        inRangeExclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_maxLessThanMin() {
        short value = 0;
        short min = 2;
        short max = 1;
        inRangeExclusive("test value", value, min, max);
    }

    // inRangeInclusive


    @Test
    public void isBetweenOrEquals_positive() {
        short value = 2;
        short min = 1;
        short max = 3;
        assertEquals(value, inRangeInclusive("test value", value, min, max));
    }


    @Test
    public void isBetweenOrEquals_negative() {
        short value = -2;
        short min = -3;
        short max = -1;
        assertEquals(value, inRangeInclusive("test value", value, min, max));
    }


    @Test
    public void isBetweenOrEquals_zero() {
        short value = 0;
        short min = -1;
        short max = 1;
        assertEquals(value, inRangeInclusive("test value", value, min, max));
    }


    @Test
    public void isBetweenOrEquals_minIsZero() {
        short value = 1;
        short min = 0;
        short max = 2;
        assertEquals(value, inRangeInclusive("test value", value, min, max));
    }


    @Test
    public void isBetweenOrEquals_maxIsZero() {
        short value = -1;
        short min = -2;
        short max = 0;
        assertEquals(value, inRangeInclusive("test value", value, min, max));
    }


    @Test
    public void isBetweenOrEquals_cornerBoundaries() {
        short value = 0;
        short min = Short.MIN_VALUE;
        short max = Short.MAX_VALUE;
        assertEquals(value, inRangeInclusive("test value", value, min, max));
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetweenOrEquals_greaterThanMax() {
        short value = 3;
        short min = 1;
        short max = 2;
        inRangeInclusive("test value", value, min, max);
    }


    @Test
    public void isBetweenOrEquals_equalsMax() {
        short value = 2;
        short min = 1;
        short max = 2;
        inRangeInclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetweenOrEquals_lessThanMin() {
        short value = 3;
        short min = 1;
        short max = 2;
        inRangeInclusive("test value", value, min, max);
    }


    @Test
    public void isBetweenOrEquals_equalsMin() {
        short value = 1;
        short min = 1;
        short max = 2;
        inRangeInclusive("test value", value, min, max);
    }


    @Test
    public void isBetweenOrEquals_equalsMinimalMin() {
        short value = Short.MIN_VALUE;
        short min = Short.MIN_VALUE;
        short max = 0;
        inRangeInclusive("test value", value, min, max);
    }


    @Test
    public void isBetweenOrEquals_equalsMaximalMax() {
        short value = Short.MAX_VALUE;
        short min = 0;
        short max = Short.MAX_VALUE;
        inRangeInclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetweenOrEquals_maxEqualsMin() {
        short value = 0;
        short min = 1;
        short max = 1;
        inRangeInclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetweenOrEquals_maxLessThanMin() {
        short value = 0;
        short min = 2;
        short max = 1;
        inRangeInclusive("test value", value, min, max);
    }


    @Test
    public void isBetweenOrEquals_equalsBoundaries() {
        short value = 1;
        short min = 1;
        short max = 1;
        inRangeInclusive("test value", value, min, max);
    }


    //minimumIfLess


    @Test
    public void minimumIfLess_greaterThanBoundary() {
        short value = 1;
        short boundary = 0;
        assertEquals(value, minimumIfLess(value, boundary));
    }


    @Test
    public void minimumIfLess_lessThanBoundary() {
        short value = 0;
        short boundary = 1;
        assertEquals(boundary, minimumIfLess(value, boundary));
    }


    @Test
    public void minimumIfLess_equalsToBoundary() {
        short value = 0;
        short boundary = 0;
        assertEquals(value, minimumIfLess(value, boundary));
    }


    //maximumIfGreater


    @Test
    public void maximumIfGreater_lessThanBoundary() {
        short value = 1;
        short boundary = 2;
        assertEquals(value, maximumIfGreater(value, boundary));
    }


    @Test
    public void maximumIfGreater_greaterThanBoundary() {
        short value = 1;
        short boundary = 0;
        assertEquals(boundary, maximumIfGreater(value, boundary));
    }


    @Test
    public void maximumIfGreater_equalsToBoundary() {
        short value = 0;
        short boundary = 0;
        assertEquals(value, maximumIfGreater(value, boundary));
    }
}