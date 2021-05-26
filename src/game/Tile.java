package game;

public class Tile {
    private int tileValue;

    private boolean isMine;
    private boolean isUncovered;
    private boolean isMarked;
    private char character;


    public int getTileValue() {
        return tileValue;
    }

    public char getCharacter() {
        return character;
    }

    public boolean isTileBomb() {
        return isMine;
    }

    public boolean isTileUncovered() {
        return isUncovered;
    }

    public boolean isTileMarked() {
        return isMarked;
    }


    public void setBomb(boolean isMine) {
        this.isMine = isMine;
    }

    public void setValue(int tileValue) {
        this.tileValue = tileValue;
    }

    public void setUncovered(boolean isUncovered) {
        this.isUncovered = isUncovered;
    }

    public void setMarked(boolean isMarked) {
        this.isMarked = isMarked;
    }

    public void setCharacter(char character) {
        this.character = character;
    }
}
