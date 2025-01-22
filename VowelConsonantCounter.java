import java.util.Scanner;

public class VowelConsonantCounter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt the user for input
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

        // Convert input to lowercase to simplify checks
        String sanitizedInput = input.toLowerCase();

        int vowels = 0, consonants = 0;

        // Iterate over each character in the string
        for (int i = 0; i < sanitizedInput.length(); i++) {
            char ch = sanitizedInput.charAt(i);

            // Check if the character is a letter
            if (Character.isLetter(ch)) {
                switch (ch) {
                    case 'a':
                    case 'e':
                    case 'i':
                    case 'o':
                    case 'u':
                        vowels++;
                        break;
                    default:
                        consonants++;
                }
            }
        }

        // Print the counts
        System.out.println("Number of vowels: " + vowels);
        System.out.println("Number of consonants: " + consonants);

        scanner.close();
    }
}
