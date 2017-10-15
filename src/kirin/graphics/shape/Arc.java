package kirin.graphics.shape;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import kirin.util.Direction;

public class Arc extends Shape {
	
	protected double angle;
	protected Direction direction;
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param color
	 * @param direction
	 * @param angle
	 * @param stroked
	 */
	public Arc(double x, double y, double width, double height, Color color, Direction direction, double angle, boolean stroked) {
		super(x, y, width, height, color, Color.BLACK);
		this.angle = angle;
		this.direction = direction;
		if (stroked) {
			this.strokeColor = Color.BLACK;
			this.hasStroke = true;
		}
	}
	
	/**
	 * 
	 * @return Direction
	 */
	public Direction getDirection() {
		return this.direction;
	}
	
	/**
	 * 
	 * @param direction New direction
	 */
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	/**
	 * 
	 * @return Starting angle
	 */
	public double getAngleStart() {
		return this.direction.getAngle() - (angle / 2);
	}
	  
	/**
	 * 
	 * @return Angle
	 */
	public double getAngleExtent() {
		return angle;
	}

	@Override
	public Node getUnderlayingNode() {
		// TODO Auto-generated method stub
		return null;
	}
}
