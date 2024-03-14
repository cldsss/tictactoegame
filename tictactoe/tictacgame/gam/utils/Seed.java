package tictactoe.tictacgame.gam.utils;

public enum Seed {
    CROSS('X'), EMPTY(' '), NOUGHT('O');

    private final char seed;


    Seed(char seed) {
        this.seed = seed;
    }


    public char getMark() {
        return this.seed;
    }
}
