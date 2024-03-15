public class Door extends Space{
    public Door() {
        super("\uD83D\uDEAA");
    }
    public void exit(Robot player) {
        player.increaseScore(5);
    }
}
