package xyz.enhorse.commons.convert.datetime;


import xyz.enhorse.commons.convert.Converter;

import java.time.ZonedDateTime;

/**
 * A converter from the type {@link T} to {@link ZonedDateTime} and backward
 * @param <T> the type that supports conversion from/to {@link ZonedDateTime}
 * @author Pavel Kalinin 09.04.2018
 */
public interface TemporalConverter<T> extends Converter<ZonedDateTime, T> {

}
