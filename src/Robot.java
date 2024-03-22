import java.util.Scanner;

public class Robot extends Shuimen {
    private int score;
    private int moves;
    private int lastRow;
    public Robot(String symbol) {
        super(symbol, new int[]{2, 0});
    }

    public void setLastRow(int lr) {
        lastRow = lr;
    }

    public int getLastRow() {
        return lastRow;
    }
    public int getScore() {
        return score;
    }
    public void increaseScore(int num) {
        score += num;
    }
    public int getMoves() {
        return moves;
    }
    @Override
    public boolean move() {
        int[] current = getCoords();
        lastRow = current[0];
        Scanner s = new Scanner(System.in);
        System.out.print("Enter a direction(W, A, S, D): ");
        String d = s.nextLine().toLowerCase();
        System.out.println();
        switch (d) {
            case "w" :
                if (current[0] != 0) {
                    setCoords(new int[]{current[0] - 1, current[1]});
                    moves++;
                    return true;
                } else {
                    System.out.println("Ouch, a wall!");
                    return false;
                }
            case "a" :
                if (current[1] != 0) {
                    setCoords(new int[]{current[0], current[1] - 1});
                    moves++;
                    return true;
                } else {
                    System.out.println("Ouch, a wall!");
                    return false;
                }
            case "s" :
                if (current[0] != 4) {
                    setCoords(new int[]{current[0] + 1, current[1]});
                    moves++;
                    return true;
                } else {
                    System.out.println("Ouch, a wall!");
                    return false;
                }
            case "d" :
                if (current[1] != 11) {
                    setCoords(new int[]{current[0], current[1] + 1});
                    moves++;
                    return true;
                } else {
                    System.out.println("Ouch, a wall!");
                    return false;
                }
            default : System.out.println("I don't think I that's a direction I can go to.");
                return false;
        }
    }

}
