package xyz.enhorse.commons.random;

import xyz.enhorse.commons.Pair;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;
import java.util.concurrent.ThreadLocalRandom;

import static xyz.enhorse.commons.Validate.defaultIfNull;
import static xyz.enhorse.commons.Validate.minimumIfLess;

/**
 * The generator of a pseudo random {@link BufferedImage}
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 * on 24/12/17.
 */
public class RandomImage implements RandomGenerator<BufferedImage> {

    /** The size of a generated image */
    private final Pair<Integer> size;
    /** The color space of a generated image */
    private final ColorSpace colorSpace;


    /**
     * Creates a new {@code RandomImage}
     * @param width      a width of an image
     * @param height     a height of an image
     * @param colorSpace a color space of an image
     */
    public RandomImage(final int width, final int height, final ColorSpace colorSpace) {
        this.size = new Pair<>(minimumIfLess(width, 1), minimumIfLess(height, 1));
        this.colorSpace = defaultIfNull(colorSpace, ColorSpace.RGB);
    }


    /** ${@inheritDoc} */
    @Override
    public BufferedImage next() {
        return colorSpace.generate(size.a, size.b);
    }


    /** Color spaces of images */
    public enum ColorSpace {
        /** RGB color space */
        RGB(BufferedImage.TYPE_INT_RGB) {
            /** ${@inheritDoc} */
            @Override
            BufferedImage generate(final int width, final int height) {
                final BufferedImage result = new BufferedImage(width, height, code());
                int[] array = ((DataBufferInt) result.getRaster().getDataBuffer()).getData();

                for (int i = 0; i < array.length; ++i) {
                    array[i] = ThreadLocalRandom.current().nextInt(0xFFFFFF);
                }

                return result;
            }
        },

        /** Grayscale color space */
        GRAYSCALE(BufferedImage.TYPE_BYTE_GRAY),

        /** Bitmap color space */
        BITMAP(BufferedImage.TYPE_BYTE_BINARY);

        private final int code;


        ColorSpace(final int code) {
            this.code = code;
        }


        /**
         * Returns an integer code of the color space
         * @return an integer code of the color space
         */
        public int code() {
            return code;
        }


        /**
         * Returns a pseudo random {@link BufferedImage} with given parameters
         * @param width  a width of an image
         * @param height a height of an image
         * @return a pseudo random {@link BufferedImage}
         */
        BufferedImage generate(final int width, final int height) {
            final BufferedImage result = new BufferedImage(width, height, code());
            byte[] array = ((DataBufferByte) result.getRaster().getDataBuffer()).getData();

            for (int i = 0; i < array.length; ++i) {
                array[i] = (byte) ThreadLocalRandom.current().nextInt(0xFFFFFF);
            }

            return result;
        }
    }
}
