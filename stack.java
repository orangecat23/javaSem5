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
        Stack stack1 = new Stack(3); // Create a stack of size 3
        
        try {
            // Perform some stack operations
            stack1.push(10);
            stack1.push(20);
            stack1.push(30);

            stack1.displayStack(); // Display stack elements

            // This push will cause a StackOverflowException
            stack1.push(40);

        } catch (StackOverflowException e) {
            System.out.println("Error");
            System.out.println(e.getMessage());
        }

        try {
            stack1.pop();
            stack1.pop();
            stack1.pop();

            // This pop will cause a StackUnderflowException
            stack1.pop();

        } catch (StackUnderflowException e) {
            System.out.println("Error");
            System.out.println(e.getMessage());
        }
    }
}

