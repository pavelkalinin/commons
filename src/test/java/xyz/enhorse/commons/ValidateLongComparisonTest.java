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
public class ValidateLongComparisonTest {


    // less


    @Test
    public void isLess_positive() {
        long value = 1;
        long boundary = 2;
        assertEquals(value, less("test value", value, boundary));
    }


    @Test
    public void isLess_negative() {
        long value = -2;
        long boundary = -1;
        assertEquals(value, less("test value", value, boundary));
    }


    @Test
    public void isLess_zero() {
        long value = 0;
        long boundary = 1;
        assertEquals(value, less("test value", value, boundary));
    }


    @Test
    public void isLess_maximumBoundary() {
        long value = 0;
        long boundary = Long.MAX_VALUE;
        assertEquals(value, less("test value", value, boundary));
    }


    @Test
    public void isLess_minimum() {
        long value = Long.MIN_VALUE;
        long boundary = 0;
        assertEquals(value, less("test value", value, boundary));
    }


    @Test(expected = IllegalArgumentException.class)
    public void isLess_greater() {
        long value = 1;
        long boundary = 0;
        less("test value", value, boundary);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isLess_equalsToBoundary() {
        long value = 1;
        long boundary = 1;
        less("test value", value, boundary);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isLess_minimums() {
        long value = Long.MIN_VALUE;
        long boundary = Long.MIN_VALUE;
        less("test value", value, boundary);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isLess_maximums() {
        long value = Long.MAX_VALUE;
        long boundary = Long.MAX_VALUE;
        less("test value", value, boundary);
    }


    // lessOrEquals


    @Test
    public void isLessOrEquals_positive() {
        long value = 1;
        long boundary = 2;
        assertEquals(value, lessOrEquals("test value", value, boundary));
    }


    @Test
    public void isLessOrEquals_negative() {
        long value = -2;
        long boundary = -1;
        assertEquals(value, lessOrEquals("test value", value, boundary));
    }


    @Test
    public void isLessOrEquals_zero() {
        long value = 0;
        long boundary = 1;
        assertEquals(value, lessOrEquals("test value", value, boundary));
    }


    @Test
    public void isLessOrEquals_maximumBoundary() {
        long value = 0;
        long boundary = Long.MAX_VALUE;
        assertEquals(value, lessOrEquals("test value", value, boundary));
    }


    @Test
    public void isLessOrEquals_minimum() {
        long value = Long.MIN_VALUE;
        long boundary = 0;
        assertEquals(value, lessOrEquals("test value", value, boundary));
    }


    @Test(expected = IllegalArgumentException.class)
    public void isLessOrEquals_greater() {
        long value = 1;
        long boundary = 0;
        lessOrEquals("test value", value, boundary);
    }


    @Test
    public void isLessOrEqual_equals() {
        long value = 1;
        long boundary = 1;
        lessOrEquals("test value", value, boundary);
    }


    @Test
    public void isLessOrEquals_equalsMinimums() {
        long value = Long.MIN_VALUE;
        long boundary = Long.MIN_VALUE;
        greaterOrEquals("test value", value, boundary);
    }


    @Test
    public void isLessOrEquals_equalsMaximums() {
        long value = Long.MAX_VALUE;
        long boundary = Long.MAX_VALUE;
        greaterOrEquals("test value", value, boundary);
    }


    // greater


    @Test
    public void isGreater_positive() {
        long value = 2;
        long boundary = 1;
        assertEquals(value, greater("test value", value, boundary));
    }


    @Test
    public void isGreater_negative() {
        long value = -1;
        long boundary = -2;
        assertEquals(value, greater("test value", value, boundary));
    }


    @Test
    public void isGreater_zero() {
        long value = 1;
        long boundary = 0;
        assertEquals(value, greater("test value", value, boundary));
    }


    @Test
    public void isGreater_maximumBoundary() {
        long value = 0;
        long boundary = Long.MIN_VALUE;
        assertEquals(value, greater("test value", value, boundary));
    }


    @Test
    public void isGreater_maximum() {
        long value = Long.MAX_VALUE;
        long boundary = 0;
        assertEquals(value, greater("test value", value, boundary));
    }


    @Test(expected = IllegalArgumentException.class)
    public void isGreater_less() {
        long value = 0;
        long boundary = 1;
        greater("test value", value, boundary);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isGreater_equals() {
        long value = 1;
        long boundary = 1;
        greater("test value", value, boundary);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isGreater_minimums() {
        long value = Long.MIN_VALUE;
        long boundary = Long.MIN_VALUE;
        less("test value", value, boundary);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isGreater_maximums() {
        long value = Long.MAX_VALUE;
        long boundary = Long.MAX_VALUE;
        less("test value", value, boundary);
    }


    // greaterOrEquals


    @Test
    public void isGreaterOrEquals_positive() {
        long value = 2;
        long boundary = 1;
        assertEquals(value, greaterOrEquals("test value", value, boundary));
    }


    @Test
    public void isGreaterOrEquals_negative() {
        long value = -1;
        long boundary = -2;
        assertEquals(value, greaterOrEquals("test value", value, boundary));
    }


    @Test
    public void isGreaterOrEquals_zero() {
        long value = 1;
        long boundary = 0;
        assertEquals(value, greaterOrEquals("test value", value, boundary));
    }


    @Test
    public void isGreaterOrEquals_minimumBoundary() {
        long value = 0;
        long boundary = Long.MIN_VALUE;
        assertEquals(value, greaterOrEquals("test value", value, boundary));
    }


    @Test
    public void isGreaterOrEquals_maximum() {
        long value = Long.MAX_VALUE;
        long boundary = 0;
        assertEquals(value, greaterOrEquals("test value", value, boundary));
    }


    @Test(expected = IllegalArgumentException.class)
    public void isGreaterOrEquals_greater() {
        long value = 0;
        long boundary = 1;
        greaterOrEquals("test value", value, boundary);
    }


    @Test
    public void isGreaterOrEquals_equals() {
        long value = 1;
        long boundary = 1;
        greaterOrEquals("test value", value, boundary);
    }


    @Test
    public void isGreaterOrEquals_minimums() {
        long value = Long.MIN_VALUE;
        long boundary = Long.MIN_VALUE;
        greaterOrEquals("test value", value, boundary);
    }


    @Test
    public void isGreaterOrEquals_maximums() {
        long value = Long.MAX_VALUE;
        long boundary = Long.MAX_VALUE;
        greaterOrEquals("test value", value, boundary);
    }


    // inRangeExclusive


    @Test
    public void isBetween_positive() {
        long value = 2;
        long min = 1;
        long max = 3;
        assertEquals(value, inRangeExclusive("test value", value, min, max));
    }


    @Test
    public void isBetween_negative() {
        long value = -2;
        long min = -3;
        long max = -1;
        assertEquals(value, inRangeExclusive("test value", value, min, max));
    }


    @Test
    public void isBetween_zero() {
        long value = 0;
        long min = -1;
        long max = 1;
        assertEquals(value, inRangeExclusive("test value", value, min, max));
    }


    @Test
    public void isBetween_minIsZero() {
        long value = 1;
        long min = 0;
        long max = 2;
        assertEquals(value, inRangeExclusive("test value", value, min, max));
    }


    @Test
    public void isBetween_maxIsZero() {
        long value = -1;
        long min = -2;
        long max = 0;
        assertEquals(value, inRangeExclusive("test value", value, min, max));
    }


    @Test
    public void isBetween_cornerBoundaries() {
        long value = 0;
        long min = Long.MIN_VALUE;
        long max = Long.MAX_VALUE;
        assertEquals(value, inRangeExclusive("test value", value, min, max));
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_greaterThanMax() {
        long value = 3;
        long min = 1;
        long max = 2;
        inRangeExclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_equalMax() {
        long value = 2;
        long min = 1;
        long max = 2;
        inRangeExclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_lessThanMin() {
        long value = 3;
        long min = 1;
        long max = 2;
        inRangeExclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_equalMin() {
        long value = 1;
        long min = 1;
        long max = 2;
        inRangeExclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_equalMinimalMin() {
        long value = Long.MIN_VALUE;
        long min = Long.MIN_VALUE;
        long max = 0;
        inRangeExclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_equalMaximalMax() {
        long value = Long.MAX_VALUE;
        long min = 0;
        long max = Long.MAX_VALUE;
        inRangeExclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_maxEqualsMin() {
        long value = 0;
        long min = 1;
        long max = 1;
        inRangeExclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_maxLessThanMin() {
        long value = 0;
        long min = 2;
        long max = 1;
        inRangeExclusive("test value", value, min, max);
    }

    // inRangeInclusive


    @Test
    public void isBetweenOrEquals_positive() {
        long value = 2;
        long min = 1;
        long max = 3;
        assertEquals(value, inRangeInclusive("test value", value, min, max));
    }


    @Test
    public void isBetweenOrEquals_negative() {
        long value = -2;
        long min = -3;
        long max = -1;
        assertEquals(value, inRangeInclusive("test value", value, min, max));
    }


    @Test
    public void isBetweenOrEquals_zero() {
        long value = 0;
        long min = -1;
        long max = 1;
        assertEquals(value, inRangeInclusive("test value", value, min, max));
    }


    @Test
    public void isBetweenOrEquals_minIsZero() {
        long value = 1;
        long min = 0;
        long max = 2;
        assertEquals(value, inRangeInclusive("test value", value, min, max));
    }


    @Test
    public void isBetweenOrEquals_maxIsZero() {
        long value = -1;
        long min = -2;
        long max = 0;
        assertEquals(value, inRangeInclusive("test value", value, min, max));
    }


    @Test
    public void isBetweenOrEquals_cornerBoundaries() {
        long value = 0;
        long min = Long.MIN_VALUE;
        long max = Long.MAX_VALUE;
        assertEquals(value, inRangeInclusive("test value", value, min, max));
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetweenOrEquals_greaterThanMax() {
        long value = 3;
        long min = 1;
        long max = 2;
        inRangeInclusive("test value", value, min, max);
    }


    @Test
    public void isBetweenOrEquals_equalsMax() {
        long value = 2;
        long min = 1;
        long max = 2;
        inRangeInclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetweenOrEquals_lessThanMin() {
        long value = 3;
        long min = 1;
        long max = 2;
        inRangeInclusive("test value", value, min, max);
    }


    @Test
    public void isBetweenOrEquals_equalsMin() {
        long value = 1;
        long min = 1;
        long max = 2;
        inRangeInclusive("test value", value, min, max);
    }


    @Test
    public void isBetweenOrEquals_equalsMinimalMin() {
        long value = Long.MIN_VALUE;
        long min = Long.MIN_VALUE;
        long max = 0;
        inRangeInclusive("test value", value, min, max);
    }


    @Test
    public void isBetweenOrEquals_equalsMaximalMax() {
        long value = Long.MAX_VALUE;
        long min = 0;
        long max = Long.MAX_VALUE;
        inRangeInclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetweenOrEquals_maxEqualsMin() {
        long value = 0;
        long min = 1;
        long max = 1;
        inRangeInclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetweenOrEquals_maxLessThanMin() {
        long value = 0;
        long min = 2;
        long max = 1;
        inRangeInclusive("test value", value, min, max);
    }


    @Test
    public void isBetweenOrEquals_equalsBoundaries() {
        long value = 1;
        long min = 1;
        long max = 1;
        inRangeInclusive("test value", value, min, max);
    }


    //minimumIfLess


    @Test
    public void minimumIfLess_greaterThanBoundary() {
        long value = 1;
        long boundary = 0;
        assertEquals(value, minimumIfLess(value, boundary));
    }


    @Test
    public void minimumIfLess_lessThanBoundary() {
        long value = 0;
        long boundary = 1;
        assertEquals(boundary, minimumIfLess(value, boundary));
    }


    @Test
    public void minimumIfLess_equalsToBoundary() {
        long value = 0;
        long boundary = 0;
        assertEquals(value, minimumIfLess(value, boundary));
    }


    //maximumIfGreater


    @Test
    public void maximumIfGreater_lessThanBoundary() {
        long value = 1;
        long boundary = 2;
        assertEquals(value, maximumIfGreater(value, boundary));
    }


    @Test
    public void maximumIfGreater_greaterThanBoundary() {
        long value = 1;
        long boundary = 0;
        assertEquals(boundary, maximumIfGreater(value, boundary));
    }


    @Test
    public void maximumIfGreater_equalsToBoundary() {
        long value = 0;
        long boundary = 0;
        assertEquals(value, maximumIfGreater(value, boundary));
    }
}