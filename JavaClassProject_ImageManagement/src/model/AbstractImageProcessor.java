// Package declaration for the model
package model;

// Importing necessary JavaFX classes for handling images
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File; // For file handling

/**
 * Abstract class representing a base image processor.
 * Provides common functionality for image processing tasks
 * and defines a structure for specific implementations.
 */
public abstract class AbstractImageProcessor {

    /**
     * Creates a thumbnail (scaled-down version) of an image file.
     *
     * @param file - The image file for which the thumbnail is to be generated
     * @return An ImageView containing the generated thumbnail
     * @throws Exception if an error occurs during thumbnail creation
     */
    public ImageView createThumbnail(File file) throws Exception {
        try {
            // Load the image from the file and scale it to 100x100 pixels while preserving aspect ratio
            Image image = new Image(file.toURI().toString(), 100, 100, true, true);

            // Wrap the image in an ImageView for display
            return new ImageView(image);
        } catch (Exception e) {
            // Throw a descriptive exception if thumbnail generation fails
            throw new Exception("Error generating thumbnail for file: " + file.getName(), e);
        }
    }

    /**
     * Abstract method for processing an image file.
     * Specific processing logic must be implemented in subclasses.
     *
     * @param file - The image file to be processed
     * @throws Exception if an error occurs during processing
     */
    public abstract void process(File file) throws Exception;
}
