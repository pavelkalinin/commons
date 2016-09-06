package xyz.enhorse.commons.parameters.schemas.constraints;

import xyz.enhorse.commons.Check;
import xyz.enhorse.commons.Email;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         06.09.2016
 */
public enum StringConstraints implements Constraint<String> {
    E_MAIL {
        @Override
        public boolean isApplicable(final String value) {
            return Email.isValid(value);
        }
    },


    URL_SAFE {
        @Override
        public boolean isApplicable(final String value) {
            return Check.isUrlSafe(value);
        }
    },


    NOT_EMPTY {
        @Override
        public boolean isApplicable(final String value) {
            return (value != null) && (!(value.trim().isEmpty()));
        }
    };


    @Override
    public String toString() {
        return name();
    }
}
