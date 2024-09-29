/* assignment 1 Design a base class STACK and Handle runtime anomalies like Overflow when the stack is
full and underflow when the stack is empty. Display error codes and messages by using
appropriate try and catch block to handle the exceptions thrown. */



import java.util.Scanner;

// Custom exception for Stack Overflow
class StackOverflowException extends Exception {
    public StackOverflowException(String message) {
        super(message);
    }
}

// Custom exception for Stack Underflow
class StackUnderflowException extends Exception {
    public StackUnderflowException(String message) {
        super(message);
    }
}

// Base class STACK
class Stack {
    private int[] stack; // Array to hold the stack elements
    private int top;          // Index of the top element
    private int maxSize;      // Maximum capacity of the stack

    // Constructor to initialize the stack with a specified size
    public Stack(int size) {
        maxSize = size;
        stack = new int[maxSize];
        top = -1; // Stack is initially empty, so top is set to -1
    }

    // Method to push an element onto the stack
    public void push(int value) throws StackOverflowException {
        if (top >= maxSize - 1) { // Stack Overflow condition
            throw new StackOverflowException("Stack Overflow: Cannot push " + value + " as the stack is full.");
        }
        stack[++top] = value; // Increment top and insert value
        System.out.println("Pushed: " + value);
    }

    // Method to pop an element from the stack
    public int pop() throws StackUnderflowException {
        if (top < 0) { // Stack Underflow condition
            throw new StackUnderflowException("Stack Underflow: Cannot pop as the stack is empty.");
        }
        int poppedValue = stack[top--]; // Return the top value and decrement top
        System.out.println("Popped: " + poppedValue);
        return poppedValue;
    }

    // Method to display the stack elements
    public void displayStack() {
        if (top == -1) {
            System.out.println("Stack is empty.");
            return;
        }
        System.out.print("Stack elements: ");
        for (int i = 0; i <= top; i++) {
            System.out.print(stack[i] + " ");
        }
        System.out.println();
    }
}

public class StackInput {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the size of the stack: ");
        int size = scanner.nextInt();

        Stack stack = new Stack(size); // Create a stack of the given size

        boolean exit = false;

        while (!exit) {
            System.out.println("\nChoose an operation:");
            System.out.println("1. Push");
            System.out.println("2. Pop");
            System.out.println("3. Display stack");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter a value to push: ");
                    int value = scanner.nextInt();
                    try {
                        stack.push(value);
                    } catch (StackOverflowException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        stack.pop();
                    } catch (StackUnderflowException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 3:
                    stack.displayStack();
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}
