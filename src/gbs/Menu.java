package gbs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Menu {
	
	private int level;
	private Board board;
	
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	public Menu() {
		levelSelect();
		createBoard();
		board.drawBoard();
		
	}
	
	public void levelSelect() {
		System.out.println("Schwierigkeitsgrad auswaehlen: ");
		System.out.println("[1] Einfach [2] Mittel [3] Schwer");
		System.out.print("Ihre Eingabe: ");
		
		try {
			while (level != 1 || level != 2 || level != 3) {
				level = Integer.parseInt(reader.readLine());
				System.out.println(level);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void createBoard() {
		switch (level) {
		case 1:
			board = new Board(8, 8);
			break;
		case 2:
			board = new Board(16, 16);
			break;
		case 3:
			board = new Board(30, 16);
			break;
		default:
			break;
		}
		
		
	}
	
	
	
}
