package xyz.enhorse.commons.converters.temporal;

import java.time.ZoneId;
import java.util.Optional;

public abstract class AbstractZonedConverter<T> implements ZonedConverter<T> {

    private final ZoneId zoneId;


    AbstractZonedConverter(final ZoneId zoneId) {
        this.zoneId = Optional.ofNullable(zoneId).orElse(ZoneId.systemDefault());
    }


    AbstractZonedConverter() {
        this(null);
    }


    protected ZoneId zoneId() {
        return zoneId;
    }
}
