package gbs;

import java.util.Iterator;

public class Board {
	private Tile[][] board;
	
	public Board(int sizeX, int sizeY) {
		board = new Tile[sizeX][sizeY];
		for (int row = 0; row < sizeX; row++)
		{
			for (int col = 0; col < sizeY; col++)
		    {
				Tile tile = new Tile(sizeX, sizeX, 1);
		    	board[row][col] = tile;
		    }
		}
	}
	
	
	
	public void drawBoard() {
		for (int row = 0; row < board.length; row++)
		{
			for (int col = 0; col < board[row].length ; col++)
		    {
				System.out.print(" 1 ");
		    }
			System.out.println();
		}
	}
}
