/*assignment 1 part 2 Accept students' name, id and marks. Display the highest and the lowest score using Hashmap */
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StudentScores {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashMap<String, Integer> students = new HashMap<>();
        
        // Accept input from the user
        System.out.println("Enter the number of students:");
        int n = sc.nextInt();
        sc.nextLine(); // Consume the newline character (\n) when we press enter after integer input

        for (int i = 0; i < n; i++) {
            System.out.println("Enter student name:");
            String name = sc.nextLine();
            System.out.println("Enter student ID:");
            String id = sc.nextLine();
            System.out.println("Enter student marks:");
            int marks = sc.nextInt();
            sc.nextLine(); // Consume the newline character
            
            // Store the student's marks using their name as the key
            students.put(name, marks);
        }

        // Initialize variables to find highest and lowest scores
        int highestScore = Integer.MIN_VALUE;
        int lowestScore = Integer.MAX_VALUE;
        String highestScorer = "";
        String lowestScorer = "";

        // Iterate through the HashMap to find highest and lowest scores
        for (Map.Entry<String, Integer> entry : students.entrySet()) {
            String studentName = entry.getKey();
            int marks = entry.getValue();

            if (marks > highestScore) {
                highestScore = marks;
                highestScorer = studentName;
            }
            if (marks < lowestScore) {
                lowestScore = marks;
                lowestScorer = studentName;
            }
        }

        // Display the results
        System.out.println("Highest Score: " + highestScore + " by " + highestScorer);
        System.out.println("Lowest Score: " + lowestScore + " by " + lowestScorer);

        sc.close();
    }
}
