package kirin.graphics.shapes;

import java.awt.Color;

import kirin.graphics.Canvas;
import kirin.graphics.render.EditableEntity;
import kirin.graphics.render.RenderableEntity;
import kirin.utils.math.Direction;

public class Arc implements RenderableEntity, EditableEntity {
	
	protected static final Canvas CANVAS = Canvas.getInstance();
	
	protected double x, y, width, height, angle;
	protected Direction direction;
	protected Color color1;
	protected boolean stroked;
	
	public Arc(double x, double y, double width, double height, Color color, Direction direction, double angle, boolean stroked) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.direction = direction;
		this.angle = angle;
		this.color1 = color;
		this.stroked = stroked;
	}
	
	public double getX() {
		return this.x;
	}
	  
	public void setX(double x) {
		this.x = x;
	}
	
	public double getY() {
		return this.y;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	  
	public double getWidth() {
		return this.width;
	}
	
	public void setWidth(double width) {
		this.width = width;
	}
	  
	public double getHeight() {
		return this.height;
	}
	
	public void setHeight(double height) {
		this.height = height;
	}
	
	public void moveX(double dx) {
		this.x += dx;
	}
	
	public void moveY(double dy) {
		this.y += dy;
	}
	
	public Color getColor() {
		return this.color1;
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
	
	public Arc draw() {
		CANVAS.fill(this);
		
		if (this.stroked) {
			CANVAS.stroke(this);
		}
	
		return this;
	}
}
