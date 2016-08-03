package xyz.enhorse.commons;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static xyz.enhorse.commons.Validate.*;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         11.07.2016
 */
public class ValidateIntComparisonTest {


    // isLess


    @Test
    public void isLess_positive() throws Exception {
        int value = 1;
        int boundary = 2;
        assertEquals(value, isLess("test value", value, boundary));
    }


    @Test
    public void isLess_negative() throws Exception {
        int value = -2;
        int boundary = -1;
        assertEquals(value, isLess("test value", value, boundary));
    }


    @Test
    public void isLess_zero() throws Exception {
        int value = 0;
        int boundary = 1;
        assertEquals(value, isLess("test value", value, boundary));
    }


    @Test
    public void isLess_maximumBoundary() throws Exception {
        int value = 0;
        int boundary = Integer.MAX_VALUE;
        assertEquals(value, isLess("test value", value, boundary));
    }


    @Test
    public void isLess_minimum() throws Exception {
        int value = Integer.MIN_VALUE;
        int boundary = 0;
        assertEquals(value, isLess("test value", value, boundary));
    }


    @Test(expected = IllegalArgumentException.class)
    public void isLess_greater() throws Exception {
        int value = 1;
        int boundary = 0;
        isLess("test value", value, boundary);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isLess_equalsToBoundary() throws Exception {
        int value = 1;
        int boundary = 1;
        isLess("test value", value, boundary);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isLess_minimums() throws Exception {
        int value = Integer.MIN_VALUE;
        int boundary = Integer.MIN_VALUE;
        isLess("test value", value, boundary);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isLess_maximums() throws Exception {
        int value = Integer.MAX_VALUE;
        int boundary = Integer.MAX_VALUE;
        isLess("test value", value, boundary);
    }


    // isLessOrEquals


    @Test
    public void isLessOrEquals_positive() throws Exception {
        int value = 1;
        int boundary = 2;
        assertEquals(value, isLessOrEquals("test value", value, boundary));
    }


    @Test
    public void isLessOrEquals_negative() throws Exception {
        int value = -2;
        int boundary = -1;
        assertEquals(value, isLessOrEquals("test value", value, boundary));
    }


    @Test
    public void isLessOrEquals_zero() throws Exception {
        int value = 0;
        int boundary = 1;
        assertEquals(value, isLessOrEquals("test value", value, boundary));
    }


    @Test
    public void isLessOrEquals_maximumBoundary() throws Exception {
        int value = 0;
        int boundary = Integer.MAX_VALUE;
        assertEquals(value, isLessOrEquals("test value", value, boundary));
    }


    @Test
    public void isLessOrEquals_minimum() throws Exception {
        int value = Integer.MIN_VALUE;
        int boundary = 0;
        assertEquals(value, isLessOrEquals("test value", value, boundary));
    }


    @Test(expected = IllegalArgumentException.class)
    public void isLessOrEquals_greater() throws Exception {
        int value = 1;
        int boundary = 0;
        isLessOrEquals("test value", value, boundary);
    }


    @Test
    public void isLessOrEqual_equals() throws Exception {
        int value = 1;
        int boundary = 1;
        isLessOrEquals("test value", value, boundary);
    }


    @Test
    public void isLessOrEquals_equalsMinimums() throws Exception {
        int value = Integer.MIN_VALUE;
        int boundary = Integer.MIN_VALUE;
        isGreaterOrEquals("test value", value, boundary);
    }


    @Test
    public void isLessOrEquals_equalsMaximums() throws Exception {
        int value = Integer.MAX_VALUE;
        int boundary = Integer.MAX_VALUE;
        isGreaterOrEquals("test value", value, boundary);
    }


    // isGreater


    @Test
    public void isGreater_positive() throws Exception {
        int value = 2;
        int boundary = 1;
        assertEquals(value, isGreater("test value", value, boundary));
    }


    @Test
    public void isGreater_negative() throws Exception {
        int value = -1;
        int boundary = -2;
        assertEquals(value, isGreater("test value", value, boundary));
    }


    @Test
    public void isGreater_zero() throws Exception {
        int value = 1;
        int boundary = 0;
        assertEquals(value, isGreater("test value", value, boundary));
    }


    @Test
    public void isGreater_maximumBoundary() throws Exception {
        int value = 0;
        int boundary = Integer.MIN_VALUE;
        assertEquals(value, isGreater("test value", value, boundary));
    }


    @Test
    public void isGreater_maximum() throws Exception {
        int value = Integer.MAX_VALUE;
        int boundary = 0;
        assertEquals(value, isGreater("test value", value, boundary));
    }


    @Test(expected = IllegalArgumentException.class)
    public void isGreater_less() throws Exception {
        int value = 0;
        int boundary = 1;
        isGreater("test value", value, boundary);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isGreater_equals() throws Exception {
        int value = 1;
        int boundary = 1;
        isGreater("test value", value, boundary);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isGreater_minimums() throws Exception {
        int value = Integer.MIN_VALUE;
        int boundary = Integer.MIN_VALUE;
        isLess("test value", value, boundary);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isGreater_maximums() throws Exception {
        int value = Integer.MAX_VALUE;
        int boundary = Integer.MAX_VALUE;
        isLess("test value", value, boundary);
    }


    // isGreaterOrEquals


    @Test
    public void isGreaterOrEquals_positive() throws Exception {
        int value = 2;
        int boundary = 1;
        assertEquals(value, isGreaterOrEquals("test value", value, boundary));
    }


    @Test
    public void isGreaterOrEquals_negative() throws Exception {
        int value = -1;
        int boundary = -2;
        assertEquals(value, isGreaterOrEquals("test value", value, boundary));
    }


    @Test
    public void isGreaterOrEquals_zero() throws Exception {
        int value = 1;
        int boundary = 0;
        assertEquals(value, isGreaterOrEquals("test value", value, boundary));
    }


    @Test
    public void isGreaterOrEquals_minimumBoundary() throws Exception {
        int value = 0;
        int boundary = Integer.MIN_VALUE;
        assertEquals(value, isGreaterOrEquals("test value", value, boundary));
    }


    @Test
    public void isGreaterOrEquals_maximum() throws Exception {
        int value = Integer.MAX_VALUE;
        int boundary = 0;
        assertEquals(value, isGreaterOrEquals("test value", value, boundary));
    }


    @Test(expected = IllegalArgumentException.class)
    public void isGreaterOrEquals_greater() throws Exception {
        int value = 0;
        int boundary = 1;
        isGreaterOrEquals("test value", value, boundary);
    }


    @Test
    public void isGreaterOrEquals_equals() throws Exception {
        int value = 1;
        int boundary = 1;
        isGreaterOrEquals("test value", value, boundary);
    }


    @Test
    public void isGreaterOrEquals_minimums() throws Exception {
        int value = Integer.MIN_VALUE;
        int boundary = Integer.MIN_VALUE;
        isGreaterOrEquals("test value", value, boundary);
    }


    @Test
    public void isGreaterOrEquals_maximums() throws Exception {
        int value = Integer.MAX_VALUE;
        int boundary = Integer.MAX_VALUE;
        isGreaterOrEquals("test value", value, boundary);
    }


    // isBetween


    @Test
    public void isBetween_positive() throws Exception {
        int value = 2;
        int min = 1;
        int max = 3;
        assertEquals(value, isBetween("test value", value, min, max));
    }


    @Test
    public void isBetween_negative() throws Exception {
        int value = -2;
        int min = -3;
        int max = -1;
        assertEquals(value, isBetween("test value", value, min, max));
    }


    @Test
    public void isBetween_zero() throws Exception {
        int value = 0;
        int min = -1;
        int max = 1;
        assertEquals(value, isBetween("test value", value, min, max));
    }


    @Test
    public void isBetween_minIsZero() throws Exception {
        int value = 1;
        int min = 0;
        int max = 2;
        assertEquals(value, isBetween("test value", value, min, max));
    }


    @Test
    public void isBetween_maxIsZero() throws Exception {
        int value = -1;
        int min = -2;
        int max = 0;
        assertEquals(value, isBetween("test value", value, min, max));
    }


    @Test
    public void isBetween_cornerBoundaries() throws Exception {
        int value = 0;
        int min = Integer.MIN_VALUE;
        int max = Integer.MAX_VALUE;
        assertEquals(value, isBetween("test value", value, min, max));
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_greaterThanMax() throws Exception {
        int value = 3;
        int min = 1;
        int max = 2;
        isBetween("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_equalMax() throws Exception {
        int value = 2;
        int min = 1;
        int max = 2;
        isBetween("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_lessThanMin() throws Exception {
        int value = 3;
        int min = 1;
        int max = 2;
        isBetween("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_equalMin() throws Exception {
        int value = 1;
        int min = 1;
        int max = 2;
        isBetween("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_equalMinimalMin() throws Exception {
        int value = Integer.MIN_VALUE;
        int min = Integer.MIN_VALUE;
        int max = 0;
        isBetween("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_equalMaximalMax() throws Exception {
        int value = Integer.MAX_VALUE;
        int min = 0;
        int max = Integer.MAX_VALUE;
        isBetween("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_maxEqualsMin() throws Exception {
        int value = 0;
        int min = 1;
        int max = 1;
        isBetween("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_maxLessThanMin() throws Exception {
        int value = 0;
        int min = 2;
        int max = 1;
        isBetween("test value", value, min, max);
    }

    // isBetweenOrEquals


    @Test
    public void isBetweenOrEquals_positive() throws Exception {
        int value = 2;
        int min = 1;
        int max = 3;
        assertEquals(value, isBetweenOrEquals("test value", value, min, max));
    }


    @Test
    public void isBetweenOrEquals_negative() throws Exception {
        int value = -2;
        int min = -3;
        int max = -1;
        assertEquals(value, isBetweenOrEquals("test value", value, min, max));
    }


    @Test
    public void isBetweenOrEquals_zero() throws Exception {
        int value = 0;
        int min = -1;
        int max = 1;
        assertEquals(value, isBetweenOrEquals("test value", value, min, max));
    }


    @Test
    public void isBetweenOrEquals_minIsZero() throws Exception {
        int value = 1;
        int min = 0;
        int max = 2;
        assertEquals(value, isBetweenOrEquals("test value", value, min, max));
    }


    @Test
    public void isBetweenOrEquals_maxIsZero() throws Exception {
        int value = -1;
        int min = -2;
        int max = 0;
        assertEquals(value, isBetweenOrEquals("test value", value, min, max));
    }


    @Test
    public void isBetweenOrEquals_cornerBoundaries() throws Exception {
        int value = 0;
        int min = Integer.MIN_VALUE;
        int max = Integer.MAX_VALUE;
        assertEquals(value, isBetweenOrEquals("test value", value, min, max));
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetweenOrEquals_greaterThanMax() throws Exception {
        int value = 3;
        int min = 1;
        int max = 2;
        isBetweenOrEquals("test value", value, min, max);
    }


    @Test
    public void isBetweenOrEquals_equalsMax() throws Exception {
        int value = 2;
        int min = 1;
        int max = 2;
        isBetweenOrEquals("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetweenOrEquals_lessThanMin() throws Exception {
        int value = 3;
        int min = 1;
        int max = 2;
        isBetweenOrEquals("test value", value, min, max);
    }


    @Test
    public void isBetweenOrEquals_equalsMin() throws Exception {
        int value = 1;
        int min = 1;
        int max = 2;
        isBetweenOrEquals("test value", value, min, max);
    }


    @Test
    public void isBetweenOrEquals_equalsMinimalMin() throws Exception {
        int value = Integer.MIN_VALUE;
        int min = Integer.MIN_VALUE;
        int max = 0;
        isBetweenOrEquals("test value", value, min, max);
    }


    @Test
    public void isBetweenOrEquals_equalsMaximalMax() throws Exception {
        int value = Integer.MAX_VALUE;
        int min = 0;
        int max = Integer.MAX_VALUE;
        isBetweenOrEquals("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetweenOrEquals_maxEqualsMin() throws Exception {
        int value = 0;
        int min = 1;
        int max = 1;
        isBetweenOrEquals("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetweenOrEquals_maxLessThanMin() throws Exception {
        int value = 0;
        int min = 2;
        int max = 1;
        isBetweenOrEquals("test value", value, min, max);
    }


    @Test
    public void isBetweenOrEquals_equalsBoundaries() throws Exception {
        int value = 1;
        int min = 1;
        int max = 1;
        isBetweenOrEquals("test value", value, min, max);
    }
}