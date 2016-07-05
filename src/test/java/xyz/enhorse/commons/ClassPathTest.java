package xyz.enhorse.commons;

import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         04.04.2016
 */
public class ClassPathTest {

    @Test
    public void testFindClassesMatchingType_ExistClass() throws Exception {
        assertNotNull(ClassPath.findAllInstantiableClasses(String.class));
    }


    @Test
    public void testFindClassesMatchingType_ExistInterface() throws Exception {
        assertNotNull(ClassPath.findAllInstantiableClasses(Collection.class));
    }


    @Test
    public void testFindClassesMatchingType_Null() throws Exception {
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

}