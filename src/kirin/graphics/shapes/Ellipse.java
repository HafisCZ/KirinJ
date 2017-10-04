package kirin.graphics.shapes;

import java.awt.Color;

import kirin.graphics.Canvas;
import kirin.graphics.render.EditableEntity;
import kirin.graphics.render.RenderableEntity;

public class Ellipse implements RenderableEntity, EditableEntity {
	
	protected static final Canvas CANVAS = Canvas.getInstance();

	protected double x, y, width, height;
	protected Color color;
  
	public Ellipse(double x, double y, double size) {
		this(x, y, size, size);
	}
	
	public Ellipse(double x, double y, double height, double width) {
		this(x, y, width, height, Color.BLACK);
	}
	
	public Ellipse(double x, double y, double height, double width, Color color) {
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

	public Ellipse draw() {
		CANVAS.fill(this);
		return this;
	}
}
