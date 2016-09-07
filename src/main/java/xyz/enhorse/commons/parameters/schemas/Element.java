package xyz.enhorse.commons.parameters.schemas;

import xyz.enhorse.commons.Validate;
import xyz.enhorse.commons.collections.NamedValue;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         05.09.2016
 */
public class Element<T> implements NamedValue<Description<?>> {

    private final String name;
    private final Description<T> description;


    public Element(final String name, final Description<T> description) {
        this.name = Validate.notNullOrEmpty("element name", name);
        this.description = Validate.notNull("element description", description);
    }


    @Override
    public String toString() {
        return String.format("\'%s\'[%s]", name, description);
    }


    @Override
    public String name() {
        return name;
    }


    @Override
    public Description<?> value() {
        return description;
    }
}
