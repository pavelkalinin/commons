package xyz.enhorse.commons.parameters;

import xyz.enhorse.commons.types.PureTypes;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         29.08.2016
 */
public enum PureTypesLoaderCompanion implements LoaderCompanion {
    INSTANCE;


    @Override
    public Object postProcessValue(final String value) {
        return PureTypes.convert(value);
    }
}
