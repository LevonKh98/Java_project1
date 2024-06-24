//Levon Khachatryan
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Project3 {

    
    public static void main(String[] args) {
        // Check if the file name is provided via command line arguments
        if (args.length < 1) {
            System.out.println("Please provide the input file name as a command line argument.");
            return;
        }

        String inputFileName = args[0]; 
        try {
            // Create a scanner to read from the specified file
            Scanner scanner = new Scanner(new File(inputFileName));
            
            // Read the dimensions of the matrix
            int m = scanner.nextInt(); // Number of rows
            int n = scanner.nextInt(); // Number of columns

            // Initialize the matrix and dp (dynamic programming) table
            int[][] matrix = new int[m][n];
            int[][] dp = new int[m][n];

            // Populate the matrix with values from the input file
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = scanner.nextInt(); // Read each element
                    dp[i][j] = -1; // Initialize dp array with -1 indicating uncomputed paths
                }
            }
            scanner.close(); 

            int longestPath = 0; // Variable to store the longest path found
            // Compute the longest path from each cell in the matrix
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    longestPath = Math.max(longestPath, computeLongestFrom(i, j, matrix, dp));
                }
            }

            // Output the length of the longest path found
            System.out.println(longestPath);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + inputFileName); 
            e.printStackTrace();
        }
    }

    // compute the longest path from a given cell
    private static int computeLongestFrom(int i, int j, int[][] matrix, int[][] dp) {
        // Return the computed path length if already calculated
        if (dp[i][j] != -1) return dp[i][j];

        int m = matrix.length, n = matrix[0].length; // Dimensions of the matrix
        int max = 1; // The path length including the current cell itself

        
        // Move right and compute path if next value is smaller
        if (j + 1 < n && matrix[i][j] > matrix[i][j + 1]) {
            max = Math.max(max, 1 + computeLongestFrom(i, j + 1, matrix, dp));
        }
        // Move down and compute path if next value is smaller
        if (i + 1 < m && matrix[i][j] > matrix[i + 1][j]) {
            max = Math.max(max, 1 + computeLongestFrom(i + 1, j, matrix, dp));
        }

        dp[i][j] = max; // Store the maximum path length found from this cell
        return max; // Return the longest path length
    }
}
