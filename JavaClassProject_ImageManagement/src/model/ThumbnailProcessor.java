// Package declaration for the model
package model;

import javafx.scene.image.ImageView; // For displaying thumbnail images
import java.io.File; // For handling file input

/**
 * Processes image files to generate and handle thumbnails.
 * Extends the AbstractImageProcessor to provide specific processing behavior.
 */
public class ThumbnailProcessor extends AbstractImageProcessor {

    /**
     * Processes the given image file to generate a thumbnail.
     *
     * @param file - The image file to be processed
     * @throws Exception if an error occurs during thumbnail creation
     */
    @Override
    public void process(File file) throws Exception {
        // Generate a thumbnail for the given file
        ImageView thumbnail = createThumbnail(file);

        // For demonstration purposes, print a confirmation message
        System.out.println("Generated thumbnail for: " + file.getName());
    }
}
