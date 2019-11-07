package rubik.solver.implementation;

import java.util.concurrent.ThreadLocalRandom;

import rubik.model.Cube;
import rubik.solver.Solver;

public class BacktrackingSolver extends Solver {
	
	private int maxSteps;
	
	
	public String solve(Cube cube, int maxSteps) {
		this.maxSteps = maxSteps;
		String movements = "";
		int steps = 0;
		movements = solveAux(cube, movements, steps);
		
		return movements;
	}
	
	private String solveAux (Cube cube, String movements, int steps) {
		if (cube.isSolved()) return movements;
		if(steps >= maxSteps) {
//			System.out.println(movements);
			return movements;
		}
		
		String newMovement;
		
		//try a U turn
		cube.uTurn(true);
		newMovement = movements + logMovement("U", true);
		newMovement = solveAux(cube, newMovement, ++steps);
		if (cube.isSolved()) return newMovement;
		cube.uTurn(false);
		newMovement = movements;
		--steps;
		
		//try a U' turn
		cube.uTurn(false);
		newMovement = movements + logMovement("U", false);
		newMovement = solveAux(cube, newMovement, ++steps);
		if (cube.isSolved()) return newMovement;
		cube.uTurn(true);
		newMovement = movements;
		--steps;
		
		//try a D turn
		cube.dTurn(true);
		newMovement = movements + logMovement("D", true);
		newMovement = solveAux(cube, newMovement, ++steps);
		if (cube.isSolved()) return newMovement;
		cube.dTurn(false);
		newMovement = movements;
		--steps;
		
		//try a D' turn
		cube.dTurn(false);
		newMovement = movements + logMovement("D", false);
		newMovement = solveAux(cube, newMovement, ++steps);
		if (cube.isSolved()) return newMovement;
		cube.dTurn(true);
		newMovement = movements;
		--steps;
		
		//try a R turn
		cube.rTurn(true);
		newMovement = movements + logMovement("R", true);
		newMovement = solveAux(cube, newMovement, ++steps);
		if (cube.isSolved()) return newMovement;
		cube.rTurn(false);
		newMovement = movements;
		--steps;
		
		//try a R' turn
		cube.rTurn(false);
		newMovement = movements + logMovement("R", false);
		newMovement = solveAux(cube, newMovement, ++steps);
		if (cube.isSolved()) return newMovement;
		cube.rTurn(true);
		newMovement = movements;
		--steps;
		
		//try a L turn
		cube.lTurn(true);
		newMovement = movements + logMovement("L", true);
		newMovement = solveAux(cube, newMovement, ++steps);
		if (cube.isSolved()) return newMovement;
		cube.lTurn(false);
		newMovement = movements;
		--steps;
		
		//try a L' turn
		cube.lTurn(false);
		newMovement = movements + logMovement("L", false);
		newMovement = solveAux(cube, newMovement, ++steps);
		if (cube.isSolved()) return newMovement;
		cube.lTurn(true);
		newMovement = movements;
		--steps;
		
		//try a F turn
		cube.fTurn(true);
		newMovement = movements + logMovement("F", true);
		newMovement = solveAux(cube, newMovement, ++steps);
		if (cube.isSolved()) return newMovement;
		cube.fTurn(false);
		newMovement = movements;
		--steps;
		
		//try a F' turn
		cube.fTurn(false);
		newMovement = movements + logMovement("F", false);
		newMovement = solveAux(cube, newMovement, ++steps);
		if (cube.isSolved()) return newMovement;
		cube.fTurn(true);
		newMovement = movements;
		--steps;
		
		//try a B turn
		cube.bTurn(true);
		newMovement = movements + logMovement("B", true);
		newMovement = solveAux(cube, newMovement, ++steps);
		if (cube.isSolved()) return newMovement;
		cube.bTurn(false);
		newMovement = movements;
		--steps;
		
		//try a B' turn
		cube.bTurn(false);
		newMovement = movements + logMovement("B", false);
		newMovement = solveAux(cube, newMovement, ++steps);
		if (cube.isSolved()) return newMovement;
		cube.bTurn(true);
		newMovement = movements;
		--steps;
		
		return movements;
	}

	

}
