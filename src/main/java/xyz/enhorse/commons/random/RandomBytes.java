package xyz.enhorse.commons.random;

import java.util.concurrent.ThreadLocalRandom;

/**
 * The generator of a pseudo random array of bytes
 * @author <a href="mailto:ext_pkalinin@rencredit.ru">Pavel Kalinin</a>
 * on 23/12/17.
 */
public class RandomBytes implements RandomGenerator<byte[]> {

    /** The specified size of an array */
    final int size;


    /**
     * Creates a new {@code RandomBytes}
     * @param size the size of a byte array to generate
     */
    public RandomBytes(final int size) {
        this.size = size > 0 ? size : 0;
    }


    /** ${@inheritDoc} */
    @Override
    public byte[] next() {
        final byte[] result = new byte[size];
        ThreadLocalRandom.current().nextBytes(result);

        return result;
    }
}
