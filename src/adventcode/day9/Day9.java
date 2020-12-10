package adventcode.day9;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day9 {

    public static ArrayList<Long> numbers = new ArrayList<>();

    public static void readFile(String filename){
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            while (line != null) {
                numbers.add(Long.valueOf(line));
                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean findTwoNumbers(List<Long> numbersList, Long number){
        //Order numbers ASC
        Collections.sort(numbersList);

        boolean exist = false;
        int left = 0;
        int right = numbersList.size()-1;
        long leftNumber = 0;
        long rightNumber = 0;

        while(left != right){

            leftNumber = numbersList.get(left);
            rightNumber = numbersList.get(right);

            long result = leftNumber + rightNumber;

            if(result == number){
                exist = true;
                break;
            }
            else if(result < number){
                left++;
            }else {
                right--;
            }
        }

        return exist;
    }

    public static long findError(){

        long error = 0;

        for(int i = 25; i < numbers.size(); i++){

            List<Long> subList = new ArrayList<>();

            for(int j = i-25; j < i; j++){
                subList.add(numbers.get(j));
            }

            boolean valid = findTwoNumbers(subList, numbers.get(i));

            if(!valid){
                error = numbers.get(i);
                break;
            }
        }

        return error;
    }

    public static long findWeakness(){
        long error = findError();

        long sum = 0;
        int leftIndex = 0;
        int rightIndex = 2;
        long result = 0;

        while(sum != error){

            List<Long> subList = numbers.subList(leftIndex, rightIndex);
            sum = subList.stream().mapToLong(Long::longValue).sum();

            if(sum == error){
                result = Collections.min(subList) + Collections.max(subList);
                break;
            }
            else if(sum < error){
                rightIndex++;
            }
            else{
                leftIndex++;
                rightIndex = leftIndex + 2;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        readFile("/home/joan/dev/java/AdventCode/inputs/input_9.txt");
        System.out.println("Error: " + findError());
        System.out.println("Weakness: " + findWeakness());
    }
}
