package kirin.graphics.shapes;

import java.awt.Color;

import kirin.graphics.Canvas;
import kirin.graphics.render.EditableEntity;
import kirin.graphics.render.RenderableEntity;
import kirin.utils.math.Direction;

public class Triangle implements RenderableEntity, EditableEntity {

	protected static final Canvas CANVAS = Canvas.getInstance();
  
	protected double x, y, width, height;
	protected Color color;
	protected Direction direction;

	public Triangle(double x, double y, double width, double height) {
		this(x, y, width, height, Color.BLACK, Direction.NORTH);
	}

	public Triangle(double x, double y, double width, double height, Color color) {
		this(x, y, width, height, color, Direction.NORTH);
	}

	public Triangle(double x, double y, double width, double height, Direction direction) {
		this(x, y, width, height, Color.BLACK, direction);
	}
	
	public Triangle(double x, double y, double width, double height, Color color, Direction direction) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
		this.direction = direction;
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public Direction getDirection() {
		return this.direction;
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
	
	public double[][] getVertices() {
		double[] xPoints = null;
	    double[] yPoints = null;

	    switch (this.direction) {
		    case EAST:
		        xPoints = new double[]{this.x, this.x + (width), this.x};
		        yPoints = new double[]{this.y, this.y + (height / 2), this.y + height};
		        break;
	
		    case NORTHEAST:
		        xPoints = new double[]{this.x, this.x + width, this.x + width};
		        yPoints = new double[]{this.y, this.y, this.y + height};
		        break;
	
		    case NORTH:
		        xPoints = new double[]{this.x, this.x + (width / 2), this.x + width};
		        yPoints = new double[]{this.y + height, this.y, this.y + height};
		        break;
	
		    case NORTHWEST:
		        xPoints = new double[]{this.x, this.x, this.x + width};
		        yPoints = new double[]{this.y + height, this.y, this.y};
		        break;
	
		    case WEST:
		        xPoints = new double[]{this.x, this.x + width, this.x + width};
		        yPoints = new double[]{this.y + (height / 2), this.y, this.y + height};
		        break;
	
		    case SOUTHWEST:
		        xPoints = new double[]{this.x, this.x, this.x + width};
		        yPoints = new double[]{this.y, this.y + height, this.y + height};
		        break;
	
		    case SOUTH:
		        xPoints = new double[]{this.x, this.x + (width / 2), this.x + width};
		        yPoints = new double[]{this.y, this.y + height, this.y,};
		        break;
	
		    case SOUTHEAST:
		        xPoints = new double[]{this.x, this.x + width, this.x + width};
		        yPoints = new double[]{this.y + height, this.y + height, this.y};
		        break;
	    }
	    
	    return new double[][]{xPoints, yPoints};
	}
	
	public Triangle draw() {
		CANVAS.fill(this);
		return this;
	}
}
