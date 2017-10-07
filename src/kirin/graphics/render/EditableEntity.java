package kirin.graphics.render;

/**
 * Interface for objects with editable position / dimensions
 */
public interface EditableEntity {

	public void setX(double x);
	public void setY(double y);
	public default void setPosition(double x, double y) {
		setX(x);
		setY(y);
	}
	
	public void moveX(double dx);
	public void moveY(double dy);
	public default void move(double dx, double dy) {
		moveX(dx);
		moveY(dy);
	}
	
	public void setWidth(double width);
	public void setHeight(double height);
	public default void setDimensions(double width, double height) {
		setWidth(width);
		setHeight(height);
	}
}
