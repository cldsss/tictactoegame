package tictactoe.tictacgame.gam.players;

import tictactoe.tictacgame.gam.utils.Board;
import tictactoe.tictacgame.gam.utils.Seed;

import java.util.Scanner;

public class HumanPlayer extends Player {
    private static final Scanner in = new Scanner(System.in);

    public HumanPlayer(Board board, Seed seed) {
        super(board, seed);
    }


    @Override
    protected int[] move() {
        boolean validInput = false;
        int[] rowAndCol = new int[2];
        do {
            if (getSeed() == Seed.CROSS) {
                System.out.println("\nPlayer 'X', enter your move (row[1-3] column[1-3]): ");
            } else {
                System.out.println("\nPlayer 'O', enter your move (row[1-3] column[1-3]): ");
            }
            String rowStr = in.nextLine();
            String colStr = in.nextLine();
            if (rowStr.matches("[1-3]") && colStr.matches("[1-3]")) {
                rowAndCol[0] = Integer.parseInt(rowStr) - 1;
                rowAndCol[1] = Integer.parseInt(colStr) - 1;
                if (getBoard().getCells()[rowAndCol[0]][rowAndCol[1]].getContent() == Seed.EMPTY) {
                    validInput = true;
                } else {
                    System.err.println("This cell at (" + (rowAndCol[0] + 1) + "," + (rowAndCol[1] + 1)
                            + ") is busy. Try again...");
                }
            } else {
                System.err.println("This input is not valid");
            }
        } while (!validInput);
        return rowAndCol;
    }
}
