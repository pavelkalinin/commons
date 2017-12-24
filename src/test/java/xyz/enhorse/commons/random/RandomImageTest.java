package xyz.enhorse.commons.random;

import org.junit.Test;
import xyz.enhorse.commons.random.RandomImage.ColorSpace;

import java.awt.image.BufferedImage;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Tests for {@link RandomImage}
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 * on 24/12/17.
 */
public class RandomImageTest {

    @Test
    public void next() {
        final RandomEnumValue<ColorSpace> type = new RandomEnumValue<>(ColorSpace.class);

        for (int i = 0; i < 5; i++) {

            final int expectedWidth = ThreadLocalRandom.current().nextInt(100, 300);
            final int expectedHeight = ThreadLocalRandom.current().nextInt(100, 300);
            final ColorSpace expectedType = type.next();

            final BufferedImage actual
                    = new RandomImage(expectedWidth, expectedHeight, expectedType).next();

            assertEquals("Generated image must have a correct width", expectedWidth, actual.getWidth());
            assertEquals("Generated image must have a correct height", expectedHeight, actual.getHeight());
            assertEquals("Generated image must have a correct type", expectedType.code(), actual.getType());
        }
    }


    @Test
    public void createWithIncorrectParameters() {
        assertNotNull(new RandomImage(-1, -1, null).next());
    }
}