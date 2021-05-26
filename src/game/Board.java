package game;

import difficulty.Difficulty;

import java.util.ArrayList;
import java.util.Random;

public class Board {
    private Tile[][] board;
    Difficulty difficulty;
    private int tilesToDiscover;

    public int getTilesToDiscover() {
        return tilesToDiscover;
    }

    public Tile[][] getBoard() {
        return board;
    }

    public void setBoard(Tile[][] board) {
        this.board = board;
    }
    //User Actions
    public void uncoverTile(int x, int y) {
        Tile tile = board[x][y];
        int bombCount = 0;
        if (tile.isTileMarked()) {
        	tile.setMarked(false);
        }
        if (!tile.isTileUncovered()) {
            tilesToDiscover--;
        }
        tile.setUncovered(true);
        
        ArrayList<Tile> neighbours = getNeighbors(x, y);
        for (Tile neighbor : neighbours) {
			if (neighbor.isTileBomb()) {
				bombCount++;
			}
		}
        
        if (bombCount == 0) {
        	for (int xx = -1; xx <= 1; xx++) {
                for (int yy = -1; yy <= 1; yy++) {
                	if (xx == 0 && yy == 0 ) {
                        continue;
                    }
                    try {
                    	if(!board[xx + x][yy + y].isTileBomb() && !board[xx + x][yy + y].isTileUncovered()) {
                    		uncoverTile(xx + x, yy + y);
                    	}
                    } catch (Exception e) {

                    }
                }
        	}
        	
        }
    }
    

    public void markTile(int x, int y) {
        Tile tile = board[x][y];
        if (tile.isTileMarked()) {
            tile.setMarked(false);
        } else {
            tile.setMarked(true);
        }
    }


    public void createBoard() {
        board = new Tile[difficulty.getSizeX()][difficulty.getSizeY()];
        
        for(int x = 0; x < board.length; x++){
            for(int y = 0; y < board[0].length; y++) {
                board[x][y] = new Tile();
            }
        }
    }
    
    public void setCharacters() {
    	Tile tile;
    	for(int x = 0; x < board.length; x++){
            for(int y = 0; y < board[0].length; y++) {
            	tile = board[x][y];
            	if (tile.isTileMarked()) {
            		tile.setCharacter('!');	
            	} else if (!tile.isTileUncovered()) {
            		tile.setCharacter('-');
            	} else if (tile.isTileBomb()) {
            		tile.setCharacter('*');
            	} else if (tile.isTileUncovered()) {
            		tile.setCharacter(Character.forDigit(board[x][y].getTileValue(),10));
            	}
            }
        }
    }
    
    public void uncoverAll() {
    	for(int x = 0; x < board.length; x++){
            for(int y = 0; y < board[0].length; y++) {
            	uncoverTile(x, y);
            }}
    }

    public void createMines() {
        Random random = new Random();
        for (int minesPlaced = 0; minesPlaced < difficulty.getMines(); minesPlaced++) {
            int x = random.nextInt(difficulty.getSizeX());
            int y = random.nextInt(difficulty.getSizeY());
            Tile tile = board[x][y];
            
            if (tile.isTileBomb()) {
                minesPlaced--;
            } else {
                tile.setBomb(true);
                tile.setValue(10);
            }
        }
    }

    
    public void setValues() {
        for(int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[0].length; y++) {
                int count = 0;
                ArrayList<Tile> neighbors = getNeighbors(x, y);
                for (Tile neighbor : neighbors) {
                    if (neighbor.isTileBomb()) {
                        count++;
                    }
                }
                if (!board[x][y].isTileBomb()) {
                    board[x][y].setValue(count);
                }
            }
        }
    }


   public ArrayList<Tile> getNeighbors(int x, int y) {
        ArrayList<Tile> neighbors = new ArrayList<>();
        for (int xx = -1; xx <= 1; xx++) {
            for (int yy = -1; yy <= 1; yy++) {
                if (xx == 0 && yy == 0) {
                    continue;
                }
                try {
                    neighbors.add(board[x + xx][y + yy]);
                } catch (Exception e) {
                    Tile tile = new Tile();
                    tile.setValue(20);
                    tile.setUncovered(true);
                    neighbors.add(tile);
                }

            }
        }
        return neighbors;
   }

   
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
        tilesToDiscover = this.difficulty.getSizeX() * this.difficulty.getSizeY() - this.difficulty.getMines();
    }
}
