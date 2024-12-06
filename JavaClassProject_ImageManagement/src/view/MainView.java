package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Map;

public class MainView {
    private Stage stage; // The primary stage for the application
    private VBox rootLayout; // The main container layout
    private HBox buttonLayout; // Layout for buttons (upload, convert)
    private VBox previewLayout; // Layout for image thumbnails
    private VBox propertiesLayout; // Layout for image properties
    private VBox conversionLayout; // Layout for conversion options

    private Button uploadButton; // Button to upload images
    private Button convertButton; // Button to convert images
    private ComboBox<String> formatSelector; // Dropdown to select image formats

    private FlowPane imagePreviewLayout; // Layout for displaying image thumbnails
    private TableView<Map.Entry<String, String>> propertiesTable; // Table to display image properties

    /**
     * Constructor initializes the main view and sets up the GUI.
     *
     * @param stage The primary stage for the application.
     */
    public MainView(Stage stage) {
        this.stage = stage;
        initUI(); // Initialize the user interface
    }

    /**
     * Initializes the graphical user interface components.
     */
    private void initUI() {
        // Root Layout - Vertical Box Layout
        rootLayout = new VBox(10); // Vertical box with spacing of 10
        rootLayout.setPadding(new Insets(10)); // Padding around the root layout

        // Button Layout - Horizontal Layout for buttons
        buttonLayout = new HBox(10); // Horizontal box with spacing of 10
        buttonLayout.setPadding(new Insets(10));
        uploadButton = new Button("Upload Images"); // Button to upload images
        convertButton = new Button("Convert Images"); // Button to convert images
        formatSelector = new ComboBox<>(FXCollections.observableArrayList("png", "jpg", "bmp", "gif")); // Dropdown for formats
        formatSelector.setPromptText("Select Format"); // Placeholder text for dropdown

        // Add buttons and dropdown to button layout
        buttonLayout.getChildren().addAll(uploadButton, formatSelector, convertButton);

        // Preview Layout - Thumbnails Section
        previewLayout = new VBox(10); // Vertical box for thumbnails
        previewLayout.setPadding(new Insets(10));
        Label previewLabel = new Label("Image Thumbnails:"); // Label for the section
        imagePreviewLayout = new FlowPane(); // FlowPane for arranging thumbnails
        imagePreviewLayout.setPadding(new Insets(10));
        imagePreviewLayout.setHgap(10); // Horizontal gap between thumbnails
        imagePreviewLayout.setVgap(10); // Vertical gap between thumbnails
        previewLayout.getChildren().addAll(previewLabel, imagePreviewLayout);

        // Add border to the preview layout for better visuals
        previewLayout.setBorder(new Border(new BorderStroke(javafx.scene.paint.Color.LIGHTGRAY,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        // Properties Layout - Table for image properties
        propertiesLayout = new VBox(10); // Vertical box for properties
        propertiesLayout.setPadding(new Insets(10));
        Label propertiesLabel = new Label("Image Properties:"); // Label for the section

        // TableView to display image properties
        propertiesTable = new TableView<>();
        propertiesTable.setPlaceholder(new Label("No image properties to display.")); // Placeholder when table is empty

        // Configure Table Columns
        TableColumn<Map.Entry<String, String>, String> keyColumn = new TableColumn<>("Property"); // Column for property name
        keyColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getKey()));

        TableColumn<Map.Entry<String, String>, String> valueColumn = new TableColumn<>("Value"); // Column for property value
        valueColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getValue()));

        // Add columns to the table
        propertiesTable.getColumns().addAll(keyColumn, valueColumn);

        // Add table and label to properties layout
        propertiesLayout.getChildren().addAll(propertiesLabel, propertiesTable);

        // Add border to the properties layout for better visuals
        propertiesLayout.setBorder(new Border(new BorderStroke(javafx.scene.paint.Color.LIGHTGRAY,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        // Conversion Layout - Options for image conversion
        conversionLayout = new VBox(10); // Vertical box for conversion options
        conversionLayout.setPadding(new Insets(10));
        Label conversionLabel = new Label("Conversion Options:"); // Label for the section

        // Add label and button layout to conversion layout
        conversionLayout.getChildren().addAll(conversionLabel, buttonLayout);

        // Add all layouts to the root layout
        rootLayout.getChildren().addAll(previewLayout, propertiesLayout, conversionLayout);

        // Scene Setup
        Scene scene = new Scene(rootLayout, 800, 600); // Scene with fixed width and height
        stage.setScene(scene); // Set the scene to the stage
        stage.setTitle("Image Management Tool"); // Title of the application window
        stage.show(); // Display the stage
    }

    /**
     * Returns the upload button.
     *
     * @return The upload button.
     */
    public Button getUploadButton() {
        return uploadButton;
    }

    /**
     * Returns the convert button.
     *
     * @return The convert button.
     */
    public Button getConvertButton() {
        return convertButton;
    }

    /**
     * Returns the format selector combo box.
     *
     * @return The combo box for selecting formats.
     */
    public ComboBox<String> getFormatSelector() {
        return formatSelector;
    }

    /**
     * Returns the layout for displaying image thumbnails.
     *
     * @return The flow pane for image thumbnails.
     */
    public FlowPane getImagePreviewLayout() {
        return imagePreviewLayout;
    }

    /**
     * Adds properties for an image to the properties table.
     *
     * @param imageName The name of the image file.
     * @param properties The properties of the image.
     */
    public void addPropertiesToTable(String imageName, Map<String, String> properties) {
        // Convert the properties map to an observable list
        ObservableList<Map.Entry<String, String>> items = FXCollections.observableArrayList(properties.entrySet());
        propertiesTable.setItems(items); // Set items to the table
    }
}
