package xyz.enhorse.commons.convert.datetime.converters;


import xyz.enhorse.commons.convert.ConverterFor;
import xyz.enhorse.commons.convert.datetime.TemporalConverter;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.chrono.ChronoZonedDateTime;
import java.util.Date;
import java.util.Optional;

/**
 * The converter for {@link Date}
 * @author Pavel Kalinin on 09.04.2018.
 */
@ConverterFor(Date.class)
@SuppressWarnings("unused")
public class DateConverter implements TemporalConverter<Date> {

    /** {@inheritDoc} */
    @Override
    public Optional<Date> nominated(final ZonedDateTime value) {
        return Optional.ofNullable(value)
                .map(ChronoZonedDateTime::toInstant)
                .map(Date::from);
    }


    /** {@inheritDoc} */
    @Override
    public Optional<ZonedDateTime> denominated(final Date value) {
        //java.util.Date objects do not contain any timezone information by themselves so we use UTC
        return Optional.ofNullable(value)
                .map(Date::toInstant)
                .map(instant -> instant.atZone(ZoneOffset.UTC));
    }
}
