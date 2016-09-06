package xyz.enhorse.commons.parameters.schemas.constraints;

import org.junit.Test;

import static org.junit.Assert.*;
import static xyz.enhorse.commons.parameters.schemas.constraints.NullConstraints.NOT_NULL;
import static xyz.enhorse.commons.parameters.schemas.constraints.NullConstraints.NULL;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         06.09.2016
 */
public class NullConstraintsTest {

    @Test
    public void check_NOT_NULL() throws Exception {
        assertTrue(NOT_NULL.check("string"));
        assertFalse(NOT_NULL.check(null));
        assertTrue(NOT_NULL.check("string", null));
        assertFalse(NOT_NULL.check(null, null));
    }


    @Test
    public void type_NOT_NULL() throws Exception {
        assertNotNull(NOT_NULL.type());
    }


    @Test
    public void constraint_NOT_NULL() throws Exception {
        assertNull(NOT_NULL.constraint());
    }


    @Test
    public void check_NULL() throws Exception {
        assertFalse(NULL.check("string"));
        assertTrue(NULL.check(null));
        assertFalse(NULL.check("string", null));
        assertTrue(NULL.check(null, null));
    }


    @Test
    public void type_NULL() throws Exception {
        assertNotNull(NULL.type());
    }


    @Test
    public void constraint_NULL() throws Exception {
        assertNull(NULL.constraint());
    }
}