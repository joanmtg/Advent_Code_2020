package adventcode.day6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Day6 {

    public static ArrayList<Integer> positiveAnswers = new ArrayList<>();

    public static void readTotalPositiveAnswers(String filename){

        positiveAnswers = new ArrayList<>();

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            while (line != null) {

                HashSet<Character> characters = new HashSet<>();

                while(line != null && !line.equals("")){

                    for(int i = 0; i < line.length(); i++){
                        characters.add(line.charAt(i));
                    }
                    line = reader.readLine();
                }

                positiveAnswers.add(characters.size());

                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void findTotalPositiveAnswers(){

        readTotalPositiveAnswers("/home/joan/dev/java/AdventCode/inputs/input_6.txt");

        long sum = positiveAnswers.stream()
                    .mapToLong(Integer::longValue)
                    .sum();

        System.out.println("Total positive answers: " + sum);
    }

    public static void readPositiveAnswersAll(String filename){

        positiveAnswers = new ArrayList<>();

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            while (line != null) {

                int persons = 0;
                HashMap<Character, Integer> quantities = new HashMap<>();

                while(line != null && !line.equals("")){

                    for(int i = 0; i < line.length(); i++){
                        int current = quantities.getOrDefault(line.charAt(i), 0);
                        quantities.put(line.charAt(i), current + 1);
                    }

                    persons++;
                    line = reader.readLine();
                }

                int positive = 0;

                for (Character key: quantities.keySet()){
                    if(quantities.get(key).equals(persons)){
                        positive++;
                    }
                }

                positiveAnswers.add(positive);

                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void findPositiveAnswersAll(){

        readPositiveAnswersAll("/home/joan/dev/java/AdventCode/inputs/input_6.txt");

        long sum = positiveAnswers.stream()
                .mapToLong(Integer::longValue)
                .sum();

        System.out.println("Positive answers for all: " + sum);
    }

    public static void main(String[] args) {
        findTotalPositiveAnswers();
        findPositiveAnswersAll();
    }
}
