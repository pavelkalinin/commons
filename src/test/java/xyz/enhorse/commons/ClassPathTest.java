package xyz.enhorse.commons;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.lang.reflect.Constructor;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         04.04.2016
 */
public class ClassPathTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();


    @Test
    public void constructor_DefaultIsExist() throws Exception {
        Constructor<ClassPath> constructor = ClassPath.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        assertNotNull(constructor.newInstance());

        constructor.setAccessible(false);
    }


    @Test
    public void constructor_DefaultIsPrivate() throws Exception {
        Constructor<ClassPath> constructor = ClassPath.class.getDeclaredConstructor();
        exception.expect(IllegalAccessException.class);
        exception.expectMessage("private");

        assertNull(constructor.newInstance());
    }


    @Test
    public void findAllInstantiableClasses_existingClass() throws Exception {
        assertNotNull(ClassPath.findAllInstantiableClasses(String.class));
    }


    @Test
    public void findAllInstantiableClasses_existingInterface() throws Exception {
        assertNotNull(ClassPath.findAllInstantiableClasses(Collection.class));
    }


    @Test
    public void findAllInstantiableClasses_null() throws Exception {
        assertTrue(ClassPath.findAllInstantiableClasses(null).size() == 0);
    }


    @Test
    public void getClasses() throws Exception {

    }


    @Test
    public void getClassesOfPackage() throws Exception {

    }


    @Test
    public void findClasses() throws Exception {

    }


    @Test
    public void findAllAssignable_null() throws Exception {
        assertNotNull(ClassPath.findAllAssignable(null));
    }
}