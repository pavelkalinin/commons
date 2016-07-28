package xyz.enhorse.commons;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static xyz.enhorse.commons.Validate.*;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         11.07.2016
 */
public class ValidateShortComparisonTest {


    // isLess


    @Test
    public void isLess_positive() throws Exception {
        short value = 1;
        short boundary = 2;
        assertEquals(value, isLess("test value", value, boundary));
    }


    @Test
    public void isLess_negative() throws Exception {
        short value = -2;
        short boundary = -1;
        assertEquals(value, isLess("test value", value, boundary));
    }


    @Test
    public void isLess_zero() throws Exception {
        short value = 0;
        short boundary = 1;
        assertEquals(value, isLess("test value", value, boundary));
    }


    @Test
    public void isLess_maximumBoundary() throws Exception {
        short value = 0;
        short boundary = Short.MAX_VALUE;
        assertEquals(value, isLess("test value", value, boundary));
    }


    @Test
    public void isLess_minimum() throws Exception {
        short value = Short.MIN_VALUE;
        short boundary = 0;
        assertEquals(value, isLess("test value", value, boundary));
    }


    @Test(expected = IllegalArgumentException.class)
    public void isLess_greater() throws Exception {
        short value = 1;
        short boundary = 0;
        isLess("test value", value, boundary);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isLess_equalsToBoundary() throws Exception {
        short value = 1;
        short boundary = 1;
        isLess("test value", value, boundary);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isLess_minimums() throws Exception {
        short value = Short.MIN_VALUE;
        short boundary = Short.MIN_VALUE;
        isLess("test value", value, boundary);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isLess_maximums() throws Exception {
        short value = Short.MAX_VALUE;
        short boundary = Short.MAX_VALUE;
        isLess("test value", value, boundary);
    }


    // isLessOrEquals


    @Test
    public void isLessOrEquals_positive() throws Exception {
        short value = 1;
        short boundary = 2;
        assertEquals(value, isLessOrEquals("test value", value, boundary));
    }


    @Test
    public void isLessOrEquals_negative() throws Exception {
        short value = -2;
        short boundary = -1;
        assertEquals(value, isLessOrEquals("test value", value, boundary));
    }


    @Test
    public void isLessOrEquals_zero() throws Exception {
        short value = 0;
        short boundary = 1;
        assertEquals(value, isLessOrEquals("test value", value, boundary));
    }


    @Test
    public void isLessOrEquals_maximumBoundary() throws Exception {
        short value = 0;
        short boundary = Short.MAX_VALUE;
        assertEquals(value, isLessOrEquals("test value", value, boundary));
    }


    @Test
    public void isLessOrEquals_minimum() throws Exception {
        short value = Short.MIN_VALUE;
        short boundary = 0;
        assertEquals(value, isLessOrEquals("test value", value, boundary));
    }


    @Test(expected = IllegalArgumentException.class)
    public void isLessOrEquals_greater() throws Exception {
        short value = 1;
        short boundary = 0;
        isLessOrEquals("test value", value, boundary);
    }


    @Test
    public void isLessOrEqual_equals() throws Exception {
        short value = 1;
        short boundary = 1;
        isLessOrEquals("test value", value, boundary);
    }


    @Test
    public void isLessOrEquals_equalsMinimums() throws Exception {
        short value = Short.MIN_VALUE;
        short boundary = Short.MIN_VALUE;
        isGreaterOrEquals("test value", value, boundary);
    }


    @Test
    public void isLessOrEquals_equalsMaximums() throws Exception {
        short value = Short.MAX_VALUE;
        short boundary = Short.MAX_VALUE;
        isGreaterOrEquals("test value", value, boundary);
    }


    // isGreater


    @Test
    public void isGreater_positive() throws Exception {
        short value = 2;
        short boundary = 1;
        assertEquals(value, isGreater("test value", value, boundary));
    }


    @Test
    public void isGreater_negative() throws Exception {
        short value = -1;
        short boundary = -2;
        assertEquals(value, isGreater("test value", value, boundary));
    }


    @Test
    public void isGreater_zero() throws Exception {
        short value = 1;
        short boundary = 0;
        assertEquals(value, isGreater("test value", value, boundary));
    }


    @Test
    public void isGreater_maximumBoundary() throws Exception {
        short value = 0;
        short boundary = Short.MIN_VALUE;
        assertEquals(value, isGreater("test value", value, boundary));
    }


    @Test
    public void isGreater_maximum() throws Exception {
        short value = Short.MAX_VALUE;
        short boundary = 0;
        assertEquals(value, isGreater("test value", value, boundary));
    }


    @Test(expected = IllegalArgumentException.class)
    public void isGreater_less() throws Exception {
        short value = 0;
        short boundary = 1;
        isGreater("test value", value, boundary);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isGreater_equals() throws Exception {
        short value = 1;
        short boundary = 1;
        isGreater("test value", value, boundary);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isGreater_minimums() throws Exception {
        short value = Short.MIN_VALUE;
        short boundary = Short.MIN_VALUE;
        isLess("test value", value, boundary);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isGreater_maximums() throws Exception {
        short value = Short.MAX_VALUE;
        short boundary = Short.MAX_VALUE;
        isLess("test value", value, boundary);
    }


    // isGreaterOrEquals


    @Test
    public void isGreaterOrEquals_positive() throws Exception {
        short value = 2;
        short boundary = 1;
        assertEquals(value, isGreaterOrEquals("test value", value, boundary));
    }


    @Test
    public void isGreaterOrEquals_negative() throws Exception {
        short value = -1;
        short boundary = -2;
        assertEquals(value, isGreaterOrEquals("test value", value, boundary));
    }


    @Test
    public void isGreaterOrEquals_zero() throws Exception {
        short value = 1;
        short boundary = 0;
        assertEquals(value, isGreaterOrEquals("test value", value, boundary));
    }


    @Test
    public void isGreaterOrEquals_minimumBoundary() throws Exception {
        short value = 0;
        short boundary = Short.MIN_VALUE;
        assertEquals(value, isGreaterOrEquals("test value", value, boundary));
    }


    @Test
    public void isGreaterOrEquals_maximum() throws Exception {
        short value = Short.MAX_VALUE;
        short boundary = 0;
        assertEquals(value, isGreaterOrEquals("test value", value, boundary));
    }


    @Test(expected = IllegalArgumentException.class)
    public void isGreaterOrEquals_greater() throws Exception {
        short value = 0;
        short boundary = 1;
        isGreaterOrEquals("test value", value, boundary);
    }


    @Test
    public void isGreaterOrEquals_equals() throws Exception {
        short value = 1;
        short boundary = 1;
        isGreaterOrEquals("test value", value, boundary);
    }


    @Test
    public void isGreaterOrEquals_minimums() throws Exception {
        short value = Short.MIN_VALUE;
        short boundary = Short.MIN_VALUE;
        isGreaterOrEquals("test value", value, boundary);
    }


    @Test
    public void isGreaterOrEquals_maximums() throws Exception {
        short value = Short.MAX_VALUE;
        short boundary = Short.MAX_VALUE;
        isGreaterOrEquals("test value", value, boundary);
    }


    // isBetween


    @Test
    public void isBetween_positive() throws Exception {
        short value = 2;
        short min = 1;
        short max = 3;
        assertEquals(value, isBetween("test value", value, min, max));
    }


    @Test
    public void isBetween_negative() throws Exception {
        short value = -2;
        short min = -3;
        short max = -1;
        assertEquals(value, isBetween("test value", value, min, max));
    }


    @Test
    public void isBetween_zero() throws Exception {
        short value = 0;
        short min = -1;
        short max = 1;
        assertEquals(value, isBetween("test value", value, min, max));
    }


    @Test
    public void isBetween_minIsZero() throws Exception {
        short value = 1;
        short min = 0;
        short max = 2;
        assertEquals(value, isBetween("test value", value, min, max));
    }


    @Test
    public void isBetween_maxIsZero() throws Exception {
        short value = -1;
        short min = -2;
        short max = 0;
        assertEquals(value, isBetween("test value", value, min, max));
    }


    @Test
    public void isBetween_cornerBoundaries() throws Exception {
        short value = 0;
        short min = Short.MIN_VALUE;
        short max = Short.MAX_VALUE;
        assertEquals(value, isBetween("test value", value, min, max));
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_greaterThanMax() throws Exception {
        short value = 3;
        short min = 1;
        short max = 2;
        isBetween("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_equalMax() throws Exception {
        short value = 2;
        short min = 1;
        short max = 2;
        isBetween("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_lessThanMin() throws Exception {
        short value = 3;
        short min = 1;
        short max = 2;
        isBetween("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_equalMin() throws Exception {
        short value = 1;
        short min = 1;
        short max = 2;
        isBetween("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_equalMinimalMin() throws Exception {
        short value = Short.MIN_VALUE;
        short min = Short.MIN_VALUE;
        short max = 0;
        isBetween("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_equalMaximalMax() throws Exception {
        short value = Short.MAX_VALUE;
        short min = 0;
        short max = Short.MAX_VALUE;
        isBetween("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_maxEqualsMin() throws Exception {
        short value = 0;
        short min = 1;
        short max = 1;
        isBetween("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetween_maxLessThanMin() throws Exception {
        short value = 0;
        short min = 2;
        short max = 1;
        isBetween("test value", value, min, max);
    }

    // isBetweenOrEquals


    @Test
    public void isBetweenOrEquals_positive() throws Exception {
        short value = 2;
        short min = 1;
        short max = 3;
        assertEquals(value, isBetweenOrEquals("test value", value, min, max));
    }


    @Test
    public void isBetweenOrEquals_negative() throws Exception {
        short value = -2;
        short min = -3;
        short max = -1;
        assertEquals(value, isBetweenOrEquals("test value", value, min, max));
    }


    @Test
    public void isBetweenOrEquals_zero() throws Exception {
        short value = 0;
        short min = -1;
        short max = 1;
        assertEquals(value, isBetweenOrEquals("test value", value, min, max));
    }


    @Test
    public void isBetweenOrEquals_minIsZero() throws Exception {
        short value = 1;
        short min = 0;
        short max = 2;
        assertEquals(value, isBetweenOrEquals("test value", value, min, max));
    }


    @Test
    public void isBetweenOrEquals_maxIsZero() throws Exception {
        short value = -1;
        short min = -2;
        short max = 0;
        assertEquals(value, isBetweenOrEquals("test value", value, min, max));
    }


    @Test
    public void isBetweenOrEquals_cornerBoundaries() throws Exception {
        short value = 0;
        short min = Short.MIN_VALUE;
        short max = Short.MAX_VALUE;
        assertEquals(value, isBetweenOrEquals("test value", value, min, max));
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetweenOrEquals_greaterThanMax() throws Exception {
        short value = 3;
        short min = 1;
        short max = 2;
        isBetweenOrEquals("test value", value, min, max);
    }


    @Test
    public void isBetweenOrEquals_equalsMax() throws Exception {
        short value = 2;
        short min = 1;
        short max = 2;
        isBetweenOrEquals("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetweenOrEquals_lessThanMin() throws Exception {
        short value = 3;
        short min = 1;
        short max = 2;
        isBetweenOrEquals("test value", value, min, max);
    }


    @Test
    public void isBetweenOrEquals_equalsMin() throws Exception {
        short value = 1;
        short min = 1;
        short max = 2;
        isBetweenOrEquals("test value", value, min, max);
    }


    @Test
    public void isBetweenOrEquals_equalsMinimalMin() throws Exception {
        short value = Short.MIN_VALUE;
        short min = Short.MIN_VALUE;
        short max = 0;
        isBetweenOrEquals("test value", value, min, max);
    }


    @Test
    public void isBetweenOrEquals_equalsMaximalMax() throws Exception {
        short value = Short.MAX_VALUE;
        short min = 0;
        short max = Short.MAX_VALUE;
        isBetweenOrEquals("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetweenOrEquals_maxEqualsMin() throws Exception {
        short value = 0;
        short min = 1;
        short max = 1;
        isBetweenOrEquals("test value", value, min, max);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isBetweenOrEquals_maxLessThanMin() throws Exception {
        short value = 0;
        short min = 2;
        short max = 1;
        isBetweenOrEquals("test value", value, min, max);
    }


    @Test
    public void isBetweenOrEquals_equalsBoundaries() throws Exception {
        short value = 1;
        short min = 1;
        short max = 1;
        isBetweenOrEquals("test value", value, min, max);
    }
}