package xyz.enhorse.commons;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static xyz.enhorse.commons.Validate.*;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         11.07.2016
 */
public class ValidateIntegerComparisonTest {

    // isLess


    @Test
    public void isLess_validPositive() throws Exception {
        int value = 1;
        int boundary = 2;
        assertEquals(value, isLess("test value", value, boundary));
    }


    @Test
    public void isLess_validNegative() throws Exception {
        int value = -2;
        int boundary = -1;
        assertEquals(value, isLess("test value", value, boundary));
    }


    @Test
    public void isLess_validZero() throws Exception {
        int value = 0;
        int boundary = 1;
        assertEquals(value, isLess("test value", value, boundary));
    }


    @Test
    public void isLess_maxBoundary() throws Exception {
        int value = 0;
        int boundary = Integer.MAX_VALUE;
        assertEquals(value, isLess("test value", value, boundary));
    }


    @Test
    public void isLess_minBoundary() throws Exception {
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
    public void isLess_equal() throws Exception {
        int value = 1;
        int boundary = 1;
        isLess("test value", value, boundary);
    }


    // isLessOrEqual


    @Test
    public void isLessOrEqual_validPositive() throws Exception {
        int value = 1;
        int boundary = 2;
        assertEquals(value, isLessOrEqual("test value", value, boundary));
    }


    @Test
    public void isLessOrEqual_validNegative() throws Exception {
        int value = -2;
        int boundary = -1;
        assertEquals(value, isLessOrEqual("test value", value, boundary));
    }


    @Test
    public void isLessOrEqual_validZero() throws Exception {
        int value = 0;
        int boundary = 1;
        assertEquals(value, isLessOrEqual("test value", value, boundary));
    }


    @Test
    public void isLessOrEqual_maxBoundary() throws Exception {
        int value = 0;
        int boundary = Integer.MAX_VALUE;
        assertEquals(value, isLessOrEqual("test value", value, boundary));
    }


    @Test
    public void isLessOrEqual_minBoundary() throws Exception {
        int value = Integer.MIN_VALUE;
        int boundary = 0;
        assertEquals(value, isLessOrEqual("test value", value, boundary));
    }


    @Test(expected = IllegalArgumentException.class)
    public void isLessOrEqual_greater() throws Exception {
        int value = 1;
        int boundary = 0;
        isLessOrEqual("test value", value, boundary);
    }


    @Test
    public void isLessOrEqual_equal() throws Exception {
        int value = 1;
        int boundary = 1;
        isLessOrEqual("test value", value, boundary);
    }


    @Test
    public void isLessOrEqual_equalMinimums() throws Exception {
        int value = Integer.MIN_VALUE;
        int boundary = Integer.MIN_VALUE;
        isGreaterOrEqual("test value", value, boundary);
    }


    @Test
    public void isLessOrEqual_equalMaximums() throws Exception {
        int value = Integer.MAX_VALUE;
        int boundary = Integer.MAX_VALUE;
        isGreaterOrEqual("test value", value, boundary);
    }


    // isGreater


    @Test
    public void isGreater_validPositive() throws Exception {
        int value = 2;
        int boundary = 1;
        assertEquals(value, isGreater("test value", value, boundary));
    }


    @Test
    public void isGreater_validNegative() throws Exception {
        int value = -1;
        int boundary = -2;
        assertEquals(value, isGreater("test value", value, boundary));
    }


    @Test
    public void isGreater_validZero() throws Exception {
        int value = 1;
        int boundary = 0;
        assertEquals(value, isGreater("test value", value, boundary));
    }


    @Test
    public void isGreater_maxBoundary() throws Exception {
        int value = 0;
        int boundary = Integer.MIN_VALUE;
        assertEquals(value, isGreater("test value", value, boundary));
    }


    @Test
    public void isGreater_minBoundary() throws Exception {
        int value = Integer.MAX_VALUE;
        int boundary = 0;
        assertEquals(value, isGreater("test value", value, boundary));
    }


    @Test(expected = IllegalArgumentException.class)
    public void isGreater_greater() throws Exception {
        int value = 0;
        int boundary = 1;
        isGreater("test value", value, boundary);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isGreater_equal() throws Exception {
        int value = 1;
        int boundary = 1;
        isGreater("test value", value, boundary);
    }


    // isGreaterOrEqual


    @Test
    public void isGreaterOrEqual_validPositive() throws Exception {
        int value = 2;
        int boundary = 1;
        assertEquals(value, isGreaterOrEqual("test value", value, boundary));
    }


    @Test
    public void isGreaterOrEqual_validNegative() throws Exception {
        int value = -1;
        int boundary = -2;
        assertEquals(value, isGreaterOrEqual("test value", value, boundary));
    }


    @Test
    public void isGreaterOrEqual_validZero() throws Exception {
        int value = 1;
        int boundary = 0;
        assertEquals(value, isGreaterOrEqual("test value", value, boundary));
    }


    @Test
    public void isGreaterOrEqual_maxBoundary() throws Exception {
        int value = 0;
        int boundary = Integer.MIN_VALUE;
        assertEquals(value, isGreaterOrEqual("test value", value, boundary));
    }


    @Test
    public void isGreaterOrEqual_minBoundary() throws Exception {
        int value = Integer.MAX_VALUE;
        int boundary = 0;
        assertEquals(value, isGreaterOrEqual("test value", value, boundary));
    }


    @Test(expected = IllegalArgumentException.class)
    public void isGreaterOrEqual_greater() throws Exception {
        int value = 0;
        int boundary = 1;
        isGreaterOrEqual("test value", value, boundary);
    }


    @Test
    public void isGreaterOrEqual_equal() throws Exception {
        int value = 1;
        int boundary = 1;
        isGreaterOrEqual("test value", value, boundary);
    }


    @Test
    public void isGreaterOrEqual_equalMinimums() throws Exception {
        int value = Integer.MIN_VALUE;
        int boundary = Integer.MIN_VALUE;
        isGreaterOrEqual("test value", value, boundary);
    }


    @Test
    public void isGreaterOrEqual_equalMaximums() throws Exception {
        int value = Integer.MAX_VALUE;
        int boundary = Integer.MAX_VALUE;
        isGreaterOrEqual("test value", value, boundary);
    }
}