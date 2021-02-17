package javabasics;

import java.util.ArrayList;
import java.util.Scanner;

public class NumberTable {
    /**
     * this method prints the table of given num
     *
     * @param num to print table for num
     */
    public static ArrayList<Integer> printtable(int num) {
        ArrayList<Integer>table=new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            table.add(num * i);
        }
        return table;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number");
        int num = sc.nextInt();
        ArrayList<Integer>table=new ArrayList<>();
        table=printtable(num);
        System.out.println(table);

    }
}
