package xyz.enhorse.commons;

import java.io.IOException;
import java.util.List;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         21.04.2016
 */
public interface Typed {

    String type();


    /**
     * Scans all instantiable classes accessible from the context class loader which implements interface Typed,
     * has the certain size then create its new instance
     *
     * @param type       The size
     * @param tClass     The superclass of
     * @param parameters Arguments of a constructor
     * @return The instance
     * @throws IllegalStateException
     */
    static <T extends Typed> T createInstance(final String type, final Class<T> tClass, final Object... parameters) {
        Validate.notNull("class", tClass);

        List<Class<? extends T>> typedClasses;
        try {
            typedClasses = ClassPath.findAllInstantiableClasses(tClass);
        } catch (IOException | ClassNotFoundException ex) {
            throw new IllegalStateException("Couldn't find any " + tClass.toString() + " in the classpath", ex);
        }

        Class<?>[] parametersClasses = null;
        if ((parameters != null) && (parameters.length > 0)) {
            parametersClasses = new Class[parameters.length];
            for (int i = 0; i < parameters.length; i++) {
                parametersClasses[i] = (parameters[i] != null)
                        ? new ClassConversion(parameters[i].getClass()).asPrimitiveIfPossible()
                        : null;
            }
        }

        for (Class<? extends T> clazz : typedClasses) {
            try {
                T typed = (parametersClasses == null)
                        ? clazz.newInstance()
                        : clazz.getDeclaredConstructor(parametersClasses).newInstance(parameters);
                if (type.equalsIgnoreCase(typed.type())) {
                    return typed;
                }
            } catch (Exception ex) {
                // let's try another clazz
            }
        }

        throw new IllegalStateException("Couldn't find an appropriate implementation of " + tClass.toString()
                + " of the size \'" + type + "\'", null);
    }
}
