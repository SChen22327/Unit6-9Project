import java.util.Scanner;

public class Robot extends Shuimen {
    private int score;
    private int lastRow;
    public Robot(String symbol) {
        super(symbol, new int[]{2, 0});
    }

    public int getScore() {
        return score;
    }
    public void increaseScore(int num) {
        score += num;
    }

    public void move() {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter a direction(W, A, S, D): ");
        String d = s.nextLine().toLowerCase();

    }
}
