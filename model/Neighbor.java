package rubik.model;

import java.util.HashMap;
import java.util.Map;

public class Neighbor {
	//represents the neighbor of each face
	//UP, DOWN, LEFT, RIGHT, OPPOSITE;
	
	private static Map<Integer, Integer[]> phase;
	
	public static void init(int size, int sizeOuterLayer) {
		phase = new HashMap<>();
		int pos = 0;
		for(int i = 0; i < 4; i++) {
			Integer[] side = new Integer[size];
			for(int j = 0; j < size; j++, pos++) {
				if(pos == sizeOuterLayer) {
					pos = 0;
				}
				side[j] = pos;
			}
			pos--;
			phase.put(i, side);
		}
	}
	
	public static Integer[] getPhase(String side) {
		switch (side) {
			case "UP":
				return phase.get(0);
			case "RIGHT":
				return phase.get(1);		
			case "DOWN":
				return phase.get(2);
			case "LEFT":
				return phase.get(3);
			default:
				return null;
		}
	}
	
}
