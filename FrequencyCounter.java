import java.util.Scanner;

public class FrequencyCounter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input the size of the array
        System.out.print("Enter the size of the array: ");
        int size = scanner.nextInt();

        // Create and populate the array
        int[] array = new int[size];
        System.out.println("Enter elements of the array:");
        for (int i = 0; i < size; i++) {
            System.out.print("Element " + (i + 1) + ": ");
            array[i] = scanner.nextInt();
        }

        // Array to keep track of processed elements
        boolean[] isCounted = new boolean[size];

        System.out.println("\nFrequency of elements:");

        // Loop through the array to calculate frequencies
        for (int i = 0; i < size; i++) {
            if (isCounted[i])
                continue; // Skip already counted elements

            int count = 1; // Start count at 1 for the current element

            // Check for duplicates
            for (int j = i + 1; j < size; j++) {
                if (array[i] == array[j]) {
                    count++;
                    isCounted[j] = true; // Mark duplicate as counted
                }
            }

            // Print the frequency of the current element
            System.out.println(array[i] + " occurs " + count + " time(s).");
        }

        scanner.close();
    }
}
