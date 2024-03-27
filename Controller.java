package application; // Package declaration for the application

import java.io.IOException; // Import statement for IOException class
import java.sql.*; // Import statement for JDBC classes

import javafx.event.ActionEvent; // Import statement for ActionEvent class
import javafx.fxml.FXML; // Import statement for FXML annotation
import javafx.scene.control.Button; // Import statement for Button class
import javafx.scene.control.Label; // Import statement for Label class
import javafx.scene.control.PasswordField; // Import statement for PasswordField class
import javafx.scene.control.TextField; // Import statement for TextField class
import javafx.scene.input.KeyCode; // Import statement for KeyCode enum
import javafx.scene.input.KeyEvent; // Import statement for KeyEvent class

// Controller class for the main application interface
public class Controller {
    private static String user = ""; // Static variable to store the logged-in user

    public Controller() {
        // Default constructor
    }

    @FXML // Annotation for injecting FXML elements
    private Button button; // Button for login action
    @FXML // Annotation for injecting FXML elements
    private Label wrongLogIn; // Label for displaying login status messages
    @FXML // Annotation for injecting FXML elements
    private TextField username; // TextField for entering username
    @FXML // Annotation for injecting FXML elements
    private PasswordField password; // PasswordField for entering password

    // Getter method for retrieving the logged-in user
    public static String getUser() {
        return user;
    }

    // Method to handle user login on button click
    public void userlogin(ActionEvent event) throws IOException {
        checkLogin(); // Call the checkLogin method to validate the login credentials
    }

    // Method to check the login credentials
    private void checkLogin() throws IOException {
        Main m = new Main(); // Create an instance of the Main class
        
        // Database connection and login validation
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://sql5.freesqldatabase.com:3306/sql5482717","sql5482717","dFLcvrbMxR");
            Statement stmt=con.createStatement();
            String sql="Select * from login where username='"+username.getText().toString()+"' and password = '"+password.getText().toString()+"'";
            ResultSet rs=stmt.executeQuery(sql);
            
            // Check if login is successful
            if(rs.next()) {
                wrongLogIn.setText("Success!");
                
                // Keep the username for other parts of the application
                Finance.setUser(username.getText().toString());
                MainMenu.setUser(username.getText().toString());

                // Change the scene to the main interface after successful login
                m.changeScene("afterLogin.fxml");
            }
            // Display error messages for incorrect or missing credentials
            else if(username.getText().isEmpty() || password.getText().isEmpty()) {
                wrongLogIn.setText("Please enter your data.");
            }
            else {
                wrongLogIn.setText("Wrong username or password!");
            }
        } catch(Exception e) {
            System.out.println(e); // Print any exceptions that occur during database operations
        }
    }
    
    // Method to handle login on pressing Enter key
    public void easyLog(KeyEvent event) throws IOException {
        if(event.getCode() == KeyCode.ENTER) {
            checkLogin(); // Call the checkLogin method on pressing Enter key
        }
    }
}
