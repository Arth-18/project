package application; // Package declaration for the application

import java.io.IOException; // Import statement for IOException class

import javafx.application.Application; // Import statement for JavaFX Application class
import javafx.stage.Stage; // Import statement for JavaFX Stage class
import javafx.scene.Parent; // Import statement for JavaFX Parent class
import javafx.scene.Scene; // Import statement for JavaFX Scene class
import javafx.scene.layout.BorderPane; // Import statement for JavaFX BorderPane class
import javafx.fxml.FXMLLoader; // Import statement for JavaFX FXMLLoader class

// Main class that extends Application, making it a JavaFX application
public class Main extends Application {
    
    // Stage object to hold the primary stage of the application
    private static Stage stg;

    // Override the start method from Application class
    @Override
    public void start(Stage primaryStage) {
        try {
            // Set the primary stage to the given primaryStage
            stg = primaryStage;
            
            // Load the FXML file "Sample.fxml" into a BorderPane
            BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Sample.fxml"));
            // Create a new Scene with the root BorderPane
            Scene scene = new Scene(root);
            
            // Add the CSS stylesheet "application.css" to the scene
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            // Set the scene to the primary stage
            primaryStage.setScene(scene);
            // Show the primary stage
            primaryStage.show();
        } catch(Exception e) {
            // Print stack trace if an exception occurs during loading
            e.printStackTrace();
        }
    }
    
    // Method to change the scene based on the given FXML file
    public void changeScene(String fxml) throws IOException {
        // Load the FXML file into a Parent node
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        // Set the root of the current scene to the loaded Parent node
        stg.getScene().setRoot(pane);
    }

    // Main method to launch the JavaFX application
    public static void main(String[] args) {
        launch(args); // Launch the JavaFX application
    }
}
