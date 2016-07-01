package xyz.enhorse.commons;


/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         24/04/16
 */
public class ClassConversion {
    private final Class CONTENT;


    public ClassConversion(Class object) {
        CONTENT = object;
    }


    public Class asPrimitive() {
        if (CONTENT == null) {
            return null;
        }

        if (CONTENT.isPrimitive()) {
            return CONTENT;
        }

        if (Byte.class.equals(CONTENT)) {
            return byte.class;
        }
        if (Short.class.equals(CONTENT)) {
            return short.class;
        }
        if (Integer.class.equals(CONTENT)) {
            return int.class;
        }
        if (Long.class.equals(CONTENT)) {
            return long.class;
        }
        if (Float.class.equals(CONTENT)) {
            return float.class;
        }
        if (Double.class.equals(CONTENT)) {
            return double.class;
        }
        if (Character.class.equals(CONTENT)) {
            return char.class;
        }
        if (Boolean.class.equals(CONTENT)) {
            return boolean.class;
        }

        throw new IllegalStateException(CONTENT.toString() + " cannot be unboxed to primitive size");
    }


    public Class asPrimitiveIfPossible() {
        try {
            return asPrimitive();
        } catch (IllegalStateException ex) {
            return CONTENT;
        }
    }


    public Class asReference() {
        if (CONTENT == null) {
            return null;
        }

        if (CONTENT.isPrimitive()) {
            if (byte.class.equals(CONTENT)) {
                return Byte.class;
            }
            if (short.class.equals(CONTENT)) {
                return Short.class;
            }
            if (int.class.equals(CONTENT)) {
                return Integer.class;
            }
            if (long.class.equals(CONTENT)) {
                return Long.class;
            }
            if (float.class.equals(CONTENT)) {
                return Float.class;
            }
            if (double.class.equals(CONTENT)) {
                return Double.class;
            }
            if (char.class.equals(CONTENT)) {
                return Character.class;
            }
            if (boolean.class.equals(CONTENT)) {
                return Boolean.class;
            }
        }

        return CONTENT;
    }
}
