package kirin.graphics.render;

public interface CompositeObject extends RenderableObject {

	/**
	 * 
	 * @return Array of render-able objects
	 */
	public RenderableObject[] getObjects();
	
	/**
	 * Returns itself for backward compatibility
	 */
	@Deprecated
	public default RenderableObject draw() {
		return this;
	}
	
	/**
	 * Returns width of composite entity
	 */
	@Deprecated
	public default double getWidth() {
		return 0;
	}
	
	/**
	 * Return height of composite entity
	 */
	@Deprecated
	public default double getHeight() {
		return 0;
	}
}
