import java.util.ArrayList;

public class Board {
    private Space[][] board;
    private int difficulty;
    private int room;
    private Robot player;
    private ArrayList<Shuimen> enemies;
    int[] pOld;
    public Board() {
        difficulty = 1;
        room = 1;
        player = new Robot("⚇");
        enemies = new ArrayList<Shuimen>();
        pOld = new int[2];
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
            pOld[0] = player.getCoords()[0];
            pOld[1] = player.getCoords()[1];
            if (player.move()) {
                int[] pCoords = player.getCoords();
                for (int i = 0; i < enemies.size(); i++) {
                    Shuimen enemy = enemies.get(i);
                    int[] eOld = new int[2];
                    eOld[0] = enemy.getCoords()[0];
                    eOld[1] = enemy.getCoords()[1];
                    enemy.move();
                    int[] eCoord = new int[]{enemy.getCoords()[0], enemy.getCoords()[1]};
                    if (board[eCoord[0]][eCoord[1]] instanceof Robot) {
                        endGame = true;
                    } else if (!(board[eCoord[0]][eCoord[1]] instanceof Shuimen)){
                        board[eOld[0]][eOld[1]] = new Space("☐");;
                        board[eCoord[0]][eCoord[1]] = enemy;
                    }
                }
                board[pOld[0]][pOld[1]] = new Space("☐");;
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
