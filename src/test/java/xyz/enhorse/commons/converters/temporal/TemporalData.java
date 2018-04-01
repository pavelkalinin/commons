package xyz.enhorse.commons.converters.temporal;

import org.junit.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static org.junit.Assert.assertEquals;

public class TemporalData {

    private final static int YEAR = 2010;
    private final static int MONTH = Calendar.NOVEMBER + 1; // 11
    private final static int DAY = 12;
    private final static int HOUR = 13;
    private final static int MINUTE = 14;
    private final static int SECOND = 15;
    private final static int MILLIS = 678;
    private final static int TZ_HOUR = 0;
    private final static int TZ_MINUTE = 0;
    final static ZoneId ZONE_ID = ZoneId.of("UTC");
    final static String PATTERN = "yyyy-MM-dd\'T\'HH:mm:ss.SSSxxx";
    final static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(PATTERN);
    final static String TIMESTAMP = String.format(
            "%04d-%02d-%02dT%02d:%02d:%02d.%03d+%02d:%02d",
            YEAR, MONTH, DAY, HOUR, MINUTE, SECOND, MILLIS, TZ_HOUR, TZ_MINUTE); //"2010-11-12T13:14:15.678+00:00"
    final static Calendar CALENDAR = initCalendar();
    final static LocalDateTime LOCAL_DATE_TIME = LocalDateTime.parse(TIMESTAMP, FORMATTER);
    final static Long EPOCH = 1289567655L; // 12 November 2010 13:14:15 UTC
    final static ZonedDateTime ZONED = ZonedDateTime.of(LOCAL_DATE_TIME, ZONE_ID);
    final static Date DATE = Date.from(ZONED.toInstant());
    final static Instant INSTANT = LOCAL_DATE_TIME.toInstant(ZoneOffset.UTC);
    final static LocalDate LOCAL_DATE = LocalDate.parse(TIMESTAMP, FORMATTER);


    @Test
    public void zonedEquivalence() {
        assertEquals(INSTANT, ZONED.toInstant());
    }


    @Test
    public void calendarEquivalence() {
        assertEquals(INSTANT, CALENDAR.toInstant());
    }


    @Test
    public void localDateEquivalence() {
        assertEquals(INSTANT.truncatedTo(ChronoUnit.DAYS), LOCAL_DATE.atStartOfDay(ZONE_ID).toInstant());
    }


    @Test
    public void dateEquivalence() {
        assertEquals(INSTANT, DATE.toInstant());
    }


    @Test
    public void epochEquivalence() {
        assertEquals(INSTANT.truncatedTo(ChronoUnit.SECONDS), Instant.ofEpochSecond(EPOCH));
    }


    private static Calendar initCalendar() {
        final Calendar result = Calendar.getInstance();
        result.setTimeZone(TimeZone.getTimeZone(ZONE_ID));
        result.set(YEAR, Calendar.NOVEMBER, DAY, HOUR, MINUTE, SECOND);
        result.set(Calendar.MILLISECOND, MILLIS);
        return result;
    }
}
