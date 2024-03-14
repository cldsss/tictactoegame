package tictactoe.tictacgame.gam.utils;

public class Cell {

    private Seed content;


    public Cell() {
        clear();
    }


    public void clear() {
        content = Seed.EMPTY;
    }


    public void paint() {
        switch (content) {
            case CROSS -> System.out.print(" X ");
            case NOUGHT -> System.out.print(" O ");
            case EMPTY -> System.out.print("   ");
        }
    }


    public Seed getContent() {
        return content;
    }


    public void setContent(Seed content) {
        this.content = content;
    }

}

