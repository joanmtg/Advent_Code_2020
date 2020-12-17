package adventcode.day10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Day10 {

    public static ArrayList<Integer> adapters = new ArrayList<>();

    public static HashMap<Integer, Long> cache = new HashMap<>();

    public static void readFile(String filename){
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            while (line != null) {
                adapters.add(Integer.valueOf(line));
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void findDifferences(){
        Collections.sort(adapters);
        System.out.println(adapters);

        int i = -1, current = 0, dif1 = 0, dif2 = 0, dif3 = 1;

        while (i < adapters.size() - 1){
            int next = adapters.get(i+1);
            int dif = next-current;

            switch (dif){
                case 1:
                    dif1++;
                    break;
                case 2:
                    dif2++;
                    break;
                case 3:
                    dif3++;
                    break;
            }

            i++;
            current = adapters.get(i);
        }

        System.out.println("Result: " + (dif1*dif3));
    }

    public static long combinations(int number){

        if(!adapters.contains(number)){
            return 0;
        }

        if(adapters.indexOf(number) == adapters.size() - 2){
            return 1;
        }

        if(cache.containsKey(number)){
            return cache.get(number);
        }

        cache.put(number, combinations(number+1) + combinations(number+2) + combinations(number+3));
        return cache.get(number);
    }

    public static void main(String[] args) {
        readFile("/home/joan/dev/java/AdventCode/inputs/input_10.txt");

        findDifferences();

        adapters.add(0, 0);
        adapters.add(adapters.get(adapters.size()-1) + 3);

        Collections.sort(adapters);

        System.out.println("Result: " + combinations(0));
    }
}
