package xyz.enhorse.commons;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static xyz.enhorse.commons.Validate.*;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         11.07.2016
 */
public class ValidateByteComparisonTest {


    // isLess


    @Test
    public void isLess_positive() throws Exception {
        byte value = 1;
        byte boundary = 2;
        assertEquals(value, isLess("test value", value, boundary));
    }


    @Test
    public void isLess_negative() throws Exception {
        byte value = -2;
        byte boundary = -1;
        assertEquals(value, isLess("test value", value, boundary));
    }


    @Test
    public void isLess_zero() throws Exception {
        byte value = 0;
        byte boundary = 1;
        assertEquals(value, isLess("test value", value, boundary));
    }


    @Test
    public void isLess_maximumBoundary() throws Exception {
        byte value = 0;
        byte boundary = Byte.MAX_VALUE;
        assertEquals(value, isLess("test value", value, boundary));
    }


    @Test
    public void isLess_minimum() throws Exception {
        byte value = Byte.MIN_VALUE;
        byte boundary = 0;
        assertEquals(value, isLess("test value", value, boundary));
    }


    @Test(expected = IllegalArgumentException.class)
    public void isLess_greater() throws Exception {
        byte value = 1;
        byte boundary = 0;
        isLess("test value", value, boundary);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isLess_equalsToBoundary() throws Exception {
        byte value = 1;
        byte boundary = 1;
        isLess("test value", value, boundary);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isLess_minimums() throws Exception {
        byte value = Byte.MIN_VALUE;
        byte boundary = Byte.MIN_VALUE;
        isLess("test value", value, boundary);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isLess_maximums() throws Exception {
        byte value = Byte.MAX_VALUE;
        byte boundary = Byte.MAX_VALUE;
        isLess("test value", value, boundary);
    }


    // isLessOrEquals


    @Test
    public void isLessOrEquals_positive() throws Exception {
        byte value = 1;
        byte boundary = 2;
        assertEquals(value, isLessOrEquals("test value", value, boundary));
    }


    @Test
    public void isLessOrEquals_negative() throws Exception {
        byte value = -2;
        byte boundary = -1;
        assertEquals(value, isLessOrEquals("test value", value, boundary));
    }


    @Test
    public void isLessOrEquals_zero() throws Exception {
        byte value = 0;
        byte boundary = 1;
        assertEquals(value, isLessOrEquals("test value", value, boundary));
    }


    @Test
    public void isLessOrEquals_maximumBoundary() throws Exception {
        byte value = 0;
        byte boundary = Byte.MAX_VALUE;
        assertEquals(value, isLessOrEquals("test value", value, boundary));
    }


    @Test
    public void isLessOrEquals_minimum() throws Exception {
        byte value = Byte.MIN_VALUE;
        byte boundary = 0;
        assertEquals(value, isLessOrEquals("test value", value, boundary));
    }


    @Test(expected = IllegalArgumentException.class)
    public void isLessOrEquals_greater() throws Exception {
        byte value = 1;
        byte boundary = 0;
        isLessOrEquals("test value", value, boundary);
    }


    @Test
    public void isLessOrEqual_equals() throws Exception {
        byte value = 1;
        byte boundary = 1;
        isLessOrEquals("test value", value, boundary);
    }


    @Test
    public void isLessOrEquals_equalsMinimums() throws Exception {
        byte value = Byte.MIN_VALUE;
        byte boundary = Byte.MIN_VALUE;
        isGreaterOrEquals("test value", value, boundary);
    }


    @Test
    public void isLessOrEquals_equalsMaximums() throws Exception {
        byte value = Byte.MAX_VALUE;
        byte boundary = Byte.MAX_VALUE;
        isGreaterOrEquals("test value", value, boundary);
    }


    // isGreater


    @Test
    public void isGreater_positive() throws Exception {
        byte value = 2;
        byte boundary = 1;
        assertEquals(value, isGreater("test value", value, boundary));
    }


    @Test
    public void isGreater_negative() throws Exception {
        byte value = -1;
        byte boundary = -2;
        assertEquals(value, isGreater("test value", value, boundary));
    }


    @Test
    public void isGreater_zero() throws Exception {
        byte value = 1;
        byte boundary = 0;
        assertEquals(value, isGreater("test value", value, boundary));
    }


    @Test
    public void isGreater_maximumBoundary() throws Exception {
        byte value = 0;
        byte boundary = Byte.MIN_VALUE;
        assertEquals(value, isGreater("test value", value, boundary));
    }


    @Test
    public void isGreater_maximum() throws Exception {
        byte value = Byte.MAX_VALUE;
        byte boundary = 0;
        assertEquals(value, isGreater("test value", value, boundary));
    }


    @Test(expected = IllegalArgumentException.class)
    public void isGreater_less() throws Exception {
        byte value = 0;
        byte boundary = 1;
        isGreater("test value", value, boundary);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isGreater_equals() throws Exception {
        byte value = 1;
        byte boundary = 1;
        isGreater("test value", value, boundary);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isGreater_minimums() throws Exception {
        byte value = Byte.MIN_VALUE;
        byte boundary = Byte.MIN_VALUE;
        isLess("test value", value, boundary);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isGreater_maximums() throws Exception {
        byte value = Byte.MAX_VALUE;
        byte boundary = Byte.MAX_VALUE;
        isLess("test value", value, boundary);
    }


    // isGreaterOrEquals


    @Test
    public void isGreaterOrEquals_positive() throws Exception {
        byte value = 2;
        byte boundary = 1;
        assertEquals(value, isGreaterOrEquals("test value", value, boundary));
    }


    @Test
    public void isGreaterOrEquals_negative() throws Exception {
        byte value = -1;
        byte boundary = -2;
        assertEquals(value, isGreaterOrEquals("test value", value, boundary));
    }


    @Test
    public void isGreaterOrEquals_zero() throws Exception {
        byte value = 1;
        byte boundary = 0;
        assertEquals(value, isGreaterOrEquals("test value", value, boundary));
    }


    @Test
    public void isGreaterOrEquals_minimumBoundary() throws Exception {
        byte value = 0;
        byte boundary = Byte.MIN_VALUE;
        assertEquals(value, isGreaterOrEquals("test value", value, boundary));
    }


    @Test
    public void isGreaterOrEquals_maximum() throws Exception {
        byte value = Byte.MAX_VALUE;
        byte boundary = 0;
        assertEquals(value, isGreaterOrEquals("test value", value, boundary));
    }


    @Test(expected = IllegalArgumentException.class)
    public void isGreaterOrEquals_greater() throws Exception {
        byte value = 0;
        byte boundary = 1;
        isGreaterOrEquals("test value", value, boundary);
    }


    @Test
    public void isGreaterOrEquals_equals() throws Exception {
        byte value = 1;
        byte boundary = 1;
        isGreaterOrEquals("test value", value, boundary);
    }


    @Test
    public void isGreaterOrEquals_minimums() throws Exception {
        byte value = Byte.MIN_VALUE;
        byte boundary = Byte.MIN_VALUE;
        isGreaterOrEquals("test value", value, boundary);
    }


    @Test
    public void isGreaterOrEquals_maximums() throws Exception {
        byte value = Byte.MAX_VALUE;
        byte boundary = Byte.MAX_VALUE;
        isGreaterOrEquals("test value", value, boundary);
    }


    // isBetween


    @Test
    public void isBetween_positive() throws Exception {
        byte value = 2;
        byte min = 1;
        byte max = 3;
        assertEquals(value, isBetween("test value", value, min, max));
    }


    @Test
    public void isBetween_negative() throws Exception {
        byte value = -2;
        byte min = -3;
        byte max = -1;
        assertEquals(value, isBetween("test value", value, min, max));
    }


    @Test
    public void isBetween_zero() throws Exception {
        byte value = 0;
        byte min = -1;
        byte max = 1;
        assertEquals(value, isBetween("test value", value, min, max));
    }


    @Test
    public void isBetween_minIsZero() throws Exception {
        byte value = 1;
        byte min = 0;
        byte max = 2;
        assertEquals(value, isBetween("test value", value, min, max));
    }


    @Test
    public void isBetween_maxIsZero() throws Exception {
        byte value = -1;
        byte min = -2;
        byte max = 0;
        assertEquals(value, isBetween("test value", value, min, max));
    }


    @Test
    public void isBetween_cornerBoundaries() throws Exception {
        byte value = 0;
        byte min = Byte.MIN_VALUE;
        byte max = Byte.MAX_VALUE;
        assertEquals(value, isBetween("test value", value, min, max));
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_greaterThanMax() throws Exception {
        byte value = 3;
        byte min = 1;
        byte max = 2;
        isBetween("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_equalMax() throws Exception {
        byte value = 2;
        byte min = 1;
        byte max = 2;
        isBetween("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_lessThanMin() throws Exception {
        byte value = 3;
        byte min = 1;
        byte max = 2;
        isBetween("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_equalMin() throws Exception {
        byte value = 1;
        byte min = 1;
        byte max = 2;
        isBetween("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_equalMinimalMin() throws Exception {
        byte value = Byte.MIN_VALUE;
        byte min = Byte.MIN_VALUE;
        byte max = 0;
        isBetween("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_equalMaximalMax() throws Exception {
        byte value = Byte.MAX_VALUE;
        byte min = 0;
        byte max = Byte.MAX_VALUE;
        isBetween("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_maxEqualsMin() throws Exception {
        byte value = 0;
        byte min = 1;
        byte max = 1;
        isBetween("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_maxLessThanMin() throws Exception {
        byte value = 0;
        byte min = 2;
        byte max = 1;
        isBetween("test value", value, min, max);
    }

    // isBetweenOrEquals


    @Test
    public void isBetweenOrEquals_positive() throws Exception {
        byte value = 2;
        byte min = 1;
        byte max = 3;
        assertEquals(value, isBetweenOrEquals("test value", value, min, max));
    }


    @Test
    public void isBetweenOrEquals_negative() throws Exception {
        byte value = -2;
        byte min = -3;
        byte max = -1;
        assertEquals(value, isBetweenOrEquals("test value", value, min, max));
    }


    @Test
    public void isBetweenOrEquals_zero() throws Exception {
        byte value = 0;
        byte min = -1;
        byte max = 1;
        assertEquals(value, isBetweenOrEquals("test value", value, min, max));
    }


    @Test
    public void isBetweenOrEquals_minIsZero() throws Exception {
        byte value = 1;
        byte min = 0;
        byte max = 2;
        assertEquals(value, isBetweenOrEquals("test value", value, min, max));
    }


    @Test
    public void isBetweenOrEquals_maxIsZero() throws Exception {
        byte value = -1;
        byte min = -2;
        byte max = 0;
        assertEquals(value, isBetweenOrEquals("test value", value, min, max));
    }


    @Test
    public void isBetweenOrEquals_cornerBoundaries() throws Exception {
        byte value = 0;
        byte min = Byte.MIN_VALUE;
        byte max = Byte.MAX_VALUE;
        assertEquals(value, isBetweenOrEquals("test value", value, min, max));
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetweenOrEquals_greaterThanMax() throws Exception {
        byte value = 3;
        byte min = 1;
        byte max = 2;
        isBetweenOrEquals("test value", value, min, max);
    }


    @Test
    public void isBetweenOrEquals_equalsMax() throws Exception {
        byte value = 2;
        byte min = 1;
        byte max = 2;
        isBetweenOrEquals("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetweenOrEquals_lessThanMin() throws Exception {
        byte value = 3;
        byte min = 1;
        byte max = 2;
        isBetweenOrEquals("test value", value, min, max);
    }


    @Test
    public void isBetweenOrEquals_equalsMin() throws Exception {
        byte value = 1;
        byte min = 1;
        byte max = 2;
        isBetweenOrEquals("test value", value, min, max);
    }


    @Test
    public void isBetweenOrEquals_equalsMinimalMin() throws Exception {
        byte value = Byte.MIN_VALUE;
        byte min = Byte.MIN_VALUE;
        byte max = 0;
        isBetweenOrEquals("test value", value, min, max);
    }


    @Test
    public void isBetweenOrEquals_equalsMaximalMax() throws Exception {
        byte value = Byte.MAX_VALUE;
        byte min = 0;
        byte max = Byte.MAX_VALUE;
        isBetweenOrEquals("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetweenOrEquals_maxEqualsMin() throws Exception {
        byte value = 0;
        byte min = 1;
        byte max = 1;
        isBetweenOrEquals("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetweenOrEquals_maxLessThanMin() throws Exception {
        byte value = 0;
        byte min = 2;
        byte max = 1;
        isBetweenOrEquals("test value", value, min, max);
    }


    @Test
    public void isBetweenOrEquals_equalsBoundaries() throws Exception {
        byte value = 1;
        byte min = 1;
        byte max = 1;
        isBetweenOrEquals("test value", value, min, max);
    }
}