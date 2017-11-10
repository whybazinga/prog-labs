package main.TailorRows;

import java.util.Scanner;


public class TailorRows {
    public static final int TEN = 10;

    public double menu(double x, double eps, Scanner in) {
        double result = 0;
        int choiceInMenu = in.nextInt();

        switch (choiceInMenu) {
            case 1: {
                result = exponent(x, eps);
                break;
            }
            case 2: {
                result = logarithmShortWithMinus(x, eps);
                break;
            }
            case 3: {
                result = highFraction(x, eps);
                break;
            }

        }
        return result;
    }

    public double exponent(double x, double eps) {
        double summ = 0;
        double element = 1;
        for (int i = 1; Math.abs(element) > eps; i++) {
            summ += element;
            element *= x / i;
        }
        return summ;
    }

    public double highFraction(double x, double eps) {
        double summ = 0;
        double element = 1;
        for (int i = 1; Math.abs(element) > eps; i++) {
            summ += element;
            element *= -(i + 2) * x / i;
        }
        return summ;
    }

    public double logarithmShortWithMinus(double x, double eps) {

        double summ = 0;
        double element = x;
        for (int i = 2; Math.abs(element) > eps; i++) {
            summ -= element;
            element *= x * (i - 1) / i;
        }
        return summ;
    }

    public static void main(String[] args) {
        TailorRows programm = new TailorRows();
        Scanner in = new Scanner(System.in);
        System.out.println("Enter x");
        double x = in.nextDouble();
        System.out.println("Now enter the degree for your epsilon");
        int k = in.nextInt();
        double eps = Math.pow(TEN, -k);
        System.out.println("Your result: " + programm.menu(x, eps, in));
    }
}
