//shuimen - water people, using the chinese word for water
//robots fear water, Shuimen will destroy any robot it makes contact with
import java.util.ArrayList;
public class Shuimen extends Space {
    private ArrayList<int[]> nextMoves;
    private int[] coords;
    private int[] nextCoords;
    private boolean moved;
    private Robot player;
    public Shuimen(String symbol, int[] coords, Robot player) {
        super(symbol);
        this.coords = new int[2];
        nextCoords = new int[2];
        setCoords(coords);
        moved = true;
        this.player = player;
    }
    public boolean move() {
        nextMoves(player);
        int i = (int) (Math.random() * nextMoves.size());
        int[] coordPair = nextMoves.get(i);
        nextCoords[0] = coordPair[0];
        nextCoords[1] = coordPair[1];
        return true;
    }

    public void setMoved(boolean move) {
        moved = move;
    }
    public boolean getMoved() {
        return moved;
    }
    public void setCoords(int[] newC) {
        coords[0] = newC[0];
        coords[1] = newC[1];
    }
    public int[] getCoords() {
        return coords;
    }
    public int[] getNextCoords() {
        return nextCoords;
    }
    private void nextMoves(Robot player) {
        nextMoves = new ArrayList<int[]>();
        int[] pCoord = player.getCoords();
        if (coords[0] < pCoord[0]) {
            nextMoves.add(new int[]{coords[0] + 1,coords[1]});
        } else if (coords[0] > pCoord[0]){
            nextMoves.add(new int[]{coords[0] - 1, coords[1]});
        }
        if (coords[1] < pCoord[1]) {
            nextMoves.add(new int[]{coords[0], coords[1] + 1});
        } else if (coords[1] > pCoord[1]) {
            nextMoves.add(new int[]{coords[0], coords[1] - 1});
        }
    }
}
