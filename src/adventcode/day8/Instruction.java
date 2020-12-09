package adventcode.day8;

public class Instruction {

    private String operation;

    private Integer number;

    public Instruction(String operation, Integer number) {
        this.operation = operation;
        this.number = number;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
