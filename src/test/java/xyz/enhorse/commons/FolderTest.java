package xyz.enhorse.commons;

import org.junit.Test;

import static org.junit.Assert.*;


/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         16/07/16
 */
public class FolderTest {

    @Test
    public void getContents() throws Exception {
        Folder folder = new Folder("");
        assertNotNull(folder);
    }

}