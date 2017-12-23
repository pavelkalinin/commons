package xyz.enhorse.commons;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests for {@link Pair}
 * @author <a href="mailto:ext_pkalinin@rencredit.ru">Pavel Kalinin</a>
 * on 23/12/17.
 */
public class PairTest {

    @Test
    public void swap() {
        final Long a = 1L;
        final Long b = 2L;

        final Pair<Long> pair = new Pair<>(a, b);
        final Pair<Long> clone = new Pair<>(a, b);

        final Pair<Long> actual = pair.swap();
        assertEquals("Invoking must not change the instance", pair, clone);
        assertNotEquals("Values must be swapped", pair, actual);
    }


    @Test
    public void toStringFormat() {
        final Pair<String> pair = new Pair<>("test a", "test b");
        final String toString = pair.toString();

        assertTrue("Must contain the first value", toString.contains(pair.a));
        assertTrue("Must contain the second value", toString.contains(pair.b));
    }


    @Test
    public void equalsCorrect() {
        EqualsVerifier.forClass(Pair.class).verify();
    }
}