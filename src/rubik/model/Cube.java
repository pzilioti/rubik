package rubik.model;

public abstract class Cube implements Movable {

	// Dimensions of cube
	private int x;	

	public Cube() {
		//default dimension is 3
		this.x = 3;
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

	public abstract void randomCube(String r);

	public abstract String transferState();

}
