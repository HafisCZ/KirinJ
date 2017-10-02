package graphics.shapes;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import graphics.render.Canvas;
import graphics.render.MoveableEntity;
import graphics.render.RenderableEntity;
import utils.Direction8;

public class Arc implements RenderableEntity, MoveableEntity {
	
	protected static final Canvas CANVAS = Canvas.getInstance();
	
	protected double x, y, width, height, angle;
	protected Direction8 direction;
	protected Color color1;
	protected boolean stroked;
	  
	protected static Map<Direction8, Double> directionMap = new HashMap<Direction8, Double>();
	static {
	  directionMap.put(Direction8.EAST, (double) 0);
	  directionMap.put(Direction8.NORTHEAST, (double) 45);
	  directionMap.put(Direction8.NORTH, (double) 90);
	  directionMap.put(Direction8.NORTHWEST, (double) 135);
	  directionMap.put(Direction8.WEST, (double) 180);
	  directionMap.put(Direction8.SOUTHWEST, (double) 225);
	  directionMap.put(Direction8.SOUTH, (double) 270);
	  directionMap.put(Direction8.SOUTHEAST, (double) 315);
	}
	
	public Arc(double x, double y, double width, double height, Color color, Direction8 direction, double angle, boolean stroked) {
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
	
	public Direction8 getDirection() {
		return this.direction;
	}
	
	public void setDirection(Direction8 direction) {
		this.direction = direction;
	}
	
	public double getAngleStart() {
		return directionMap.get(direction) - (angle / 2);
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
