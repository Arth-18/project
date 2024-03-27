package application; // Package declaration for the application

// Class representing an event table entry
public class EventTable {

    // Instance variables to store event details
    String id, title, date, time, description;
    
    // Constructor to initialize the event details
    public EventTable(String id, String title, String date, String time, String description) {
        this.id = id;
        this.title = title;
        this.time = time;
        this.date = date;
        this.description = description;
    }

    // Getter method for retrieving the event ID
    public String getId() {
        return id;
    }

    // Setter method for setting the event ID
    public void setId(String id) {
        this.id = id;
    }

    // Getter method for retrieving the event title
    public String getTitle() {
        return title;
    }

    // Setter method for setting the event title
    public void setTitle(String title) {
        this.title = title;
    }

    // Getter method for retrieving the event date
    public String getDate() {
        return date;
    }

    // Setter method for setting the event date
    public void setDate(String date) {
        this.date = date;
    }

    // Getter method for retrieving the event time
    public String getTime() {
        return time;
    }

    // Setter method for setting the event time
    public void setTime(String time) {
        this.time = time;
    }

    // Getter method for retrieving the event description
    public String getDescription() {
        return description;
    }

    // Setter method for setting the event description
    public void setDescription(String description) {
        this.description = description;
    }
}
