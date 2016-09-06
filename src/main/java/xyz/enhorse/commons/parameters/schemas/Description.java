package xyz.enhorse.commons.parameters.schemas;

import xyz.enhorse.commons.DefaultsProducer;
import xyz.enhorse.commons.Pretty;
import xyz.enhorse.commons.Validate;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static xyz.enhorse.commons.parameters.schemas.PureTypes.STRING;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         01.09.2016
 */
public class Description<T extends Comparable<T>> {

    private final PureType<T> type;
    private final DefaultsProducer<T> producer;
    private final Set<Constraint<T>> constraints;


    @SafeVarargs
    public Description(final PureType<T> type,
                       final DefaultsProducer<T> producer,
                       final Constraint<T>... constraints) {
        this.type = Validate.notNull("type", type);
        this.producer = validateProducer(producer);
        this.constraints = arrayToSet(constraints);
    }


    public boolean isApplicable(final String value) {
        return (isAcceptableNull(value)) || (hasValidType(value) && doesMeetConstraints(value));
    }


    public boolean canBeNull() {
        for (Constraint constraint : constraints) {
            if ((constraint.type() == Constraints.NOT_EQUAL) && (constraint.constraint() == null)) {
                return false;
            }
        }

        return true;
    }


    public T defaultValue() {
        return producer != null ? producer.getDefault() : null;
    }


    public PureType<T> type() {
        return type;
    }


    public T cast(final String value) {
        return type().cast(value);
    }


    @Override
    public String toString() {
        return String.format("%s constraints:%s default value producer:%s",
                type(), Pretty.format(constraints), producer);
    }


    private boolean isAcceptableNull(final String string) {
        return ((string == null) && (producer != null)) || (canBeNull());
    }


    private boolean hasValidType(final String string) {
        return (type == STRING) || (PureTypes.identify(string) == type);
    }


    private boolean doesMeetConstraints(final String string) {
        return doesMeetConstraints(type.cast(string));
    }


    private boolean doesMeetConstraints(final T object) {
        for (Constraint<T> constraint : constraints) {
            try {
                if (!constraint.check(object)) {
                    return false;
                }
            } catch (ClassCastException ex) {
                return false;
            }
        }

        return true;
    }


    private DefaultsProducer<T> validateProducer(final DefaultsProducer<T> producer) {
        if ((producer != null) && (!doesMeetConstraints(producer.getDefault()))) {
            throw new IllegalArgumentException("Default value doesn't meet constraints");
        }

        return producer;
    }


    private Set<Constraint<T>> arrayToSet(final Constraint<T>[] constraints) {
        Set<Constraint<T>> set = new HashSet<>();

        if (constraints != null) {
            Collections.addAll(set, constraints);
        }

        return set;
    }
}
