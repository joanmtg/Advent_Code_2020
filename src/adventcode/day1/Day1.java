package adventcode.day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Day1 {

    public static ArrayList<Integer> numbers = new ArrayList<>();

    public static void readFile(String filename){
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            while (line != null) {
                numbers.add(Integer.valueOf(line));
                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static int findTwoNumbers(){
        numbers = new ArrayList<>();

        readFile("/home/joan/dev/java/AdventCode/inputs/input_1_1.txt");

        //Order numbers ASC
        Collections.sort(numbers);

        int result = 0;
        int left = 0;
        int right = numbers.size()-1;
        int leftNumber = 0;
        int rightNumber = 0;

        while(result != 2020){

            leftNumber = numbers.get(left);
            rightNumber = numbers.get(right);

            result = leftNumber + rightNumber;

            if(result < 2020){
                left++;
            }else {
                right--;
            }
        }

        return leftNumber * rightNumber;
    }

    public static int findThreeNumbers(){
        numbers = new ArrayList<>();

        readFile("/home/joan/dev/java/AdventCode/inputs/input_1_2.txt");

        //Order numbers ASC
        Collections.sort(numbers);

        int result = 0;
        int left = 0;
        int right = numbers.size()-1;
        int leftNumber = 0;
        int rightNumber = 0;
        int middleNumber = 0;

        while(result != 2020){

            leftNumber = numbers.get(left);
            rightNumber = numbers.get(right);

            for(int middle = left; middle < right; middle++){
                middleNumber = numbers.get(middle);
                result = leftNumber + middleNumber + rightNumber;

                if(result == 2020){
                    break;
                }
            }

            if(result < 2020){
                left++;
            }else {
                right--;
            }
        }

        return leftNumber * middleNumber * rightNumber;
    }

    public static void main(String[] args) {
        System.out.println(findTwoNumbers());
        System.out.println(findThreeNumbers());
    }
}
