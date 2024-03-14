//shuimen - water people, using the chinese word for water
//robots fear water, Shuimen will destroy any robot it makes contact with
import java.util.ArrayList;
public class Shuimen extends Space {
    private ArrayList<int[]> nextMoves;
    private int[] coords;
    public Shuimen(String symbol, int[] coords) {
        super(symbol);
        this.coords = new int[2];
        setCoords(coords);
        nextMoves = new ArrayList<int[]>();
    }
    public void move(String[][] board) {
        int i = (int) (Math.random() * nextMoves.size());
        int[] coordPair = nextMoves.get(i);
        coords[0] = coordPair[0];
        coords[1] = coordPair[1];
    }
    private void nextMoves() {
        if (coords[0] == 0) {
            if (coords[1] == 0) {
                nextMoves.add(new int[]{0, 1});
                nextMoves.add(new int[]{1, 0});
            } else {
                nextMoves.add(new int[]{0, coords[1] - 1});
                nextMoves.add(new int[]{0, coords[1] + 1});
                nextMoves.add(new int[]{1, coords[1]});
            }
        } else if (coords[0] == 5) {
            if (coords[1] == 0) {
                nextMoves.add(new int[]{5, 1});
                nextMoves.add(new int[]{4, 0});
            } else {
                nextMoves.add(new int[]{5, coords[1] - 1});
                nextMoves.add(new int[]{5, coords[1] + 1});
                nextMoves.add(new int[]{4, coords[1]});
            }
        } else {
            nextMoves.add(new int[]{coords[0], coords[1] - 1});
            nextMoves.add(new int[]{coords[0], coords[1] + 1});
            nextMoves.add(new int[]{coords[0] + 1, coords[1]});
            nextMoves.add(new int[]{coords[0] - 1, coords[1]});
        }
    }
    public void setCoords(int[] newC) {
        coords[0] = newC[0];
        coords[1] = newC[1];
    }
    public int[] getCoords() {
        return coords;
    }
}
