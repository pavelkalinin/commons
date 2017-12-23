package xyz.enhorse.commons;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Assert;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;


/**
 * Tests for {@link Interval}
 * @author <a href="mailto:ext_pkalinin@rencredit.ru">Pavel Kalinin</a>
 * on 23/12/17.
 */
public class IntervalTest {

    @Test
    public void fromCreatedWithCorrectOrder() {
        final Long expected = 1L;
        assertEquals(expected, new Interval<>(expected, expected + 1).from());
    }


    @Test
    public void fromCreatedWithInCorrectOrder() {
        final Long expected = 1L;
        assertEquals(expected, new Interval<>(expected + 1, expected).from());
    }


    @Test
    public void toCreatedWithCorrectOrder() {
        final Long expected = 2L;
        assertEquals(expected, new Interval<>(expected - 1, expected).to());
    }


    @Test
    public void toCreatedWithInCorrectOrder() {
        final Long expected = 2L;
        assertEquals(expected, new Interval<>(expected, expected - 1).to());
    }


    @Test
    public void isDegeneratedWithSameBounds() {
        assertTrue(new Interval<>(1L, 1L).isDegenerated());
    }


    @Test
    public void isDegeneratedWithDifferentBounds() {
        assertFalse(new Interval<>(1L, 2L).isDegenerated());
    }


    @Test
    public void equalsCorrect() {
        EqualsVerifier.forClass(Interval.class).verify();
    }


    @Test
    public void toStringFormat() {
        final Interval<String> pair = new Interval<>("test a", "test b");
        final String toString = pair.toString();

        Assert.assertTrue("Must contain a value \'from\'", toString.contains(pair.from()));
        Assert.assertTrue("Must contain a value \'to\'", toString.contains(pair.to()));
    }

}