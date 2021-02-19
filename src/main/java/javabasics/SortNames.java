package javabasics;

import java.util.ArrayList;
import java.util.Scanner;

public class SortNames {
    /**
     *
     * @param names this parameter takes all the input names
     * @param num it tell the number of names
     *            this method return the names in sorted (ascending) order
     */
    public static ArrayList<String> sortnames(String names[], int num)
    {
        ArrayList<String>sorted=new ArrayList<>();
        for(int i=0;i<num;i++)
        {
            for(int j=i+1;j<num;j++)
            {
                //compareto used to compare two strings if it is positive that means second is greater then swap
                if(names[i].compareTo(names[j])>0)
                {
                    String temp=names[i];
                    names[i]=names[j];
                    names[j]=temp;
                }
            }
        }
        for(int i=0;i<num;i++)
        {
            sorted.add(names[i]);
        }
        return sorted;


    }
    public static void main(String args[]) {
        ArrayList<String>sorted=new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the value of num");
        int num=sc.nextInt();
        String names[]=new String[num];
        System.out.println("Enter the names");
        for(int i=0;i<num;i++)
        {
            names[i]=sc.next();
        }
        System.out.println("Sorted order of names in ascending form");
        sorted=sortnames(names,num);
        System.out.println(sorted);

    }
}