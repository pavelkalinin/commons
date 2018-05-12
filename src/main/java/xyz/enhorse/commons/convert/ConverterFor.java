package xyz.enhorse.commons.convert;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that it's a converter for the certain class specified within the value
 * @author Pavel Kalinin on 06.04.2018
 * @see Converter
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ConverterFor {

    /** Returns the certain class that the converter operated with */
    Class<?> value();
}
