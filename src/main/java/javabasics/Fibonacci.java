package javabasics;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * this class contain fibonacci method to print fibonacci
 */
public class Fibonacci {


    /**
     * this method  prints finobaci series starting from 1
     *
     * @param num is the value upto which we print fibbonaci series
     */

    public static ArrayList<Integer> fibonacci(int num) {
        ArrayList<Integer>fiblist=new ArrayList<>();
        //a is first number
        int a = 1;
        fiblist.add(a);
        if(num==1)
        {
            return fiblist;
        }

        //b is second number
        int b = 1;

        fiblist.add(b);
        if(num==2)
        {
            return fiblist;
        }
        for (int i = 2; i < num; i++) {
            //adding the previous to number
            int c = a + b;
            fiblist.add(c);
            //updates the previous two values
            a = b;
            b = c;
        }
        return fiblist;

    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number till we want to print fibonacci");
        int num = sc.nextInt();
        ArrayList<Integer>fiblist=new ArrayList<>();
        fiblist=fibonacci(num);
        System.out.println(fiblist);
    }
}
