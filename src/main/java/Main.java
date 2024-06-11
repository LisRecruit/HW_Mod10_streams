import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;




public class Main {
    public static void main(String[] args) throws IOException {





        File phoneNumbers = new File("PhoneNumbers.txt");
        FileInputStream fis = new FileInputStream(phoneNumbers);

        validatePhoneNumbers(phoneNumbers);
        System.out.println("++++++");
        File wordsTest = new File("WordsTest.txt");
        wordCounter(wordsTest);

        System.out.println("===============");
//        System.out.println("===============");
//
        File jsonUsers = new File("Users.txt");
        createUserObjects(jsonUsers);

    }


    public static void validatePhoneNumbers(File file){
        if (file.exists()) {
            String[] phoneNumberRegex = new String[]{"\\d{3}-\\d{3}-\\d{4}", "^[(]\\d{3}[)]\\s\\d{3}-\\d{4}$"};
            Pattern[] pattern = new Pattern[2];
            pattern[0] = Pattern.compile(phoneNumberRegex[0]);
            pattern[1] = Pattern.compile(phoneNumberRegex[1]);
            Matcher matcher;
            try (
                    FileInputStream fis = new FileInputStream(file);
                    Scanner fileScanner = new Scanner(fis);
            ) {

                while (fileScanner.hasNext()) {

                    System.out.println(fileScanner.nextLine());
                    for (int i = 0; i < 2; i++) {
                        String s=fileScanner.nextLine();
                        matcher=pattern[i].matcher(s);
                        if (matcher.matches()){
                            System.out.println(s);
                        }
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        } else {
            System.out.println("File not found");
        }

    }


    public static void wordCounter(File file) throws FileNotFoundException {
        String s="";
        //String[] words=new String[6];
        ArrayList<Words> wordList=new ArrayList<>();
        HashMap<String, Words> wordsMap = new HashMap<>();
        boolean skip=false;

        try (
                FileInputStream fis = new FileInputStream(file);
                Scanner scanner = new Scanner(fis);
        ) {
            while (scanner.hasNext()) {
                s = scanner.nextLine();
                String []words = s.split("\\s");

                for (int i = 0; i < words.length; i++) {
                    skip=false;
                    Words wordToAdd = new Words(words[i]);
                    for (int j=0;j<wordList.size();j++){
                        if(words[i].equals(wordList.get(j).getWord())){
                            wordList.get(j).addWord();
                            skip=true;
                        }
                    }
                    if (skip==true){
                        continue;
                    }
                    wordList.add(wordToAdd);
                }
            }
        } catch (IOException ex){
            ex.printStackTrace();
        }

        Collections.sort(wordList, new Comparator<Words>() {
            @Override
            public int compare(Words o1, Words o2) {
                return Integer.compare(o1.getCount(), o2.getCount());
            }
        }.reversed());

        for (Words words : wordList) {
            System.out.println(words.toString());
        }


    }





    public static void createUserObjects(File file){
        if (file.exists()) {
            ArrayList<User> userslist = new ArrayList<>();
            ArrayList<String> fileRows = new ArrayList<>();
            String s ="";
            try (
                    FileInputStream fis = new FileInputStream(file);
                    Scanner scanner = new Scanner(fis);
            ) {

                while (scanner.hasNext()) {
                    s = scanner.nextLine();
                    fileRows.add(s);
                }

                for (int i=1;i<fileRows.size();i++){
                    String[] tempStrArr= fileRows.get(i).split("\\s");
                    int ageToAdd=Integer.parseInt(tempStrArr[1]);
                    userslist.add(new User(tempStrArr[0],ageToAdd));
                }

            } catch (IOException ex) {
                ex.printStackTrace();
            }

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String jsonFormat = gson.toJson(userslist);
            //System.out.println(jsonFormat);
            try (
                    FileOutputStream fos = new FileOutputStream("Users.json"); // may add ", true" (append)
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
            )
            {

                bw.write(jsonFormat);


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


    }

}
