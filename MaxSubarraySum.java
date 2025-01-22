import java.util.Scanner;

public class MaxSubarraySum {

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

        // Find the maximum sum of the contiguous subarray
        int maxSum = findMaxSum(array);

        // Output the result
        System.out.println("The maximum sum of the contiguous subarray is: " + maxSum);

        scanner.close();
    }

    public static int findMaxSum(int[] array) {
        // Initialize variables for current sum and maximum sum
        int currentSum = 0;
        int maxSum = Integer.MIN_VALUE; // Start with the smallest possible integer value

        // Traverse through the array
        for (int i = 0; i < array.length; i++) {
            // Use conditional operator to update current sum
            currentSum = (currentSum + array[i] > array[i]) ? (currentSum + array[i]) : array[i];

            // Use conditional operator to update max sum
            maxSum = (currentSum > maxSum) ? currentSum : maxSum;
        }

        return maxSum;
    }
}
