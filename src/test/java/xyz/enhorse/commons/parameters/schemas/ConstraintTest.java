package xyz.enhorse.commons.parameters.schemas;

import org.junit.Test;

import static org.junit.Assert.*;
import static xyz.enhorse.commons.parameters.schemas.Constraints.*;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         01.09.2016
 */
public class ConstraintTest {

    @Test
    public void create() throws Exception {
        assertNotNull(new Constraint<>(EQUAL, 42));
    }


    @Test
    public void create_constraint_null() throws Exception {
        assertNotNull(new Constraint<>(EQUAL, null));
    }


    @Test(expected = IllegalArgumentException.class)
    public void create_type_null() throws Exception {
        assertNotNull(new Constraint<>(null, 42));
    }


    @Test
    public void check_EQUAL() throws Exception {
        Constraint<Integer> constraint = new Constraint<>(EQUAL, 42);
        assertTrue(constraint.check(42));
        assertFalse(constraint.check(60));
    }


    @Test
    public void check_EQUAL_constraint_null() throws Exception {
        Constraint<Integer> constraint = new Constraint<>(EQUAL, null);
        assertFalse(constraint.check(60));
    }


    @Test
    public void check_EQUAL_value_null() throws Exception {
        Constraint<Integer> constraint = new Constraint<>(EQUAL, 42);
        assertFalse(constraint.check(null));
    }


    @Test
    public void check_NOT_EQUAL() throws Exception {
        Constraint<Integer> constraint = new Constraint<>(NOT_EQUAL, 42);
        assertTrue(constraint.check(41));
        assertTrue(constraint.check(43));
        assertFalse(constraint.check(42));
    }


    @Test
    public void check_NOT_EQUAL_constraint_null() throws Exception {
        Constraint<Integer> constraint = new Constraint<>(NOT_EQUAL, null);
        assertFalse(constraint.check(42));
    }


    @Test
    public void check_NOT_EQUAL_value_null() throws Exception {
        Constraint<Integer> constraint = new Constraint<>(NOT_EQUAL, 42);
        assertFalse(constraint.check(null));
    }


    @Test
    public void check_GREATER() throws Exception {
        Constraint<Integer> constraint = new Constraint<>(GREATER, 42);
        assertTrue(constraint.check(43));
        assertFalse(constraint.check(42));
        assertFalse(constraint.check(41));
    }


    @Test
    public void check_GREATER_constraint_null() throws Exception {
        Constraint<Integer> constraint = new Constraint<>(GREATER, null);
        assertFalse(constraint.check(42));
    }


    @Test
    public void check_GREATER_value_null() throws Exception {
        Constraint<Integer> constraint = new Constraint<>(GREATER, 42);
        assertFalse(constraint.check(null));
    }


    @Test
    public void check_LESS() throws Exception {
        Constraint<Integer> constraint = new Constraint<>(LESS, 42);
        assertTrue(constraint.check(41));
        assertFalse(constraint.check(42));
        assertFalse(constraint.check(43));
    }


    @Test
    public void check_LESS_constraint_null() throws Exception {
        Constraint<Integer> constraint = new Constraint<>(LESS, null);
        assertFalse(constraint.check(42));
    }


    @Test
    public void check_LESS_value_null() throws Exception {
        Constraint<Integer> constraint = new Constraint<>(LESS, 42);
        assertFalse(constraint.check(null));
    }


    @Test
    public void check_GREATER_OR_EQUAL() throws Exception {
        Constraint<Integer> constraint = new Constraint<>(GREATER_OR_EQUAL, 42);
        assertTrue(constraint.check(42));
        assertTrue(constraint.check(43));
        assertFalse(constraint.check(41));
    }


    @Test
    public void check_GREATER_OR_EQUAL_constraint_null() throws Exception {
        Constraint<Integer> constraint = new Constraint<>(GREATER_OR_EQUAL, null);
        assertFalse(constraint.check(42));
    }


    @Test
    public void check_GREATER_OR_EQUAL_value_null() throws Exception {
        Constraint<Integer> constraint = new Constraint<>(GREATER_OR_EQUAL, 42);
        assertFalse(constraint.check(null));
    }


    @Test
    public void check_LESS_OR_EQUAL() throws Exception {
        Constraint<Integer> constraint = new Constraint<>(LESS_OR_EQUAL, 42);
        assertTrue(constraint.check(42));
        assertTrue(constraint.check(41));
        assertFalse(constraint.check(43));
    }


    @Test
    public void check_LESS_OR_EQUAL_constraint_null() throws Exception {
        Constraint<Integer> constraint = new Constraint<>(LESS_OR_EQUAL, null);
        assertFalse(constraint.check(42));
    }


    @Test
    public void check_LESS_OR_EQUAL_value_null() throws Exception {
        Constraint<Integer> constraint = new Constraint<>(LESS_OR_EQUAL, 42);
        assertFalse(constraint.check(null));
    }


    @Test
    public void check_NOT_NULL() throws Exception {
        Constraint<Integer> constraint = new Constraint<>(NOT_NULL, 42);
        assertTrue(constraint.check(41));
        assertFalse(constraint.check(null));
    }


    @Test
    public void check_NOT_NULL_constraint_null() throws Exception {
        Constraint<Integer> constraint = new Constraint<>(NOT_NULL, null);
        assertTrue(constraint.check(42));
    }


    @Test
    public void check_NOT_NULL_value_null() throws Exception {
        Constraint<Integer> constraint = new Constraint<>(NOT_NULL, 42);
        assertFalse(constraint.check(null));
    }


    @Test
    public void toString_output() throws Exception {
        Constraint<Integer> constraint = new Constraint<>(EQUAL, 42);
        String toString = constraint.toString();

        assertTrue("doesn't contain constraint", toString.contains(String.valueOf(42)));
        assertTrue("doesn't contain constraint type", toString.contains(String.valueOf(EQUAL)));
    }


    @Test
    public void toString_output_value_null() throws Exception {
        Constraint<Integer> constraint = new Constraint<>(EQUAL, null);
        String toString = constraint.toString();

        assertTrue("doesn't contain constraint", toString.contains("null"));
    }


    @Test
    public void equals_same() throws Exception {
        Constraint<Integer> constraint = new Constraint<>(EQUAL, 42);
        assertEquals(constraint, constraint);
    }


    @Test
    public void equals_different() throws Exception {
        Constraint<Integer> constraint1 = new Constraint<>(EQUAL, 42);
        Constraint<Integer> constraint2 = new Constraint<>(GREATER, 41);
        assertFalse(constraint1.equals(constraint2));
    }


    @Test
    public void equals_different_constraints() throws Exception {
        Constraint<Integer> constraint1 = new Constraint<>(EQUAL, 42);
        Constraint<Integer> constraint2 = new Constraint<>(EQUAL, 41);
        assertFalse(constraint1.equals(constraint2));
    }


    @Test
    public void equals_different_types() throws Exception {
        Constraint<Integer> constraint1 = new Constraint<>(EQUAL, 42);
        Constraint<Integer> constraint2 = new Constraint<>(GREATER, 42);
        assertFalse(constraint1.equals(constraint2));
    }


    @Test
    public void equals_different_constraint_null() throws Exception {
        Constraint<Integer> constraint1 = new Constraint<>(EQUAL, null);
        Constraint<Integer> constraint2 = new Constraint<>(EQUAL, 42);
        assertFalse(constraint1.equals(constraint2));
    }


    @Test
    public void equals_different_null() throws Exception {
        Constraint<Integer> constraint = new Constraint<>(EQUAL, 42);
        assertNotEquals(constraint, null);
    }


    @Test
    public void hashCode_same() throws Exception {
        Constraint<Integer> constraint = new Constraint<>(EQUAL, 42);
        assertEquals(constraint.hashCode(), constraint.hashCode());
    }


    @Test
    public void hasCode_different() throws Exception {
        Constraint<Integer> constraint1 = new Constraint<>(EQUAL, 42);
        Constraint<Integer> constraint2 = new Constraint<>(GREATER, 41);
        assertNotEquals(constraint1.hashCode(), constraint2.hashCode());
    }


    @Test
    public void hasCode_different_constraints() throws Exception {
        Constraint<Integer> constraint1 = new Constraint<>(EQUAL, 42);
        Constraint<Integer> constraint2 = new Constraint<>(EQUAL, 41);
        assertNotEquals(constraint1.hashCode(), constraint2.hashCode());
    }


    @Test
    public void hasCode_different_types() throws Exception {
        Constraint<Integer> constraint1 = new Constraint<>(EQUAL, 42);
        Constraint<Integer> constraint2 = new Constraint<>(GREATER, 42);
        assertNotEquals(constraint1.hashCode(), constraint2.hashCode());
    }


    @Test
    public void hasCode_different_constraint_null() throws Exception {
        Constraint<Integer> constraint1 = new Constraint<>(EQUAL, null);
        Constraint<Integer> constraint2 = new Constraint<>(EQUAL, 42);
        assertNotEquals(constraint1.hashCode(), constraint2.hashCode());
    }
}