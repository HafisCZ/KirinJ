package graphics.shapes;

import java.awt.Color;

import graphics.render.Canvas;
import graphics.render.MoveableEntity;
import graphics.render.RenderableEntity;

public class Rectangle implements RenderableEntity, MoveableEntity {
	
	protected static final Canvas CANVAS = Canvas.getInstance();

	protected Color color;
	protected double x, y, width, height;
	
	public Rectangle(double x, double y, double size) {
		this(x, y, size, size);
	}
	
	public Rectangle(double x, double y, double width, double height) {
		this(x, y, width, height, Color.BLACK);
	}
	
	public Rectangle(double x, double y, double width, double height, Color color) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
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
		return this.color;
	}
	
	public Rectangle draw() {
		CANVAS.fill(this);
		return this;
	}

}
