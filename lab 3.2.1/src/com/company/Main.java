package com.company;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.println("Enter the size of your matrix");
        int n = in.nextInt();
        int[][] matrix = new int[n][n];
        System.out.println("Fill your matrix");
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                matrix[j][i] = in.nextInt();
            }
            System.out.println();
        }

        System.out.println("So here is your matrix:");
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                System.out.print(" "+matrix[i][j]);
            }
            System.out.println();
        }

        System.out.print("Following columns are monotonous:");
        for(int i = 0; i < n; i++){
	        boolean monotonicIncrease = true;
            boolean monotonicDecrease = true;
	        for(int j = 0; j < n-1; j++){
	            if(matrix[j][i] > matrix[j+1][i]){
                    monotonicIncrease = false;
                }
                if(matrix[j][i] < matrix[j+1][i]){
                    monotonicDecrease = false;
                }
            }
            if(monotonicIncrease | monotonicDecrease){
                System.out.print(" "+(i+1));
            }
        }

    }
}
