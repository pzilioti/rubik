package rubik.model;

public interface Movable {
	//interface for the movements of the cube
	
	void dTurn(boolean clockwise);
	void fTurn(boolean clockwise);
	void bTurn(boolean clockwise);
	void rTurn(boolean clockwise);
	void lTurn(boolean clockwise);
	void uTurn(boolean clockwise);

}
