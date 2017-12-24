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
public class ValidateDoubleComparisonTest {

    private static final double PRECISION = 0.00001;

    // less


    @Test
    public void isLess_positive() {
        double value = 1;
        double boundary = 2;
        assertEquals(value, less("test value", value, boundary), PRECISION);
    }


    @Test
    public void isLess_negative() {
        double value = -2;
        double boundary = -1;
        assertEquals(value, less("test value", value, boundary), PRECISION);
    }


    @Test
    public void isLess_zero() {
        double value = 0;
        double boundary = 1;
        assertEquals(value, less("test value", value, boundary), PRECISION);
    }


    @Test
    public void isLess_maximumBoundary() {
        double value = 0;
        double boundary = Double.MAX_VALUE;
        assertEquals(value, less("test value", value, boundary), PRECISION);
    }


    @Test
    public void isLess_minimum() {
        double value = Double.MAX_VALUE * -1;
        double boundary = 0;
        assertEquals(value, less("test value", value, boundary), PRECISION);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isLess_greater() {
        double value = 1;
        double boundary = 0;
        less("test value", value, boundary);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isLess_equalsToBoundary() {
        double value = 1;
        double boundary = 1;
        less("test value", value, boundary);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isLess_minimums() {
        double value = Double.MIN_VALUE;
        double boundary = Double.MIN_VALUE;
        less("test value", value, boundary);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isLess_maximums() {
        double value = Double.MAX_VALUE;
        double boundary = Double.MAX_VALUE;
        less("test value", value, boundary);
    }


    // lessOrEquals


    @Test
    public void isLessOrEquals_positive() {
        double value = 1;
        double boundary = 2;
        assertEquals(value, lessOrEquals("test value", value, boundary), PRECISION);
    }


    @Test
    public void isLessOrEquals_negative() {
        double value = -2;
        double boundary = -1;
        assertEquals(value, lessOrEquals("test value", value, boundary), PRECISION);
    }


    @Test
    public void isLessOrEquals_zero() {
        double value = 0;
        double boundary = 1;
        assertEquals(value, lessOrEquals("test value", value, boundary), PRECISION);
    }


    @Test
    public void isLessOrEquals_maximumBoundary() {
        double value = 0;
        double boundary = Double.MAX_VALUE;
        assertEquals(value, lessOrEquals("test value", value, boundary), PRECISION);
    }


    @Test
    public void isLessOrEquals_minimum() {
        double value = Double.MAX_VALUE * -1;
        double boundary = 0;
        assertEquals(value, lessOrEquals("test value", value, boundary), PRECISION);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isLessOrEquals_greater() {
        double value = 1;
        double boundary = 0;
        lessOrEquals("test value", value, boundary);
    }


    @Test
    public void isLessOrEqual_equals() {
        double value = 1;
        double boundary = 1;
        lessOrEquals("test value", value, boundary);
    }


    @Test
    public void isLessOrEquals_equalsMinimums() {
        double value = Double.MIN_VALUE;
        double boundary = Double.MIN_VALUE;
        greaterOrEquals("test value", value, boundary);
    }


    @Test
    public void isLessOrEquals_equalsMaximums() {
        double value = Double.MAX_VALUE;
        double boundary = Double.MAX_VALUE;
        greaterOrEquals("test value", value, boundary);
    }


    // greater


    @Test
    public void isGreater_positive() {
        double value = 2;
        double boundary = 1;
        assertEquals(value, greater("test value", value, boundary), PRECISION);
    }


    @Test
    public void isGreater_negative() {
        double value = -1;
        double boundary = -2;
        assertEquals(value, greater("test value", value, boundary), PRECISION);
    }


    @Test
    public void isGreater_zero() {
        double value = 1;
        double boundary = 0;
        assertEquals(value, greater("test value", value, boundary), PRECISION);
    }


    @Test
    public void isGreater_minimumBoundary() {
        double value = 0;
        double boundary = Double.MAX_VALUE * -1;
        assertEquals(value, greater("test value", value, boundary), PRECISION);
    }


    @Test
    public void isGreater_maximum() {
        double value = Double.MAX_VALUE;
        double boundary = 0;
        assertEquals(value, greater("test value", value, boundary), PRECISION);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isGreater_less() {
        double value = 0;
        double boundary = 1;
        greater("test value", value, boundary);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isGreater_equals() {
        double value = 1;
        double boundary = 1;
        greater("test value", value, boundary);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isGreater_minimums() {
        double value = Double.MIN_VALUE;
        double boundary = Double.MIN_VALUE;
        less("test value", value, boundary);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isGreater_maximums() {
        double value = Double.MAX_VALUE;
        double boundary = Double.MAX_VALUE;
        less("test value", value, boundary);
    }


    // greaterOrEquals


    @Test
    public void isGreaterOrEquals_positive() {
        double value = 2;
        double boundary = 1;
        assertEquals(value, greaterOrEquals("test value", value, boundary), PRECISION);
    }


    @Test
    public void isGreaterOrEquals_negative() {
        double value = -1;
        double boundary = -2;
        assertEquals(value, greaterOrEquals("test value", value, boundary), PRECISION);
    }


    @Test
    public void isGreaterOrEquals_zero() {
        double value = 1;
        double boundary = 0;
        assertEquals(value, greaterOrEquals("test value", value, boundary), PRECISION);
    }


    @Test
    public void isGreaterOrEquals_minimumBoundary() {
        double value = 0;
        double boundary = Double.MAX_VALUE * -1;
        assertEquals(value, greaterOrEquals("test value", value, boundary), PRECISION);
    }


    @Test
    public void isGreaterOrEquals_maximum() {
        double value = Double.MAX_VALUE;
        double boundary = 0;
        assertEquals(value, greaterOrEquals("test value", value, boundary), PRECISION);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isGreaterOrEquals_greater() {
        double value = 0;
        double boundary = 1;
        greaterOrEquals("test value", value, boundary);
    }


    @Test
    public void isGreaterOrEquals_equals() {
        double value = 1;
        double boundary = 1;
        greaterOrEquals("test value", value, boundary);
    }


    @Test
    public void isGreaterOrEquals_minimums() {
        double value = Double.MIN_VALUE;
        double boundary = Double.MIN_VALUE;
        greaterOrEquals("test value", value, boundary);
    }


    @Test
    public void isGreaterOrEquals_maximums() {
        double value = Double.MAX_VALUE;
        double boundary = Double.MAX_VALUE;
        greaterOrEquals("test value", value, boundary);
    }


    // inRangeExclusive


    @Test
    public void isBetween_positive() {
        double value = 2;
        double min = 1;
        double max = 3;
        assertEquals(value, inRangeExclusive("test value", value, min, max), PRECISION);
    }


    @Test
    public void isBetween_negative() {
        double value = -2;
        double min = -3;
        double max = -1;
        assertEquals(value, inRangeExclusive("test value", value, min, max), PRECISION);
    }


    @Test
    public void isBetween_zero() {
        double value = 0;
        double min = -1;
        double max = 1;
        assertEquals(value, inRangeExclusive("test value", value, min, max), PRECISION);
    }


    @Test
    public void isBetween_minIsZero() {
        double value = 1;
        double min = 0;
        double max = 2;
        assertEquals(value, inRangeExclusive("test value", value, min, max), PRECISION);
    }


    @Test
    public void isBetween_maxIsZero() {
        double value = -1;
        double min = -2;
        double max = 0;
        assertEquals(value, inRangeExclusive("test value", value, min, max), PRECISION);
    }


    @Test
    public void isBetween_cornerBoundaries() {
        double value = 0;
        double min = Double.MAX_VALUE * -1;
        double max = Double.MAX_VALUE;
        assertEquals(value, inRangeExclusive("test value", value, min, max), PRECISION);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_greaterThanMax() {
        double value = 3;
        double min = 1;
        double max = 2;
        inRangeExclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_equalMax() {
        double value = 2;
        double min = 1;
        double max = 2;
        inRangeExclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_lessThanMin() {
        double value = 3;
        double min = 1;
        double max = 2;
        inRangeExclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_equalMin() {
        double value = 1;
        double min = 1;
        double max = 2;
        inRangeExclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_equalMinimalMin() {
        double value = Double.MIN_VALUE;
        double min = Double.MIN_VALUE;
        double max = 0;
        inRangeExclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_equalMaximalMax() {
        double value = Double.MAX_VALUE;
        double min = 0;
        double max = Double.MAX_VALUE;
        inRangeExclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_maxEqualsMin() {
        double value = 0;
        double min = 1;
        double max = 1;
        inRangeExclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_maxLessThanMin() {
        double value = 0;
        double min = 2;
        double max = 1;
        inRangeExclusive("test value", value, min, max);
    }

    // inRangeInclusive


    @Test
    public void isBetweenOrEquals_positive() {
        double value = 2;
        double min = 1;
        double max = 3;
        assertEquals(value, inRangeInclusive("test value", value, min, max), PRECISION);
    }


    @Test
    public void isBetweenOrEquals_negative() {
        double value = -2;
        double min = -3;
        double max = -1;
        assertEquals(value, inRangeInclusive("test value", value, min, max), PRECISION);
    }


    @Test
    public void isBetweenOrEquals_zero() {
        double value = 0;
        double min = -1;
        double max = 1;
        assertEquals(value, inRangeInclusive("test value", value, min, max), PRECISION);
    }


    @Test
    public void isBetweenOrEquals_minIsZero() {
        double value = 1;
        double min = 0;
        double max = 2;
        assertEquals(value, inRangeInclusive("test value", value, min, max), PRECISION);
    }


    @Test
    public void isBetweenOrEquals_maxIsZero() {
        double value = -1;
        double min = -2;
        double max = 0;
        assertEquals(value, inRangeInclusive("test value", value, min, max), PRECISION);
    }


    @Test
    public void isBetweenOrEquals_cornerBoundaries() {
        double value = 0;
        double min = Double.MAX_VALUE * -1;
        double max = Double.MAX_VALUE;
        assertEquals(value, inRangeInclusive("test value", value, min, max), PRECISION);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetweenOrEquals_greaterThanMax() {
        double value = 3;
        double min = 1;
        double max = 2;
        inRangeInclusive("test value", value, min, max);
    }


    @Test
    public void isBetweenOrEquals_equalsMax() {
        double value = 2;
        double min = 1;
        double max = 2;
        inRangeInclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetweenOrEquals_lessThanMin() {
        double value = 3;
        double min = 1;
        double max = 2;
        inRangeInclusive("test value", value, min, max);
    }


    @Test
    public void isBetweenOrEquals_equalsMin() {
        double value = 1;
        double min = 1;
        double max = 2;
        inRangeInclusive("test value", value, min, max);
    }


    @Test
    public void isBetweenOrEquals_equalsMinimalMin() {
        double value = Double.MAX_VALUE * -1;
        double min = Double.MAX_VALUE * -1;
        double max = 0;
        inRangeInclusive("test value", value, min, max);
    }


    @Test
    public void isBetweenOrEquals_equalsMaximalMax() {
        double value = Double.MAX_VALUE;
        double min = 0;
        double max = Double.MAX_VALUE;
        inRangeInclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetweenOrEquals_maxEqualsMin() {
        double value = 0;
        double min = 1;
        double max = 1;
        inRangeInclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetweenOrEquals_maxLessThanMin() {
        double value = 0;
        double min = 2;
        double max = 1;
        inRangeInclusive("test value", value, min, max);
    }


    @Test
    public void isBetweenOrEquals_equalsBoundaries() {
        double value = 1;
        double min = 1;
        double max = 1;
        inRangeInclusive("test value", value, min, max);
    }


    //minimumIfLess


    @Test
    public void minimumIfLess_greaterThanBoundary() {
        double value = 1;
        double boundary = 0;
        assertEquals(value, minimumIfLess(value, boundary), PRECISION);
    }


    @Test
    public void minimumIfLess_lessThanBoundary() {
        double value = 0;
        double boundary = 1;
        assertEquals(boundary, minimumIfLess(value, boundary), PRECISION);
    }


    @Test
    public void minimumIfLess_equalsToBoundary() {
        double value = 0;
        double boundary = 0;
        assertEquals(value, minimumIfLess(value, boundary), PRECISION);
    }


    //maximumIfGreater


    @Test
    public void maximumIfGreater_lessThanBoundary() {
        double value = 1;
        double boundary = 2;
        assertEquals(value, maximumIfGreater(value, boundary), PRECISION);
    }


    @Test
    public void maximumIfGreater_greaterThanBoundary() {
        double value = 1;
        double boundary = 0;
        assertEquals(boundary, maximumIfGreater(value, boundary), PRECISION);
    }


    @Test
    public void maximumIfGreater_equalsToBoundary() {
        double value = 0;
        double boundary = 0;
        assertEquals(value, maximumIfGreater(value, boundary), PRECISION);
    }
}