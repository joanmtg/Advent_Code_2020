package adventcode.day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day2 {

    public static ArrayList<Password> passwords = new ArrayList<>();

    public static void readFile(String filename){
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            while (line != null) {

                String[] info = line.split(" ");

                String[] rules = info[0].split("-");
                int min = Integer.parseInt(rules[0]);
                int max = Integer.parseInt(rules[1]);

                char letter = info[1].charAt(0);

                String password = info[2];

                passwords.add(new Password(min, max, letter, password));

                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int validPasswords(){
        passwords = new ArrayList<>();

        readFile("/home/joan/dev/java/AdventCode/inputs/input_2_1.txt");

        int valid = 0;

        for (Password password: passwords){
            int ocurrences = 0;

            for(int i=0; i<password.getContent().length(); i++){
                if(password.getContent().charAt(i) == password.getLetter()){
                    ocurrences++;
                }
            }

            if(ocurrences >= password.getLeft() && ocurrences <= password.getRight()){
                valid++;
            }
        }

        return valid;
    }

    public static int actualValidPasswords(){
        passwords = new ArrayList<>();

        readFile("/home/joan/dev/java/AdventCode/inputs/input_2_2.txt");

        System.out.println(passwords.size());

        int valid = 0;

        for (Password password: passwords){
            int ocurrences = 0;

            if(password.getContent().charAt(password.getLeft()-1) == password.getLetter()){
                ocurrences++;
            }

            if(password.getContent().charAt(password.getRight()-1) == password.getLetter()){
                ocurrences++;
            }

            if(ocurrences == 1){
                valid++;
            }
        }

        return valid;
    }

    public static void main(String[] args) {
        System.out.println(validPasswords());
        System.out.println(actualValidPasswords());
    }
}
