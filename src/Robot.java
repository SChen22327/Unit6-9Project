public class Robot extends Shuimen {
    private int score;
    private int lastRow;
    public Robot(String symbol) {
        super(symbol);
    }

    public int getScore() {
        return score;
    }
    public void increaseScore(int num) {
        score += num;
    }
}
