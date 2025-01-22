import java.util.Scanner;
import java.util.Stack;

public class BracketValidator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input the string
        System.out.print("Enter a string containing only {}, [], and (): ");
        String input = scanner.nextLine();

        // Check if the string is valid
        if (isValid(input)) {
            System.out.println("The string is valid.");
        } else {
            System.out.println("The string is not valid.");
        }

        scanner.close();
    }

    // Method to check if the brackets are valid
    private static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        // Iterate through each character in the string
        for (char ch : s.toCharArray()) {
            // Push opening brackets onto the stack
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            }
            // Check closing brackets
            else if (ch == ')' || ch == '}' || ch == ']') {
                if (stack.isEmpty()) {
                    return false; // No matching opening bracket
                }
                char top = stack.pop();
                if (!isMatchingPair(top, ch)) {
                    return false; // Mismatched pair
                }
            } else {
                return false; // Invalid character
            }
        }

        // The string is valid if the stack is empty
        return stack.isEmpty();
    }

    // Helper method to check if two characters form a matching pair of brackets
    private static boolean isMatchingPair(char open, char close) {
        return (open == '(' && close == ')') ||
                (open == '{' && close == '}') ||
                (open == '[' && close == ']');
    }
}
