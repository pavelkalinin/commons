package xyz.enhorse.commons;


import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         08.08.2016
 */
public class EmailTest {

    public static final String POSTBOX = "postbox";
    public static final String DOMAIN = "domain.zone";
    public static final String ZONE = "zone";
    private final static String EMAIL_ADDRESS = POSTBOX + '@' + DOMAIN;
    private final static Email EMAIL = Email.parse(EMAIL_ADDRESS);


    @Test
    public void parse_valid() throws Exception {
        assertEquals(EMAIL_ADDRESS, Email.parse(EMAIL_ADDRESS).address());
    }


    @Test
    public void toString_valid() throws Exception {
        assertEquals(EMAIL_ADDRESS, Email.parse(EMAIL_ADDRESS).toString());
    }


    @Test
    public void address_valid() throws Exception {
        assertEquals(EMAIL_ADDRESS, EMAIL.address());
    }


    @Test
    public void postbox_valid() throws Exception {
        assertEquals(POSTBOX, EMAIL.postbox());
    }


    @Test
    public void domain() throws Exception {
        assertEquals(DOMAIN, EMAIL.domain());
    }


    @Test
    public void zone() throws Exception {
        assertEquals(ZONE, EMAIL.zone());
    }


    @Test
    public void parse_withoutDot() throws Exception {
        String address = "postbox@zone";
        assertEquals(address, Email.parse(address).address());
    }


    @Test
    public void zone_withoutDot() throws Exception {
        assertEquals("", Email.parse("postbox@domain").zone());
    }


    @Test
    public void domain_withoutDot() throws Exception {
        assertEquals("domain", Email.parse("postbox@domain").domain());
    }


    @Test
    public void testGetPostbox_WithoutDot() throws Exception {
        assertEquals("postbox", Email.parse("postbox@domain").postbox());
    }


    @Test
    public void parse_manyDots() throws Exception {
        String address = "postbox@sub.domain.zone";
        assertEquals(address, Email.parse(address).address());
    }


    @Test
    public void zone_manyDots() throws Exception {
        assertEquals("zone", Email.parse("postbox@sub.domain.zone").zone());
    }


    @Test
    public void domain_manyDots() throws Exception {
        assertEquals("sub.domain.zone", Email.parse("postbox@sub.domain.zone").domain());
    }


    @Test
    public void postbox_manyDots() throws Exception {
        assertEquals("postbox", Email.parse("postbox@sub.domain.zone").postbox());
    }


    @Test
    public void hashCode_same() throws Exception {
        int first = Email.parse("postbox@domain.zone").hashCode();
        assertEquals(first, first);
    }


    @Test
    public void hashCode_identical() throws Exception {
        int first = Email.parse("postbox@domain.zone").hashCode();
        int second = Email.parse("postbox@domain.zone").hashCode();
        assertEquals(first, second);
    }


    @Test
    public void hashCode_differentPostboxes() throws Exception {
        int first = Email.parse("postbox1@domain.zone").hashCode();
        int second = Email.parse("postbox2@domain.zone").hashCode();
        assertNotEquals(first, second);
    }


    @Test
    public void hashCode_differentDomains() throws Exception {
        int first = Email.parse("postbox@domain1.zone").hashCode();
        int second = Email.parse("postbox@domain2.zone").hashCode();
        assertNotEquals(first, second);
    }


    @Test
    public void hashCode_differentZones() throws Exception {
        int first = Email.parse("postbox@domain.zone").hashCode();
        int second = Email.parse("postbox@domain.sone").hashCode();
        assertNotEquals(first, second);
    }


    @Test
    public void hashCode_differentAddresses() throws Exception {
        int first = Email.parse("postbox@sub.domain.zone").hashCode();
        int second = Email.parse("postbox@domain.zone").hashCode();
        assertNotEquals(first, second);
    }


    @Test
    public void equals_same() throws Exception {
        Email email = Email.parse("postbox@domain.zone");
        assertEquals(email, email);
    }


    @Test
    public void equals_null() throws Exception {
        Email email = Email.parse("postbox@domain.zone");
        assertNotEquals(email, null);
    }


    @Test
    public void equals_identical() throws Exception {
        Email first = Email.parse("postbox@domain.zone");
        Email second = Email.parse("postbox@domain.zone");
        assertEquals(first, second);
    }


    @Test
    public void equals_differentPostboxes() throws Exception {
        Email first = Email.parse("postbox1@domain.zone");
        Email second = Email.parse("postbox2@domain.zone");
        assertNotEquals(first, second);
    }


    @Test
    public void equals_differentDomains() throws Exception {
        Email first = Email.parse("postbox@domain1.zone");
        Email second = Email.parse("postbox@domain2.zone");
        assertNotEquals(first, second);
    }


    @Test
    public void equals_differentZones() throws Exception {
        Email first = Email.parse("postbox@domain.zone");
        Email second = Email.parse("postbox@domain.sone");
        assertNotEquals(first, second);
    }


    @Test
    public void testEquals_Different() throws Exception {
        Email first = Email.parse("post@domain.zone");
        Email second = Email.parse("box@tomain.sone");
        assertNotEquals(first, second);
    }


    @Test(expected = IllegalArgumentException.class)
    public void parse_withoutAt() throws Exception {
        assertNull(Email.parse("postbox.zone"));
    }


    @Test(expected = IllegalArgumentException.class)
    public void parse_manyAts() throws Exception {
        assertNull(Email.parse("postbox@zone@zone"));
    }


    @Test(expected = IllegalArgumentException.class)
    public void parse_tailedAt() throws Exception {
        assertNull(Email.parse("postbox@"));
    }


    @Test(expected = IllegalArgumentException.class)
    public void parse_startsWithAt() throws Exception {
        assertNull(Email.parse("@postbox"));
    }


    @Test(expected = IllegalArgumentException.class)
    public void parse_empty() throws Exception {
        assertNull(Email.parse(""));
    }


    @Test(expected = IllegalArgumentException.class)
    public void parse_justOneSpace() throws Exception {
        assertNull(Email.parse(" "));
    }


    @Test(expected = IllegalArgumentException.class)
    public void parse_justSomeSpaces() throws Exception {
        assertNull(Email.parse("  "));
    }


    @Test(expected = IllegalArgumentException.class)
    public void parse_spaceIntoPostbox() throws Exception {
        assertNull(Email.parse("post box@domain.zone"));
    }


    @Test(expected = IllegalArgumentException.class)
    public void parse_spaceIntoDomain() throws Exception {
        assertNull(Email.parse("postbox@sub domain.zone"));
    }


    @Test(expected = IllegalArgumentException.class)
    public void parse_spaceIntoZone() throws Exception {
        assertNull(Email.parse("postbox@subdomain.zo ne"));
    }


    @Test(expected = IllegalArgumentException.class)
    public void parse_tailedSpace() throws Exception {
        assertNull(Email.parse("postbox@domain.zone "));
    }


    @Test(expected = IllegalArgumentException.class)
    public void parse_startsWithSpace() throws Exception {
        assertNull(Email.parse(" postbox@domain.zone"));
    }


    @Test(expected = IllegalArgumentException.class)
    public void parse_null() throws Exception {
        assertNull(Email.parse(null));
    }
}