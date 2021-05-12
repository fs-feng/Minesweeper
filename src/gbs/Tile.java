package gbs;

public class Tile {
	private int posX;
	private int posY;
	
	private int value;
	
	public Tile(int posX, int posY, int value) {
		this.posX = posX;
		this.posY = posY;
		this.value = value;
	}
	
	public boolean isTileBomb() {
		return false;
		
	}
	
	public int getValue() {
		return value;
		
	}
	
}
