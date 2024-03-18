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
        player = new Robot("⚇");
        enemies = new ArrayList<Shuimen>();
        createBoard();
        play();
        end();
    }

    private void createBoard() {
        board = new Space[5][12];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = new Space("☐");;
            }
        }

        if (room % 5 == 1) {
            difficulty++;
        }
        if (room == 1) {
            board[2][0] = player;
        }

        for (int i = 0; i < difficulty; i++) {
            int row = (int) (Math.random() * 5);
            int col = (int) (Math.random() * 6) + 6;
            board[row][col] = new Shuimen("♚", new int[]{row, col});
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
        while (!endGame || room != 21) {
            printBoard();
            int[] pOldCoords = player.getCoords();
            if (player.move()) {
                int[] pCoords = player.getCoords();
                for (int i = 0; i < enemies.size(); i++) {
                    Shuimen enemy = enemies.get(i);
                    int [] oldCoords = enemy.getCoords();
                    enemy.move();
                    if (enemy.getCoords() == pCoords) {
                        endGame = true;
                    } else {
                        board[oldCoords[0]][oldCoords[1]] = new Space("☐");;
                        int[] eCoord = enemy.getCoords();
                        board[eCoord[0]][eCoord[1]] = enemy;
                    }
                }
                board[pOldCoords[0]][pOldCoords[1]] = new Space("☐");;
                board[pCoords[0]][pCoords[1]] = player;
            }
        }
        System.out.println("\"Noo, get away from me!\"");
        System.out.println("\033[3mThe Shuimen walks to the trembling robot and raises a fist." +
                "\nThe robot could only scream as its circuits fry upon contact with the Shuimen's watery attacks.\033[0m");
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
