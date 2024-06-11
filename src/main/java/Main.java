import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class Main {
    public static void main(String[] args) {

        File phoneNumbers = new File("PhoneNumbers.txt");
        try {
            FileInputStream fis = new FileInputStream(phoneNumbers);
        } catch (FileNotFoundException e) {
            System.out.println("File not found\\nPlease, check the path to the file");
            ;
        }
        Tools.validatePhoneNumbers(phoneNumbers);
        System.out.println("++++++");
        File wordsTest = new File("WordsTest.txt");
        try {
            Tools.wordCounter(wordsTest);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println("===============");

        File jsonUsers = new File("Users.txt");
        Tools.createUserObjects(jsonUsers);

    }






}
