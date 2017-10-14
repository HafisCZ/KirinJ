package kirin.graphics.render;

import java.awt.Color;

public interface RenderableObject {

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
	
	/**
	 * 
	 * @return x
	 */
	public double getX();
	
	/**
	 * 
	 * @return y
	 */
	public double getY();
	
	/**
	 * 
	 * @return width
	 */
	public double getWidth();
	
	/**
	 * 
	 * @return height
	 */
	public double getHeight();
}
