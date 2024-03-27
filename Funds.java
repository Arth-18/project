package application; // Package declaration for the application

import java.io.IOException; // Import statement for IOException class
import java.net.URL; // Import statement for URL class
import java.sql.Connection; // Import statement for Connection class
import java.sql.DriverManager; // Import statement for DriverManager class
import java.sql.ResultSet; // Import statement for ResultSet class
import java.sql.Statement; // Import statement for Statement class
import java.util.Optional; // Import statement for Optional class
import java.util.ResourceBundle; // Import statement for ResourceBundle class

import javafx.animation.KeyFrame; // Import statement for KeyFrame class
import javafx.animation.Timeline; // Import statement for Timeline class
import javafx.event.ActionEvent; // Import statement for ActionEvent class
import javafx.fxml.FXML; // Import statement for FXML annotation
import javafx.fxml.Initializable; // Import statement for Initializable interface
import javafx.scene.control.Alert; // Import statement for Alert class
import javafx.scene.control.Button; // Import statement for Button class
import javafx.scene.control.Label; // Import statement for Label class
import javafx.util.Duration; // Import statement for Duration class

// Controller class for displaying funds
public class Funds implements Initializable {

    @FXML // Annotation for injecting FXML elements
    private Button ret; // Button for returning to previous screen
    @FXML // Annotation for injecting FXML elements
    private Button download; // Button for downloading funds data

    @FXML // Annotation for injecting FXML elements
    private Label tAmount; // Label for displaying total amount

    // Method to handle returning to the finance screen
    @FXML
    void doneChecking(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("Finance.fxml"); // Change scene to finance screen
    }

    // Initialize method for initializing the controller
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // Timeline for updating total amount periodically
        Timeline time = new Timeline(new KeyFrame(Duration.seconds(0.1), e -> {
            try {
                // Database connection to fetch current total amount
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://sql5.freesqldatabase.com:3306/sql5482717",
                        "sql5482717", "dFLcvrbMxR");
                Statement stmt = con.createStatement();
                String sql = "Select currentAmount from tAmount where tID = (Select MAX(tID) from tAmount)";
                ResultSet rs = stmt.executeQuery(sql);
                if (rs.next()) {
                    tAmount.setText(rs.getString(1)); // Update the total amount label
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }));

        time.setCycleCount(1); // Set cycle count for timeline
        time.play(); // Start the timeline
    }

    // Method to handle saving the funds data
    public void save(ActionEvent event) {
        PrintCSV.print(); // Call method to print funds data to CSV file

        // Bring up an alert box to inform about download completion
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Download Complete");
        alert.setContentText(".csv file saved at 'C:\\transactions'");
        alert.showAndWait();
    }

}
