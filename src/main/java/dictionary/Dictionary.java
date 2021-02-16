package dictionary;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;
import java.lang.Math;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Dictionary {
    private static HashMap<String, String> dict = new HashMap<String, String>();
    private static Logger logger;
    private final static Scanner scan = new Scanner(System.in);

    static {
        System.setProperty("java.util.logging.config.file",
                "/home/raramuri/IdeaProjects/testinggradle/src/main/resources/logging.properties");

        logger = java.util.logging.Logger.getLogger(Dictionary.class.getName());

    }

    public static HashMap<String, String> getDict() {
        return dict;
    }

    /**
     * This method insert the word with their meaning in dictionary
     *
     * @param dict        takes dictionary hashmap from user to insert input
     * @param word        takes input from user
     * @param description takes input from description
     */
    public static void insert(String word, String description) {
        boolean flag=true;
        for(int i=0;i<word.length();i++)
        {
            char ch=word.charAt(i);
            if(Character.isDigit(ch))
            {
                flag=false;
                break;
            }
        }
        if(flag) {
            if (word != null) {
                dict.put(word, description);
            }
        }


    }

    /**
     * This method search the meaning of given word
     *
     * @param dict takes dictionary hashmap from user
     * @param word takes word from user
     */
    public static String search(String word) {
        String meaning = "Not present in dictionary";
        if (dict.containsKey(word)) {
            meaning = dict.get(word);
            return meaning;
        }
        return meaning;

    }


    /**
     * This method returns the min length between two words given by user
     *
     * @param dictword array of dictionary word
     * @param word     is the word for which we are comparing
     * @return minlength
     */
    public static int minlength(String dictword, String word) {
        int minlen = 0;
        if (dictword.length() > word.length()) {
            minlen = word.length();
        } else {
            minlen = dictword.length();
        }
        return minlen;
    }

    /**
     * This method returns all the list of matching complete words
     * from the input of user
     *
     * @param dict           takes dictionary hashmap from user
     * @param incompleteWord takes incomplete word from user
     * @return array lsit of all matching words
     */
    public static ArrayList<String> autocomplete(String incompleteWord) {
        String completeWord = "No word is found in dictionary to be autocompleted";
        ArrayList<String> listOfCorrectWords = new ArrayList<>();
        boolean flag = false;
        for (String word : dict.keySet()) {

            int length = 0;
            char[] wordArray = word.toCharArray();
            length = minlength(word, incompleteWord);
            for (int i = 0; i < length; i++) {
                if (word.charAt(i) != incompleteWord.charAt(i)) {
                    if (flag) {
                        break;
                    }
                    listOfCorrectWords.add(completeWord);
                    flag = true;
                    break;
                } else if (i == length - 1) {
                    listOfCorrectWords.add(word);
                }
            }
        }

        return listOfCorrectWords;

    }

    /**
     * This  method corrects the word given by user by searching in the dict hashmap
     *
     * @param dict          takes dictionary hashmap from user
     * @param incorrectWord takes input from user
     * @return the correct word
     */
    public static String autocorrect(String incorrectWord) {
        int diff = Integer.MAX_VALUE;
        String correct = "Not a single word matches with given words";
        for (String word : dict.keySet()) {

            int length = 0;

            length = minlength(word, incorrectWord);

            for (int i = 0; i < length; i++) {

                if (word.charAt(i) != incorrectWord.charAt(i)) {
                    if (i > 0) {
                        if (Math.abs(incorrectWord.length() - word.length()) < diff) {
                            correct = word;
                            diff = Math.abs(incorrectWord.length() - word.length());
                            break;
                        }
                    }

                }

            }
        }
        return correct;


    }

    public static void main(String args[]) {


        Scanner sc = new Scanner(System.in);

        boolean flag = true;
        while (flag) {
            logger.info("Enter 1 to insert in Dictionary");
            logger.info("Enter 2 to search in Dictionary");
            logger.info("Enter 3 to autocomplete ");
            logger.info("Enter 4 to autocorrect ");
            logger.info("Enter 5 to exit the dictionary");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("enter the word to insert in dictionary");
                    String word = sc.next();
                    sc.nextLine();
                    System.out.println("Enter the meaning of word");
                    String meaning = sc.nextLine();
                    insert(word, meaning);
                    logger.info("Word and meaning is inserted in dictionary\n.......");
                    break;
                case 2:
                    System.out.println("Enter the word for which we want meaning");
                    String strword = sc.next();
                    String s = search(strword);
                    System.out.println("Meaning of given word is " + s);
                    break;
                case 3:
                    System.out.println("Enter the word to be auto completed");
                    String incompleteWord = sc.next();
                    logger.info("Complete words " +
                            "  " + autocomplete(incompleteWord));
                    System.out.println("\n");


                    break;
                case 4:
                    System.out.println("Enter the word to be autocorrected");
                    String incorrectWord = sc.next();
                    logger.info("Autocorrected word " + autocorrect(incorrectWord));

                    break;
                case 5:
                    flag = false;
                    break;


            }
        }


    }

}

