package xyz.enhorse.commons.parameters.schemas;

import xyz.enhorse.commons.DefaultsProducer;
import xyz.enhorse.commons.Validate;

import java.util.HashSet;
import java.util.Set;

import static xyz.enhorse.commons.parameters.schemas.PureTypes.STRING;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         01.09.2016
 */
public class Description {

    private final PureTypes type;
    private final DefaultsProducer producer;
    private final Set<Constraint> constraints;


    public Description(final PureTypes type,
                       final Set<Constraint> constraints,
                       final DefaultsProducer producer) {
        this.type = Validate.defaultIfNull(type, STRING);
        this.producer = producer;
        this.constraints = Validate.defaultIfNull(constraints, new HashSet<Constraint>());
    }


    public boolean isApplicable(final String value) {
        return (isAcceptableNull(value)) || (hasValidType(value) && doesMeetConstraints(value));
    }


    private boolean isAcceptableNull(final String string) {
        return ((string == null) && (producer != null)) || (doesConstraintsHasNotNull());
    }


    private boolean hasValidType(final String string) {
        return (type == STRING) || (PureTypes.identify(string) == type);
    }


    @SuppressWarnings("unchecked")
    private boolean doesMeetConstraints(final String string) {
        Comparable object = type.cast(string);

        for (Constraint constraint : constraints) {
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


    private boolean doesConstraintsHasNotNull() {
        for (Constraint constraint : constraints) {
            if (constraint.type() == Constraints.NOT_NULL) {
                return true;
            }
        }

        return false;
    }
}
