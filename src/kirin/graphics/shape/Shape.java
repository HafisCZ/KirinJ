package kirin.graphics.shape;

import javafx.scene.paint.Color;
import javafx.scene.Node;
import kirin.graphics.render.EditableObject;
import kirin.graphics.render.RenderableObject;

public abstract class Shape implements RenderableObject, EditableObject {

	protected double x, y, width, height;
	protected Color fillColor, strokeColor;
	protected boolean hasStroke, hasFill;
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	protected Shape(double x, double y, double width, double height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.hasFill = false;
		this.hasStroke = false;
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param fill
	 */
	protected Shape(double x, double y, double width, double height, Color fill) {
		this(x, y, width, height);
		this.fillColor = fill;
		this.hasFill = true;
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
	protected Shape(double x, double y, double width, double height, Color fill, Color stroke) {
		this(x, y, width, height, fill);
		this.strokeColor = stroke;
		this.hasStroke = true;
	}
	
	/**
	 * 
	 * @return
	 */
	public abstract Node getUnderlayingNode();
	
	/**
	 * Set fill color of shape
	 * @param color Fill color
	 */
	public void setFill(Color color) {
		this.fillColor = color;
		this.hasFill = true;
	}
	
	/**
	 * Set stroke color of shape
	 * @param color Stroke color
	 */
	public void setStroke(Color color) {
		this.strokeColor = color;
		this.hasStroke = true;
	}
	
	@Override
	public Color getFill() {
		return this.fillColor;
	}
	
	@Override
	public Color getStroke() {
		return this.strokeColor;
	}
	
	@Override
	public boolean hasFill() {
		return this.hasFill;
	}

	@Override
	public boolean hasStroke() {
		return this.hasStroke;
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
