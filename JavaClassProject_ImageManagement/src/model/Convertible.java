// Package declaration for the model
package model;

import java.io.File; // For handling file input and output

/**
 * Convertible interface defines the contract for converting files
 * (e.g., images) from one format to another.
 */
public interface Convertible {

    /**
     * Converts a source file to a specified format and saves it to the destination file.
     *
     * @param source - The original file to be converted
     * @param destination - The target file where the converted output will be saved
     * @param format - The desired output format (e.g., "png", "jpg", "bmp")
     * @throws Exception if an error occurs during the conversion process
     */
    void convert(File source, File destination, String format) throws Exception;
}
