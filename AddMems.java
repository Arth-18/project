package application; // Package declaration for the application

import java.io.IOException; // Import statement for IOException class
import java.sql.Connection; // Import statement for Connection class
import java.sql.DriverManager; // Import statement for DriverManager class
import java.sql.ResultSet; // Import statement for ResultSet class
import java.sql.Statement; // Import statement for Statement class
import java.util.regex.Matcher; // Import statement for Matcher class
import java.util.regex.Pattern; // Import statement for Pattern class

import javafx.event.ActionEvent; // Import statement for ActionEvent class
import javafx.fxml.FXML; // Import statement for FXML annotation
import javafx.scene.control.Button; // Import statement for Button class
import javafx.scene.control.Label; // Import statement for Label class
import javafx.scene.control.TextField; // Import statement for TextField class
import javafx.scene.input.KeyCode; // Import statement for KeyCode enum
import javafx.scene.input.KeyEvent; // Import statement for KeyEvent class

// Controller class for adding members in the application
public class AddMems {

    @FXML // Annotation for injecting FXML elements
    private Button confirm; // Button for confirming member insertion
    @FXML // Annotation for injecting FXML elements
    private Button cancel; // Button for canceling member insertion
    @FXML // Annotation for injecting FXML elements
    private Label warning; // Label for displaying warning messages

    @FXML // Annotation for injecting FXML elements
    private TextField mDateJoined; // TextField for entering member's join date
    @FXML // Annotation for injecting FXML elements
    private TextField mEmail; // TextField for entering member's email
    @FXML // Annotation for injecting FXML elements
    private TextField mID; // TextField for entering member's ID
    @FXML // Annotation for injecting FXML elements
    private TextField mName; // TextField for entering member's name

    // Method to insert a member on button click
    public void insertMember(ActionEvent event) throws IOException {
        insert(); // Call the insert method to insert the member
    }
    
    // Method to navigate to the members list after inserting a member
    public void doneChecking(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("Members.fxml"); // Change the scene to the members list
    }
    
    // Method to handle keyboard shortcuts
    public void easy(KeyEvent event) throws IOException {
        if(event.getCode() == KeyCode.ENTER) {
            insert(); // Call the insert method on pressing Enter key
        }
    }

    // Method to insert the member
    private void insert() throws IOException {
        Main m = new Main();
        
        // Check if any field is empty
        if(mID.getText().isEmpty() || mName.getText().isEmpty() || mEmail.getText().isEmpty() || mDateJoined.getText().isEmpty()) {
            warning.setText("Incomplete Information provided"); // Display warning message
        } else {
            String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}";
            Pattern pattern = Pattern.compile(regex); 
            Matcher matcher = pattern.matcher(mEmail.getText());
            if(!matcher.matches()) {
                warning.setText("Email Format is Incorrect"); // Display warning for incorrect email format
            } else {
                regex = "^\\d{4}-\\d{2}-\\d{2}$"; // Regular expression for date format
                pattern = Pattern.compile(regex); 
                matcher = pattern.matcher(mDateJoined.getText());
                if(!matcher.matches()) {
                    warning.setText("Date format is incorrect"); // Display warning for incorrect date format
                } else {
                    regex = "[0-9]{5}"; // Regular expression for 5-digit number
                    pattern = Pattern.compile(regex); 
                    matcher = pattern.matcher(mID.getText());
                    if(!matcher.matches()) {
                        warning.setText("StudentID is 5-digit number only"); // Display warning for incorrect student ID format
                    } else {
                        // Database connection
                        try {
                            Class.forName("com.mysql.jdbc.Driver");
                            Connection con= DriverManager.getConnection("jdbc:mysql://sql5.freesqldatabase.com:3306/sql5482717","sql5482717","dFLcvrbMxR");
                            Statement stmt=con.createStatement();
                            
                            String sql="Select memberID from member where memberID = "+mID.getText(); // SQL query to check if member ID already exists
                            ResultSet rs=stmt.executeQuery(sql);
                            if(rs.next()) {
                                warning.setText("This StudentID already exists"); // Display warning if member ID already exists
                                return;
                            }
                            else {
                                // Insert new member into the database
                                String sql2="Insert into member values("+mID.getText().toString()+",'"+mName.getText().toString()+"', '"+mEmail.getText().toString()+"', '"+mDateJoined.getText().toString()+"')";
                                stmt.execute(sql2); // Execute SQL insert query
                            }
                        } catch(Exception e) {
                            System.out.println(e); // Print exception if database operation fails
                        }
                        m.changeScene("Members.fxml"); // Change scene to members list
                    }
                } 
            }
        }
    }
}
