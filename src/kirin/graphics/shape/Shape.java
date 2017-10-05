package kirin.graphics.shape;

import java.awt.Color;

import kirin.graphics.render.EditableEntity;
import kirin.graphics.render.RenderableEntity;

public abstract class Shape implements RenderableEntity, EditableEntity {

	protected double x, y, width, height;
	protected Color fillColor, strokeColor;
	protected boolean hasStroke, hasFill;
	
	protected Shape(double x, double y, double width, double height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.hasFill = false;
		this.hasStroke = false;
	}
	
	protected Shape(double x, double y, double width, double height, Color fill) {
		this(x, y, width, height);
		this.fillColor = fill;
		this.hasFill = true;
	}
	
	protected Shape(double x, double y, double width, double height, Color fill, Color stroke) {
		this(x, y, width, height, fill);
		this.strokeColor = stroke;
		this.hasStroke = true;
	}
	
	public void setFill(Color color) {
		this.fillColor = color;
		
	}
	
	public void setStroke(Color color) {
		this.strokeColor = color;
	}
	
	public Color getFill() {
		return this.fillColor;
	}
	
	public Color getStroke() {
		return this.strokeColor;
	}
	
	public boolean hasFill() {
		return this.hasFill;
	}
	
	public boolean hasStroke() {
		return this.hasStroke;
	}
	
	@Override
	@Deprecated
	public Shape draw() {
		return this;
	}

	@Override
	public double getX() {
		return this.x;
	}

	@Override
	public double getY() {
		return this.y;
	}

	@Override
	public double getWidth() {
		return this.width;
	}

	@Override
	public double getHeight() {
		return this.height;
	}

	@Override
	public void setX(double x) {
		this.x = x;
	}

	@Override
	public void setY(double y) {
		this.y = y;
	}

	@Override
	public void moveX(double dx) {
		this.x += dx;
	}

	@Override
	public void moveY(double dy) {
		this.y += dy;
	}

	@Override
	public void setWidth(double width) {
		this.width = width;
	}

	@Override
	public void setHeight(double height) {
		this.height = height;
	}
}
