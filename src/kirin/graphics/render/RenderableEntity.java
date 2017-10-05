package kirin.graphics.render;

import java.awt.Color;

public interface RenderableEntity {

	/**
	 * Single draw call for object
	 * To be used with Canvas.getInstance().fill()
	 * @return itself
	 */
	@Deprecated
	public RenderableEntity draw();
	
	/**
	 * Returns true if object has fill color set
	 * @return has fill
	 */
	public default boolean hasFill() {
		return false;
	}
	
	/**
	 * Get fill color
	 * @return color
	 */
	public default Color getFill() {
		return Color.WHITE;
	}
	
	/**
	 * Returns true if object has stroke color set
	 * @return has stroke
	 */
	public default boolean hasStroke() {
		return false;
	}

	/**
	 * Get stroke color
	 * @return color
	 */
	public default Color getStroke() {
		return Color.BLACK;
	}
	
	public double getX();
	public double getY();
	public double getWidth();
	public double getHeight();
}
