package dk.tec.maso.object;

public class Player {
	private int id;
	private int x;
	private int y;
	
	public Player(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }
    public void setX(int x) {
    	this.x = x;
    }

    public int getY() {
        return y;
    }
    public void setY(int y) {
    	this.y = y;
    }
}
