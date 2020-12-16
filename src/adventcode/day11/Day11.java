package adventcode.day11;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Day11 {

    public static int[][] currentMatrix;
    public static int[][] tempMatrix;

    public static void readFile(String filename){
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            ArrayList<ArrayList<Integer>> readMatrix = new ArrayList<>();

            while (line != null) {
                String[] split = line.split("");

                ArrayList<Integer> row = new ArrayList<>();
                for(String s: split){

                    switch (s){
                        case ".":
                            row.add(0);
                            break;
                        case "L":
                            row.add(1);
                            break;
                        case "#":
                            row.add(2);
                            break;
                    }
                }
                readMatrix.add(row);
                line = reader.readLine();
            }
            reader.close();

            currentMatrix = new int[readMatrix.size()][readMatrix.get(0).size()];

            for(int i = 0; i < readMatrix.size(); i++){
                for(int j = 0; j < readMatrix.get(i).size(); j++){
                    currentMatrix[i][j] = readMatrix.get(i).get(j);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean equalMatrixes(){
        boolean flag = true;
        for (int i = 0; i <currentMatrix.length; i++){
            for (int j = 0; j < currentMatrix[i].length; j++){
                if(currentMatrix[i][j] != tempMatrix[i][j]){
                    flag = false;
                    break;
                }
            }
        }
        return flag;
    }

    public static boolean validIndex(int i, int j){
        return (i >= 0 && i < currentMatrix.length) && (j >= 0 && j < currentMatrix[0].length);
    }

    public static int adjacentOccupied(int i, int j){

        int occupied = 0;

        //-, -
        if(validIndex(i-1,j-1) && currentMatrix[i-1][j-1] == 2){
            occupied++;
        }

        //-, same
        if(validIndex(i-1,j) && currentMatrix[i-1][j] == 2){
            occupied++;
        }

        //-, +
        if(validIndex(i-1,j+1) && currentMatrix[i-1][j+1] == 2){
            occupied++;
        }

        //+, -
        if(validIndex(i+1,j-1) && currentMatrix[i+1][j-1] == 2){
            occupied++;
        }

        //+, same
        if(validIndex(i+1,j) && currentMatrix[i+1][j] == 2){
            occupied++;
        }

        //+, +
        if(validIndex(i+1,j+1) && currentMatrix[i+1][j+1] == 2){
            occupied++;
        }

        //same, -
        if(validIndex(i,j-1) && currentMatrix[i][j-1] == 2){
            occupied++;
        }

        //same, +
        if(validIndex(i,j+1) && currentMatrix[i][j+1] == 2){
            occupied++;
        }

        return occupied;
    }

    public static int applyRules(){

        tempMatrix = new int[currentMatrix.length][currentMatrix[0].length];

        //Copy current matrix to temp
        for (int i = 0; i <currentMatrix.length; i++){
            for (int j = 0; j < currentMatrix[i].length; j++){
                tempMatrix[i][j] = currentMatrix[i][j];
            }
        }

        do{
            //Copy temp to current matrix
            for (int i = 0; i <currentMatrix.length; i++){
                for (int j = 0; j < currentMatrix[i].length; j++){
                    currentMatrix[i][j] = tempMatrix[i][j];
                }
            }

            //Update temp, apply rules to current and save in temp
            for (int i = 0; i <currentMatrix.length; i++){
                for (int j = 0; j < currentMatrix[i].length; j++){
                    switch (currentMatrix[i][j]){
                        case 1:
                            if(adjacentOccupied(i, j) == 0){
                                tempMatrix[i][j] = 2;
                            }
                            break;
                        case 2:
                            if(adjacentOccupied(i, j) >= 4){
                                tempMatrix[i][j] = 1;
                            }
                    }
                }
            }

        }while (!equalMatrixes()); //Compare if current and temp are the same

        //Sum up the 2's in the matrix
        int sum = 0;
        for (int[] list: currentMatrix){
            for (int number: list){
                sum+= number == 2 ? 1 : 0;
            }
        }

        return sum;
    }

    public static void main(String[] args) {
        readFile("/home/joan/dev/java/AdventCode/inputs/input_11.txt");

        System.out.println(applyRules());
    }
}
