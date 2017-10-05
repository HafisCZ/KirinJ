package kirin.graphics.shape;

import java.awt.Color;

import kirin.util.math.Direction;

public class Arc extends Shape {
	
	protected double angle;
	protected Direction direction;
	
	public Arc(double x, double y, double width, double height, Color color, Direction direction, double angle, boolean stroked) {
		super(x, y, width, height, color, Color.BLACK);
		this.angle = angle;
		this.direction = direction;
		if (stroked) {
			this.strokeColor = Color.BLACK;
			this.hasStroke = true;
		}
	}
	
	public Direction getDirection() {
		return this.direction;
	}
	
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	public double getAngleStart() {
		return this.direction.getAngle() - (angle / 2);
	}
	  
	public double getAngleExtent() {
		return angle;
	}
}
