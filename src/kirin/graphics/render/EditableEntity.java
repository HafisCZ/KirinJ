package kirin.graphics.render;

/**
 * Interface for objects with editable position / dimensions
 */
public interface EditableEntity {

	/**
	 * Place entity at
	 * @param x New x
	 */
	public void setX(double x);
	
	/**
	 * Place entity at
	 * @param y New y
	 */
	public void setY(double y);
	
	/**
	 * Place entity at
	 * @param x New x
	 * @param y New y
	 */
	public default void setPosition(double x, double y) {
		setX(x);
		setY(y);
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
	 * Move entity
	 * @param dx Change of x
	 */
	public void moveX(double dx);
	
	/**
	 * Move entity
	 * @param dy Change of y
	 */
	public void moveY(double dy);
	
	/**
	 * Move entity
	 * @param dx Change of x
	 * @param dy Change of y
	 */
	public default void move(double dx, double dy) {
		moveX(dx);
		moveY(dy);
	}
	
	/**
	 * Set width of entity
	 * @param width New width
	 */
	public void setWidth(double width);
	
	/**
	 * Set height of entity
	 * @param height New height
	 */
	public void setHeight(double height);
	
	/**
	 * Set dimensions of entity
	 * @param width New width
	 * @param height New width
	 */
	public default void setDimensions(double width, double height) {
		setWidth(width);
		setHeight(height);
	}
	
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
