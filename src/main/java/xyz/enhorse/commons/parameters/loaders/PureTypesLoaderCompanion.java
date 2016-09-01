package xyz.enhorse.commons.parameters.loaders;

import xyz.enhorse.commons.parameters.schemas.PureTypes;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         31.08.2016
 */
public enum PureTypesLoaderCompanion implements LoaderCompanion {
    INSTANCE;

    @Override
    public Object postProcessValue(final String value) {
        return PureTypes.convert(value);
    }
}
