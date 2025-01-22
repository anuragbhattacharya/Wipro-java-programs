import java.util.Scanner;

public class ArrayElementComparator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input the size of the arrays
        System.out.print("Enter the size of the arrays: ");
        int size = scanner.nextInt();

        // Create arrays
        int[] array1 = new int[size];
        int[] array2 = new int[size];

        // Input elements for the first array
        System.out.println("Enter elements for the first array:");
        for (int i = 0; i < size; i++) {
            System.out.print("Element " + (i + 1) + ": ");
            array1[i] = scanner.nextInt();
        }

        // Input elements for the second array
        System.out.println("Enter elements for the second array:");
        for (int i = 0; i < size; i++) {
            System.out.print("Element " + (i + 1) + ": ");
            array2[i] = scanner.nextInt();
        }

        // Compare elements of the arrays
        System.out.println("\nComparison of array elements:");
        for (int i = 0; i < size; i++) {
            System.out.print("Element " + (i + 1) + ": ");
            System.out.println(
                    (array1[i] == array2[i]) ? "Equal" : (array1[i] > array2[i]) ? "Greater" : "Lesser");
        }

        scanner.close();
    }
}
