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
public class ValidateIntComparisonTest {


    // less


    @Test
    public void isLess_positive() {
        int value = 1;
        int boundary = 2;
        assertEquals(value, less("test value", value, boundary));
    }


    @Test
    public void isLess_negative() {
        int value = -2;
        int boundary = -1;
        assertEquals(value, less("test value", value, boundary));
    }


    @Test
    public void isLess_zero() {
        int value = 0;
        int boundary = 1;
        assertEquals(value, less("test value", value, boundary));
    }


    @Test
    public void isLess_maximumBoundary() {
        int value = 0;
        int boundary = Integer.MAX_VALUE;
        assertEquals(value, less("test value", value, boundary));
    }


    @Test
    public void isLess_minimum() {
        int value = Integer.MIN_VALUE;
        int boundary = 0;
        assertEquals(value, less("test value", value, boundary));
    }


    @Test(expected = IllegalArgumentException.class)
    public void isLess_greater() {
        int value = 1;
        int boundary = 0;
        less("test value", value, boundary);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isLess_equalsToBoundary() {
        int value = 1;
        int boundary = 1;
        less("test value", value, boundary);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isLess_minimums() {
        int value = Integer.MIN_VALUE;
        int boundary = Integer.MIN_VALUE;
        less("test value", value, boundary);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isLess_maximums() {
        int value = Integer.MAX_VALUE;
        int boundary = Integer.MAX_VALUE;
        less("test value", value, boundary);
    }


    // lessOrEquals


    @Test
    public void isLessOrEquals_positive() {
        int value = 1;
        int boundary = 2;
        assertEquals(value, lessOrEquals("test value", value, boundary));
    }


    @Test
    public void isLessOrEquals_negative() {
        int value = -2;
        int boundary = -1;
        assertEquals(value, lessOrEquals("test value", value, boundary));
    }


    @Test
    public void isLessOrEquals_zero() {
        int value = 0;
        int boundary = 1;
        assertEquals(value, lessOrEquals("test value", value, boundary));
    }


    @Test
    public void isLessOrEquals_maximumBoundary() {
        int value = 0;
        int boundary = Integer.MAX_VALUE;
        assertEquals(value, lessOrEquals("test value", value, boundary));
    }


    @Test
    public void isLessOrEquals_minimum() {
        int value = Integer.MIN_VALUE;
        int boundary = 0;
        assertEquals(value, lessOrEquals("test value", value, boundary));
    }


    @Test(expected = IllegalArgumentException.class)
    public void isLessOrEquals_greater() {
        int value = 1;
        int boundary = 0;
        lessOrEquals("test value", value, boundary);
    }


    @Test
    public void isLessOrEqual_equals() {
        int value = 1;
        int boundary = 1;
        lessOrEquals("test value", value, boundary);
    }


    @Test
    public void isLessOrEquals_equalsMinimums() {
        int value = Integer.MIN_VALUE;
        int boundary = Integer.MIN_VALUE;
        greaterOrEquals("test value", value, boundary);
    }


    @Test
    public void isLessOrEquals_equalsMaximums() {
        int value = Integer.MAX_VALUE;
        int boundary = Integer.MAX_VALUE;
        greaterOrEquals("test value", value, boundary);
    }


    // greater


    @Test
    public void isGreater_positive() {
        int value = 2;
        int boundary = 1;
        assertEquals(value, greater("test value", value, boundary));
    }


    @Test
    public void isGreater_negative() {
        int value = -1;
        int boundary = -2;
        assertEquals(value, greater("test value", value, boundary));
    }


    @Test
    public void isGreater_zero() {
        int value = 1;
        int boundary = 0;
        assertEquals(value, greater("test value", value, boundary));
    }


    @Test
    public void isGreater_maximumBoundary() {
        int value = 0;
        int boundary = Integer.MIN_VALUE;
        assertEquals(value, greater("test value", value, boundary));
    }


    @Test
    public void isGreater_maximum() {
        int value = Integer.MAX_VALUE;
        int boundary = 0;
        assertEquals(value, greater("test value", value, boundary));
    }


    @Test(expected = IllegalArgumentException.class)
    public void isGreater_less() {
        int value = 0;
        int boundary = 1;
        greater("test value", value, boundary);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isGreater_equals() {
        int value = 1;
        int boundary = 1;
        greater("test value", value, boundary);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isGreater_minimums() {
        int value = Integer.MIN_VALUE;
        int boundary = Integer.MIN_VALUE;
        less("test value", value, boundary);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isGreater_maximums() {
        int value = Integer.MAX_VALUE;
        int boundary = Integer.MAX_VALUE;
        less("test value", value, boundary);
    }


    // greaterOrEquals


    @Test
    public void isGreaterOrEquals_positive() {
        int value = 2;
        int boundary = 1;
        assertEquals(value, greaterOrEquals("test value", value, boundary));
    }


    @Test
    public void isGreaterOrEquals_negative() {
        int value = -1;
        int boundary = -2;
        assertEquals(value, greaterOrEquals("test value", value, boundary));
    }


    @Test
    public void isGreaterOrEquals_zero() {
        int value = 1;
        int boundary = 0;
        assertEquals(value, greaterOrEquals("test value", value, boundary));
    }


    @Test
    public void isGreaterOrEquals_minimumBoundary() {
        int value = 0;
        int boundary = Integer.MIN_VALUE;
        assertEquals(value, greaterOrEquals("test value", value, boundary));
    }


    @Test
    public void isGreaterOrEquals_maximum() {
        int value = Integer.MAX_VALUE;
        int boundary = 0;
        assertEquals(value, greaterOrEquals("test value", value, boundary));
    }


    @Test(expected = IllegalArgumentException.class)
    public void isGreaterOrEquals_greater() {
        int value = 0;
        int boundary = 1;
        greaterOrEquals("test value", value, boundary);
    }


    @Test
    public void isGreaterOrEquals_equals() {
        int value = 1;
        int boundary = 1;
        greaterOrEquals("test value", value, boundary);
    }


    @Test
    public void isGreaterOrEquals_minimums() {
        int value = Integer.MIN_VALUE;
        int boundary = Integer.MIN_VALUE;
        greaterOrEquals("test value", value, boundary);
    }


    @Test
    public void isGreaterOrEquals_maximums() {
        int value = Integer.MAX_VALUE;
        int boundary = Integer.MAX_VALUE;
        greaterOrEquals("test value", value, boundary);
    }


    // inRangeExclusive


    @Test
    public void isBetween_positive() {
        int value = 2;
        int min = 1;
        int max = 3;
        assertEquals(value, inRangeExclusive("test value", value, min, max));
    }


    @Test
    public void isBetween_negative() {
        int value = -2;
        int min = -3;
        int max = -1;
        assertEquals(value, inRangeExclusive("test value", value, min, max));
    }


    @Test
    public void isBetween_zero() {
        int value = 0;
        int min = -1;
        int max = 1;
        assertEquals(value, inRangeExclusive("test value", value, min, max));
    }


    @Test
    public void isBetween_minIsZero() {
        int value = 1;
        int min = 0;
        int max = 2;
        assertEquals(value, inRangeExclusive("test value", value, min, max));
    }


    @Test
    public void isBetween_maxIsZero() {
        int value = -1;
        int min = -2;
        int max = 0;
        assertEquals(value, inRangeExclusive("test value", value, min, max));
    }


    @Test
    public void isBetween_cornerBoundaries() {
        int value = 0;
        int min = Integer.MIN_VALUE;
        int max = Integer.MAX_VALUE;
        assertEquals(value, inRangeExclusive("test value", value, min, max));
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_greaterThanMax() {
        int value = 3;
        int min = 1;
        int max = 2;
        inRangeExclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_equalMax() {
        int value = 2;
        int min = 1;
        int max = 2;
        inRangeExclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_lessThanMin() {
        int value = 3;
        int min = 1;
        int max = 2;
        inRangeExclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_equalMin() {
        int value = 1;
        int min = 1;
        int max = 2;
        inRangeExclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_equalMinimalMin() {
        int value = Integer.MIN_VALUE;
        int min = Integer.MIN_VALUE;
        int max = 0;
        inRangeExclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_equalMaximalMax() {
        int value = Integer.MAX_VALUE;
        int min = 0;
        int max = Integer.MAX_VALUE;
        inRangeExclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_maxEqualsMin() {
        int value = 0;
        int min = 1;
        int max = 1;
        inRangeExclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_maxLessThanMin() {
        int value = 0;
        int min = 2;
        int max = 1;
        inRangeExclusive("test value", value, min, max);
    }

    // inRangeInclusive


    @Test
    public void isBetweenOrEquals_positive() {
        int value = 2;
        int min = 1;
        int max = 3;
        assertEquals(value, inRangeInclusive("test value", value, min, max));
    }


    @Test
    public void isBetweenOrEquals_negative() {
        int value = -2;
        int min = -3;
        int max = -1;
        assertEquals(value, inRangeInclusive("test value", value, min, max));
    }


    @Test
    public void isBetweenOrEquals_zero() {
        int value = 0;
        int min = -1;
        int max = 1;
        assertEquals(value, inRangeInclusive("test value", value, min, max));
    }


    @Test
    public void isBetweenOrEquals_minIsZero() {
        int value = 1;
        int min = 0;
        int max = 2;
        assertEquals(value, inRangeInclusive("test value", value, min, max));
    }


    @Test
    public void isBetweenOrEquals_maxIsZero() {
        int value = -1;
        int min = -2;
        int max = 0;
        assertEquals(value, inRangeInclusive("test value", value, min, max));
    }


    @Test
    public void isBetweenOrEquals_cornerBoundaries() {
        int value = 0;
        int min = Integer.MIN_VALUE;
        int max = Integer.MAX_VALUE;
        assertEquals(value, inRangeInclusive("test value", value, min, max));
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetweenOrEquals_greaterThanMax() {
        int value = 3;
        int min = 1;
        int max = 2;
        inRangeInclusive("test value", value, min, max);
    }


    @Test
    public void isBetweenOrEquals_equalsMax() {
        int value = 2;
        int min = 1;
        int max = 2;
        inRangeInclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetweenOrEquals_lessThanMin() {
        int value = 3;
        int min = 1;
        int max = 2;
        inRangeInclusive("test value", value, min, max);
    }


    @Test
    public void isBetweenOrEquals_equalsMin() {
        int value = 1;
        int min = 1;
        int max = 2;
        inRangeInclusive("test value", value, min, max);
    }


    @Test
    public void isBetweenOrEquals_equalsMinimalMin() {
        int value = Integer.MIN_VALUE;
        int min = Integer.MIN_VALUE;
        int max = 0;
        inRangeInclusive("test value", value, min, max);
    }


    @Test
    public void isBetweenOrEquals_equalsMaximalMax() {
        int value = Integer.MAX_VALUE;
        int min = 0;
        int max = Integer.MAX_VALUE;
        inRangeInclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetweenOrEquals_maxEqualsMin() {
        int value = 0;
        int min = 1;
        int max = 1;
        inRangeInclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetweenOrEquals_maxLessThanMin() {
        int value = 0;
        int min = 2;
        int max = 1;
        inRangeInclusive("test value", value, min, max);
    }


    @Test
    public void isBetweenOrEquals_equalsBoundaries() {
        int value = 1;
        int min = 1;
        int max = 1;
        inRangeInclusive("test value", value, min, max);
    }


    //minimumIfLess


    @Test
    public void minimumIfLess_greaterThanBoundary() {
        int value = 1;
        int boundary = 0;
        assertEquals(value, minimumIfLess(value, boundary));
    }


    @Test
    public void minimumIfLess_lessThanBoundary() {
        int value = 0;
        int boundary = 1;
        assertEquals(boundary, minimumIfLess(value, boundary));
    }


    @Test
    public void minimumIfLess_equalsToBoundary() {
        int value = 0;
        int boundary = 0;
        assertEquals(value, minimumIfLess(value, boundary));
    }


    //maximumIfGreater


    @Test
    public void maximumIfGreater_lessThanBoundary() {
        int value = 1;
        int boundary = 2;
        assertEquals(value, maximumIfGreater(value, boundary));
    }


    @Test
    public void maximumIfGreater_greaterThanBoundary() {
        int value = 1;
        int boundary = 0;
        assertEquals(boundary, maximumIfGreater(value, boundary));
    }


    @Test
    public void maximumIfGreater_equalsToBoundary() {
        int value = 0;
        int boundary = 0;
        assertEquals(value, maximumIfGreater(value, boundary));
    }
}