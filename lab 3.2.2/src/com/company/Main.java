package com.company;
import java.util.Scanner;

public class Main {

    public static final int MAX_INT = 2147483647;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the size of your matrix");
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] matrix = new int[n][m];
        System.out.println("Fill your matrix");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = in.nextInt();
            }
            System.out.println();
        }

        System.out.println("So here is your matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(" " + matrix[i][j]);
            }
            System.out.println();
        }

        int[] mins = new int[n];
        for (int i = 0; i < n; i++) {
            int min = MAX_INT;
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] <= min) {
                    min = matrix[i][j];
                    mins[i] = j;
                }
            }
        }
        boolean resultFlag = true;
        for (int i = 0; i < n - 1; i++) {
            if (mins[i] > mins[i + 1]) {
                resultFlag = false;
            }
        }
        System.out.println(resultFlag ? "The statement is right" : "The statement is wrong");
    }
}
