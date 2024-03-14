package tictactoe.tictacgame.gam.game;

import tictactoe.tictacgame.gam.players.CpuPlayer;
import tictactoe.tictacgame.gam.players.HumanPlayer;
import tictactoe.tictacgame.gam.players.Player;
import tictactoe.tictacgame.gam.utils.Board;
import tictactoe.tictacgame.gam.utils.GameMode;
import tictactoe.tictacgame.gam.utils.GameState;
import tictactoe.tictacgame.gam.utils.Seed;

import java.util.Arrays;
import java.util.Scanner;

public class Game {
    private final Board board;
    private GameMode gameMode;
    private GameState currentState;
    private Player currentPlayer;
    private Player[] players;
    private static final Scanner in = new Scanner(System.in);


    public Game() {
        this.board = Board.getBoard();
        initGame();

        do {
            currentPlayer.playerMove();
            this.board.paint();
            updateGame(currentPlayer.getSeed());
            if (currentState == GameState.CROSS_WON) {
                System.out.println("\n'X' won! Bye!");
            } else if (currentState == GameState.NOUGHT_WON) {
                System.out.println("\n'O' won! Bye!");
            } else if (currentState == GameState.DRAW) {
                System.out.println("\nIt's Draw! Bye!");
            }

            currentPlayer = (currentPlayer.getSeed() == Seed.CROSS)
                    ?
                    Arrays.stream(players)
                            .filter(player -> player.getSeed() == Seed.NOUGHT)
                            .findFirst()
                            .get()
                    :
                    Arrays.stream(players)
                            .filter(player -> player.getSeed() == Seed.CROSS)
                            .findFirst()
                            .get();
        } while (currentState == GameState.PLAYING);
    }


    public void initGame() {
        setGameMode();
        initPlayers();
        currentState = GameState.PLAYING;
        board.init();
        board.paint();
    }


    private void initPlayers() {
        if (this.gameMode == GameMode.PVP) {
            players = new Player[]{new HumanPlayer(board, Seed.CROSS), new HumanPlayer(board, Seed.NOUGHT)};
            currentPlayer = players[0];
        } else {
            selectSeedType();

            selectMoveTurn();
        }
    }


    private void selectSeedType() {
        String seedType = inputValidation("Which mark will you play for?\nX - 1\nO - 2");
        if (seedType.equals("1")) {
            players = new Player[]{new HumanPlayer(board, Seed.CROSS), new CpuPlayer(board, Seed.NOUGHT)};
        } else {
            players = new Player[]{new HumanPlayer(board, Seed.NOUGHT), new CpuPlayer(board, Seed.CROSS)};
        }
    }


    private String inputValidation(String output) {
        System.out.println(output);
        do {
            String input = in.nextLine();
            if (input.matches("[1-2]")) {
                return input;
            } else {
                System.err.println("This input is not valid");
            }
        } while (true);
    }


    private void selectMoveTurn() {
        String turn = inputValidation("Who will move first?\nAI - 1\nMe - 2");
        if (turn.equals("1")) {
            currentPlayer = players[1];
        } else {
            currentPlayer = players[0];
        }
    }


    private void setGameMode() {
        String gameMode = inputValidation("Select game mode:\nPVP - 1\nPVE - 2\n");
        if (gameMode.equals("1")) {
            this.gameMode = GameMode.PVP;
        } else {
            this.gameMode = GameMode.PVE;
        }
    }


    public void updateGame(Seed theSeed) {
        if (board.hasWon(theSeed)) {
            currentState = (theSeed == Seed.CROSS) ? GameState.CROSS_WON : GameState.NOUGHT_WON;
        } else if (board.isDraw()) {
            currentState = GameState.DRAW;
        }
    }
}