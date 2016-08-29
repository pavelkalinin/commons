package xyz.enhorse.commons.types;

import org.junit.Assert;
import org.junit.Test;

import static xyz.enhorse.commons.types.Identify.type;
import static xyz.enhorse.commons.types.PureTypes.*;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         26.08.2016
 */
public class IdentifyTest {

    @Test
    public void type_null() throws Exception {
        Assert.assertEquals(NULL, type(null));
    }


    @Test
    public void type_emptyString() throws Exception {
        Assert.assertEquals(STRING, type(""));
    }


    @Test
    public void type_stringFromOneSpace() throws Exception {
        Assert.assertEquals(CHAR, type(" "));
    }


    @Test
    public void type_stringFromOneMinus() throws Exception {
        Assert.assertEquals(CHAR, type("-"));
    }


    @Test
    public void type_stringFromSpaces() throws Exception {
        Assert.assertEquals(STRING, type("  "));
    }


    @Test
    public void type_stringFromOneDigit() throws Exception {
        Assert.assertEquals(INTEGER, type("1"));
    }


    @Test
    public void type_stringFromOneSymbol() throws Exception {
        Assert.assertEquals(CHAR, type("*"));
    }


    @Test
    public void type_stringFromDigits_minimalInteger() throws Exception {
        Assert.assertEquals(INTEGER, type(String.valueOf(Integer.MIN_VALUE)));
    }


    @Test
    public void type_stringFromDigits_minimalLong() throws Exception {
        Assert.assertEquals(LONG, type(String.valueOf(Long.MIN_VALUE)));
    }


    @Test
    public void type_stringFromDigits_maximalInteger() throws Exception {
        Assert.assertEquals(INTEGER, type(String.valueOf(Integer.MAX_VALUE)));
    }


    @Test
    public void type_stringFromDigits_maximalLong() throws Exception {
        Assert.assertEquals(LONG, type(String.valueOf(Long.MAX_VALUE)));
    }


    @Test
    public void type_stringFromDigits_onlyZeroes() throws Exception {
        Assert.assertEquals(INTEGER, type("000"));
    }


    @Test
    public void type_stringFromDigits_manyZeroes() throws Exception {
        Assert.assertEquals(INTEGER, type("0000000000000000000000000000000000000000000000000000000000000000000000"));
    }


    @Test
    public void type_stringFromDigits_onlyZeroesAndMinus() throws Exception {
        Assert.assertEquals(INTEGER, type("-000"));
    }


    @Test
    public void type_stringFromDigits_digitAndMinus() throws Exception {
        Assert.assertEquals(INTEGER, type("-4"));
    }


    @Test
    public void type_stringFromDigits_twoMinuses() throws Exception {
        Assert.assertEquals(STRING, type("-2-"));
    }


    @Test
    public void type_stringFromDigits_twoFloatingPoints() throws Exception {
        Assert.assertEquals(STRING, type("1.2.3"));
    }


    @Test
    public void type_stringFromDigits_oneFloatingPoint() throws Exception {
        Assert.assertEquals(DOUBLE, type("1.1"));
    }


    @Test
    public void type_stringFromDigits_oneFloatingPointAtStart() throws Exception {
        Assert.assertEquals(DOUBLE, type(".1"));
    }


    @Test
    public void type_stringFromDigits_oneFloatingPointAtEnd() throws Exception {
        Assert.assertEquals(DOUBLE, type("1."));
    }


    @Test
    public void type_stringFromDigits_oneFloatingPointAndMinusAtStart() throws Exception {
        Assert.assertEquals(DOUBLE, type("-0.0"));
    }


    @Test
    public void type_stringFromDigits_twoFloatingPointAndMinusAtStart() throws Exception {
        Assert.assertEquals(STRING, type("-0.0.0"));
    }


    @Test
    public void type_stringFromDigits_oneFloatingPointAndMinusInTheMiddle() throws Exception {
        Assert.assertEquals(STRING, type("0-0"));
    }


    @Test
    public void type_boolean_false() throws Exception {
        Assert.assertEquals(BOOLEAN, type("faLse"));
    }


    @Test
    public void type_boolean_true() throws Exception {
        Assert.assertEquals(BOOLEAN, type("TRuE"));
    }


    @Test
    public void type_boolean_betweenSpaces() throws Exception {
        Assert.assertEquals(BOOLEAN, type(" faLse  "));
    }

}