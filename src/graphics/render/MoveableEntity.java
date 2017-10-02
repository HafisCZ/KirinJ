package graphics.render;

public interface MoveableEntity {

	public void setX(double x);
	public void setY(double y);
	public default void setPosition(double x, double y) {
		setX(x);
		setY(y);
	}
	
	public void moveX(double dx);
	public void moveY(double dy);
	
	public void setWidth(double width);
	public void setHeight(double height);
	public default void setDimensions(double width, double height) {
		setWidth(width);
		setHeight(height);
	}
}
