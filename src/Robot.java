import java.util.Scanner;

public class Robot extends Shuimen {
    private int score;
    private int moves;
    private int lastRow;
    private int lastCol;
    private boolean row;
    private boolean col;
    public Robot(String symbol) {
        super(symbol, new int[]{2, 0});
    }
    public void setLast() {
        if (getCoords()[1] == 11) {
            row = true;
            col = false;
            lastRow = getCoords()[0];
        } else if (getCoords()[0] == 0 || getCoords()[0] == 4) {
            col = true;
            row = false;
            lastCol = getCoords()[1];
        }
    }
    public int getLastRow() {
        return lastRow;
    }
    public int getLastCol() {
        return lastCol;
    }
    public boolean getRow() {
        return row;
    }
    public boolean getCol() {
        return col;
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
