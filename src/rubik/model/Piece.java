package rubik.model;

public class Piece {
	//represents one piece of the cube

	private Color color;

	public Piece(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}


	@Override
	public String toString() {
		switch (color) {
		case BLUE:
			return "B";
		case GREEN:
			return "G";
		case ORANGE:
			return "O";
		case RED:
			return "R";
		case WHITE:
			return "W";
		case YELLOW:
			return "Y";
		default:
			return "X";
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Piece)) return false;
		
		Piece piece = (Piece)obj;
		if(this.getColor() == piece.getColor()) return true;
			
		return false;
	}
}
