package adventcode.day7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Day7 {

    public static HashMap<String, HashMap<String, Integer>> rules = new HashMap<>();

    public static void readRulesFile(String filename){

        rules = new HashMap<>();

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            while (line != null) {

                String[] rulesArray = line.split("contain");

                String bagColor = rulesArray[0].replace("bags", "").trim();
                String[] containedBags = rulesArray[1].split(",");

                HashMap<String, Integer> specs = new HashMap<>();

                for(String spec: containedBags){

                    if(!spec.contains("no other bags")){
                        String[] details = spec.replace("bags", "").replace("bag", "").replace(".", "").trim().split(" ", 2);

                        int quantity = Integer.parseInt(details[0]);
                        String color = details[1];

                        specs.put(color, quantity);
                    }
                }

                rules.put(bagColor, specs);

                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static HashSet<String> possibleBagsToContain(String bagColor){
        HashSet<String> bags = new HashSet();

        for(String key: rules.keySet()){
            if(rules.get(key).containsKey(bagColor)){
                bags.add(key);
                bags.addAll(possibleBagsToContain(key));
            }
        }

        return bags;
    }

    public static long bagsContainedIn(String bagColor){
        HashMap<String, Integer> specs = rules.get(bagColor);

        long sum = 0;
        for(String key: specs.keySet()){
            sum += specs.get(key) * (1 + bagsContainedIn(key));
        }

        return sum;
    }

    public static void main(String[] args) {
        readRulesFile("/home/joan/dev/java/AdventCode/inputs/input_7.txt");
        System.out.println(possibleBagsToContain("shiny gold").size());
        System.out.println(bagsContainedIn("shiny gold"));
    }
}
