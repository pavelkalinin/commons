package xyz.enhorse.commons.parameters.schemas.constraints;

import xyz.enhorse.commons.Check;
import xyz.enhorse.commons.Email;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         06.09.2016
 */
public enum StringConstraints implements Constraint<String>, ConstraintChecker<String> {
    E_MAIL {
        @Override
        public ConstraintChecker type() {
            return this;
        }


        @Override
        public String constraint() {
            return "@";
        }


        @Override
        public boolean check(final String value) {
            return Email.isValid(value);
        }


        @Override
        public boolean check(final String value, final String constraint) {
            return check(value);
        }
    },


    URL_SAFE {
        @Override
        public ConstraintChecker type() {
            return this;
        }


        @Override
        public String constraint() {
            return "";
        }


        @Override
        public boolean check(final String value) {
            return Check.isUrlSafe(value);
        }


        @Override
        public boolean check(final String value, final String constraint) {
            return check(value);
        }
    },


    NOT_EMPTY {
        @Override
        public ConstraintChecker type() {
            return NumberConstraintCheckers.NOT_EQUAL;
        }


        @Override
        public String constraint() {
            return "";
        }


        @Override
        public boolean check(final String value) {
            return (value != null) && (!(value.trim().isEmpty()));
        }


        @Override
        public boolean check(final String value, final String constraint) {
            return check(value);
        }
    }
}
