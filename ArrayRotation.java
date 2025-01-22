import java.util.Scanner;

public class ArrayRotation {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input array size
        System.out.print("Enter the number of elements in the array: ");
        int n = scanner.nextInt();

        // Input array elements
        int[] array = new int[n];
        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }

        // Input rotation direction and number of rotations
        System.out.print("Enter the number of rotations: ");
        int rotations = scanner.nextInt();

        System.out.print("Enter the direction of rotation (1 for right, -1 for left): ");
        int direction = scanner.nextInt();

        // Perform the rotation
        rotateArray(array, rotations, direction);

        // Output the rotated array
        System.out.print("Rotated array: ");
        for (int num : array) {
            System.out.print(num + " ");
        }

        scanner.close();
    }

    public static void rotateArray(int[] array, int rotations, int direction) {
        int n = array.length;

        // Handle boundary conditions if rotations are greater than array length
        rotations = rotations % n;

        // If direction is 1 (right rotation), we rotate the array to the right
        if (direction == 1) {
            // Right rotate the array 'rotations' times
            for (int i = 0; i < rotations; i++) {
                int last = array[n - 1]; // Save the last element
                // Shift all elements to the right
                for (int j = n - 1; j > 0; j--) {
                    array[j] = array[j - 1];
                }
                array[0] = last; // Place the last element at the first position
            }
        }
        // If direction is -1 (left rotation), we rotate the array to the left
        else if (direction == -1) {
            // Left rotate the array 'rotations' times
            for (int i = 0; i < rotations; i++) {
                int first = array[0]; // Save the first element
                // Shift all elements to the left
                for (int j = 0; j < n - 1; j++) {
                    array[j] = array[j + 1];
                }
                array[n - 1] = first; // Place the first element at the last position
            }
        }
        // Handle invalid direction input
        else {
            System.out.println("Invalid direction input. Please enter 1 for right or -1 for left.");
        }
    }
}
