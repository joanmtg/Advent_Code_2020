package adventcode.day13;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day13 {

    public static long arrivalTime;
    public static ArrayList<Long> busFrequencies = new ArrayList<>();
    public static ArrayList<Route> routes = new ArrayList<>();

    public static void readFile(String filename){
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            int index = 0;

            while (line != null) {

                if(index == 0){
                    arrivalTime = Long.parseLong(line);
                }else{
                    String[] split = line.split(",");

                    for (int i = 0; i < split.length; i++) {
                        String element = split[i];
                        if (!element.equals("x")) {
                            busFrequencies.add(Long.parseLong(element));
                            routes.add(new Route(Long.parseLong(element), i));
                        }
                    }
                }

                line = reader.readLine();
                index++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static long findWaitingTime(){

        HashMap<Long, Long> waitingTimes = new HashMap<>();

        for(Long frequency: busFrequencies){

            long wait = Math.abs(frequency - (arrivalTime % frequency));
            wait = wait == frequency ? 0 : wait;

            waitingTimes.put(frequency, wait);
        }

        long lowestKey = 0;
        long lowest = 70000000L;

        for(Long frequency: waitingTimes.keySet()){
            if(waitingTimes.get(frequency) < lowest){
                lowest = waitingTimes.get(frequency);
                lowestKey = frequency;
            }
        }

        System.out.println("Waiting time taking earliest bus: " + waitingTimes.get(lowestKey)  + " minutes");

        return lowestKey * waitingTimes.get(lowestKey);
    }

    private static long gcd(long a, long b)
    {
        while (b > 0)
        {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private static long lcm(long a, long b)
    {
        return a * (b / gcd(a, b));
    }

    private static long lcm(HashSet<Long> input)
    {
        List<Long> list = new ArrayList<>(input);

        long result = list.get(0);

        for(int i = 1; i < list.size(); i++)  {
            result = lcm(result, list.get(i));
        }

        return result;
    }

    public static long findEarliestTimestamp(){

        boolean found = false;
        long timestamp = 0;
        long longestFrequency = routes.get(routes.size() - 1).getFrequency();
        HashSet<Long> foundRoutes = new HashSet<>();

        while (!found){

            boolean valid = true;

            for (Route route: routes) {
                long tempTimestamp = timestamp + route.getPosition();

                long wait = Math.abs(route.getFrequency() - (tempTimestamp % route.getFrequency()));
                wait = wait == route.getFrequency() ? 0 : wait;

                if(wait == 0){
                    foundRoutes.add(route.getFrequency());
                }
                else if(wait != 0){
                    valid = false;
                    break;
                }
            }

            found = valid;

            if(!found){
                timestamp += !foundRoutes.isEmpty() ? lcm(foundRoutes): 1;
            }
        }

        return timestamp;
    }


    public static void main(String[] args) {
        readFile("/home/joan/dev/java/AdventCode/inputs/input_13.txt");

        System.out.println(arrivalTime);
        System.out.println(busFrequencies);

        System.out.println("Result Part 1: " + findWaitingTime());
        System.out.println("Earliest timestamp: " + findEarliestTimestamp());
    }
}
