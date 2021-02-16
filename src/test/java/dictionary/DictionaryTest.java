package dictionary;


import java.util.ArrayList;
import java.util.Collections;

public class DictionaryTest {
    DictionaryTest dictionarytest;

    static Dictionary dict;

    static {
        dict.insert("prashant", "human");
        dict.insert("prabhat", "tution");
    }

    public static boolean testInsert() {
         dict.insert("pra4","human");
        if (dict.getDict().containsKey("prashant")) {
            return true;
        }
        if (!(dict.getDict().containsKey("sahrawat"))) {
            return true;
        }
        if(dict.getDict().containsKey("pra4"))
        {
            return false;
        }

        return false;


    }

    public static boolean testSearch() {

        String s = dict.search("prashant");
        if (s == "human") {
            return true;
        }
        return false;

    }

    public static boolean testAutoComplete() {
        ArrayList<String> list = new ArrayList<>();
        ArrayList<String> dictionaryList = new ArrayList<>();
        list.add("prashant");
        dictionaryList = dict.autocomplete("pra");
        list.add("prashant");
        list.add("prabhat");
        Collections.sort(list);
        Collections.sort(dictionaryList);

        for (int i = 0; i < dictionaryList.size(); i++) {
            if (list.get(i) != dictionaryList.get(i)) {
                return false;
            }
        }
        return true;
    }

    public static boolean testAutoCorrect() {
        String correct = "pradthantys";
        String auto = dict.autocorrect("prthant");
        if (correct == auto) {
            return true;
        }
        return false;
    }

    public static boolean testminlength() {
        int ans = 4;
        int output = dict.minlength("prash", "prabhat");
        if (output == ans) {
            return true;
        }
        return false;
    }

    public static void main(String args[]) {
        dict = new Dictionary();

        if (testInsert()) {
            System.out.println("insert test case works\n");
        } else {
            System.out.println("insert test case fails\n");
        }
        if (testSearch()) {
            System.out.println("Search test case works\n");
        } else {
            System.out.println("Serach test case fails\n");
        }
        if (testAutoComplete()) {
            System.out.println("Auto complete test case passes\n");
        } else {
            System.out.println("Auto complete test case fails\n");
        }
        if (testAutoCorrect()) {
            System.out.println("Auto correct test case passes\n");
        } else {
            System.out.println("Auto correct test case fails\n");
        }
        if (testminlength()) {
            System.out.println("min length test case works\n");
        } else {
            System.out.println("min length test case fails\n");
        }
    }

}

