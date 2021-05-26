package difficulty;

public abstract class Difficulty {
	protected int sizeX;
	protected int sizeY;
	
	protected int mines;
	
	public int getMines() {
		return mines;
	}
	
	public int getSizeX() {
		return sizeX;
	}
	
	public int getSizeY() {
		return sizeY;
	}
}

