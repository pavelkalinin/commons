package xyz.enhorse.commons.convert.datetime.converters;


import xyz.enhorse.commons.convert.ConverterFor;
import xyz.enhorse.commons.convert.datetime.TemporalConverter;

import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Optional;

/**
 * The converter for {@link Calendar}
 * @author Pavel Kalinin on 09.04.2018.
 */
@ConverterFor(Calendar.class)
@SuppressWarnings("unused")
public class CalendarConverter implements TemporalConverter<Calendar> {

    /** {@inheritDoc} */
    @Override
    public Optional<Calendar> nominated(final ZonedDateTime value) {
        return Optional.ofNullable(value)
                .map(GregorianCalendar::from);
    }


    /** {@inheritDoc} */
    @Override
    public Optional<ZonedDateTime> denominated(final Calendar value) {
        //assert that Calendar has the only implementation - GregorianCalendar in the JDK
        return Optional.ofNullable(value)
                .map(calendar -> (GregorianCalendar) calendar)
                .map(GregorianCalendar::toZonedDateTime);
    }
}
