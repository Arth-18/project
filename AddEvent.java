package application; // Package declaration for the application

import java.io.IOException; // Import statement for IOException class
import java.sql.Connection; // Import statement for Connection class
import java.sql.DriverManager; // Import statement for DriverManager class
import java.sql.ResultSet; // Import statement for ResultSet class
import java.sql.Statement; // Import statement for Statement class
import java.util.Stack; // Import statement for Stack class
import java.util.regex.Matcher; // Import statement for Matcher class
import java.util.regex.Pattern; // Import statement for Pattern class

import javafx.event.ActionEvent; // Import statement for ActionEvent class
import javafx.fxml.FXML; // Import statement for FXML annotation
import javafx.scene.control.Button; // Import statement for Button class
import javafx.scene.control.CheckBox; // Import statement for CheckBox class
import javafx.scene.control.Label; // Import statement for Label class
import javafx.scene.control.TextField; // Import statement for TextField class
import javafx.scene.input.KeyCode; // Import statement for KeyCode enum
import javafx.scene.input.KeyEvent; // Import statement for KeyEvent class

// Controller class for adding events in the application
public class AddEvent {

    @FXML // Annotation for injecting FXML elements
    private Button add; // Button for adding an event
    @FXML // Annotation for injecting FXML elements
    private Button cancel; // Button for canceling the event addition
    @FXML // Annotation for injecting FXML elements
    private Label warning; // Label for displaying warning messages

    @FXML // Annotation for injecting FXML elements
    private TextField eDate; // TextField for entering event date
    @FXML // Annotation for injecting FXML elements
    private TextField eDescript; // TextField for entering event description
    @FXML // Annotation for injecting FXML elements
    private TextField eTime; // TextField for entering event time
    @FXML // Annotation for injecting FXML elements
    private TextField eTitle; // TextField for entering event title
    @FXML // Annotation for injecting FXML elements
    private CheckBox cbox; // CheckBox for sending email notifications

    // Method to handle adding an event on button click
    public void addEvent(ActionEvent event) throws IOException {
        add(); // Call the add method to add the event
    }
    
    // Method to send email notifications
    private void send() {
        Stack<String> recipient=new Stack<String>(); // Stack to store email recipients
        String body="TITLE:"+eTitle.getText().toString()+"\nDATE: "+eDate.getText().toString()+"\nTIME: "+eTime.getText().toString()+"\nDESCRIPTION: "+eDescript.getText().toString(); // Email body
        String title="COSC CLUB EVENT: "+eTitle.getText().toString(); // Email title
        
        try {
            // Load MySQL JDBC driver and establish connection
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://sql5.freesqldatabase.com:3306/sql5482717","sql5482717","dFLcvrbMxR");
            Statement stmt=con.createStatement();
            String sql="Select email from member"; // SQL query to select emails from members
            ResultSet rs=stmt.executeQuery(sql); // Execute the SQL query
            // Store emails in the recipient stack
            while(rs.next()) {
                recipient.push(rs.getString(1));
            }
            // Send email notifications to recipients
            while(!recipient.isEmpty()) {
                SendEmail.send(recipient.pop(),title,body);
            }
        } catch(Exception ex) {
            // Print exception if an error occurs during email sending
            System.out.println(ex);
        }
    }

    // Method to navigate to the event list after adding an event
    public void doneChecking(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("Event.fxml"); // Change the scene to the event list
    }
    
    // Method to handle keyboard shortcuts
    public void easy(KeyEvent event) throws IOException {
        if(event.getCode() == KeyCode.ENTER) {
            add(); // Call the add method on pressing Enter key
        }
    }

    // Method to add the event
    private void add() throws IOException {
        Main m = new Main();
        
        // Check if any field is empty
        if(eTitle.getText().isEmpty() || eDate.getText().isEmpty() || eTime.getText().isEmpty() || eDescript.getText().isEmpty()) {
            warning.setText("Incomplete Information provided"); // Display warning message
        } else {
            String regex = "^\\d{4}-\\d{2}-\\d{2}$"; // Regular expression for date format
            Pattern pattern = Pattern.compile(regex); 
            Matcher matcher = pattern.matcher(eDate.getText()); // Match date format
            if(!matcher.matches()) {
                warning.setText("Date Format is Incorrect"); // Display warning for incorrect date format
            } else {
                regex = "([01]?[0-9]|2[0-3]):[0-5][0-9]"; // Regular expression for time format
                pattern = Pattern.compile(regex); 
                matcher = pattern.matcher(eTime.getText()); // Match time format
                if(!matcher.matches()) {
                    warning.setText("Time Format is Incorrect"); // Display warning for incorrect time format
                } else {
                    if(cbox.isSelected()) { // Check if checkbox is selected
                        send(); // Send email notifications
                    }
                    String d=eDescript.getText().toString().replaceAll("\"", "'"); // Replace quotes in description
                    try {
                        // Establish database connection and insert event data into database
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con= DriverManager.getConnection("jdbc:mysql://sql5.freesqldatabase.com:3306/sql5482717","sql5482717","dFLcvrbMxR");
                        Statement stmt=con.createStatement();
                        String sql="Insert into eventInfo(title,date,time,description) values('"+eTitle.getText().toString()+"', '"+eDate.getText().toString()+"', '"+eTime.getText().toString()+"', \""+d+"\")"; // SQL query to insert event data
                        stmt.execute(sql); // Execute SQL query
                    } catch(Exception e) {
                        System.out.println(e); // Print exception if database operation fails
                    }
                    
                    m.changeScene("Event.fxml"); // Change scene to event list
                }
            }
        }
    }
}
