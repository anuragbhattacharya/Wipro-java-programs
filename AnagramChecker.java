import java.util.Scanner;

public class AnagramChecker {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input two strings
        System.out.print("Enter the first string: ");
        String str1 = scanner.nextLine();

        System.out.print("Enter the second string: ");
        String str2 = scanner.nextLine();

        // Check if the strings are anagrams
        if (areAnagrams(str1, str2)) {
            System.out.println("The strings are anagrams.");
        } else {
            System.out.println("The strings are not anagrams.");
        }

        scanner.close();
    }

    public static boolean areAnagrams(String str1, String str2) {
        // If the lengths are different, they cannot be anagrams
        if (str1.length() != str2.length()) {
            return false;
        }

        // Convert strings to lower case to ignore case sensitivity
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();

        // Create arrays to count character occurrences
        int[] charCount1 = new int[26]; // For storing counts of characters in str1
        int[] charCount2 = new int[26]; // For storing counts of characters in str2

        // Count occurrences of each character in both strings
        for (int i = 0; i < str1.length(); i++) {
            // Ensure the characters are alphabetic
            if (Character.isLetter(str1.charAt(i))) {
                charCount1[str1.charAt(i) - 'a']++;
            }
            if (Character.isLetter(str2.charAt(i))) {
                charCount2[str2.charAt(i) - 'a']++;
            }
        }

        // Compare the character counts
        for (int i = 0; i < 26; i++) {
            if (charCount1[i] != charCount2[i]) {
                return false; // If any character count doesn't match, they are not anagrams
            }
        }

        return true; // All character counts match, so they are anagrams
    }
}
