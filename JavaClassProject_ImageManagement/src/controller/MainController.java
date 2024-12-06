// Package declaration for the controller
package controller;

// Importing necessary JavaFX classes for UI and file handling
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

// Importing custom model and view components
import model.*;
import view.MainView;

import java.io.File; // For file handling
import java.util.ArrayList; // For managing lists of files
import java.util.List; // List interface for file collections
import java.util.Map; // For handling key-value pairs (image properties)

/**
 * The MainController class acts as the central controller for managing the application's logic.
 * It bridges the interaction between the view (UI) and the model (business logic).
 */
public class MainController {

    // Reference to the MainView for UI interactions
    private MainView view;

    // List to store the uploaded image files
    private List<File> uploadedFiles;

    // Instances of various processors for handling image operations
    private AbstractImageProcessor imageProcessor; // For creating thumbnails
    private Convertible converter; // For image format conversions
    private ImagePropertiesExtractor propertiesExtractor; // For extracting image metadata

    /**
     * Constructor initializes the MainController, sets up the view, and configures button actions.
     *
     * @param stage - The primary stage (window) for the application
     */
    public MainController(Stage stage) {
        this.view = new MainView(stage); // Initialize the view
        this.uploadedFiles = new ArrayList<>(); // Initialize the list for uploaded files
        this.imageProcessor = new ThumbnailProcessor(); // Instantiate a processor for thumbnails
        this.converter = new ImageConverter(); // Instantiate a converter for image formats
        this.propertiesExtractor = new ImagePropertiesExtractor(); // Instantiate a properties extractor

        // Set event handlers for buttons
        view.getUploadButton().setOnAction(e -> handleUploadImages(stage));
        view.getConvertButton().setOnAction(e -> handleConvertImages());
    }

    /**
     * Handles the logic for uploading image files.
     * Opens a file chooser dialog and processes selected files.
     *
     * @param stage - The primary stage to show the file chooser dialog
     */
    private void handleUploadImages(Stage stage) {
        try {
            // Create a FileChooser to select image files
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.bmp", "*.gif"));
            List<File> files = fileChooser.showOpenMultipleDialog(stage);

            // Check if no files were selected
            if (files == null || files.isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "No files selected.", "Please select one or more image files to upload.");
                return;
            }

            // Add selected files to the uploaded files list
            uploadedFiles.addAll(files);

            // Display thumbnails and extract properties of the uploaded files
            displayThumbnails(files);
            displayProperties(files);
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error Uploading Files", "An unexpected error occurred: " + e.getMessage());
        }
    }

    /**
     * Generates and displays thumbnails for the uploaded images.
     *
     * @param files - List of uploaded image files
     */
    private void displayThumbnails(List<File> files) {
        try {
            for (File file : files) {
                // Create a thumbnail for each image and add it to the view
                ImageView thumbnail = imageProcessor.createThumbnail(file);
                view.getImagePreviewLayout().getChildren().add(thumbnail);
            }
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Thumbnail Error", "Failed to display image thumbnails: " + e.getMessage());
        }
    }

    /**
     * Extracts and displays properties (metadata) of the uploaded images.
     *
     * @param files - List of uploaded image files
     */
    private void displayProperties(List<File> files) {
        try {
            for (File file : files) {
                // Extract properties and add them to the view's table
                Map<String, String> properties = propertiesExtractor.extractProperties(file);
                view.addPropertiesToTable(file.getName(), properties);
            }
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Property Extraction Error", "Failed to extract image properties: " + e.getMessage());
        }
    }

    /**
     * Handles the logic for converting images to a selected format.
     * Uses the converter to create new files in the specified format.
     */
    private void handleConvertImages() {
        try {
            // Get the format selected by the user
            String format = view.getFormatSelector().getValue();

            // Check if no format was selected
            if (format == null || format.isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "No Format Selected", "Please select a format to convert the images.");
                return;
            }

            // Convert each uploaded file to the selected format
            for (File file : uploadedFiles) {
                // Generate a new file with the desired format extension
                File destination = new File(file.getParent(), file.getName().replaceFirst("[.][^.]+$", "." + format));
                converter.convert(file, destination, format);
            }

            // Notify the user of successful conversion
            showAlert(Alert.AlertType.INFORMATION, "Conversion Success", "All images converted successfully!");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Conversion Error", "An error occurred during image conversion: " + e.getMessage());
        }
    }

    /**
     * Utility method to display alerts for user notifications.
     *
     * @param type - Type of alert (e.g., INFORMATION, WARNING, ERROR)
     * @param title - Title of the alert dialog
     * @param message - Message content for the alert
     */
    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
