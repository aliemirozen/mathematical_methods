//--------------------------------------------------//
//MATH226 - Numerical Methods for EE
//Fall 2023 – 2024
//Project 2
//Name-Surname: <Ali Emir Özen>
//Student ID: < 042002029>
//---------------------------------------------------//
import java.util.Scanner;
public class Main {
    static double function(int functionNumber, double x)
    {
        switch (functionNumber){
            case 1:
                return x*x*x - 2*x -5;
            case 2:
                return Math.exp(-x) - x;
            case 3:
                return x*Math.sin(x)- 1;
            case 4:
                return x*x*x - 3*x*x + 3*x - 1;
            case 5:
                return x*x - 2*x + 1;
            case 6:
                return 0.5 - x*Math.exp((-x)*(x));
            case 7:
                return x*x + 4*Math.cos(x);
            default:
                throw new Error("Please try again and choose a number between 1 and 7");
        }
    }

    static void bisection(int functionNumber, double tol) {
        double a = 3;
        double b = 5;
        int n = 1;
        double iteration = 0;
        while (b - a >= tol)
        {
            iteration = (a+b)/2;
            if (function(functionNumber, iteration) * function(functionNumber, a) < 0)
                b = iteration;
            else
                a = iteration;
            System.out.println((n) + ". iteration = " + iteration);
            System.out.println("\nSteps = " + (n));
            n++;
        }
        System.out.printf("\nRoot of the Function = " + iteration);
        System.out.println("\nSteps = " + (n-1));
        System.out.println("Difference between a and b = " + (b - a));
        System.out.println("Is this bigger than tolerance = " + (Math.abs(b-a)>=tol));
    }

    static void secantMethod(int functionNumber, double x0, double x1) {
        double x2 = 0, x3 = 0.1, a;
        int n = 0;
        if ( function(functionNumber, x0) * function(functionNumber, x1) < 0){
            while (x3 != x2) {
                x2 = x1 - (function(functionNumber, x1)*((x1-x0)/(function(functionNumber, x1)-function(functionNumber, x0))));
                a = function(functionNumber, x0) * function(functionNumber, x2);
                x0 = x1;
                x1 = x2;
                if (a == 0) {
                    System.out.println("\nCan not find root for this function. Try another function please!!!");
                    return;
                }
                x3 = x1 - (function(functionNumber, x1)*((x1-x0)/(function(functionNumber, x1)-function(functionNumber, x0))));
                if(n==0)
                    System.out.println((n+1) + ". Step = " + x2);
                n++;
                System.out.println((n+1) + ". Step = " + x3);
            }
            System.out.println("\nRoot of the Function = " + x2);
            System.out.println("Steps = " + n);
        }else
            System.out.println("\nCan not find root for this function. Try another function please!!!");
    }

    static void goldenSectionSearch(int functionNumber, double a, double b, double tol){
        double gr, d, c=1, x1 = 0, x2 = 1, f1 = 0, f2 = 0;
        int n = 0;
        gr = (Math.sqrt(5)-1)/2; //golden ratio
        while (Math.abs(c) > tol){
            d = gr * (b-a);
            x1 = a + d;
            x2 = b - d;
            f1 = function(functionNumber, x1);
            f2 = function(functionNumber, x2);
            if (f1>f2)
                b = x1;
            else
                a = x2;
            c = x2 - x1;
            n++;
            System.out.println("d value is : " + d);
            System.out.println(n + ". step is   ------>   x1 = " + x1 + "        x2 = " + x2 + "\n");
        }
        System.out.println("x1 = " +x1 +"\nx2 = " + x2 + "\nDifference between x1 and x2 = " + Math.abs(c) + "\nSteps = " + n);
    }

    public static void main(String[] args) {
        Scanner scanner1 = new Scanner(System.in);
        double tol_bisection = 0.0001;
        double tol_golden = 0.01;
        Scanner scanner = new Scanner(System.in);
        System.out.print("1 -> x*x*x - 2*x -5 \n2 -> Math.exp(-x) - x\n3 -> x*Math.sin(x)- 1\n4 -> x*x*x - 3*x*x + 3*x - 1\n5 -> x*x - 2*x + 1\n6 -> 0.5 - x*Math.exp((-x)*(x))\n7 -> x*x + 4*Math.cos(x)\nEnter the function number (1-7): ");
        int functionNumber = scanner.nextInt();
        System.out.println("\n------------------------------------------------------------------\n");
        bisection(functionNumber, tol_bisection);
        System.out.println("\n------------------------------------------------------------------");
        secantMethod(functionNumber,0,2);
        System.out.println("\n------------------------------------------------------------------");
        double min_limit = 0;
        double max_limit = 10;
        goldenSectionSearch(functionNumber,min_limit,max_limit, tol_golden);
    }
}
