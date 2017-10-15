package kirin.graphics.shape;

import javafx.scene.Node;
import javafx.scene.paint.Color;

public class Rectangle extends Shape {

	/**
	 * 
	 * @param x
	 * @param y
	 * @param size
	 */
	public Rectangle(double x, double y, double size) {
		super(x, y, size, size);
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Rectangle(double x, double y, double width, double height) {
		super(x, y, width, height, Color.BLACK);
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param fill
	 */
	public Rectangle(double x, double y, double width, double height, Color fill) {
		super(x, y, width, height, fill);
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param fill
	 * @param stroke
	 */
	public Rectangle(double x, double y, double width, double height, Color fill, Color stroke) {
		super(x, y, width, height, fill, stroke);
	}

	@Override
	public Node getUnderlayingNode() {
		javafx.scene.shape.Rectangle rectangle = new javafx.scene.shape.Rectangle(this.x, this.y, this.width, this.height);
		if (this.hasFill) {
			rectangle.setFill(this.fillColor);
		}
		
		if (this.hasStroke) {
			rectangle.setStroke(this.strokeColor);
		}
		
		return rectangle;
	}
}
