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
    }
    public boolean move() {
        nextMoves();
        int i = (int) (Math.random() * nextMoves.size());
        int[] coordPair = nextMoves.get(i);
        setCoords(coordPair);
        return true;
    }

    public void setCoords(int[] newC) {
        coords[0] = newC[0];
        coords[1] = newC[1];
    }
    private void nextMoves() {
        nextMoves = new ArrayList<int[]>();
        if (coords[0] == 0) {
            if (coords[1] == 0) {
                nextMoves.add(new int[]{0, 1});
                nextMoves.add(new int[]{1, 0});
            } else if (coords[1] == 11){
                nextMoves.add(new int[]{0, 10});
                nextMoves.add(new int[]{1, 11});
            } else {
                nextMoves.add(new int[]{0, coords[1] - 1});
                nextMoves.add(new int[]{0, coords[1] + 1});
                nextMoves.add(new int[]{1, coords[1]});
            }
        } else if (coords[0] == 4) {
            if (coords[1] == 0) {
                nextMoves.add(new int[]{4, 1});
                nextMoves.add(new int[]{3, 0});
            } else if (coords[1] == 11) {
                nextMoves.add(new int[]{4, 10});
                nextMoves.add(new int[]{3, 11});
            } else {
                nextMoves.add(new int[]{4, coords[1] - 1});
                nextMoves.add(new int[]{4, coords[1] + 1});
                nextMoves.add(new int[]{3, coords[1]});
            }
        } else {
            if (coords[1] == 0) {
                nextMoves.add(new int[]{coords[0], 1});
                nextMoves.add(new int[]{coords[0] - 1, 0});
                nextMoves.add(new int[]{coords[0] + 1, 0});
            } else if (coords[1] == 11) {
                nextMoves.add(new int[]{coords[0], 10});
                nextMoves.add(new int[]{coords[0] - 1, 11});
                nextMoves.add(new int[]{coords[0] + 1, 11});
            } else {
                nextMoves.add(new int[]{coords[0], coords[1] - 1});
                nextMoves.add(new int[]{coords[0], coords[1] + 1});
                nextMoves.add(new int[]{coords[0] - 1, coords[1]});
                nextMoves.add(new int[]{coords[0] + 1, coords[1]});
            }
        }
    }

    public int[] getCoords() {
        return coords;
    }
}
