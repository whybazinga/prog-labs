package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the size of your matrix");
        int n = in.nextInt();
        int[][] matrix1 = new int[n][n];
        int[][] matrix2 = new int[n][n];
        System.out.println("Fill your 1st matrix");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix1[i][j] = in.nextInt();
            }
            System.out.println();
        }

        System.out.println("Fill your 2nd matrix");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix2[i][j] = in.nextInt();
            }
            System.out.println();
        }

        System.out.println("So here is your 1st matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(" " + matrix1[i][j]);
            }
            System.out.println();
        }

        System.out.println("So here is your 2nd matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(" " + matrix2[i][j]);
            }
            System.out.println();
        }

        int[] maxs = new int[n];
        for (int i = 0; i < n; i++) {
            maxs[i] = 0;
            for (int j = 0; j < n; j++) {
                if (matrix2[i][j] > maxs[i]) {
                    maxs[i] = matrix2[i][j];
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix1[i][j]*=maxs[i];
            }
        }

        System.out.println("So here is your result matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(" " + matrix1[i][j]);
            }
            System.out.println();
        }
    }
}
