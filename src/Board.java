import java.util.ArrayList;

public class Board {
    private Space[][] board;
    private int difficulty;
    private int room;
    private Robot player;
    private ArrayList<Shuimen> enemies;
    public Board() {
        difficulty = 1;
        room = 1;
        player = new Robot("⚡");
        enemies = new ArrayList<Shuimen>();
        createBoard();
        play();
        end();
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
            board[2][0] = player;
        }

        for (int i = 0; i < difficulty; i++) {
            int row = (int) (Math.random() * 6);
            int col = (int) (Math.random() * 6) + 6;
            board[row][col] = new Shuimen("\uD83D\uDCA7", new int[]{row, col});
            enemies.add((Shuimen) board[row][col]);
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
            if (room == 21) {
                endGame = true;
            }
            int[] pCoords = new int[2];
            ArrayList<int[]> eCoords = new ArrayList<int[]>();
            printBoard();
            if (player.move()) {
                pCoords = player.getCoords();
                for (Shuimen enemy : enemies) {
                    enemy.move();
                    eCoords.add(enemy.getCoords());
                }
            }


        }
    }

    private void end() {
        System.out.println("Finally, this robot can rest...");
        System.out.println("Z");
        sleep(750);
        System.out.println("Z");
        sleep(750);
        System.out.println("Z");
        sleep(750);
        System.out.println("Final Score: " + player.getScore());
        System.out.println("Total Moves: " + player.getMoves());
    }

    private void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception e) {
            System.out.println("Error occurred");
        }
    }
}
