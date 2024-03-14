package tictactoe.tictacgame.gam.players;

import tictactoe.tictacgame.gam.utils.Board;
import tictactoe.tictacgame.gam.utils.MiniMaxAlgorithm;
import tictactoe.tictacgame.gam.utils.Seed;

public class CpuPlayer extends Player {

    public CpuPlayer(Board board, Seed seed) {
        super(board, seed);
    }

    @Override
    protected int[] move() {
        System.out.println("\nAI plays for '" + getSeed().getMark() + "', AI made a move: ");

        return MiniMaxAlgorithm.getBestMove(Board.getBoard(), getSeed());
    }
}
