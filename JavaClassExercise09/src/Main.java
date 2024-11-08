import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main  extends Application{
    private Label displayLabel;
    private StringBuilder input = new StringBuilder();
    private double num1 = 0;
    private String operator = "";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Display label for showing input and results
        displayLabel = new Label("0");
        displayLabel.setStyle("-fx-font-size: 20px; -fx-padding: 10px;");

        // Setting up the grid layout for calculator buttons
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(20));

        // Adding display label to the grid
        grid.add(displayLabel, 0, 0, 4, 1);

        // Adding buttons for numbers and operations
        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "C", "0", "=", "+"
        };

        int row = 1;
        int col = 0;
        for (String label : buttonLabels) {
            Button button = new Button(label);
            button.setPrefSize(50, 50);
            button.setStyle("-fx-font-size: 18px;");
            button.setOnAction(e -> buttonPressed(label));
            grid.add(button, col, row);
            col++;
            if (col == 4) {
                col = 0;
                row++;
            }
        }

        // Setting up the scene
        Scene scene = new Scene(grid, 300, 400);
        primaryStage.setTitle("Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void buttonPressed(String label) {
        switch (label) {
            case "C":
                // Clear everything
                input.setLength(0);
                num1 = 0;
                operator = "";
                displayLabel.setText("0");
                break;
            case "=":
                // Calculate the result
                if (operator.isEmpty()) return;

                try {
                    double num2 = Double.parseDouble(input.toString());
                    double result = calculate(num1, num2, operator);
                    displayLabel.setText(String.valueOf(result));
                    input.setLength(0);
                    input.append(result); // Allow chaining calculations
                    operator = "";
                } catch (NumberFormatException e) {
                    displayLabel.setText("Error");
                }
                break;
            case "+":
            case "-":
            case "*":
            case "/":
                // Set operator and store the first number
                if (!operator.isEmpty()) return; // Operator already selected

                try {
                    num1 = Double.parseDouble(input.toString());
                    operator = label;
                    input.setLength(0);
                } catch (NumberFormatException e) {
                    displayLabel.setText("Error");
                }
                break;
            default:
                // Handle number input
                if (input.length() < 10) { // Limit input length
                    input.append(label);
                    displayLabel.setText(input.toString());
                }
                break;
        }
    }

    private double calculate(double num1, double num2, String operator) {
        return switch (operator) {
            case "+" -> num1 + num2;
            case "-" -> num1 - num2;
            case "*" -> num1 * num2;
            case "/" -> num2 != 0 ? num1 / num2 : Double.NaN; // Avoid division by zero
            default -> 0;
        };
    }



    /*@Override
    public void start(Stage stage) throws Exception{
        StackPane layout=new StackPane();

        Button button_Result=new Button("Result");
        button_Result.setOnAction(actiononEvent->{
            System.out.println(System.getProperty("java.version"));
        });

        layout.getChildren().add(button_Result);
        Scene scene=new Scene(layout,300,300);
        stage.setScene(scene);
        stage.setTitle("Calculator");
        stage.show();
    }*/
}