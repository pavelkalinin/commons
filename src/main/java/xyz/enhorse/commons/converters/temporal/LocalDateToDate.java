package xyz.enhorse.commons.converters.temporal;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class LocalDateToDate extends AbstractTemporalConverter<LocalDate, Date> {

    public LocalDateToDate(final ZoneId zoneId) {
        super(new LocalDateConverter(zoneId), new DateConverter(zoneId));
    }


    public LocalDateToDate() {
        this(null);
    }
}

