package adventcode.day12;

public class Instruction {

    private char operation;

    private int number;

    public Instruction(char operation, int number) {
        this.operation = operation;
        this.number = number;
    }

    public char getOperation() {
        return operation;
    }

    public void setOperation(char operation) {
        this.operation = operation;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}