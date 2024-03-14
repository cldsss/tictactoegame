package tictactoe.tictacgame.gam.utils;

public class Board {
    private static final int ROWS = 3;
    private static final int COLS = 3;
    private static Board board;
    private final Cell[][] cells;
    private int availableMoves = ROWS * COLS;
    private int currentRow, currentCol;

    public static Board getBoard() {
        if (board == null) {
            board = new Board();
        }
        return board;
    }


    private Board() {
        cells = new Cell[ROWS][COLS];
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                cells[row][col] = new Cell();
            }
        }
    }


    public boolean isCellEmpty(int row, int col) {
        return cells[row][col].getContent() == Seed.EMPTY;
    }


    public void placeSeed(int row, int col, Seed seed) {
        cells[row][col].setContent(seed);
        currentRow = row;
        currentCol = col;
        availableMoves--;
    }


    public boolean anyMovesAvailable() {
        return availableMoves > 0;
    }


    public void init() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                cells[row][col].clear();
            }
        }
    }


    public boolean isDraw() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (cells[row][col].getContent() == Seed.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }


    public boolean hasWon(Seed theSeed) {
        return (cells[currentRow][0].getContent() == theSeed
                && cells[currentRow][1].getContent() == theSeed
                && cells[currentRow][2].getContent() == theSeed
                ||
                cells[0][currentCol].getContent() == theSeed
                        && cells[1][currentCol].getContent() == theSeed
                        && cells[2][currentCol].getContent() == theSeed
                ||
                currentRow == currentCol
                        && cells[0][0].getContent() == theSeed
                        && cells[1][1].getContent() == theSeed
                        && cells[2][2].getContent() == theSeed
                ||
                currentRow + currentCol == 2
                        && cells[0][2].getContent() == theSeed
                        && cells[1][1].getContent() == theSeed
                        && cells[2][0].getContent() == theSeed);
    }


    public void paint() {
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                cells[row][col].paint();
                if (col < COLS - 1) System.out.print("|");
            }
            System.out.println();
            if (row < ROWS - 1) {
                System.out.println("-----------");
            }
        }
    }


    public static int getROWS() {
        return ROWS;
    }


    public static int getCOLS() {
        return COLS;
    }


    public Cell[][] getCells() {
        return cells;
    }

}