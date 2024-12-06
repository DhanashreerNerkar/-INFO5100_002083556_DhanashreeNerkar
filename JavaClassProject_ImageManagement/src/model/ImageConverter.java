// Package declaration for the model
package model;

import javax.imageio.ImageIO; // For image reading and writing
import java.awt.image.BufferedImage; // For representing images in memory
import java.io.File; // For file handling

/**
 * Implementation of the Convertible interface.
 * Provides functionality to convert images from one format to another.
 */
public class ImageConverter implements Convertible {

    /**
     * Converts the given source image file to the specified format and saves it to the destination file.
     *
     * @param source - The source file to be converted
     * @param destination - The file where the converted image will be saved
     * @param format - The desired output format (e.g., "png", "jpg", "bmp")
     * @throws Exception if an error occurs during the conversion process
     */
    @Override
    public void convert(File source, File destination, String format) throws Exception {
        try {
            // Read the source image file into a BufferedImage
            BufferedImage image = ImageIO.read(source);

            // Check if the image could not be read (unsupported format or invalid file)
            if (image == null) {
                throw new UnsupportedOperationException("Unsupported file format for: " + source.getName());
            }

            // Attempt to write the image in the desired format to the destination file
            boolean result = ImageIO.write(image, format, destination);

            // If the write operation fails, throw an exception
            if (!result) {
                throw new Exception("Conversion failed for file: " + source.getName());
            }
        } catch (Exception e) {
            // Wrap and rethrow any exceptions with a descriptive message
            throw new Exception("Error during conversion: " + e.getMessage(), e);
        }
    }
}
