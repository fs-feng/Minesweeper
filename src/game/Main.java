package game;

import difficulty.*;

public class Main {
    public static void main(String[] args) {
        GameGUI gameGUI = new GameGUI();
        Board board = new Board();
        Caretaker caretaker = new Caretaker();
        GameController gameController = new GameController(gameGUI, board, caretaker);
    }
}
