public class Board {
    private Space[][] board;
    public Board() {
        createBoard();
    }

    private void createBoard() {
        board = new Space[6][12];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = new Space("☐");
            }
        }
    }
}
