package adventcode.day2;

public class Password {
    private int left;
    private int right;
    private char letter;
    private String content;

    public Password() {
    }

    public Password(int left, int right, char letter, String content) {
        this.left = left;
        this.right = right;
        this.letter = letter;
        this.content = content;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
