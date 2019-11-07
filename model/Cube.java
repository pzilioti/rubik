package rubik.model;

public abstract class Cube implements Movable {

	// Dimensions of cube
	private int x;	

	public Cube() {
	}

	public Cube(int x) {
		this.x = x;
		
	}

	@Override
	public String toString() {
		return x + "x" + x + "x" + x +" rubik's cube";
	}

	public int getX() {
		return x;
	}
	
	public abstract void fillOneColor(Color color, int position, int piece);
	
	public abstract boolean isSolved();

}
