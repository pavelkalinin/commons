package xyz.enhorse.commons;

import org.slf4j.Logger;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         01.08.2016
 */
public abstract class AbstractLogged {

    private final Logger log;


    protected AbstractLogged(final Logger logger) {
        this.log = Validate.notNull("logger", logger);
    }


    protected void logSuccess(String operation) {
        log.info("Successfully " + operation);
    }


    protected void logRequest(String operation) {
        log.info("Request to " + operation);
    }


    protected void logError(Exception ex) {
        log.error(ex.getMessage(), ex);
    }
}
