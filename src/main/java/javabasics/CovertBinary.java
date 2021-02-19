package javabasics;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * this class contain method convertdectobin which converts decimal to binary
 */
public class CovertBinary {
    /**
     * this method print the binary of given decimal number
     *
     * @param num to convert the given num to binary
     *
     */
    public static ArrayList<Integer> convertdectobin(int num) {
        //binarynum stores the binary number
        ArrayList<Integer>binarynum=new ArrayList<Integer>();

        while (num > 0) {
            binarynum.add(num%2);
            num = num / 2;

        }
        return binarynum;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number ");
        int num = sc.nextInt();
        ArrayList<Integer>binarynum=new ArrayList<Integer>();
        binarynum=convertdectobin(num);
        System.out.println(binarynum);

    }
}
