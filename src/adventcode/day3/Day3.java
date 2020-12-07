package adventcode.day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day3 {

    public static ArrayList<char[]> matrix = new ArrayList<>();

    public static void readFile(String filename){
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            while (line != null) {

                char[] row = new char[line.length()];

                for(int i = 0; i < line.length(); i++){
                    row[i] = line.charAt(i);
                }

                matrix.add(row);

                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static long countTrees(int x, int y, int numberExercise){

        matrix.clear();

        readFile("/home/joan/dev/java/AdventCode/inputs/input_3_" + numberExercise + ".txt");

        int row = 0;
        int column = 0;
        long trees = 0;

        while(row < matrix.size()){
            if(matrix.get(row)[column] == '#'){
                trees++;
            }

            if(column + x >= matrix.get(row).length){
                column = x - (matrix.get(row).length - column);
            }else {
                column += x;
            }

            row += y;
        }

        return trees;
    }

    public static void main(String[] args) {
        System.out.println(countTrees(3, 1, 1));

        long first = countTrees(1, 1, 2);
        long second = countTrees(3,1, 2);
        long third = countTrees(5,1, 2);
        long fourth = countTrees(7, 1, 2);
        long fifth = countTrees(1, 2, 2);

        System.out.println(first*second*third*fourth*fifth);
    }

}
