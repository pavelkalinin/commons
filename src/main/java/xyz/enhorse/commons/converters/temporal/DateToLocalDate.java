package xyz.enhorse.commons.converters.temporal;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateToLocalDate extends AbstractTemporalConverter<Date, LocalDate> {

    public DateToLocalDate(final ZoneId zoneId) {
        super(new DateConverter(zoneId), new LocalDateConverter(zoneId));
    }


    public DateToLocalDate() {
        this(null);
    }

}
