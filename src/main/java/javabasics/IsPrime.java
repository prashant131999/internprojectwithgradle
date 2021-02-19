package javabasics;

import java.util.Scanner;

//check method to check number for prime
public class IsPrime {
    /**
     * this method prints whether num is prime or not
     *
     * @param num is to check this number for prime
     */
    public static boolean check(int num) {
        int flag = 0;
        for (int i = 2; i * i <= num / 2; i++) {
            if (num % i == 0) {

                flag = 1;
                break;
            }
        }
        if (flag == 0) {
            return true;
        }
        return false;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number");
        int num = sc.nextInt();
        if(check(num))
        {
            System.out.println("It is a prime number");
        }
        else
        {
            System.out.println("Not a prime number");
        }

    }
}
