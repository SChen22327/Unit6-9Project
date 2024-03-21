import java.util.ArrayList;

public class Board {
    private Space[][] board;
    private int difficulty;
    private int room;
    private Robot player;
    private ArrayList<Shuimen> enemies;
    public Board() {
        difficulty = 1;
        room = 0;
        player = new Robot("⚇");
        play();
        end();
    }

    private void createBoard() {
        enemies = new ArrayList<Shuimen>();
        board = new Space[5][12];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = new Space("☐");;
            }
        }

        if (room % 4 == 1) {
            difficulty++;
        }
        if (room == 1) {
            board[2][0] = player;
        } else {
            board[player.getLastRow()][0] = player;
            player.setCoords(new int[]{player.getLastRow(), 0});
        }

        for (int i = 0; i < difficulty; i++) {
            int row = (int) (Math.random() * 3) + 1;
            int col = (int) (Math.random() * 5) + 6;
            board[row][col] = new Shuimen("♚", new int[]{row, col});
            enemies.add((Shuimen) board[row][col]);
        }
        board[(int) (Math.random() * 5)][11] = new Door("\uD83D\uDEAA");
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
        createBoard();
        boolean runLoop = false;
        outerloop :
        while (!runLoop && room != 21) {
            ArrayList<int[]> eOldCoords = new ArrayList<int[]>();
            ArrayList<int[]> eCoords = new ArrayList<int[]>();
            for (int i = 0; i < enemies.size(); i++) {
                Shuimen enemy = enemies.get(i);
                int [] eOld = new int[]{enemy.getCoords()[0], enemy.getCoords()[1]};
                eOldCoords.add(eOld);
                enemy.move();
                int[] eCoord = new int[]{enemy.getCoords()[0], enemy.getCoords()[1]};
                eCoords.add(eCoord);
                if (!(board[eCoord[0]][eCoord[1]] instanceof Shuimen || board[eCoord[0]][eCoord[1]] instanceof Door)){
                    board[eCoord[0]][eCoord[1]] = arrow(eOld, eCoord);
                }
            }
            printBoard();
            int[] pOld = new int[]{player.getCoords()[0], player.getCoords()[1]};
            if (player.move()) {
                int[] pCoords = player.getCoords();
                if (board[pCoords[0]][pCoords[1]] instanceof Shuimen || board[pCoords[0]][pCoords[1]] instanceof Arrow) {
                    break outerloop;
                } else if (board[pCoords[0]][pCoords[1]] instanceof Door) {
                    player.setLastRow(player.getCoords()[0]);
                    Door.exit(player);
                    room++;
                    createBoard();
                } else {
                    board[pOld[0]][pOld[1]] = new Space("☐");
                    board[pCoords[0]][pCoords[1]] = player;
                }
                for (int i = 0; i < eCoords.size(); i++) {
                    int[] eOld = eOldCoords.get(i);
                    int[] eCoord = eCoords.get(i);
                    board[eOld[0]][eOld[1]] = new Space("☐");;
                    board[eCoord[0]][eCoord[1]] = enemies.get(i);
                }
            } else {
                for (int i = 0; i < eOldCoords.size(); i++) {
                    enemies.get(i).setCoords(eOldCoords.get(i));
                }
            }
        }
        System.out.println("\"Noo, get away from me!\"");
        sleep(750);
        System.out.println("\033[3mThe Shuimen walks to the trembling robot and raises a fist." +
                "\nThe robot could only scream as its circuits fry upon contact with the Shuimen's watery attacks.\033[0m");
        sleep(1500);
    }

    private void end() {
        System.out.println("Finally, this robot can rest...");
        sleep(750);
        for (int i = 0; i < 3; i++) {
            System.out.println("Z");
            sleep(750);
        }
        System.out.println("Final Score: " + player.getScore());
        System.out.println("Total Moves: " + player.getMoves());
    }

    private Arrow arrow(int[] eOld, int[] eNew) {
        int oldy = eOld[0];
        int oldx = eOld[1];
        int newy = eNew[0];
        int newx = eNew[1];
        if (newy == oldy - 1) {
            return new Arrow("🠉");
        } else if (newy == oldy + 1) {
            return new Arrow("🠋");
        } else if (newx == oldx - 1) {
            return new Arrow("🠈");
        } else if (newx == oldx + 1) {
            return new Arrow("🠊");
        } else {
            return new Arrow("☐");
        }
    }
    private void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception e) {
            System.out.println("Error occurred");
        }
    }
}
