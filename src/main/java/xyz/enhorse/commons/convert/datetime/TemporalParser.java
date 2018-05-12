package xyz.enhorse.commons.convert.datetime;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Objects;
import java.util.Optional;

/**
 * The auxiliary class - conversion for string values with the given formatter to {@link ZonedDateTime}
 * @author Pavel Kalinin on 09.04.2018.
 */
public class TemporalParser {

    private final DateTimeFormatter formatter;


    /**
     * Creates an instance with the given formatter
     * @param formatter the formatter to parse with
     */
    public TemporalParser(final DateTimeFormatter formatter) {
        this.formatter = Objects.requireNonNull(formatter, "formatter");
    }


    /**
     * Parses the given string value to {@link ZonedDateTime}.
     *
     * Uses method based on cascading attempts to convert the value with the preservation of as full as possible
     * information about the time
     * @param value the value to parse
     * @return the converted value
     */
    public Optional<ZonedDateTime> parse(final String value) {
        return Optional.ofNullable(value)
                .map(formatter::parse)
                .flatMap(this::tryToParseAsZonedDateTime);
    }


    private Optional<ZonedDateTime> tryToParseAsZonedDateTime(final TemporalAccessor value) {
        try {
            return Optional.ofNullable(ZonedDateTime.from(value));
        } catch (DateTimeException ex) {
            return tryToParseAsDateTime(value);
        }
    }


    private Optional<ZonedDateTime> tryToParseAsDateTime(final TemporalAccessor value) {
        try {
            return Optional.of(LocalDateTime.from(value)).map(dateTime -> dateTime.atZone(ZoneOffset.UTC));
        } catch (DateTimeException ex) {
            return tryToParseAsDate(value);
        }
    }


    private Optional<ZonedDateTime> tryToParseAsDate(final TemporalAccessor value) {
        try {
            return Optional.of(LocalDate.from(value)).map(date -> date.atStartOfDay(ZoneOffset.UTC));
        } catch (DateTimeException ex) {
            return parseAsTime(value);
        }
    }


    private Optional<ZonedDateTime> parseAsTime(final TemporalAccessor value) {
        return Optional.of(LocalTime.from(value))
                .map(this::timeAsDate);
    }


    private ZonedDateTime timeAsDate(final LocalTime time) {
        return time
                .atDate(LocalDate.of(0, 1, 1))
                .atZone(ZoneOffset.UTC);
    }
}
