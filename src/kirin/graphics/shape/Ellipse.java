package kirin.graphics.shape;

import javafx.scene.Node;
import javafx.scene.paint.Color;

public class Ellipse extends Shape {
  
	/**
	 * 
	 * @param x
	 * @param y
	 * @param size
	 */
	public Ellipse(double x, double y, double size) {
		super(x, y, size, size);
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param height
	 * @param width
	 */
	public Ellipse(double x, double y, double height, double width) {
		super(x, y, width, height, Color.BLACK);
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param height
	 * @param width
	 * @param fill
	 */
	public Ellipse(double x, double y, double height, double width, Color fill) {
		super(x, y, width, height, fill);
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param height
	 * @param width
	 * @param fill
	 * @param stroke
	 */
	public Ellipse(double x, double y, double height, double width, Color fill, Color stroke) {
		super(x, y, width, height, fill, stroke);
	}

	@Override
	public Node getUnderlayingNode() {
		// TODO Auto-generated method stub
		return null;
	}
}
