package game;

import difficulty.Difficulty;
import difficulty.Easy;
import difficulty.Hard;
import difficulty.Medium;

import java.util.Scanner;

public class GameController {
    private GameGUI gameGUI;
    private Board board;
    private Caretaker caretaker;
    private Memento memento;
    private Difficulty difficulty;
    private final GameScanner scanner = GameScanner.getGameScanner();

    private boolean isDone = false;
    private boolean isWin = false;


    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public GameController(GameGUI gameGUI, Board board, Caretaker caretaker) {
        this.gameGUI = gameGUI;
        this.board = board;
        this.caretaker = caretaker;
        startGame();
    }

    public void setDifficulty(String difficulty) {
        switch (difficulty) {
            case "easy":
                this.difficulty = new Easy();
                break;
            case "medium":
                this.difficulty = new Medium();
                break;
            case "hard":
                this.difficulty = new Hard();
                break;
        }
        board.setDifficulty(this.difficulty);
    }

    public void startGame() {
        String input;
        boolean doit = true;
        setDone(false);
        do {
            gameGUI.drawLevelSelect();

            input = scanner.readString();

        } while (!input.equals("easy") && !input.equals("medium") && !input.equals("hard"));
        setDifficulty(input);
        createBoard();

        game();
    }

    public void game() {
        while (!isDone) {
            turn();
        }
    }

    public void createBoard() {
        board.createBoard();
        board.createMines();
        board.setValues();
        addMementoToCaretaker();
    }

    public void addMementoToCaretaker() {
        memento = new Memento(board.getBoard());
        caretaker.addMemento(memento);
    }

    public Tile[][] undoMove() {
        Memento mementoBoard = caretaker.getMemento();
        return mementoBoard.getMemento();
    }


    public void turn() {
        gameGUI.drawBoard(board);
        gameGUI.drawTurn();
        int x = 0;
        int y = 0;
        char letter;
        String[] input;

        do {
            input = scanner.readCoordinates();
            if(input.length == 3) {
                letter = input[1].charAt(0);
                x = (int) letter - 65;
                y = Integer.parseInt(input[2]) - 1;
            } else if (input.length == 2) {
                letter = input[0].charAt(0);
                x = (int) letter - 65;
                y = Integer.parseInt(input[1]) - 1;
            } else {
                letter = input[0].charAt(0);
            }
        } while (x > difficulty.getSizeX() || y >= difficulty.getSizeY() && Character.toString(letter).equals("z"));

            if(input.length == 3) {
                addMementoToCaretaker();
                board.markTile(x, y);

            } else if (input.length == 2 ) {
                addMementoToCaretaker();
                board.uncoverTile(x,y);
                checkEnd(x, y);
            } else {
                undoMove();
            }



    }


    public void checkEnd(int x, int y) {
        String input;
        if (board.getBoard()[x][y].isTileBomb()) {
            isWin = false;
            setDone(true);
            gameGUI.drawBoard(board);
            gameGUI.drawEndGame(isWin);
            do {
                input = scanner.readString();
            } while (!input.equals("yes") && !input.equals("no") && !input.equals("z"));
            if (input.equals("z")) {
                board.setBoard(undoMove());
                setDone(false);
            } else if (input.equals("yes")) {
                startGame();
            } else {
                System.exit(0);
            }
        } else if (board.getTilesToDiscover() == 0) {
            isWin = true;
            setDone(true);
            gameGUI.drawBoard(board);
            gameGUI.drawEndGame(isWin);
            do {
                input = scanner.readString();
            } while (!input.equals("yes") && !input.equals("no") && !input.equals("z"));
            if (input.equals("z")) {
                board.setBoard(undoMove());
                setDone(false);
            }else if (input.equals("yes")) {
                startGame();
            } else {
                System.exit(0);
            }
        }
    }

}
