package rubik.model;

import java.util.HashMap;

import rubik.model.Color;
import rubik.model.Neighbor;

public class Face {
	
	private Color color;
	private HashMap<Neighbor, Color> adjacent;
	

	public Face(Color color) {
		this.color = color;
		
//		switch (color) {
//			case BLUE:
//				adjacent.put(Neighbor.UP, Color.YELLOW);
//				adjacent.put(Neighbor.DOWN, Color.WHITE);
//				adjacent.put(Neighbor.LEFT, Color.ORANGE);
//				adjacent.put(Neighbor.RIGHT, Color.RED);
//				adjacent.put(Neighbor.OPPOSITE, Color.GREEN);
//				break;
//			case GREEN:
//				adjacent.put(Neighbor.UP, Color.WHITE);
//				adjacent.put(Neighbor.DOWN, Color.YELLOW);
//				adjacent.put(Neighbor.LEFT, Color.ORANGE);
//				adjacent.put(Neighbor.RIGHT, Color.RED);
//				adjacent.put(Neighbor.OPPOSITE, Color.BLUE);
//				break;
//			case ORANGE:
//				adjacent.put(Neighbor.UP, Color.GREEN);
//				adjacent.put(Neighbor.DOWN, Color.BLUE);
//				adjacent.put(Neighbor.LEFT, Color.WHITE);
//				adjacent.put(Neighbor.RIGHT, Color.YELLOW);
//				adjacent.put(Neighbor.OPPOSITE, Color.RED);
//				break;
//			case RED:
//				adjacent.put(Neighbor.UP, Color.GREEN);
//				adjacent.put(Neighbor.DOWN, Color.BLUE);
//				adjacent.put(Neighbor.LEFT, Color.YELLOW);
//				adjacent.put(Neighbor.RIGHT, Color.WHITE);
//				adjacent.put(Neighbor.OPPOSITE, Color.ORANGE);
//				break;
//			case WHITE:
//				adjacent.put(Neighbor.UP, Color.GREEN);
//				adjacent.put(Neighbor.DOWN, Color.BLUE);
//				adjacent.put(Neighbor.LEFT, Color.RED);
//				adjacent.put(Neighbor.RIGHT, Color.ORANGE);
//				adjacent.put(Neighbor.OPPOSITE, Color.YELLOW);
//				break;
//			case YELLOW:
//				adjacent.put(Neighbor.UP, Color.GREEN);
//				adjacent.put(Neighbor.DOWN, Color.BLUE);
//				adjacent.put(Neighbor.LEFT, Color.ORANGE);
//				adjacent.put(Neighbor.RIGHT, Color.RED);
//				adjacent.put(Neighbor.OPPOSITE, Color.WHITE);
//				break;
//			default:
//				break;
//		}
	}


	public Color getColor() {
		return color;
	}
	
	public Color getAdjacent(Neighbor ng) {
		return adjacent.get(ng);
		
	}

}
