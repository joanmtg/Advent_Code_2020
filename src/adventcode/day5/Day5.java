package adventcode.day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Day5 {

    public static ArrayList<String> passes = new ArrayList<>();

    public static void readFile(String filename){

        passes = new ArrayList<>();

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            while (line != null) {
                passes.add(line);
                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void highestSeatId(){
        readFile("/home/joan/dev/java/AdventCode/inputs/input_5.txt");

        ArrayList<Integer> seatIds = new ArrayList<>();

        for(String pass: passes){

            int front = 0, back = 127;

            for(int i = 0; i < 7; i++){

                int size = back - front + 1;

                switch (pass.charAt(i)){
                    case 'F':
                        back -= size/2;
                        break;
                    case 'B':
                        front += size/2;
                        break;
                }
            }

            int left = 0, right = 7;

            for(int i = 7; i < 10; i++){

                int size = right - left + 1;

                switch (pass.charAt(i)){
                    case 'L':
                        right -= size/2;
                        break;
                    case 'R':
                        left += size/2;
                        break;
                }
            }

            seatIds.add(front * 8 + left);
        }

        Collections.sort(seatIds);

        System.out.println(seatIds.toString());

        System.out.println("Highest seat: " + seatIds.get(seatIds.size()-1));

        for(int i = 0; i < seatIds.size()-1; i++){
            if(seatIds.get(i) + 1 != seatIds.get(i+1)){
                System.out.println("My Seat: " + (seatIds.get(i) + 1));
                break;
            }
        }

    }


    public static void main(String[] args) {
        highestSeatId();
    }
}
