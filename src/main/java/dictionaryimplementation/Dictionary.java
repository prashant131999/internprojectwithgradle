package dictionaryimplementation;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;
import java.lang.Math;
import java.util.Set;
import java.util.logging.LogManager;
import java.util.logging.Logger;


public class Dictionary {
    private static Logger logger;
    private final static Scanner scan = new Scanner(System.in);

    static {
        System.setProperty("java.util.logging.config.file",
                "/home/raramuri/Desktop/intern/InternshipAssignments/src/main/resources/logging.properties");

        logger = java.util.logging.Logger.getLogger(Dictionary.class.getName());

    }
    /**
     * This method insert the word with their meaning in dictionary
     *
     * @param dict        takes dictionary hashmap from user to insert input
     * @param word        takes input from user
     * @param description takes input from description
     */
    public static void insert(HashMap<String, String> dict, String word, String description) {
        dict.put(word, description);

    }

    /**
     * This method search the meaning of given word
     *
     * @param dict takes dictionary hashmap from user
     * @param word takes word from user
     */
    public static void search(HashMap<String, String> dict, String word) {
        if (dict.containsKey(word)) {
            String meaning = dict.get(word);
            logger.info("Meaning of searched word " + word + " " + "is" + " " + meaning + "\n.....");
        } else {
            logger.info("Word is not present in dictionary\n......");
        }
    }

    /**
     * This method returns the min length between two words given by user
     *
     * @param dictword array of dictionary word
     * @param word     is the word for which we are comparing
     * @return minlength
     */
    public static int minlength(char[] dictword, String word) {
        int minlen = 0;
        if (dictword.length > word.length()) {
            minlen = word.length();
        } else {
            minlen = dictword.length;
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
    public static ArrayList<String> autocomplete(HashMap<String, String> dict, String incompleteWord) {
        String completeWord = "No word is found in dictionary to be autocompleted";
        ArrayList<String> listOfCorrectWords = new ArrayList<>();
        boolean flag = false;
        for (String word : dict.keySet()) {

            int length = 0;
            char[] wordArray = word.toCharArray();
            length = minlength(wordArray, incompleteWord);
            for (int i = 0; i < length; i++) {
                if (wordArray[i] != incompleteWord.charAt(i)) {
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
    public static String autocorrect(HashMap<String, String> dict, String incorrectWord) {
        int diff = Integer.MAX_VALUE;
        String correct = "Not a single word matches with given words";
        for (String word : dict.keySet()) {

            int length = 0;
            char[] wordArray = word.toCharArray();
            length = minlength(wordArray, incorrectWord);

            for (int i = 0; i < length; i++) {

                if (wordArray[i] != incorrectWord.charAt(i)) {
                    if (i > 0) {
                        if (Math.abs(incorrectWord.length() - wordArray.length) < diff) {
                            correct = word;
                            diff = Math.abs(incorrectWord.length() - wordArray.length);
                            break;
                        }
                    }

                }

            }
        }
        return correct;


    }

    public static void main(String args[]) {

        HashMap<String, String> dict = new HashMap<>();

        Scanner sc = new Scanner(System.in);

        boolean flag = true;
        while (flag) {
            System.out.println("Enter 1 to insert in Dictionary");
            System.out.println("Enter 2 to search in Dictionary");
            System.out.println("Enter 3 to autocomplete ");
            System.out.println("Enter 4 to autocorrect ");
            System.out.println("Enter 5 to exit the dictionary");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("enter the word to insert in dictionary");
                    String word = sc.next();
                    sc.nextLine();
                    System.out.println("Enter the meaning of word");
                    String meaning = sc.nextLine();
                    insert(dict, word, meaning);
                    logger.info("Word and meaning is inserted in dictionary\n.......");
                    break;
                case 2:
                    System.out.println("Enter the word for which we want meaning");
                    String strword = sc.next();
                    search(dict, strword);
                    break;
                case 3:
                    System.out.println("Enter the word to be auto completed");
                    String incompleteWord = sc.next();
                    logger.info("Complete words " +
                            "  " + autocomplete(dict, incompleteWord));
                    System.out.println("\n");


                    break;
                case 4:
                    System.out.println("Enter the word to be autocorrected");
                    String incorrectWord = sc.next();
                    logger.info("Autocorrected word " + autocorrect(dict, incorrectWord));

                    break;
                case 5:
                    flag = false;
                    break;


            }
        }


    }

}
