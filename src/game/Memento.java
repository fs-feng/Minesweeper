package game;

public class Memento {
    private Tile[][] board;

    public Memento(Tile[][] board) {
        this.board = board;
    }


    public Memento save() {
        return new Memento(board);
    }

    public Tile[][] getMemento() {
        return board;
    }
}
