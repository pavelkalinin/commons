package xyz.enhorse.commons.parameters;

import org.junit.Assert;
import org.junit.Test;

import static xyz.enhorse.commons.parameters.PureTypes.*;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         26.08.2016
 */
public class IdentifyTest {

    @Test
    public void type_null() throws Exception {
        Assert.assertEquals(NULL, identify(null));
    }


    @Test
    public void type_emptyString() throws Exception {
        Assert.assertEquals(STRING, identify(""));
    }


    @Test
    public void type_stringFromOneSpace() throws Exception {
        Assert.assertEquals(CHAR, identify(" "));
    }


    @Test
    public void type_stringFromOneMinus() throws Exception {
        Assert.assertEquals(CHAR, identify("-"));
    }


    @Test
    public void type_stringFromSpaces() throws Exception {
        Assert.assertEquals(STRING, identify("  "));
    }


    @Test
    public void type_stringFromOneDigit() throws Exception {
        Assert.assertEquals(INTEGER, identify("1"));
    }


    @Test
    public void type_stringFromOneSymbol() throws Exception {
        Assert.assertEquals(CHAR, identify("*"));
    }


    @Test
    public void type_stringFromDigits_minimalInteger() throws Exception {
        Assert.assertEquals(INTEGER, identify(String.valueOf(Integer.MIN_VALUE)));
    }


    @Test
    public void type_stringFromDigits_minimalLong() throws Exception {
        Assert.assertEquals(LONG, identify(String.valueOf(Long.MIN_VALUE)));
    }


    @Test
    public void type_stringFromDigits_maximalInteger() throws Exception {
        Assert.assertEquals(INTEGER, identify(String.valueOf(Integer.MAX_VALUE)));
    }


    @Test
    public void type_stringFromDigits_maximalLong() throws Exception {
        Assert.assertEquals(LONG, identify(String.valueOf(Long.MAX_VALUE)));
    }


    @Test
    public void type_stringFromDigits_onlyZeroes() throws Exception {
        Assert.assertEquals(INTEGER, identify("000"));
    }


    @Test
    public void type_stringFromDigits_manyZeroes() throws Exception {
        Assert.assertEquals(INTEGER, identify("0000000000000000000000000000000000000000000000000000000000000000000000"));
    }


    @Test
    public void type_stringFromDigits_onlyZeroesAndMinus() throws Exception {
        Assert.assertEquals(INTEGER, identify("-000"));
    }


    @Test
    public void type_stringFromDigits_digitAndMinus() throws Exception {
        Assert.assertEquals(INTEGER, identify("-4"));
    }


    @Test
    public void type_stringFromDigits_twoMinuses() throws Exception {
        Assert.assertEquals(STRING, identify("-2-"));
    }


    @Test
    public void type_stringFromDigits_twoFloatingPoints() throws Exception {
        Assert.assertEquals(STRING, identify("1.2.3"));
    }


    @Test
    public void type_stringFromDigits_oneFloatingPoint() throws Exception {
        Assert.assertEquals(DOUBLE, identify("1.1"));
    }


    @Test
    public void type_stringFromDigits_oneFloatingPointAtStart() throws Exception {
        Assert.assertEquals(DOUBLE, identify(".1"));
    }


    @Test
    public void type_stringFromDigits_oneFloatingPointAtEnd() throws Exception {
        Assert.assertEquals(DOUBLE, identify("1."));
    }


    @Test
    public void type_stringFromDigits_oneFloatingPointAndMinusAtStart() throws Exception {
        Assert.assertEquals(DOUBLE, identify("-0.0"));
    }


    @Test
    public void type_stringFromDigits_twoFloatingPointAndMinusAtStart() throws Exception {
        Assert.assertEquals(STRING, identify("-0.0.0"));
    }


    @Test
    public void type_stringFromDigits_oneFloatingPointAndMinusInTheMiddle() throws Exception {
        Assert.assertEquals(STRING, identify("0-0"));
    }


    @Test
    public void type_boolean_false() throws Exception {
        Assert.assertEquals(BOOLEAN, identify("faLse"));
    }


    @Test
    public void type_boolean_true() throws Exception {
        Assert.assertEquals(BOOLEAN, identify("TRuE"));
    }


    @Test
    public void type_boolean_betweenSpaces() throws Exception {
        Assert.assertEquals(BOOLEAN, identify(" faLse  "));
    }

}