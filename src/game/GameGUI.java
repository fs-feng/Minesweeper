package game;

public class GameGUI {
    private int lineLength;

    public void drawBoard(Board gameBoard) {
    	Tile[][] board = gameBoard.getBoard();
        gameBoard.setCharacters();
    	lineLength = board[0].length;
        int letter = 65;
        
        

        for (int i = 0; i <= board[0].length; i++) {
            if (i == 0) {
                System.out.print("  \u2502 ");
                continue;
            }
            if (i >= 10) {
                System.out.print(i + "\u2502 ");
            } else {
                System.out.print(i + " \u2502 ");
            }
        }
        drawLine();

        for (int x = 0; x < board.length; x++) {
            System.out.print((char) letter + " \u2502 ");
            letter++;
            for(int y = 0; y < board[0].length; y++){
                System.out.print(board[x][y].getCharacter() + " \u2502 ");
            }
            drawLine();
        }

    }

    public void drawEndGame(boolean win) {
        if (win) {
            System.out.println("You Win!");
        } else {
            System.out.println("You Loose!");
        }
        System.out.println("Play again: [yes] [no] [z] to undo move");
        drawYourInput();
    }

    public void drawTurn() {
        System.out.println();
        System.out.println("[letter][index] to uncover tile  [m][letter][index] to mark tile   [z] to undo move");
        drawYourInput();
    }

    private void drawYourInput() {
        System.out.print("Your Input: ");
    }

    public void drawLevelSelect() {
        System.out.println("Choose Difficulty: [easy] [medium] [hard]");
        drawYourInput();
    }


    public void clearScreen() {
        for (int i = 0; i < 100; i++) {
            System.out.println();
        }
    }

    public void drawLine() {
        System.out.println();

        for (int i = 0; i <= lineLength * 4 + 3; i++) {
            System.out.print('\u2500');
        }
        System.out.println();
    }

}