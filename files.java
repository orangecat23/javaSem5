//assignment 2 Implement an application using Files in Java which calculates student percentage.
Store the student record in a file with fieldsrollno, name, address and marks of 3 subjects.
import java.io.*;
import java.util.*;

// Step 1: Define the Student Class
class Student implements Serializable {
    private int rollNo;
    private String name;
    private String address;
    private int marks1, marks2, marks3;

    // Constructor
    public Student(int rollNo, String name, String address, int marks1, int marks2, int marks3) {
        this.rollNo = rollNo;
        this.name = name;
        this.address = address;
        this.marks1 = marks1;
        this.marks2 = marks2;
        this.marks3 = marks3;
    }

    // Method to calculate the percentage
    public double calculatePercentage() {
        int totalMarks = marks1 + marks2 + marks3;
        return (totalMarks / 3.0);
    }

    // Override toString to display student details
    @Override
    public String toString() {
        return "Roll No: " + rollNo + "\n" +
               "Name: " + name + "\n" +
               "Address: " + address + "\n" +
               "Marks1: " + marks1 + ", Marks2: " + marks2 + ", Marks3: " + marks3 + "\n" +
               "Percentage: " + calculatePercentage() + "%\n";
    }
}

public class StudentRecordsApp {
    private static final String FILE_NAME = "student_records.dat";

    // Step 2: Write Student Records to a File
    public static void writeStudentsToFile(List<Student> students) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
        oos.writeObject(students);
        oos.close();
        System.out.println("Student records have been successfully written to the file.");
    }

    // Step 3: Read Student Records from the File and Calculate Percentage
    public static List<Student> readStudentsFromFile() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME));
        List<Student> students = (List<Student>) ois.readObject();
        ois.close();
        return students;
    }

    // Step 4: Display Student Records with Calculated Percentage
    public static void displayStudentRecords(List<Student> students) {
        if (students.isEmpty()) {
            System.out.println("No student records found.");
        } else {
            System.out.println("Student Records: ");
            for (Student student : students) {
                System.out.println(student); // Uses the overridden toString method in Student class
            }
        }
    }

    // Main Method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Student> students = new ArrayList<>();

        // Take input from the user
        System.out.println("Enter the number of students:");
        int n = sc.nextInt();
        sc.nextLine(); // Consume the leftover newline

        // Input student data
        for (int i = 0; i < n; i++) {
            System.out.println("Enter details for student " + (i + 1) + ":");
            
            System.out.println("Enter Roll No:");
            int rollNo = sc.nextInt();
            sc.nextLine(); // Consume newline
            
            System.out.println("Enter Name:");
            String name = sc.nextLine();
            
            System.out.println("Enter Address:");
            String address = sc.nextLine();
            
            System.out.println("Enter Marks for 3 subjects:");
            int marks1 = sc.nextInt();
            int marks2 = sc.nextInt();
            int marks3 = sc.nextInt();
            sc.nextLine(); // Consume newline

            // Create a Student object and add it to the list
            students.add(new Student(rollNo, name, address, marks1, marks2, marks3));
        }

        // Step 2: Write data to a file
        try {
            writeStudentsToFile(students);
        } catch (IOException e) {
            System.out.println("Error writing student records to file: " + e.getMessage());
        }

        // Step 3: Read data from the file
        List<Student> readStudents = new ArrayList<>();
        try {
            readStudents = readStudentsFromFile();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading student records from file: " + e.getMessage());
        }

        // Step 4: Display data with percentage
        displayStudentRecords(readStudents);
        
        sc.close();
    }
}
/*What is happening here?
ObjectOutputStream:

ObjectOutputStream is a class in Java that allows you to write Java objects to an output stream, such as a file. The object must be serializable, meaning it implements the Serializable interface, which marks the object as being able to be written to or read from a byte stream.
FileOutputStream(FILE_NAME):

FileOutputStream is a stream used for writing raw bytes to a file. The FILE_NAME is the name or path of the file where the data will be saved. It opens a file for writing, creating the file if it does not exist or overwriting the existing file.
new FileOutputStream(FILE_NAME) creates the file output stream, which is passed to the ObjectOutputStream to handle the serialization of objects into the file.
oos.writeObject(students):

This line writes the students object (in this case, a List<Student> or another data structure like a HashMap<Student>) to the file.
Serialization is the process where the object's state is converted into a byte stream, so it can be saved to a file or transferred over a network. The students object is serialized and written into the file defined by FileOutputStream.
oos.close():

After the object is written to the file, the output stream needs to be closed. This ensures that all resources associated with the stream are properly released, and any data still buffered is flushed to the file. Closing the stream is an important part of good resource management.
Step-by-step Explanation:
Step 1: Create a file output stream:
new FileOutputStream(FILE_NAME) opens the file where data will be written.
Step 2: Create an object output stream:
new ObjectOutputStream(...) wraps the file output stream so that it can handle objects (like your list of students).
Step 3: Serialize and write the object:
oos.writeObject(students) converts the students list into a series of bytes and writes it to the file.
Step 4: Close the output stream:
oos.close() ensures that the file is properly saved and all resources associated with the stream are released.
Why is this necessary?
When saving complex objects like a list of student records to a file, we cannot simply write the objects directly. Instead, we serialize them (convert them into a format that can be stored or transferred) using the ObjectOutputStream. By writing serialized objects to a file, we can later retrieve and reconstruct them from the file using ObjectInputStream.

Example:
Here, the students object (a collection of student records) is being saved to a file named by FILE_NAME. This serialized file can later be read back into the program using an ObjectInputStream to restore the students object with all its data intact.*/
