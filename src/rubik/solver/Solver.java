package rubik.solver;

import java.util.concurrent.ThreadLocalRandom;

import rubik.model.Cube;

public abstract class Solver {

	public abstract String solve(Cube cube, int maxSteps);

	public String scramble(Cube cube, int maxMovements) {
		String movements = "";

		for (int i = 0; i < maxMovements; i++) {
			int randomMovement = ThreadLocalRandom.current().nextInt(0, 5 + 1); // selects a random movement
			int randomClockwise = ThreadLocalRandom.current().nextInt(0, 1 + 1); // selects if clockwise or not

			boolean clockwise;
			if (randomClockwise == 0)
				clockwise = true;
			else
				clockwise = false;

			switch (randomMovement) {
			case 0:
				cube.uTurn(clockwise);
				movements = movements + " " + logMovement("U", clockwise);
				break;
			case 1:
				cube.bTurn(clockwise);
				movements = movements + " " + logMovement("B", clockwise);
				break;
			case 2:
				cube.rTurn(clockwise);
				movements = movements + " " + logMovement("R", clockwise);
				break;
			case 3:
				cube.fTurn(clockwise);
				movements = movements + " " + logMovement("F", clockwise);
				break;
			case 4:
				cube.lTurn(clockwise);
				movements = movements + " " + logMovement("L", clockwise);
				break;
			case 5:
				cube.dTurn(clockwise);
				movements = movements + " " + logMovement("D", clockwise);
				break;
			}
		}
		return movements;
	}
	
	public String logMovement (String mov, boolean clockwise) {
		if(clockwise) {
			return mov;
		}else {
			return mov + "'";
		}
	}

}
