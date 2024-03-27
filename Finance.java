package application; // Package declaration for the application

import java.io.IOException; // Import statement for IOException class
import java.sql.Connection; // Import statement for Connection class
import java.sql.DriverManager; // Import statement for DriverManager class
import java.sql.ResultSet; // Import statement for ResultSet class
import java.sql.Statement; // Import statement for Statement class
import java.util.Date; // Import statement for Date class
import java.util.regex.Matcher; // Import statement for Matcher class
import java.util.regex.Pattern; // Import statement for Pattern class
import java.time.LocalDateTime; // Import statement for LocalDateTime class
import java.time.format.DateTimeFormatter; // Import statement for DateTimeFormatter class

import javafx.event.ActionEvent; // Import statement for ActionEvent class
import javafx.fxml.FXML; // Import statement for FXML annotation
import javafx.scene.control.Button; // Import statement for Button class
import javafx.scene.control.Label; // Import statement for Label class
import javafx.scene.control.TextField; // Import statement for TextField class
import javafx.scene.input.KeyCode; // Import statement for KeyCode enum
import javafx.scene.input.KeyEvent; // Import statement for KeyEvent class

// Controller class for financial transactions
public class Finance {

    @FXML // Annotation for injecting FXML elements
    private Button mainmenu; // Button for navigating to the main menu
    @FXML // Annotation for injecting FXML elements
    private Button funds; // Button for checking available funds
    @FXML // Annotation for injecting FXML elements
    private Button ok; // Button for confirming actions
    @FXML // Annotation for injecting FXML elements
    private Button submit; // Button for submitting transactions
    @FXML // Annotation for injecting FXML elements
    private Button deposit; // Button for depositing funds
    @FXML // Annotation for injecting FXML elements
    private Button withdraw; // Button for withdrawing funds
    @FXML // Annotation for injecting FXML elements
    private Button wSub; // Button for confirming withdrawals
    @FXML // Annotation for injecting FXML elements
    private Button cancel; // Button for canceling actions
    @FXML // Annotation for injecting FXML elements
    private TextField dAmount; // TextField for entering deposit amount
    @FXML // Annotation for injecting FXML elements
    private TextField dReason; // TextField for entering deposit reason
    @FXML // Annotation for injecting FXML elements
    private TextField wAmount; // TextField for entering withdrawal amount
    @FXML // Annotation for injecting FXML elements
    private TextField wReason; // TextField for entering withdrawal reason
    @FXML // Annotation for injecting FXML elements
    private Label tAmount; // Label for displaying total amount
    @FXML // Annotation for injecting FXML elements
    private Label warning; // Label for displaying warning messages
    
    private double totalAmount; // Variable to store the total amount
    private Date currentDate; // Variable to store the current date
    private static String user = ""; // Static variable to store the logged-in user
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Date formatter
    
    public Finance() {
        // Default constructor
    }
    
    // Constructor with totalAmount parameter
    public Finance(int totalAmount) {
        this.totalAmount = totalAmount; // Initialize totalAmount
        this.currentDate = new Date(); // Initialize currentDate
    }

    // Getter method for retrieving the current total amount
    public double getCurrentAmount() {
        return totalAmount;
    }
    
    // Getter method for retrieving the current date
    public Date getDate() {
        return currentDate;
    }
    
    // Setter method for setting the logged-in user
    public static void setUser(String us) {
        user = us;
    }
    
    // Method to handle deposit transactions
    public void deposit(Transaction deposit) {
        totalAmount += deposit.getAmount(); // Add deposit amount to totalAmount
        
        // Database or list insertion for deposit transaction
    }
    
    // Method to handle withdrawal transactions
    public void withdraw(Transaction withdraw) {
        totalAmount -= withdraw.getAmount(); // Subtract withdrawal amount from totalAmount
        
        // Database or list insertion for withdrawal transaction
    }
    
    // Method to navigate to the main menu
    public void mainMenu(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("afterLogin.fxml"); // Change scene to main menu
    }
    
    // Method to check available funds
    public void checkFunds(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("DisplayFunds.fxml"); // Change scene to display funds
    }
    
    // Method to navigate to deposit interface
    public void depositAmount(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("Deposit.fxml"); // Change scene to deposit interface
    }
    
    // Method to navigate to withdrawal interface
    public void withdrawAmount(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("Withdraw.fxml"); // Change scene to withdrawal interface
    }
    
    // Method to navigate back to finance interface
    public void doneChecking(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("Finance.fxml"); // Change scene to finance interface
    }
    
    // Method to handle deposit action
    public void deposit(ActionEvent event) throws IOException {
        dep(); // Call the dep method for deposit handling
    }
    
    // Method to handle withdrawal action
    public void withdraw(ActionEvent event) throws IOException {
        wit(); // Call the wit method for withdrawal handling
    }
    
    // Method to handle Enter key press for deposit
    public void dEasy(KeyEvent event) throws IOException {
        if(event.getCode() == KeyCode.ENTER) {
            dep(); // Call the dep method on pressing Enter key
        }
    }
    
    // Method to handle Enter key press for withdrawal
    public void wEasy(KeyEvent event) throws IOException {
        if(event.getCode() == KeyCode.ENTER) {
            wit(); // Call the wit method on pressing Enter key
        }
    }
    
    // Method for withdrawal handling
    private void wit() throws IOException {
        Main m = new Main();
        
        if(wAmount.getText().isEmpty() || wReason.getText().isEmpty()) {
            warning.setText
