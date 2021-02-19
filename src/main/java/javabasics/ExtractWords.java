package javabasics;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * extractwords method to extract word and print them
 */
public class ExtractWords {
    /** this method return the extracted words when space encounterss
     * @param scentence this param takes scentence as a input
     *
     */
    public static ArrayList<String> extractWords(String scentence) {
        char ch;
        String s = "";
        ArrayList<String> words = new ArrayList<String>();
        for (int i = 0; i < scentence.length(); i++) {
            ch = scentence.charAt(i);
            if(ch!=' ') {
                s = s + ch;
            }
            if (ch == ' ' || i==scentence.length()-1) {
                words.add(s);
                s="";
            }

        }
        return words;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your input: ");
        String scentence = sc.nextLine();
        ArrayList<String>words=new ArrayList<>();
        words=extractWords(scentence);
        System.out.println(words);
    }
}

