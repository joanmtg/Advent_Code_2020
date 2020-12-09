package adventcode.day8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day8 {

    public static ArrayList<Instruction> instructions = new ArrayList<>();
    public static ArrayList<Integer> executedInstructions = new ArrayList<>();

    public static void readFile(String filename){
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            while (line != null) {

                String[] split = line.split(" ");
                String operation = split[0];
                String signedNumber = split[1];

                char sign = signedNumber.charAt(0);
                int number = Integer.parseInt(signedNumber.replace(String.valueOf(sign), ""));

                if(sign == '-'){
                    number *= -1;
                }

                instructions.add(new Instruction(operation, number));
                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String oppositeOperation(String operation){
        return operation.equals("jmp") ? "nop": "jmp";
    }

    public static Result runInstructions(){

        executedInstructions.clear();

        int index = 0;
        int acc = 0;

        while(!executedInstructions.contains(index) && index < instructions.size()){

            Instruction instruction = instructions.get(index);
            executedInstructions.add(index);

            switch (instruction.getOperation()){
                case "acc":
                    acc += instruction.getNumber();
                    index++;
                    break;

                case "jmp":
                    index += instruction.getNumber();
                    break;

                case "nop":
                    index++;
                    break;
            }
        }

        return new Result(index == instructions.size(), acc);
    }

    public static Result fixInstructions(){

        Result result = null;

        for(int i = 0; i < instructions.size(); i++){

            String op = instructions.get(i).getOperation();
            if ("jmp".equals(op) || "nop".equals(op)) {

                Instruction newInstruction = instructions.get(i);
                newInstruction.setOperation(oppositeOperation(op));

                instructions.set(i, newInstruction);
                result = runInstructions();

                if (result.getValid()) {
                    break;
                } else {
                    newInstruction.setOperation(op);
                    instructions.set(i, newInstruction);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {

        readFile("/home/joan/dev/java/AdventCode/inputs/input_8.txt");

        Result result = runInstructions();
        System.out.println("Acc: " + result.getAcc());

        Result fixedResult = fixInstructions();
        System.out.println("Fixed acc:" + fixedResult.getAcc());
    }
}
