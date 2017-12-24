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
public class ValidateByteComparisonTest {


    // less


    @Test
    public void isLess_positive() {
        byte value = 1;
        byte boundary = 2;
        assertEquals(value, less("test value", value, boundary));
    }


    @Test
    public void isLess_negative() {
        byte value = -2;
        byte boundary = -1;
        assertEquals(value, less("test value", value, boundary));
    }


    @Test
    public void isLess_zero() {
        byte value = 0;
        byte boundary = 1;
        assertEquals(value, less("test value", value, boundary));
    }


    @Test
    public void isLess_maximumBoundary() {
        byte value = 0;
        byte boundary = Byte.MAX_VALUE;
        assertEquals(value, less("test value", value, boundary));
    }


    @Test
    public void isLess_minimum() {
        byte value = Byte.MIN_VALUE;
        byte boundary = 0;
        assertEquals(value, less("test value", value, boundary));
    }


    @Test(expected = IllegalArgumentException.class)
    public void isLess_greater() {
        byte value = 1;
        byte boundary = 0;
        less("test value", value, boundary);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isLess_equalsToBoundary() {
        byte value = 1;
        byte boundary = 1;
        less("test value", value, boundary);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isLess_minimums() {
        byte value = Byte.MIN_VALUE;
        byte boundary = Byte.MIN_VALUE;
        less("test value", value, boundary);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isLess_maximums() {
        byte value = Byte.MAX_VALUE;
        byte boundary = Byte.MAX_VALUE;
        less("test value", value, boundary);
    }


    // lessOrEquals


    @Test
    public void isLessOrEquals_positive() {
        byte value = 1;
        byte boundary = 2;
        assertEquals(value, lessOrEquals("test value", value, boundary));
    }


    @Test
    public void isLessOrEquals_negative() {
        byte value = -2;
        byte boundary = -1;
        assertEquals(value, lessOrEquals("test value", value, boundary));
    }


    @Test
    public void isLessOrEquals_zero() {
        byte value = 0;
        byte boundary = 1;
        assertEquals(value, lessOrEquals("test value", value, boundary));
    }


    @Test
    public void isLessOrEquals_maximumBoundary() {
        byte value = 0;
        byte boundary = Byte.MAX_VALUE;
        assertEquals(value, lessOrEquals("test value", value, boundary));
    }


    @Test
    public void isLessOrEquals_minimum() {
        byte value = Byte.MIN_VALUE;
        byte boundary = 0;
        assertEquals(value, lessOrEquals("test value", value, boundary));
    }


    @Test(expected = IllegalArgumentException.class)
    public void isLessOrEquals_greater() {
        byte value = 1;
        byte boundary = 0;
        lessOrEquals("test value", value, boundary);
    }


    @Test
    public void isLessOrEqual_equals() {
        byte value = 1;
        byte boundary = 1;
        lessOrEquals("test value", value, boundary);
    }


    @Test
    public void isLessOrEquals_equalsMinimums() {
        byte value = Byte.MIN_VALUE;
        byte boundary = Byte.MIN_VALUE;
        greaterOrEquals("test value", value, boundary);
    }


    @Test
    public void isLessOrEquals_equalsMaximums() {
        byte value = Byte.MAX_VALUE;
        byte boundary = Byte.MAX_VALUE;
        greaterOrEquals("test value", value, boundary);
    }


    // greater


    @Test
    public void isGreater_positive() {
        byte value = 2;
        byte boundary = 1;
        assertEquals(value, greater("test value", value, boundary));
    }


    @Test
    public void isGreater_negative() {
        byte value = -1;
        byte boundary = -2;
        assertEquals(value, greater("test value", value, boundary));
    }


    @Test
    public void isGreater_zero() {
        byte value = 1;
        byte boundary = 0;
        assertEquals(value, greater("test value", value, boundary));
    }


    @Test
    public void isGreater_maximumBoundary() {
        byte value = 0;
        byte boundary = Byte.MIN_VALUE;
        assertEquals(value, greater("test value", value, boundary));
    }


    @Test
    public void isGreater_maximum() {
        byte value = Byte.MAX_VALUE;
        byte boundary = 0;
        assertEquals(value, greater("test value", value, boundary));
    }


    @Test(expected = IllegalArgumentException.class)
    public void isGreater_less() {
        byte value = 0;
        byte boundary = 1;
        greater("test value", value, boundary);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isGreater_equals() {
        byte value = 1;
        byte boundary = 1;
        greater("test value", value, boundary);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isGreater_minimums() {
        byte value = Byte.MIN_VALUE;
        byte boundary = Byte.MIN_VALUE;
        less("test value", value, boundary);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isGreater_maximums() {
        byte value = Byte.MAX_VALUE;
        byte boundary = Byte.MAX_VALUE;
        less("test value", value, boundary);
    }


    // greaterOrEquals


    @Test
    public void isGreaterOrEquals_positive() {
        byte value = 2;
        byte boundary = 1;
        assertEquals(value, greaterOrEquals("test value", value, boundary));
    }


    @Test
    public void isGreaterOrEquals_negative() {
        byte value = -1;
        byte boundary = -2;
        assertEquals(value, greaterOrEquals("test value", value, boundary));
    }


    @Test
    public void isGreaterOrEquals_zero() {
        byte value = 1;
        byte boundary = 0;
        assertEquals(value, greaterOrEquals("test value", value, boundary));
    }


    @Test
    public void isGreaterOrEquals_minimumBoundary() {
        byte value = 0;
        byte boundary = Byte.MIN_VALUE;
        assertEquals(value, greaterOrEquals("test value", value, boundary));
    }


    @Test
    public void isGreaterOrEquals_maximum() {
        byte value = Byte.MAX_VALUE;
        byte boundary = 0;
        assertEquals(value, greaterOrEquals("test value", value, boundary));
    }


    @Test(expected = IllegalArgumentException.class)
    public void isGreaterOrEquals_greater() {
        byte value = 0;
        byte boundary = 1;
        greaterOrEquals("test value", value, boundary);
    }


    @Test
    public void isGreaterOrEquals_equals() {
        byte value = 1;
        byte boundary = 1;
        greaterOrEquals("test value", value, boundary);
    }


    @Test
    public void isGreaterOrEquals_minimums() {
        byte value = Byte.MIN_VALUE;
        byte boundary = Byte.MIN_VALUE;
        greaterOrEquals("test value", value, boundary);
    }


    @Test
    public void isGreaterOrEquals_maximums() {
        byte value = Byte.MAX_VALUE;
        byte boundary = Byte.MAX_VALUE;
        greaterOrEquals("test value", value, boundary);
    }


    // inRangeExclusive


    @Test
    public void isBetween_positive() {
        byte value = 2;
        byte min = 1;
        byte max = 3;
        assertEquals(value, inRangeExclusive("test value", value, min, max));
    }


    @Test
    public void isBetween_negative() {
        byte value = -2;
        byte min = -3;
        byte max = -1;
        assertEquals(value, inRangeExclusive("test value", value, min, max));
    }


    @Test
    public void isBetween_zero() {
        byte value = 0;
        byte min = -1;
        byte max = 1;
        assertEquals(value, inRangeExclusive("test value", value, min, max));
    }


    @Test
    public void isBetween_minIsZero() {
        byte value = 1;
        byte min = 0;
        byte max = 2;
        assertEquals(value, inRangeExclusive("test value", value, min, max));
    }


    @Test
    public void isBetween_maxIsZero() {
        byte value = -1;
        byte min = -2;
        byte max = 0;
        assertEquals(value, inRangeExclusive("test value", value, min, max));
    }


    @Test
    public void isBetween_cornerBoundaries() {
        byte value = 0;
        byte min = Byte.MIN_VALUE;
        byte max = Byte.MAX_VALUE;
        assertEquals(value, inRangeExclusive("test value", value, min, max));
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_greaterThanMax() {
        byte value = 3;
        byte min = 1;
        byte max = 2;
        inRangeExclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_equalMax() {
        byte value = 2;
        byte min = 1;
        byte max = 2;
        inRangeExclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_lessThanMin() {
        byte value = 3;
        byte min = 1;
        byte max = 2;
        inRangeExclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_equalMin() {
        byte value = 1;
        byte min = 1;
        byte max = 2;
        inRangeExclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_equalMinimalMin() {
        byte value = Byte.MIN_VALUE;
        byte min = Byte.MIN_VALUE;
        byte max = 0;
        inRangeExclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_equalMaximalMax() {
        byte value = Byte.MAX_VALUE;
        byte min = 0;
        byte max = Byte.MAX_VALUE;
        inRangeExclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_maxEqualsMin() {
        byte value = 0;
        byte min = 1;
        byte max = 1;
        inRangeExclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_maxLessThanMin() {
        byte value = 0;
        byte min = 2;
        byte max = 1;
        inRangeExclusive("test value", value, min, max);
    }

    // inRangeInclusive


    @Test
    public void isBetweenOrEquals_positive() {
        byte value = 2;
        byte min = 1;
        byte max = 3;
        assertEquals(value, inRangeInclusive("test value", value, min, max));
    }


    @Test
    public void isBetweenOrEquals_negative() {
        byte value = -2;
        byte min = -3;
        byte max = -1;
        assertEquals(value, inRangeInclusive("test value", value, min, max));
    }


    @Test
    public void isBetweenOrEquals_zero() {
        byte value = 0;
        byte min = -1;
        byte max = 1;
        assertEquals(value, inRangeInclusive("test value", value, min, max));
    }


    @Test
    public void isBetweenOrEquals_minIsZero() {
        byte value = 1;
        byte min = 0;
        byte max = 2;
        assertEquals(value, inRangeInclusive("test value", value, min, max));
    }


    @Test
    public void isBetweenOrEquals_maxIsZero() {
        byte value = -1;
        byte min = -2;
        byte max = 0;
        assertEquals(value, inRangeInclusive("test value", value, min, max));
    }


    @Test
    public void isBetweenOrEquals_cornerBoundaries() {
        byte value = 0;
        byte min = Byte.MIN_VALUE;
        byte max = Byte.MAX_VALUE;
        assertEquals(value, inRangeInclusive("test value", value, min, max));
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetweenOrEquals_greaterThanMax() {
        byte value = 3;
        byte min = 1;
        byte max = 2;
        inRangeInclusive("test value", value, min, max);
    }


    @Test
    public void isBetweenOrEquals_equalsMax() {
        byte value = 2;
        byte min = 1;
        byte max = 2;
        inRangeInclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetweenOrEquals_lessThanMin() {
        byte value = 3;
        byte min = 1;
        byte max = 2;
        inRangeInclusive("test value", value, min, max);
    }


    @Test
    public void isBetweenOrEquals_equalsMin() {
        byte value = 1;
        byte min = 1;
        byte max = 2;
        inRangeInclusive("test value", value, min, max);
    }


    @Test
    public void isBetweenOrEquals_equalsMinimalMin() {
        byte value = Byte.MIN_VALUE;
        byte min = Byte.MIN_VALUE;
        byte max = 0;
        inRangeInclusive("test value", value, min, max);
    }


    @Test
    public void isBetweenOrEquals_equalsMaximalMax() {
        byte value = Byte.MAX_VALUE;
        byte min = 0;
        byte max = Byte.MAX_VALUE;
        inRangeInclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetweenOrEquals_maxEqualsMin() {
        byte value = 0;
        byte min = 1;
        byte max = 1;
        inRangeInclusive("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetweenOrEquals_maxLessThanMin() {
        byte value = 0;
        byte min = 2;
        byte max = 1;
        inRangeInclusive("test value", value, min, max);
    }


    @Test
    public void isBetweenOrEquals_equalsBoundaries() {
        byte value = 1;
        byte min = 1;
        byte max = 1;
        inRangeInclusive("test value", value, min, max);
    }


    //minimumIfLess


    @Test
    public void minimumIfLess_greaterThanBoundary() {
        byte value = 1;
        byte boundary = 0;
        assertEquals(value, minimumIfLess(value, boundary));
    }


    @Test
    public void minimumIfLess_lessThanBoundary() {
        byte value = 0;
        byte boundary = 1;
        assertEquals(boundary, minimumIfLess(value, boundary));
    }


    @Test
    public void minimumIfLess_equalsToBoundary() {
        byte value = 0;
        byte boundary = 0;
        assertEquals(value, minimumIfLess(value, boundary));
    }


    //maximumIfGreater


    @Test
    public void maximumIfGreater_lessThanBoundary() {
        byte value = 1;
        byte boundary = 2;
        assertEquals(value, maximumIfGreater(value, boundary));
    }


    @Test
    public void maximumIfGreater_greaterThanBoundary() {
        byte value = 1;
        byte boundary = 0;
        assertEquals(boundary, maximumIfGreater(value, boundary));
    }


    @Test
    public void maximumIfGreater_equalsToBoundary() {
        byte value = 0;
        byte boundary = 0;
        assertEquals(value, maximumIfGreater(value, boundary));
    }
}