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
public class ValidateFloatComparisonTest {

    private static final double PRECISION = 0.00001;

    // less


    @Test
    public void isLess_positive() {
        float value = 1;
        float boundary = 2;
        assertEquals(value, less("test value", value, boundary), PRECISION);
    }


    @Test
    public void isLess_negative() {
        float value = -2;
        float boundary = -1;
        assertEquals(value, less("test value", value, boundary), PRECISION);
    }


    @Test
    public void isLess_zero() {
        float value = 0;
        float boundary = 1;
        assertEquals(value, less("test value", value, boundary), PRECISION);
    }


    @Test
    public void isLess_maximumBoundary() {
        float value = 0;
        float boundary = Float.MAX_VALUE;
        assertEquals(value, less("test value", value, boundary), PRECISION);
    }


    @Test
    public void isLess_minimum() {
        float value = Float.MAX_VALUE * -1;
        float boundary = 0;
        assertEquals(value, less("test value", value, boundary), PRECISION);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isLess_greater() {
        float value = 1;
        float boundary = 0;
        less("test value", value, boundary);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isLess_equalsToBoundary() {
        float value = 1;
        float boundary = 1;
        less("test value", value, boundary);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isLess_minimums() {
        float value = Float.MIN_VALUE;
        float boundary = Float.MIN_VALUE;
        less("test value", value, boundary);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isLess_maximums() {
        float value = Float.MAX_VALUE;
        float boundary = Float.MAX_VALUE;
        less("test value", value, boundary);
    }


    // lessOrEquals


    @Test
    public void isLessOrEquals_positive() {
        float value = 1;
        float boundary = 2;
        assertEquals(value, lessOrEquals("test value", value, boundary), PRECISION);
    }


    @Test
    public void isLessOrEquals_negative() {
        float value = -2;
        float boundary = -1;
        assertEquals(value, lessOrEquals("test value", value, boundary), PRECISION);
    }


    @Test
    public void isLessOrEquals_zero() {
        float value = 0;
        float boundary = 1;
        assertEquals(value, lessOrEquals("test value", value, boundary), PRECISION);
    }


    @Test
    public void isLessOrEquals_maximumBoundary() {
        float value = 0;
        float boundary = Float.MAX_VALUE;
        assertEquals(value, lessOrEquals("test value", value, boundary), PRECISION);
    }


    @Test
    public void isLessOrEquals_minimum() {
        float value = Float.MAX_VALUE * -1;
        float boundary = 0;
        assertEquals(value, lessOrEquals("test value", value, boundary), PRECISION);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isLessOrEquals_greater() {
        float value = 1;
        float boundary = 0;
        lessOrEquals("test value", value, boundary);
    }


    @Test
    public void isLessOrEqual_equals() {
        float value = 1;
        float boundary = 1;
        lessOrEquals("test value", value, boundary);
    }


    @Test
    public void isLessOrEquals_equalsMinimums() {
        float value = Float.MIN_VALUE;
        float boundary = Float.MIN_VALUE;
        greaterOrEquals("test value", value, boundary);
    }


    @Test
    public void isLessOrEquals_equalsMaximums() {
        float value = Float.MAX_VALUE;
        float boundary = Float.MAX_VALUE;
        greaterOrEquals("test value", value, boundary);
    }


    // greater


    @Test
    public void isGreater_positive() {
        float value = 2;
        float boundary = 1;
        assertEquals(value, greater("test value", value, boundary), PRECISION);
    }


    @Test
    public void isGreater_negative() {
        float value = -1;
        float boundary = -2;
        assertEquals(value, greater("test value", value, boundary), PRECISION);
    }


    @Test
    public void isGreater_zero() {
        float value = 1;
        float boundary = 0;
        assertEquals(value, greater("test value", value, boundary), PRECISION);
    }


    @Test
    public void isGreater_minimumBoundary() {
        float value = 0;
        float boundary = Float.MAX_VALUE * -1;
        assertEquals(value, greater("test value", value, boundary), PRECISION);
    }


    @Test
    public void isGreater_maximum() {
        float value = Float.MAX_VALUE;
        float boundary = 0;
        assertEquals(value, greater("test value", value, boundary), PRECISION);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isGreater_less() {
        float value = 0;
        float boundary = 1;
        greater("test value", value, boundary);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isGreater_equals() {
        float value = 1;
        float boundary = 1;
        greater("test value", value, boundary);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isGreater_minimums() {
        float value = Float.MIN_VALUE;
        float boundary = Float.MIN_VALUE;
        less("test value", value, boundary);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isGreater_maximums() {
        float value = Float.MAX_VALUE;
        float boundary = Float.MAX_VALUE;
        less("test value", value, boundary);
    }


    // greaterOrEquals


    @Test
    public void isGreaterOrEquals_positive() {
        float value = 2;
        float boundary = 1;
        assertEquals(value, greaterOrEquals("test value", value, boundary), PRECISION);
    }


    @Test
    public void isGreaterOrEquals_negative() {
        float value = -1;
        float boundary = -2;
        assertEquals(value, greaterOrEquals("test value", value, boundary), PRECISION);
    }


    @Test
    public void isGreaterOrEquals_zero() {
        float value = 1;
        float boundary = 0;
        assertEquals(value, greaterOrEquals("test value", value, boundary), PRECISION);
    }


    @Test
    public void isGreaterOrEquals_minimumBoundary() {
        float value = 0;
        float boundary = Float.MAX_VALUE * -1;
        assertEquals(value, greaterOrEquals("test value", value, boundary), PRECISION);
    }


    @Test
    public void isGreaterOrEquals_maximum() {
        float value = Float.MAX_VALUE;
        float boundary = 0;
        assertEquals(value, greaterOrEquals("test value", value, boundary), PRECISION);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isGreaterOrEquals_greater() {
        float value = 0;
        float boundary = 1;
        greaterOrEquals("test value", value, boundary);
    }


    @Test
    public void isGreaterOrEquals_equals() {
        float value = 1;
        float boundary = 1;
        greaterOrEquals("test value", value, boundary);
    }


    @Test
    public void isGreaterOrEquals_minimums() {
        float value = Float.MIN_VALUE;
        float boundary = Float.MIN_VALUE;
        greaterOrEquals("test value", value, boundary);
    }


    @Test
    public void isGreaterOrEquals_maximums() {
        float value = Float.MAX_VALUE;
        float boundary = Float.MAX_VALUE;
        greaterOrEquals("test value", value, boundary);
    }


    // inRangeExclusive


    @Test
    public void isBetween_positive() {
        float value = 2;
        float min = 1;
        float max = 3;
        assertEquals(value, inRangeExclusive("test value", value, min, max), PRECISION);
    }


    @Test
    public void isBetween_negative() {
        float value = -2;
        float min = -3;
        float max = -1;
        assertEquals(value, inRangeExclusive("test value", value, min, max), PRECISION);
    }


    @Test
    public void isBetween_zero() {
        float value = 0;
        float min = -1;
        float max = 1;
        assertEquals(value, inRangeExclusive("test value", value, min, max), PRECISION);
    }


    @Test
    public void isBetween_minIsZero() {
        float value = 1;
        float min = 0;
        float max = 2;
        assertEquals(value, inRangeExclusive("test value", value, min, max), PRECISION);
    }


    @Test
    public void isBetween_maxIsZero() {
        float value = -1;
        float min = -2;
        float max = 0;
        assertEquals(value, inRangeExclusive("test value", value, min, max), PRECISION);
    }


    @Test
    public void isBetween_cornerBoundaries() {
        float value = 0;
        float min = Float.MAX_VALUE * -1;
        float max = Float.MAX_VALUE;
        assertEquals(value, inRangeExclusive("test value", value, min, max), PRECISION);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_greaterThanMax() {
        float value = 3;
        float min = 1;
        float max = 2;
        inRangeExclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_equalMax() {
        float value = 2;
        float min = 1;
        float max = 2;
        inRangeExclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_lessThanMin() {
        float value = 3;
        float min = 1;
        float max = 2;
        inRangeExclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_equalMin() {
        float value = 1;
        float min = 1;
        float max = 2;
        inRangeExclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_equalMinimalMin() {
        float value = Float.MIN_VALUE;
        float min = Float.MIN_VALUE;
        float max = 0;
        inRangeExclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_equalMaximalMax() {
        float value = Float.MAX_VALUE;
        float min = 0;
        float max = Float.MAX_VALUE;
        inRangeExclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_maxEqualsMin() {
        float value = 0;
        float min = 1;
        float max = 1;
        inRangeExclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_maxLessThanMin() {
        float value = 0;
        float min = 2;
        float max = 1;
        inRangeExclusive("test value", value, min, max);
    }

    // inRangeInclusive


    @Test
    public void isBetweenOrEquals_positive() {
        float value = 2;
        float min = 1;
        float max = 3;
        assertEquals(value, inRangeInclusive("test value", value, min, max), PRECISION);
    }


    @Test
    public void isBetweenOrEquals_negative() {
        float value = -2;
        float min = -3;
        float max = -1;
        assertEquals(value, inRangeInclusive("test value", value, min, max), PRECISION);
    }


    @Test
    public void isBetweenOrEquals_zero() {
        float value = 0;
        float min = -1;
        float max = 1;
        assertEquals(value, inRangeInclusive("test value", value, min, max), PRECISION);
    }


    @Test
    public void isBetweenOrEquals_minIsZero() {
        float value = 1;
        float min = 0;
        float max = 2;
        assertEquals(value, inRangeInclusive("test value", value, min, max), PRECISION);
    }


    @Test
    public void isBetweenOrEquals_maxIsZero() {
        float value = -1;
        float min = -2;
        float max = 0;
        assertEquals(value, inRangeInclusive("test value", value, min, max), PRECISION);
    }


    @Test
    public void isBetweenOrEquals_cornerBoundaries() {
        float value = 0;
        float min = Float.MAX_VALUE * -1;
        float max = Float.MAX_VALUE;
        assertEquals(value, inRangeInclusive("test value", value, min, max), PRECISION);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetweenOrEquals_greaterThanMax() {
        float value = 3;
        float min = 1;
        float max = 2;
        inRangeInclusive("test value", value, min, max);
    }


    @Test
    public void isBetweenOrEquals_equalsMax() {
        float value = 2;
        float min = 1;
        float max = 2;
        inRangeInclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetweenOrEquals_lessThanMin() {
        float value = 3;
        float min = 1;
        float max = 2;
        inRangeInclusive("test value", value, min, max);
    }


    @Test
    public void isBetweenOrEquals_equalsMin() {
        float value = 1;
        float min = 1;
        float max = 2;
        inRangeInclusive("test value", value, min, max);
    }


    @Test
    public void isBetweenOrEquals_equalsMinimalMin() {
        float value = Float.MAX_VALUE * -1;
        float min = Float.MAX_VALUE * -1;
        float max = 0;
        inRangeInclusive("test value", value, min, max);
    }


    @Test
    public void isBetweenOrEquals_equalsMaximalMax() {
        float value = Float.MAX_VALUE;
        float min = 0;
        float max = Float.MAX_VALUE;
        inRangeInclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetweenOrEquals_maxEqualsMin() {
        float value = 0;
        float min = 1;
        float max = 1;
        inRangeInclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetweenOrEquals_maxLessThanMin() {
        float value = 0;
        float min = 2;
        float max = 1;
        inRangeInclusive("test value", value, min, max);
    }


    @Test
    public void isBetweenOrEquals_equalsBoundaries() {
        float value = 1;
        float min = 1;
        float max = 1;
        inRangeInclusive("test value", value, min, max);
    }


    //minimumIfLess


    @Test
    public void minimumIfLess_greaterThanBoundary() {
        float value = 1;
        float boundary = 0;
        assertEquals(value, minimumIfLess(value, boundary), PRECISION);
    }


    @Test
    public void minimumIfLess_lessThanBoundary() {
        float value = 0;
        float boundary = 1;
        assertEquals(boundary, minimumIfLess(value, boundary), PRECISION);
    }


    @Test
    public void minimumIfLess_equalsToBoundary() {
        float value = 0;
        float boundary = 0;
        assertEquals(value, minimumIfLess(value, boundary), PRECISION);
    }


    //maximumIfGreater


    @Test
    public void maximumIfGreater_lessThanBoundary() {
        float value = 1;
        float boundary = 2;
        assertEquals(value, maximumIfGreater(value, boundary), PRECISION);
    }


    @Test
    public void maximumIfGreater_greaterThanBoundary() {
        float value = 1;
        float boundary = 0;
        assertEquals(boundary, maximumIfGreater(value, boundary), PRECISION);
    }


    @Test
    public void maximumIfGreater_equalsToBoundary() {
        float value = 0;
        float boundary = 0;
        assertEquals(value, maximumIfGreater(value, boundary), PRECISION);
    }
}