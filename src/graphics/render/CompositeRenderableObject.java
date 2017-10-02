package graphics.render;

public interface CompositeRenderableObject extends RenderableObject {

	public RenderableObject[] getObjects();
	
	public default RenderableObject draw() {
		return this;
	}
	
	public default double getX() {
		return 0;
	}
	
	public default void setX(double x) {
		
	}
	
	public default double getY()  {
		return 0;
	}
	
	public default void setY(double y) {
		
	}
	
	public default double getWidth() {
		return 0;
	}
	
	public default void setWidth(double width) {
		
	}
	
	public default double getHeight() {
		return 0;
	}
	
	public default void setHeight(double height) {
		
	}
}
