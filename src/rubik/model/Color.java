package rubik.model;

public enum Color {
	//represents the possible colors of every face of the cube
	YELLOW('Y'), GREEN('G'), BLUE('B'), ORANGE('O'), RED('R'), WHITE('W');

	private final char letter;

	Color(char letter) {
		this.letter = letter;
	}

	public char getLetter() {
		return letter;
	}
}
