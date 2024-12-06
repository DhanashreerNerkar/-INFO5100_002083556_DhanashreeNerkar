// Package declaration for the model
package model;

import java.io.File; // For representing image file paths

/**
 * Represents an image along with its associated metadata.
 * Provides a data model to encapsulate file details and image properties.
 */
public class ImageModel {
    // The image file
    private File imageFile;

    // Dimensions of the image
    private int width;
    private int height;

    /**
     * Constructor to initialize the ImageModel with its properties.
     *
     * @param imageFile - The file representing the image
     * @param width - The width of the image in pixels
     * @param height - The height of the image in pixels
     */
    public ImageModel(File imageFile, int width, int height){
        this.imageFile = imageFile;
        this.width = width;
        this.height = height;
    }

    /**
     * @return The file representing the image
     */
    public File getImageFile() {
        return imageFile;
    }

    /**
     * @return The width of the image in pixels
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return The height of the image in pixels
     */
    public int getHeight() {
        return height;
    }
}


