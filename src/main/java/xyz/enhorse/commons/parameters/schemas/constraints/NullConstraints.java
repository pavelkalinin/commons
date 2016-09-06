package xyz.enhorse.commons.parameters.schemas.constraints;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         06.09.2016
 */
public enum NullConstraints implements Constraint<Object>, ConstraintChecker<Object> {
    NOT_NULL {
        @Override
        public ConstraintChecker type() {
            return this;
        }


        @Override
        public Comparable constraint() {
            return null;
        }


        @Override
        public boolean check(final Object value) {
            return value != null;
        }


        @Override
        public boolean check(final Object value, final Object constraint) {
            return check(value);
        }
    },


    NULL {
        @Override
        public ConstraintChecker type() {
            return this;
        }


        @Override
        public Comparable constraint() {
            return null;
        }


        @Override
        public boolean check(final Object value) {
            return value == null;
        }


        @Override
        public boolean check(final Object value, final Object constraint) {
            return check(value);
        }
    }
}
