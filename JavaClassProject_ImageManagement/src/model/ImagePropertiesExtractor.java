// Package declaration for the model
package model;

import javax.imageio.ImageIO; // For reading image files
import java.awt.image.BufferedImage; // For representing an image in memory
import java.io.File; // For handling file input
import java.util.HashMap; // For storing properties as key-value pairs
import java.util.Map; // For returning extracted properties

/**
 * Extracts basic properties of an image, such as width and height.
 * This class reads image files and retrieves their metadata.
 */
public class ImagePropertiesExtractor {

    /**
     * Extracts properties of the given image file.
     *
     * @param file - The image file from which properties will be extracted
     * @return A map containing key-value pairs of image properties (e.g., width, height)
     * @throws Exception if the file cannot be read or properties cannot be extracted
     */
    public Map<String, String> extractProperties(File file) throws Exception {
        // Map to store the extracted properties
        Map<String, String> properties = new HashMap<>();
        try {
            // Read the image file into a BufferedImage object
            BufferedImage image = ImageIO.read(file);

            // Check if the image is null (unsupported format or invalid file)
            if (image == null) {
                throw new Exception("Unsupported file format for: " + file.getName());
            }

            // Extract and store the image width and height
            properties.put("Width", String.valueOf(image.getWidth()));
            properties.put("Height", String.valueOf(image.getHeight()));

        } catch (Exception e) {
            // Wrap and rethrow any exceptions with a descriptive error message
            throw new Exception("Error extracting properties: " + e.getMessage(), e);
        }

        // Return the map containing the extracted properties
        return properties;
    }
}
