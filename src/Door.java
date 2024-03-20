public class Door extends Space{
    public Door(String symbol) {
        super(symbol);
    }
    public void exit(Robot player) {
        player.increaseScore(5);
    }
}
