package xyz.enhorse.commons;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static xyz.enhorse.commons.Validate.*;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         11.07.2016
 */
public class ValidateDoubleComparisonTest {

    // isLess


    @Test
    public void isLess_validPositive() throws Exception {
        double value = 1;
        double boundary = 2;
        assertEquals(value, isLess("test value", value, boundary), 0.00001);
    }


    @Test
    public void isLess_validNegative() throws Exception {
        double value = -2;
        double boundary = -1;
        assertEquals(value, isLess("test value", value, boundary), 0.00001);
    }


    @Test
    public void isLess_validZero() throws Exception {
        double value = 0;
        double boundary = 1;
        assertEquals(value, isLess("test value", value, boundary), 0.00001);
    }


    @Test
    public void isLess_maxBoundary() throws Exception {
        double value = 0;
        double boundary = Double.MAX_VALUE;
        assertEquals(value, isLess("test value", value, boundary), 0.00001);
    }


    @Test
    public void isLess_minBoundary() throws Exception {
        double value = 0;
        double boundary = Double.MIN_VALUE;
        assertEquals(value, isLess("test value", value, boundary), 0.00001);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isLess_valueNaN() throws Exception {
        double value = Double.NaN;
        double boundary = 0;
        assertEquals(value, isLess("test value", value, boundary), 0.00001);
    }


    @Test
    public void isLess_boundaryNaN() throws Exception {
        double value = 0;
        double boundary = Double.NaN;
        assertEquals(value, isLess("test value", value, boundary), 0.00001);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isLess_valuePositiveInfinity() throws Exception {
        double value = Double.POSITIVE_INFINITY;
        double boundary = 0;
        assertEquals(value, isLess("test value", value, boundary), 0.00001);
    }


    @Test
    public void isLess_boundaryPositiveInfinity() throws Exception {
        double value = 0;
        double boundary = Double.POSITIVE_INFINITY;
        assertEquals(value, isLess("test value", value, boundary), 0.00001);
    }


    @Test
    public void isLess_valueNegativeInfinity() throws Exception {
        double value = Double.NEGATIVE_INFINITY;
        double boundary = 0;
        assertEquals(value, isLess("test value", value, boundary), 0.00001);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isLess_boundaryNegativeInfinity() throws Exception {
        double value = 0;
        double boundary = Double.NEGATIVE_INFINITY;
        assertEquals(value, isLess("test value", value, boundary), 0.00001);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isLess_greater() throws Exception {
        double value = 1;
        double boundary = 0;
        isLess("test value", value, boundary);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isLess_equal() throws Exception {
        double value = 1;
        double boundary = 1;
        isLess("test value", value, boundary);
    }


    // isLessOrEqual


    @Test
    public void isLessOrEqual_validPositive() throws Exception {
        double value = 1;
        double boundary = 2;
        assertEquals(value, isLessOrEqual("test value", value, boundary), 0.00001);
    }


    @Test
    public void isLessOrEqual_validNegative() throws Exception {
        double value = -2;
        double boundary = -1;
        assertEquals(value, isLessOrEqual("test value", value, boundary), 0.00001);
    }


    @Test
    public void isLessOrEqual_validZero() throws Exception {
        double value = 0;
        double boundary = 1;
        assertEquals(value, isLessOrEqual("test value", value, boundary), 0.00001);
    }


    @Test
    public void isLessOrEqual_maxBoundary() throws Exception {
        double value = 0;
        double boundary = Double.MAX_VALUE;
        assertEquals(value, isLessOrEqual("test value", value, boundary), 0.00001);
    }


    @Test
    public void isLessOrEqual_minBoundary() throws Exception {
        double value = 0;
        double boundary = Double.MIN_VALUE;
        assertEquals(value, isLessOrEqual("test value", value, boundary), 0.00001);
    }


    @Test
    public void isLessOrEqual_NaN() throws Exception {
        double value = Double.NaN;
        double boundary = Double.NaN;
        assertEquals(value, isLessOrEqual("test value", value, boundary), 0.00001);
    }


    @Test
    public void isLessOrEqual_PositiveInfinity() throws Exception {
        double value = Double.POSITIVE_INFINITY;
        double boundary = Double.POSITIVE_INFINITY;
        assertEquals(value, isLessOrEqual("test value", value, boundary), 0.00001);
    }


    @Test
    public void isLessOrEqual_NegativeInfinity() throws Exception {
        double value = Double.NEGATIVE_INFINITY;
        double boundary = Double.NEGATIVE_INFINITY;
        assertEquals(value, isLessOrEqual("test value", value, boundary), 0.00001);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isLessOrEqual_greater() throws Exception {
        double value = 1;
        double boundary = 0;
        isLessOrEqual("test value", value, boundary);
    }


    @Test
    public void isLessOrEqual_equal() throws Exception {
        double value = 1;
        double boundary = 1;
        isLessOrEqual("test value", value, boundary);
    }


    @Test
    public void isLessOrEqual_equalMinimums() throws Exception {
        double value = Double.MIN_VALUE;
        double boundary = Double.MIN_VALUE;
        isGreaterOrEqual("test value", value, boundary);
    }


    @Test
    public void isLessOrEqual_equalMaximums() throws Exception {
        double value = Double.MAX_VALUE;
        double boundary = Double.MAX_VALUE;
        isGreaterOrEqual("test value", value, boundary);
    }


    // isGreater


    @Test
    public void isGreater_validPositive() throws Exception {
        double value = 2;
        double boundary = 1;
        assertEquals(value, isGreater("test value", value, boundary), 0.00001);
    }


    @Test
    public void isGreater_validNegative() throws Exception {
        double value = -1;
        double boundary = -2;
        assertEquals(value, isGreater("test value", value, boundary), 0.00001);
    }


    @Test
    public void isGreater_validZero() throws Exception {
        double value = 1;
        double boundary = 0;
        assertEquals(value, isGreater("test value", value, boundary), 0.00001);
    }


    @Test
    public void isGreater_maxBoundary() throws Exception {
        double value = Double.MIN_VALUE;
        double boundary = 0;
        assertEquals(value, isGreater("test value", value, boundary), 0.00001);
    }


    @Test
    public void isGreater_minBoundary() throws Exception {
        double value = Double.MAX_VALUE;
        double boundary = 0;
        assertEquals(value, isGreater("test value", value, boundary), 0.00001);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isGreater_valueNaN() throws Exception {
        double value = 0;
        double boundary = Double.NaN;
        assertEquals(value, isGreater("test value", value, boundary), 0.00001);
    }


    @Test
    public void isGreater_boundaryNaN() throws Exception {
        double value = Double.NaN;
        double boundary = 0;
        assertEquals(value, isGreater("test value", value, boundary), 0.00001);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isGreater_valuePositiveInfinity() throws Exception {
        double value = 0;
        double boundary = Double.POSITIVE_INFINITY;
        assertEquals(value, isGreater("test value", value, boundary), 0.00001);
    }


    @Test
    public void isGreater_boundaryPositiveInfinity() throws Exception {
        double value = Double.POSITIVE_INFINITY;
        double boundary = 0;
        assertEquals(value, isGreater("test value", value, boundary), 0.00001);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isGreater_valueNegativeInfinity() throws Exception {
        double value = Double.NEGATIVE_INFINITY;
        double boundary = 0;
        assertEquals(value, isGreater("test value", value, boundary), 0.00001);
    }


    @Test
    public void isGreater_boundaryNegativeInfinity() throws Exception {
        double value = 0;
        double boundary = Double.NEGATIVE_INFINITY;
        assertEquals(value, isGreater("test value", value, boundary), 0.00001);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isGreater_greater() throws Exception {
        double value = 0;
        double boundary = 1;
        isGreater("test value", value, boundary);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isGreater_equal() throws Exception {
        double value = 1;
        double boundary = 1;
        isGreater("test value", value, boundary);
    }


    // isGreaterOrEqual


    @Test
    public void isGreaterOrEqual_validPositive() throws Exception {
        double value = 2;
        double boundary = 1;
        assertEquals(value, isGreaterOrEqual("test value", value, boundary), 0.00001);
    }


    @Test
    public void isGreaterOrEqual_validNegative() throws Exception {
        double value = -1;
        double boundary = -2;
        assertEquals(value, isGreaterOrEqual("test value", value, boundary), 0.00001);
    }


    @Test
    public void isGreaterOrEqual_validZero() throws Exception {
        double value = 1;
        double boundary = 0;
        assertEquals(value, isGreaterOrEqual("test value", value, boundary), 0.00001);
    }


    @Test
    public void isGreaterOrEqual_maxBoundary() throws Exception {
        double value = Double.MIN_VALUE;
        double boundary = 0;
        assertEquals(value, isGreaterOrEqual("test value", value, boundary), 0.00001);
    }


    @Test
    public void isGreaterOrEqual_minBoundary() throws Exception {
        double value = Double.MAX_VALUE;
        double boundary = 0;
        assertEquals(value, isGreaterOrEqual("test value", value, boundary), 0.00001);
    }


    @Test
    public void isGreaterOrEqual_NaN() throws Exception {
        double value = Double.NaN;
        double boundary = Double.NaN;
        assertEquals(value, isGreaterOrEqual("test value", value, boundary), 0.00001);
    }


    @Test
    public void isGreaterOrEqual_PositiveInfinity() throws Exception {
        double value = Double.POSITIVE_INFINITY;
        double boundary = Double.POSITIVE_INFINITY;
        assertEquals(value, isGreaterOrEqual("test value", value, boundary), 0.00001);
    }


    @Test
    public void isGreaterOrEqual_NegativeInfinity() throws Exception {
        double value = Double.NEGATIVE_INFINITY;
        double boundary = Double.NEGATIVE_INFINITY;
        assertEquals(value, isGreaterOrEqual("test value", value, boundary), 0.00001);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isGreaterOrEqual_greater() throws Exception {
        double value = 0;
        double boundary = 1;
        isGreaterOrEqual("test value", value, boundary);
    }


    @Test
    public void isGreaterOrEqual_equal() throws Exception {
        double value = 1;
        double boundary = 1;
        isGreaterOrEqual("test value", value, boundary);
    }


    @Test
    public void isGreaterOrEqual_equalMinimums() throws Exception {
        double value = Double.MIN_VALUE;
        double boundary = Double.MIN_VALUE;
        isGreaterOrEqual("test value", value, boundary);
    }


    @Test
    public void isGreaterOrEqual_equalMaximums() throws Exception {
        double value = Double.MAX_VALUE;
        double boundary = Double.MAX_VALUE;
        isGreaterOrEqual("test value", value, boundary);
    }
}