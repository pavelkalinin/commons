package xyz.enhorse.commons.parameters.schemas;

import xyz.enhorse.commons.DefaultsProducer;
import xyz.enhorse.commons.Pretty;
import xyz.enhorse.commons.Validate;
import xyz.enhorse.commons.parameters.schemas.constraints.Constraint;
import xyz.enhorse.commons.parameters.schemas.constraints.NotNullConstraint;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static xyz.enhorse.commons.parameters.schemas.PureTypes.*;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         01.09.2016
 */
public class Description<T> {

    private final PureType<T> type;
    private final DefaultsProducer<T> producer;
    private final Set<Constraint<T>> constraints = new HashSet<>();


    @SafeVarargs
    public Description(final PureType<T> type,
                       final DefaultsProducer<T> producer,
                       final Constraint<T>... constraints) {
        this.type = Validate.notNull("type", type);
        this.producer = producer;
        if (constraints != null) {
            Collections.addAll(this.constraints, constraints);
        }
    }


    public boolean isApplicable(final String value) {
        if (value == null) {
            return canBeNull();
        }

        return hasValidType(value) && doesMeetConstraints(value);
    }


    public boolean canBeNull() {
        return (producer != null) || (!constraints.contains(new NotNullConstraint<T>()));
    }


    public T defaultValue() {
        return producer != null ? producer.getDefault() : null;
    }


    public T cast(final String value) {
        return type.cast(value);
    }


    private boolean hasValidType(final String string) {
        return (type == STRING) || (type.equals(PureTypes.identify(string)));
    }


    private boolean doesMeetConstraints(final String string) {
        T value = type.cast(string);

        for (Constraint<T> constraint : constraints) {
            if (!constraint.isApplicable(value)) {
                return false;
            }
        }

        return true;
    }


    @Override
    public String toString() {
        return String.format("%s; constraints:%s; default value producer:%s",
                type, Pretty.format(constraints), producer);
    }
}
