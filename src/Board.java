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
        play();
        end();
    }

    private void createBoard() {
        enemies = new ArrayList<Shuimen>();
        board = new Space[5][12];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = new Space("☐");
            }
        }

        if (room % 4 == 1 && room != 1) {
            difficulty++;
        }
        if (room == 1) {
            board[2][0] = player;
        } else {
            if (player.getRow()) {
                board[player.getLastRow()][0] = player;
                player.setCoords(new int[]{player.getLastRow(), 0});
            }
            if (player.getCol()) {
                board[0][player.getLastCol()] = player;
                player.setCoords(new int[]{0, player.getLastCol()});
            }
        }

        for (int i = 0; i < difficulty; i++) {
            int row = (int) (Math.random() * 3) + 1;
            int col = (int) (Math.random() * 5) + 6;
            while (board[row][col] instanceof Shuimen) {
                row = (int) (Math.random() * 3) + 1;
                col = (int) (Math.random() * 5) + 6;
            }
            board[row][col] = new Shuimen("\u001B[34m♚\u001B[0m", new int[]{row, col}, player);
            enemies.add((Shuimen) board[row][col]);
        }
        int ran;
        if (player.getCol()) {
            ran = (int) (Math.random() * 5);
            if ((int) (Math.random() * 2) + 1 == 1) {
                if (board[ran][11] instanceof Robot) {
                    while ((board[ran][11] instanceof Robot)) {
                        ran = (int) (Math.random() * 5);
                    }
                }
                board[ran][11] = new Door("\uD83D\uDEAA");
            } else {
                if (board[ran][0] instanceof Robot) {
                    while ((board[ran][0] instanceof Robot)) {
                        ran = (int) (Math.random() * 5);
                    }
                }
                board[ran][0] = new Door("\uD83D\uDEAA");
            }
        } else {
            ran = (int) (Math.random() * 6) + 6;
            if ((int) (Math.random() * 2) + 1 == 1) {
                if (board[0][ran] instanceof Robot) {
                    while ((board[0][ran] instanceof Robot)) {
                        ran = (int) (Math.random() * 6) + 6;
                    }
                }
                board[0][ran] = new Door("\uD83D\uDEAA");
            } else {
                if (board[4][ran] instanceof Robot) {
                    while ((board[4][ran] instanceof Robot)) {
                        ran = (int) (Math.random() * 6) + 6;
                    }
                }
                board[4][ran] = new Door("\uD83D\uDEAA");
            }
        }
    }
    private void printBoard() {
        for (Space[] row : board) {
            for (Space space : row) {
                if (space instanceof Robot) {
                    System.out.print("\u001B[33m" + space.getSymbol() + "\u001B[0m");
                } else {
                    System.out.print(space.getSymbol());
                }
            }
            System.out.println();
        }
    }

    private void play() {
        createBoard();
        while (room != 21) {
            System.out.println("\u001B[47m\u001B[30mRoom " + room + "\u001B[0m");
            ArrayList<int[]> eOldCoords = new ArrayList<int[]>();
            ArrayList<int[]> eCoords = new ArrayList<int[]>();
            for (int i = 0; i < enemies.size(); i++) {
                Shuimen enemy = enemies.get(i);
                int[] eOld = new int[]{enemy.getCoords()[0], enemy.getCoords()[1]};
                eOldCoords.add(eOld);
                int[] eCoord;
                if (enemy.getMoved()) {
                    enemy.move();
                    eCoord = new int[]{enemy.getNextCoords()[0], enemy.getNextCoords()[1]};
                    eCoords.add(eCoord);
                    while ((board[eCoord[0]][eCoord[1]] instanceof Shuimen && !(board[eCoord[0]][eCoord[1]] instanceof Robot)) || board[eCoord[0]][eCoord[1]] instanceof Door || board[eCoord[0]][eCoord[1]] instanceof Arrow) {
                        enemy.setCoords(eOld);
                        enemy.move();
                        eCoord = new int[]{enemy.getNextCoords()[0], enemy.getNextCoords()[1]};
                        eCoords.set(i, eCoord);
                    }
                } else {
                    eCoord = new int[]{enemy.getNextCoords()[0], enemy.getNextCoords()[1]};
                    eCoords.add(eCoord);
                }
                if (board[eCoord[0]][eCoord[1]] instanceof Robot) {
                    board[eCoord[0]][eCoord[1]] = new Space("\u001B[47m" + "\u001B[30m" + player.getSymbol() + "\u001B[30m" + "\u001B[0m");
                    System.out.println("\u001B[31mCareful, you're on the enemy's path!\u001B[0m");
                } else {
                    board[eCoord[0]][eCoord[1]] = arrow(eOld, eCoord);
                }
            }
            printBoard();
            int[] pOld = new int[]{player.getCoords()[0], player.getCoords()[1]};
            if (player.move()) {
                int[] pCoords = player.getCoords();
                if (board[pCoords[0]][pCoords[1]] instanceof Shuimen || board[pCoords[0]][pCoords[1]] instanceof Arrow) {
                    break;
                } else if (board[pCoords[0]][pCoords[1]] instanceof Door) {
                    player.setLast();
                    Door.exit(player);
                    room++;
                    createBoard();
                } else {
                    board[pOld[0]][pOld[1]] = new Space("☐");
                    board[pCoords[0]][pCoords[1]] = player;
                    for (int i = 0; i < eCoords.size(); i++) {
                        int[] eOld = eOldCoords.get(i);
                        int[] eCoord = eCoords.get(i);
                        Shuimen enemy = enemies.get(i);
                        enemy.setCoords(eCoord);
                        enemy.setMoved(true);
                        board[eOld[0]][eOld[1]] = new Space("☐");
                        board[eCoord[0]][eCoord[1]] = enemy;
                    }
                }
            } else {
                for (int i = 0; i < eOldCoords.size(); i++) {
                    enemies.get(i).setCoords(eOldCoords.get(i));
                    enemies.get(i).setMoved(false);
                }
                for (int i = 0; i < board.length; i++) {
                    for (int j = 0; j < board[0].length; j++) {
                        if (board[i][j] instanceof Arrow) {
                            board[i][j] = new Space("☐");
                        }
                    }
                }
            }
        }
        if (room != 21) {
            System.out.println("\"Noo, get away from me!\"");
            sleep(750);
            System.out.println("""
                    \033[3mThe Shuimen walks to the trembling robot and raises a fist." +
                    "The robot could only scream as its circuits fry upon contact with the Shuimen's watery attacks.\033[0m""");
            sleep(1500);
        } else {
            System.out.println("\"The last door! I'm almost out of here!\"");
            sleep(750);
            System.out.println("""
                    \033[3mThe robot runs to the door, panting as it feels it battery running low.
                    It frantically jiggles the knob and the door gives way to light. In front of it is a charging port and a bed.
                    The door closes behind the robot as it climbs onto the bed and plugs itself in.\033[0m""");
            sleep(1500);
        }
    }

    private void end() {
        System.out.println("Finally, this robot can rest...");
        sleep(750);
        for (int i = 0; i < 3; i++) {
            System.out.print("Z");
            sleep(750);
        }
        System.out.println("\nFinal Score: " + player.getScore());
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
