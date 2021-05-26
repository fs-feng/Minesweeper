package game;

import java.util.Arrays;
import java.util.Scanner;

public class GameScanner {
    private static final GameScanner gameScanner = new GameScanner();

    public static GameScanner getGameScanner() {
        return gameScanner;
    }

    Scanner scanner = new Scanner(System.in);

    public int readInt() {
        return scanner.nextInt();
    }

    public String readString() {
        return scanner.nextLine();
    }

    public String[] readCoordinates() {
        String input = scanner.nextLine();
        StringBuilder str = new StringBuilder(input);
        if (str.toString().charAt(0) == 'm') {
            str.insert(1, '-');
            str.insert(3,'-');
        } else {
            str.insert(1, '-');
        }


        String[] array = str.toString().split("-");
        return array;
    }
}
