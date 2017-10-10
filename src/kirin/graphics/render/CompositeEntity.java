package kirin.graphics.render;

public interface CompositeEntity extends RenderableEntity {

	/**
	 * 
	 * @return Array of render-able objects
	 */
	public RenderableEntity[] getObjects();
	
	/**
	 * Returns itself for backward compatibility
	 */
	@Deprecated
	public default RenderableEntity draw() {
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
