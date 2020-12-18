package adventcode.day12;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day12 {

    public static ArrayList<Instruction> instructions = new ArrayList<>();
    public static int eastWest = 0;
    public static int northSouth = 0;
    public static int degrees = 0;
    public static Point waypoint = new Point(10, 1);

    public static void readFile(String filename){

        instructions = new ArrayList<>();

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            while (line != null) {

                char operation = line.charAt(0);
                int number = Integer.parseInt(line.substring(1));
                instructions.add(new Instruction(operation, number));

                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void move(char operation, int number){
        switch (operation) {
            case 'N':
                northSouth += number;
                break;
            case 'S':
                northSouth -= number;
                break;
            case 'E':
                eastWest += number;
                break;
            case 'W':
                eastWest -= number;
                break;
        }
    }

    public static int runInstructions(){
        eastWest = 0;
        northSouth = 0;
        degrees = 0;

        for(Instruction instruction: instructions){

            switch (instruction.getOperation()){
                case 'N':
                case 'S':
                case 'E':
                case 'W':
                    move(instruction.getOperation(), instruction.getNumber());
                    break;
                case 'L':
                    degrees += instruction.getNumber();

                    degrees = degrees < 0 ? 360 + degrees : degrees >= 360 ? degrees - 360 : degrees;

                    break;
                case 'R':
                    degrees -= instruction.getNumber();

                    degrees = degrees < 0 ? 360 + degrees : degrees >= 360 ? degrees - 360 : degrees;

                    break;
                case 'F':
                    switch (degrees){
                        case 0:
                            move('E', instruction.getNumber());
                            break;
                        case 90:
                            move('N', instruction.getNumber());
                            break;
                        case 180:
                            move('W', instruction.getNumber());
                            break;
                        case 270:
                            move('S', instruction.getNumber());
                            break;
                    }
                    break;
            }
        }

        return Math.abs(eastWest) + Math.abs(northSouth);
    }

    public static int runInstructionsV2(){

        eastWest = 0;
        northSouth = 0;
        degrees = 0;

        for(Instruction instruction: instructions){

            double degrees = 0;

            if(instruction.getOperation() == 'L'){
                degrees = instruction.getNumber();
            }
            else if(instruction.getOperation() == 'R'){
                degrees = -instruction.getNumber();
            }

            switch (instruction.getOperation()){
                case 'N':
                    waypoint.translate(0, instruction.getNumber());
                    break;
                case 'S':
                    waypoint.translate(0, -instruction.getNumber());
                    break;
                case 'E':
                    waypoint.translate(instruction.getNumber(), 0);
                    break;
                case 'W':
                    waypoint.translate(-instruction.getNumber(), 0);
                    break;
                case 'L':
                case 'R':

                    double newX = waypoint.x * Math.cos(Math.toRadians(degrees)) - waypoint.y * Math.sin(Math.toRadians(degrees));
                    double newY = waypoint.x * Math.sin(Math.toRadians(degrees)) + waypoint.y * Math.cos(Math.toRadians(degrees));

                    waypoint = new Point((int) Math.round(newX), (int) Math.round(newY));
                    break;

                case 'F':
                    eastWest += (waypoint.x * instruction.getNumber());
                    northSouth += (waypoint.y * instruction.getNumber());

                    break;
            }

            System.out.println(instruction.getOperation() + "" + instruction.getNumber() + " : " + "EW:" + eastWest + ", NS:" + northSouth + ", D: (" + waypoint.x + ", " + waypoint.y + ")");
        }

        return Math.abs(eastWest) + Math.abs(northSouth);
    }

    public static void main(String[] args) {
        readFile("/home/joan/dev/java/AdventCode/inputs/input_12.txt");
        System.out.println("Manhattan Distance: " + runInstructions());
        System.out.println("Manhattan Distance: " + runInstructionsV2());
    }
}
