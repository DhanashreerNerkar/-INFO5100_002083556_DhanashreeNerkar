// Importing necessary packages for the application
import javafx.application.Application; // Base class for JavaFX applications
import javafx.stage.Stage; // Represents the primary window (stage) of the JavaFX application
import controller.MainController; // Importing the MainController class from the controller package

/**
 * Main class serves as the entry point for the JavaFX application.
 * It extends the Application class, enabling it to be the starting point of the JavaFX lifecycle.
 */
public class Main extends Application {

    /**
     * The start method is the main entry point for a JavaFX application.
     * It is automatically called after the JavaFX runtime is initialized.
     *
     * @param stage - the primary stage (window) for this JavaFX application
     */
    @Override
    public void start(Stage stage) {
        // Initialize the MainController with the primary stage
        // MainController handles the core logic and GUI interactions of the application
        new MainController(stage);
    }

    /**
     * The main method is the standard entry point for Java applications.
     * It launches the JavaFX application by calling the launch() method.
     *
     * @param args - command-line arguments passed to the application (if any)
     */
    public static void main(String[] args) {
        // Launch the JavaFX application, triggering the start() method
        launch();
    }
}
