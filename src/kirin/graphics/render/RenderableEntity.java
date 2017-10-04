package kirin.graphics.render;

import java.awt.Color;

import kirin.graphics.Canvas;

public interface RenderableEntity {

	public static final Canvas CANVAS = Canvas.getInstance();
	
	/**
	 * Single draw call for object
	 * @return itself
	 */
	public RenderableEntity draw();
	
	/**
	 * Get fill color
	 * @return color
	 */
	public default Color getColor() {
		return Color.WHITE;
	}

	/**
	 * Get stroke color
	 * @return color
	 */
	public default Color getStrokeColor() {
		return Color.BLACK;
	}
	
	public double getX();
	public double getY();
	public double getWidth();
	public double getHeight();
}
