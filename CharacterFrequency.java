import java.util.HashMap;
import java.util.Map;

public class CharacterFrequency {
    
    public static void main(String[] args) {
        String input = "Hello, World! This is a Java Program.";
        
        // Calling the method to count the frequency of each character
        countCharacterFrequency(input);
    }

    public static void countCharacterFrequency(String str) {
        // Converting the string to lower case to ignore case sensitivity
        str = str.toLowerCase();
        
        // Creating a map to store character frequencies
        Map<Character, Integer> charCountMap = new HashMap<>();
        
        // Iterating through each character in the string
        for (int i = 0; i < str.length(); i++) {
            char currentChar = str.charAt(i);
            
            // Check if the current character is a letter (ignoring special characters)
            if (Character.isLetter(currentChar)) {
                // If the character is already in the map, increment its count
                if (charCountMap.containsKey(currentChar)) {
                    charCountMap.put(currentChar, charCountMap.get(currentChar) + 1);
                } else {
                    // If it's the first occurrence, add it to the map with count 1
                    charCountMap.put(currentChar, 1);
                }
            }
        }
        
        // Printing the character frequencies
        System.out.println("Character frequencies in the given string (ignoring case and special characters):");
        for (Map.Entry<Character, Integer> entry : charCountMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}

