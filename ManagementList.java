package application; // Package declaration for the application

// Generic class for managing a linked list of management data
public class ManagementList<T> {

    // Inner Node class to represent each node in the linked list
    private class Node {
        String username;
        String password;
        String name;
        int memberID;
        int managementID;
        Node next;

        // Node constructor
        Node(String username, String password, String name, int memberID, int managementID, Node next) {
            this.username = username;
            this.password = password;
            this.name = name;
            this.memberID = memberID;
            this.managementID = managementID;
            this.next = next;
        }
    }

    private Node head; // Reference to the first node in the list

    // Constructor to initialize an empty list
    public ManagementList() {
        head = null; // Initialize head to null indicating an empty list
    }

    // Method to check if the list is empty
    public boolean isEmpty() {
        return head == null; // Return true if head is null (empty list), false otherwise
    }

    // Method to insert a new node into the list
    public void insert(String username, String password, String name, int memberID, int managementID) {
        Node prev = null;
        Node curr = head;

        while (curr != null) {
            prev = curr;
            curr = curr.next;
        }

        // Insert the new node between nodes or at the head if the list is empty
        if (prev != null) {
            prev.next = new Node(username, password, name, memberID, managementID, curr);
        } else {
            head = new Node(username, password, name, memberID, managementID, curr);
        }
    }

    // Method to delete all nodes in the list
    public void deleteAll() {
        head = null; // Set head to null to remove all nodes (list becomes empty)
    }

    // Method to print the data in the list
    public void print() {
        Node curr = head;

        // Check if the list is empty
        if (curr == null) {
            System.out.print("There is no data in the list.");
        }

        // Print the data in each node
        while (curr != null) {
            System.out.println(curr.username + "  " + curr.password + "  " + curr.name + "  " + curr.memberID + "  " + curr.managementID);
            curr = curr.next;
        }
    }

    // Method to find the size of the list
    public int findSize() {
        return findSize(head); // Call the recursive findSize method starting from the head
    }

    // Recursive method to find the size of the list
    private int findSize(Node h) {
        if (h == null) {
            return 0; // Base case: list is empty, so size is 0
        }
        // Recursively add 1 for each node in the list
        return 1 + findSize(h.next);
    }
}
