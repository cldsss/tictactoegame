package tictactoe.tictacgame.gam.players;

import tictactoe.tictacgame.gam.utils.Board;
import tictactoe.tictacgame.gam.utils.Seed;

public abstract class Player {
    private final Board board;
    private final Seed seed;

    public Player(Board board, Seed seed) {
        this.board = board;
        this.seed = seed;
    }


    public void playerMove() {
        int[] rowAndCol = move();
        int row = rowAndCol[0];
        int col = rowAndCol[1];
        board.placeSeed(row, col, seed);

    }


    public Seed getSeed() {
        return seed;
    }


    public Board getBoard() {
        return board;
    }


    protected abstract int[] move();
}
