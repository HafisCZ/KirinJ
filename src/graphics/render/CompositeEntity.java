package graphics.render;

public interface CompositeEntity extends RenderableEntity {

	public RenderableEntity[] getObjects();
	
	public default RenderableEntity draw() {
		return this;
	}
	
	public default double getX() {
		return 0;
	}
	
	public default double getY()  {
		return 0;
	}
	
	public default double getWidth() {
		return 0;
	}
	
	public default double getHeight() {
		return 0;
	}
}
