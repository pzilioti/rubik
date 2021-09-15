package rubik.model.implementation;

import rubik.model.Color;
import rubik.model.Cube;
import rubik.model.Movable;
import rubik.model.Neighbor;
import rubik.model.Piece;

public class ArrayCube extends Cube implements Movable {
	
//		1
//	5,4,0,2
//    	3	
	private Piece[][] cube;
	
	private int neighbor[][] = {{1,2,3,4}, {5,2,0,4}, {1,5,3,0}, {0,2,5,4}, {1,0,3,5}, {1,4,3,2}};
	private String sideNeighbor[][] = {{"DOWN","LEFT","UP","RIGHT"},
										{"UP","UP","UP","UP"},
										{"RIGHT","LEFT","RIGHT","RIGHT"},
										{"DOWN","DOWN","DOWN","DOWN"},
										{"LEFT","LEFT","LEFT","RIGHT"},
										{"UP","LEFT","DOWN","RIGHT"}};

	
	public ArrayCube (int x) {
		super(x);
		cube = new Piece[6][x*x];
		
		fillColor(Color.WHITE, 0);
		fillColor(Color.RED, 1);
		fillColor(Color.GREEN, 2);
		fillColor(Color.ORANGE, 3);
		fillColor(Color.BLUE, 4);
		fillColor(Color.YELLOW, 5);
		
		Neighbor.init(x, sizeOuterLayer(x));
	}

	//Gets  string representation of the state of the cube and transforms it to the Array representation
	public void randomCube(String r){
		char[] colors = r.toCharArray();
		int position = -1;
		int piece = 0;

		for(int i = 0; i<colors.length;i++){
			if(i%9 == 0){
				position++;
				piece = 0;
			}

			switch (colors[i]) {
				case 'W':
					fillOneColor(Color.WHITE, position, piece);
					break;
				case 'R':
					fillOneColor(Color.RED, position, piece);
					break;
				case 'G':
					fillOneColor(Color.GREEN, position, piece);
					break;
				case 'O':
					fillOneColor(Color.ORANGE, position, piece);
					break;
				case 'B':
					fillOneColor(Color.BLUE, position, piece);
					break;
				case 'Y':
					fillOneColor(Color.YELLOW, position, piece);
					break;
			}

			piece++;

		}


	}

	@Override
	public String transferState() {
		String representation = "";
		for(Piece[] position : cube){
			for (Piece piece : position){
				representation = representation + piece.toString();
			}
		}
		return representation;
	}


	private void fillColor (Color color, int position) {
		
		if (position >= 6) {
			//throw error
		}else {
			for (int i = 0; i < getX()*getX(); i++){
				cube[position][i] = new Piece(color);
			}
		}
	}
	
	public void fillOneColor(Color color, int position, int piece) {
		cube[position][piece].setColor(color);
	}

	
	private void turn(int position, boolean clockwise) {
		int size = getX();
		Piece[] movedLayer = new Piece[size*size];
		turnAux(position, movedLayer, size, 0, sizeOuterLayer(size)-1, clockwise);
		cube[position] = movedLayer;
		turnOtherLayer(position, clockwise);
	}
	
	private void turnAux(int position, Piece[] movedLayer, int size, int start, int end, boolean clockwise) {
		if(size == 1) {
			movedLayer[start] = cube[position][end];
			return;
		}
		if(size == 2) {
			if(clockwise) {
				movedLayer[start+1] = cube[position][start];
				movedLayer[end-1] = cube[position][start+1];
				movedLayer[end] = cube[position][end-1];
				movedLayer[start] = cube[position][end];				
			}else {
				movedLayer[end] = cube[position][start];
				movedLayer[start] = cube[position][start+1];
				movedLayer[start+1] = cube[position][end-1];
				movedLayer[end-1] = cube[position][end];
			}

			return;
		}
		
		int positionsOuterLayer = sizeOuterLayer(size) + start;
		int positionsToMove = size-1;
		if(!clockwise) {
			positionsToMove = positionsToMove * (-1);
		}
		
		
		for(int i = start ; i <= end ; i++) {
			int newPosition = i+positionsToMove;
			if(newPosition >= positionsOuterLayer) {
				newPosition = newPosition % positionsOuterLayer + start;
			}else if (newPosition < start) {
				newPosition = (end+1)+(newPosition-start);
			}
			movedLayer[newPosition] = cube[position][i];
		}
		int nextSizeOuterLayer = sizeOuterLayer(size-2);
		turnAux(position, movedLayer, size-2, end+1, end+nextSizeOuterLayer, clockwise);
	}
	
	private void turnOtherLayer(int position, boolean clockwise) {
		Piece[] aux = new Piece[getX()];
		
		Integer[] initialPhase;
		int posInitialPhase;
		if(clockwise) {
			posInitialPhase = 3;
		}else {
			posInitialPhase = 0;
		}
		initialPhase = Neighbor.getPhase(sideNeighbor[position][posInitialPhase]);	
		for(int i = 0; i < getX(); i++) {
			aux[i] = cube[neighbor[position][posInitialPhase]][initialPhase[i]];
		}
		
		if(clockwise) {
			for(int j = 0; j < 4; j++) {
				Integer[] phase = Neighbor.getPhase(sideNeighbor[position][j]);
				for(int i = 0; i < getX(); i++) {
					Piece aux2 = cube[neighbor[position][j]][phase[i]]; 
					cube[neighbor[position][j]][phase[i]] = aux[i];
					aux[i] = aux2;
				}
			}			
		}else {
			for(int j = 3; j >= 0; j--) {
				Integer[] phase = Neighbor.getPhase(sideNeighbor[position][j]);
				for(int i = 0; i < getX(); i++) {
					Piece aux2 = cube[neighbor[position][j]][phase[i]]; 
					cube[neighbor[position][j]][phase[i]] = aux[i];
					aux[i] = aux2;
				}
			}
		}
		
	}
	
	private int sizeOuterLayer(int size) {
		if (size == 1) return 1;
		if (size == 2) return 4;
		return (2*(size))+2*(size-2);
	}

	


	@Override
	public void dTurn(boolean clockwise) {
		turn(5, clockwise);		
	}

	@Override
	public void fTurn(boolean clockwise) {
		turn(3, clockwise);		
	}

	@Override
	public void bTurn(boolean clockwise) {
		turn(1, clockwise);		
	}

	@Override
	public void rTurn(boolean clockwise) {
		turn(2, clockwise);		
	}

	@Override
	public void lTurn(boolean clockwise) {
		turn(4, clockwise);		
	}

	@Override
	public void uTurn(boolean clockwise) {
		turn(0, clockwise);
	}


	@Override
	public String toString() {
		printCube();
		System.out.println();
		return super.toString() + "\n" ;
	}
	
	private void printCube() {//prints in the console a 3x3x3 or 4x4x4 cube
		if(getX() == 3) {
			System.out.print("      ");
			System.out.print(cube[1][0]);
			System.out.print(cube[1][1]);
			System.out.println(cube[1][2]);
			
			System.out.print("      ");
			System.out.print(cube[1][7]);
			System.out.print(cube[1][8]);
			System.out.println(cube[1][3]);
			
			System.out.print("      ");
			System.out.print(cube[1][6]);
			System.out.print(cube[1][5]);
			System.out.println(cube[1][4]);
			
			System.out.print(cube[5][0]);
			System.out.print(cube[5][1]);
			System.out.print(cube[5][2]);
			
			System.out.print(cube[4][0]);
			System.out.print(cube[4][1]);
			System.out.print(cube[4][2]);
			
			System.out.print(cube[0][0]);
			System.out.print(cube[0][1]);
			System.out.print(cube[0][2]);
			
			System.out.print(cube[2][0]);
			System.out.print(cube[2][1]);
			System.out.println(cube[2][2]);
			
			System.out.print(cube[5][7]);
			System.out.print(cube[5][8]);
			System.out.print(cube[5][3]);
			
			System.out.print(cube[4][7]);
			System.out.print(cube[4][8]);
			System.out.print(cube[4][3]);
			
			System.out.print(cube[0][7]);
			System.out.print(cube[0][8]);
			System.out.print(cube[0][3]);
			
			System.out.print(cube[2][7]);
			System.out.print(cube[2][8]);
			System.out.println(cube[2][3]);
			
			System.out.print(cube[5][6]);
			System.out.print(cube[5][5]);
			System.out.print(cube[5][4]);
			
			System.out.print(cube[4][6]);
			System.out.print(cube[4][5]);
			System.out.print(cube[4][4]);
			
			System.out.print(cube[0][6]);
			System.out.print(cube[0][5]);
			System.out.print(cube[0][4]);
			
			System.out.print(cube[2][6]);
			System.out.print(cube[2][5]);
			System.out.println(cube[2][4]);
			
			System.out.print("      ");
			System.out.print(cube[3][0]);
			System.out.print(cube[3][1]);
			System.out.println(cube[3][2]);
			
			System.out.print("      ");
			System.out.print(cube[3][7]);
			System.out.print(cube[3][8]);
			System.out.println(cube[3][3]);
			
			System.out.print("      ");
			System.out.print(cube[3][6]);
			System.out.print(cube[3][5]);
			System.out.println(cube[3][4]);
		}else {
			System.out.print("        ");
			System.out.print(cube[1][0]);
			System.out.print(cube[1][1]);
			System.out.print(cube[1][2]);
			System.out.println(cube[1][3]);
			
			System.out.print("        ");
			System.out.print(cube[1][11]);
			System.out.print(cube[1][12]);
			System.out.print(cube[1][13]);
			System.out.println(cube[1][4]);
			
			System.out.print("        ");
			System.out.print(cube[1][10]);
			System.out.print(cube[1][15]);
			System.out.print(cube[1][14]);
			System.out.println(cube[1][5]);
			
			System.out.print("        ");
			System.out.print(cube[1][9]);
			System.out.print(cube[1][8]);
			System.out.print(cube[1][7]);
			System.out.println(cube[1][6]);
			
			System.out.print(cube[5][0]);
			System.out.print(cube[5][1]);
			System.out.print(cube[5][2]);
			System.out.print(cube[5][3]);
			
			System.out.print(cube[4][0]);
			System.out.print(cube[4][1]);
			System.out.print(cube[4][2]);
			System.out.print(cube[4][3]);
			
			System.out.print(cube[0][0]);
			System.out.print(cube[0][1]);
			System.out.print(cube[0][2]);
			System.out.print(cube[0][3]);
			
			System.out.print(cube[2][0]);
			System.out.print(cube[2][1]);
			System.out.print(cube[2][2]);
			System.out.println(cube[2][3]);
			
			System.out.print(cube[5][11]);
			System.out.print(cube[5][12]);
			System.out.print(cube[5][13]);
			System.out.print(cube[5][4]);
			
			System.out.print(cube[4][11]);
			System.out.print(cube[4][12]);
			System.out.print(cube[4][13]);
			System.out.print(cube[4][4]);
			
			System.out.print(cube[0][11]);
			System.out.print(cube[0][12]);
			System.out.print(cube[0][13]);
			System.out.print(cube[0][4]);
			
			System.out.print(cube[2][11]);
			System.out.print(cube[2][12]);
			System.out.print(cube[2][13]);
			System.out.println(cube[2][4]);
			
			System.out.print(cube[5][10]);
			System.out.print(cube[5][15]);
			System.out.print(cube[5][14]);
			System.out.print(cube[5][5]);
			
			System.out.print(cube[4][10]);
			System.out.print(cube[4][15]);
			System.out.print(cube[4][14]);
			System.out.print(cube[4][5]);
			
			System.out.print(cube[0][10]);
			System.out.print(cube[0][15]);
			System.out.print(cube[0][14]);
			System.out.print(cube[0][5]);
			
			System.out.print(cube[2][10]);
			System.out.print(cube[2][15]);
			System.out.print(cube[2][14]);
			System.out.println(cube[2][5]);
			
			System.out.print(cube[5][9]);
			System.out.print(cube[5][8]);
			System.out.print(cube[5][7]);
			System.out.print(cube[5][6]);
			
			System.out.print(cube[4][9]);
			System.out.print(cube[4][8]);
			System.out.print(cube[4][7]);
			System.out.print(cube[4][6]);
			
			System.out.print(cube[0][9]);
			System.out.print(cube[0][8]);
			System.out.print(cube[0][7]);
			System.out.print(cube[0][6]);
			
			System.out.print(cube[2][9]);
			System.out.print(cube[2][8]);
			System.out.print(cube[2][7]);
			System.out.println(cube[2][6]);
			
			System.out.print("        ");
			System.out.print(cube[3][0]);
			System.out.print(cube[3][1]);
			System.out.print(cube[3][2]);
			System.out.println(cube[3][3]);
			
			System.out.print("        ");
			System.out.print(cube[3][11]);
			System.out.print(cube[3][12]);
			System.out.print(cube[3][13]);
			System.out.println(cube[3][4]);
			
			System.out.print("        ");
			System.out.print(cube[3][10]);
			System.out.print(cube[3][15]);
			System.out.print(cube[3][14]);
			System.out.println(cube[3][5]);
			
			System.out.print("        ");
			System.out.print(cube[3][9]);
			System.out.print(cube[3][8]);
			System.out.print(cube[3][7]);
			System.out.println(cube[3][6]);
			
		}
	}

	
	@Override
	public boolean isSolved() {
		for(int i = 0; i < 6; i++) {
			for(int j = 1; j < getX()*getX(); j++) {
				if(!cube[i][j].equals(cube[i][j-1])) return false;
			}
		}
		return true;
	}
}
