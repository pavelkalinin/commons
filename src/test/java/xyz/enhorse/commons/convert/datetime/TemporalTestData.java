package xyz.enhorse.commons.convert.datetime;

import org.junit.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static java.time.format.DateTimeFormatter.ofPattern;
import static org.junit.Assert.assertEquals;

/**
 * The predefined test data for testing date-time converters
 * @author Pavel Kalinin on 10.04.2018.
 */
@SuppressWarnings("WeakerAccess")
public class TemporalTestData {

    public final static String TEMPORAL_STRING = "2010-11-12T13:14:15.678+00:00";
    public final static ZoneId ZONE_ID = ZoneId.of("UTC");
    public final static String PATTERN = "yyyy-MM-dd\'T\'HH:mm:ss.SSSxxx";

    public final static ZonedDateTime ZONED_DATE_TIME = ZonedDateTime.parse(TEMPORAL_STRING, ofPattern(PATTERN));
    public final static LocalDateTime LOCAL_DATE_TIME = ZONED_DATE_TIME.toLocalDateTime();
    public final static LocalDate LOCAL_DATE = ZONED_DATE_TIME.toLocalDate();
    public final static Long EPOCH = ZONED_DATE_TIME.toEpochSecond();
    public final static Calendar CALENDAR = GregorianCalendar.from(ZONED_DATE_TIME);
    public final static Date DATE = Date.from(ZONED_DATE_TIME.toInstant());

    private final static Instant INSTANT = ZONED_DATE_TIME.toInstant();


    private TemporalTestData() {
        //a class with constants
    }


    @Test
    public void zonedDateTimeEquivalence() {
        assertEquals(TEMPORAL_STRING, ZONED_DATE_TIME.format(ofPattern(PATTERN)));
        System.out.println(ZONE_ID);
    }


    @Test
    public void localDateTimeEquivalence() {
        assertEquals(INSTANT, LOCAL_DATE_TIME.atZone(ZONE_ID).toInstant());
    }


    @Test
    public void localDateEquivalence() {
        assertEquals(INSTANT.truncatedTo(ChronoUnit.DAYS), LOCAL_DATE.atStartOfDay(ZONE_ID).toInstant());
    }


    @Test
    public void calendarEquivalence() {
        assertEquals(INSTANT, CALENDAR.toInstant());
    }


    @Test
    public void dateEquivalence() {
        assertEquals(INSTANT, DATE.toInstant());
    }


    @Test
    public void epochEquivalence() {
        assertEquals(INSTANT.truncatedTo(ChronoUnit.SECONDS), Instant.ofEpochSecond(EPOCH));
    }
}
