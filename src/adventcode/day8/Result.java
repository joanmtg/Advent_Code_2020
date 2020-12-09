package adventcode.day8;

public class Result {

    private boolean valid;

    private int acc;

    public Result() {
    }

    public Result(boolean valid, int acc) {
        this.valid = valid;
        this.acc = acc;
    }

    public boolean getValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public int getAcc() {
        return acc;
    }

    public void setAcc(int acc) {
        this.acc = acc;
    }
}
