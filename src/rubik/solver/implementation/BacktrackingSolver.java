package rubik.solver.implementation;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;

import rubik.model.Cube;
import rubik.model.implementation.ArrayCube;
import rubik.solver.Solver;

public class BacktrackingSolver extends Solver {
	
	private int maxSteps;
	
	
	public String solve(Cube cube, int maxSteps) {
		this.maxSteps = maxSteps;
		long startTime = System.currentTimeMillis();

		Cube cube1 = new ArrayCube(3);
		cube1.randomCube(cube.transferState());
		cube1.uTurn(true);
		String movements1 = logMovement("U", true);

		Cube cube2 = new ArrayCube(3);
		cube2.randomCube(cube.transferState());
		cube2.dTurn(true);
		String movements2 = logMovement("D", true);

		Cube cube3 = new ArrayCube(3);
		cube3.randomCube(cube.transferState());
		cube3.rTurn(true);
		String movements3 = logMovement("R", true);

		Cube cube4 = new ArrayCube(3);
		cube4.randomCube(cube.transferState());
		cube4.lTurn(true);
		String movements4 = logMovement("L", true);

		Cube cube5 = new ArrayCube(3);
		cube5.randomCube(cube.transferState());
		cube5.fTurn(true);
		String movements5 = logMovement("F", true);

		Cube cube6 = new ArrayCube(3);
		cube6.randomCube(cube.transferState());
		cube6.bTurn(true);
		String movements6 = logMovement("B", true);

		Cube cube7 = new ArrayCube(3);
		cube7.randomCube(cube.transferState());
		cube7.uTurn(false);
		String movements7 = logMovement("U", false);

		Cube cube8 = new ArrayCube(3);
		cube8.randomCube(cube.transferState());
		cube8.dTurn(false);
		String movements8 = logMovement("D", false);

		Cube cube9 = new ArrayCube(3);
		cube9.randomCube(cube.transferState());
		cube9.rTurn(false);
		String movements9 = logMovement("R", false);

		Cube cube10 = new ArrayCube(3);
		cube10.randomCube(cube.transferState());
		cube10.lTurn(false);
		String movements10 = logMovement("L", false);

		Cube cube11 = new ArrayCube(3);
		cube11.randomCube(cube.transferState());
		cube11.fTurn(false);
		String movements11 = logMovement("F", false);

		Cube cube12 = new ArrayCube(3);
		cube12.randomCube(cube.transferState());
		cube12.bTurn(false);
		String movements12 = logMovement("B", false);


		//runs in parallel with 12 different starts.
		CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> solveAux(cube1, movements1, 1));
		CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> solveAux(cube2, movements2, 1));
		CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> solveAux(cube3, movements3, 1));
		CompletableFuture<String> future4 = CompletableFuture.supplyAsync(() -> solveAux(cube4, movements4, 1));
		CompletableFuture<String> future5 = CompletableFuture.supplyAsync(() -> solveAux(cube5, movements5, 1));
		CompletableFuture<String> future6 = CompletableFuture.supplyAsync(() -> solveAux(cube6, movements6, 1));
		CompletableFuture<String> future7 = CompletableFuture.supplyAsync(() -> solveAux(cube7, movements7, 1));
		CompletableFuture<String> future8 = CompletableFuture.supplyAsync(() -> solveAux(cube8, movements8, 1));
		CompletableFuture<String> future9 = CompletableFuture.supplyAsync(() -> solveAux(cube9, movements9, 1));
		CompletableFuture<String> future10 = CompletableFuture.supplyAsync(() -> solveAux(cube10, movements10, 1));
		CompletableFuture<String> future11 = CompletableFuture.supplyAsync(() -> solveAux(cube11, movements11, 1));
		CompletableFuture<String> future12 = CompletableFuture.supplyAsync(() -> solveAux(cube12, movements12, 1));

		CompletableFuture<Object> future = CompletableFuture.anyOf(future1, future2, future3, future4, future5, future6, future7, future8, future9, future10, future11, future12);

		String result = "";
		try {
			result = (String) future.get();
		} catch (InterruptedException  | ExecutionException e) {
			// Handle
		}

		long endTime = System.currentTimeMillis();
		System.out.println("That took " + (endTime - startTime) + " milliseconds");

		return result;

	}



	
	private String solveAux (Cube cube, String movements, int steps) {
		if (cube.isSolved()) return movements;
		if(steps >= maxSteps) {
			//System.out.println(movements);
			return movements;
		}
		
		String newMovement;

		String skipURegex1 = ".*U'[D|D']*$";
		String skipURegex2 = ".*U[D|D']*U[D|D']*$";
		boolean skipU = movements.matches(skipURegex1) || movements.matches(skipURegex2);

		String skipDRegex1 = ".*D'[U|U']*$";
		String skipDRegex2 = ".*D[U|U']*D[U|U']*$";
		boolean skipD = movements.matches(skipDRegex1) || movements.matches(skipDRegex2);

		String skipRRegex1 = ".*R'[L|L']*$";
		String skipRRegex2 = ".*R[L|L']*R[L|L']*$";
		boolean skipR = movements.matches(skipRRegex1) || movements.matches(skipRRegex2);

		String skipLRegex1 = ".*L'[R|R']*$";
		String skipLRegex2 = ".*L[R|R']*L[R|R']*$";
		boolean skipL = movements.matches(skipLRegex1) || movements.matches(skipLRegex2);

		String skipFRegex1 = ".*F'[B|B']*$";
		String skipFRegex2 = ".*F[B|B']*F[B|B']*$";
		boolean skipF = movements.matches(skipFRegex1) || movements.matches(skipFRegex2);

		String skipBRegex1 = ".*B'[F|F']*$";
		String skipBRegex2 = ".*B[F|F']*B[F|F']*$";
		boolean skipB = movements.matches(skipBRegex1) || movements.matches(skipBRegex2);


		String skipUPrimeRegex1 = ".*U[D|D']*$";
		String skipUPrimeRegex2 = ".*U'[D|D']*U'[D|D']*$";
		boolean skipUPrime = movements.matches(skipUPrimeRegex1) || movements.matches(skipUPrimeRegex2);

		String skipDPrimeRegex1 = ".*D[U|U']*$";
		String skipDPrimeRegex2 = ".*D'[U|U']*D'[U|U']*$";
		boolean skipDPrime = movements.matches(skipDPrimeRegex1) || movements.matches(skipDPrimeRegex2);

		String skipRPrimeRegex1 = ".*R[L|L']*$";
		String skipRPrimeRegex2 = ".*R'[L|L']*R'[L|L']*$";
		boolean skipRPrime = movements.matches(skipRPrimeRegex1) || movements.matches(skipRPrimeRegex2);

		String skipLPrimeRegex1 = ".*L[R|R']*$";
		String skipLPrimeRegex2 = ".*L'[R|R']*L'[R|R']*$";
		boolean skipLPrime = movements.matches(skipLPrimeRegex1) || movements.matches(skipLPrimeRegex2);

		String skipFPrimeRegex1 = ".*F[B|B']*$";
		String skipFPrimeRegex2 = ".*F'[B|B']*F'[B|B']*$";
		boolean skipFPrime = movements.matches(skipFPrimeRegex1) || movements.matches(skipFPrimeRegex2);

		String skipBPrimeRegex1 = ".*B[F|F']*$";
		String skipBPrimeRegex2 = ".*B'[F|F']*B'[F|F']*$";
		boolean skipBPrime = movements.matches(skipBPrimeRegex1) || movements.matches(skipBPrimeRegex2);


		//try a U turn, skips if the last movement were a U' or UU
		if(!skipU){
			cube.uTurn(true);
			newMovement = movements + logMovement("U", true);
			newMovement = solveAux(cube, newMovement, ++steps);
			if (cube.isSolved()) return newMovement;
			cube.uTurn(false);
			newMovement = movements;
			--steps;
		}


		//try a D turn, skips if the last movement were a D'
		if(!skipD) {
			cube.dTurn(true);
			newMovement = movements + logMovement("D", true);
			newMovement = solveAux(cube, newMovement, ++steps);
			if (cube.isSolved()) return newMovement;
			cube.dTurn(false);
			newMovement = movements;
			--steps;
		}

		//try a R turn, skips if the last movement were a R'
		if(!skipR) {
			cube.rTurn(true);
			newMovement = movements + logMovement("R", true);
			newMovement = solveAux(cube, newMovement, ++steps);
			if (cube.isSolved()) return newMovement;
			cube.rTurn(false);
			newMovement = movements;
			--steps;
		}

		//try a L turn, skips if the last movement were a L'
		if(!skipL) {
			cube.lTurn(true);
			newMovement = movements + logMovement("L", true);
			newMovement = solveAux(cube, newMovement, ++steps);
			if (cube.isSolved()) return newMovement;
			cube.lTurn(false);
			newMovement = movements;
			--steps;
		}

		//try a F turn, skips if the last movement were a F'
		if(!skipF) {
			cube.fTurn(true);
			newMovement = movements + logMovement("F", true);
			newMovement = solveAux(cube, newMovement, ++steps);
			if (cube.isSolved()) return newMovement;
			cube.fTurn(false);
			newMovement = movements;
			--steps;
		}

		//try a B turn, skips if the last movement were a B'
		if(!skipB) {
			cube.bTurn(true);
			newMovement = movements + logMovement("B", true);
			newMovement = solveAux(cube, newMovement, ++steps);
			if (cube.isSolved()) return newMovement;
			cube.bTurn(false);
			newMovement = movements;
			--steps;
		}

		//try a U' turn, skips if the last movement were a U
		if(!skipUPrime) {
			cube.uTurn(false);
			newMovement = movements + logMovement("U", false);
			newMovement = solveAux(cube, newMovement, ++steps);
			if (cube.isSolved()) return newMovement;
			cube.uTurn(true);
			newMovement = movements;
			--steps;
		}
		
		//try a D' turn, skips if the last movement were a D
		if(!skipDPrime) {
			cube.dTurn(false);
			newMovement = movements + logMovement("D", false);
			newMovement = solveAux(cube, newMovement, ++steps);
			if (cube.isSolved()) return newMovement;
			cube.dTurn(true);
			newMovement = movements;
			--steps;
		}
		
		//try a R' turn, skips if the last movement were a R
		if(!skipRPrime) {
			cube.rTurn(false);
			newMovement = movements + logMovement("R", false);
			newMovement = solveAux(cube, newMovement, ++steps);
			if (cube.isSolved()) return newMovement;
			cube.rTurn(true);
			newMovement = movements;
			--steps;
		}
		
		//try a L' turn, skips if the last movement were a L
		if(!skipLPrime) {
			cube.lTurn(false);
			newMovement = movements + logMovement("L", false);
			newMovement = solveAux(cube, newMovement, ++steps);
			if (cube.isSolved()) return newMovement;
			cube.lTurn(true);
			newMovement = movements;
			--steps;
		}
		
		//try a F' turn, skips if the last movement were a F
		if(!skipFPrime) {
			cube.fTurn(false);
			newMovement = movements + logMovement("F", false);
			newMovement = solveAux(cube, newMovement, ++steps);
			if (cube.isSolved()) return newMovement;
			cube.fTurn(true);
			newMovement = movements;
			--steps;
		}
		
		//try a B' turn, skips if the last movement were a B
		if(!skipBPrime) {
			cube.bTurn(false);
			newMovement = movements + logMovement("B", false);
			newMovement = solveAux(cube, newMovement, ++steps);
			if (cube.isSolved()) return newMovement;
			cube.bTurn(true);
			newMovement = movements;
			--steps;
		}
		
		return movements;
	}


	private String getLastMov (String movements){
		if(movements.length() == 0) return "";

		String lastMov = String.valueOf(movements.charAt(movements.length()-1));
		if(lastMov.equals("'")){
			lastMov = String.valueOf(movements.charAt(movements.length()-2)) + lastMov;
		}
		return lastMov;
	}

	private String getLastTwoMov (String movements){
		String lastMov = getLastMov(movements);
		lastMov = getLastMov(movements.substring(0, movements.lastIndexOf(lastMov))) + lastMov;

		return lastMov;
	}

	private String getLastThreeMov (String movements){
		String lastTwoMov = getLastTwoMov(movements);
		lastTwoMov = getLastMov(movements.substring(0, movements.lastIndexOf(lastTwoMov))) + lastTwoMov;
		return lastTwoMov;
	}

	private String getLastFourMov(String movements) {
		String lastMov = getLastThreeMov(movements);
		lastMov = getLastMov(movements.substring(0, movements.lastIndexOf(lastMov))) + lastMov;
		return lastMov;
	}

}
