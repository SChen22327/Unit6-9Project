public class Board {
    private Space[][] board;
    private int difficulty;
    private int room;
    public Board() {
        difficulty = 1;
        room = 1;
        createBoard();
    }

    private void createBoard() {
        board = new Space[5][12];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = new Space("☐");
            }
        }

        if (room % 5 == 1) {
            difficulty++;
        }
        if (room == 1) {
            board[2][0] = new Robot("⚡");
        }

        for (int i = 0; i < difficulty; i++) {
            int row = (int) (Math.random() * 6);
            int col = (int) (Math.random() * 6) + 6;
            board[row][col] = new Shuimen("\uD83D\uDCA7", new int[]{row, col});
        }
    }
    private void printBoard() {
        for (Space[] row : board) {
            for (Space space : row) {
                System.out.print(space.getSymbol());
            }
            System.out.println();
        }
    }

    private void play() {
        boolean endGame = false;
        while (!endGame) {

        }
    }
}
