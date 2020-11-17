package rubik.test;

import rubik.model.*;
import rubik.model.implementation.ArrayCube;
import rubik.solver.Solver;
import rubik.solver.implementation.BacktrackingSolver;

public class CubeTest {

	public static void main(String[] args) {
		Cube cube = new ArrayCube(3);
		Solver solver = new BacktrackingSolver();
		
		String movements = solver.scramble(cube, 10);
		
		System.out.println(cube);
		
		System.out.println(movements);
		
		String solution = solver.solve(cube, 6);
		
		System.out.println(cube);
		
		System.out.println(solution);
		
		
		
		
		
//		cube.fillOneColor(Color.BLUE, 0, 0);
//		cube.fillOneColor(Color.YELLOW, 0, 1);
//		cube.fillOneColor(Color.YELLOW, 0, 2);
//		cube.fillOneColor(Color.ORANGE, 0, 3);
//		cube.fillOneColor(Color.RED, 0, 4);
//		cube.fillOneColor(Color.ORANGE, 0, 5);
//		cube.fillOneColor(Color.YELLOW, 0, 6);
//		cube.fillOneColor(Color.WHITE, 0, 7);
//		cube.fillOneColor(Color.GREEN, 0, 8);
//		cube.fillOneColor(Color.WHITE, 0, 9);
//		cube.fillOneColor(Color.WHITE, 0, 10);
//		cube.fillOneColor(Color.WHITE, 0, 11);
//		cube.fillOneColor(Color.GREEN, 0, 12);
//		cube.fillOneColor(Color.WHITE, 0, 13);
//		cube.fillOneColor(Color.BLUE, 0, 14);
//		cube.fillOneColor(Color.WHITE, 0, 15);
		
//		Neighbor.init(4, 12);
//		
//		Integer[] side = Neighbor.getPhase("UP");
//		for (Integer i : side) {
//			System.out.print(i);
//		}
//		System.out.println();
//		side = Neighbor.getPhase("RIGHT");
//		for (Integer i : side) {
//			System.out.print(i);
//		}
//		System.out.println();
//		side = Neighbor.getPhase("DOWN");
//		for (Integer i : side) {
//			System.out.print(i);
//		}
//		System.out.println();
//		side = Neighbor.getPhase("LEFT");
//		for (Integer i : side) {
//			System.out.print(i);
//		}

	}

}
