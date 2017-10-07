/**
 * @author Hiraishin Software
 * @author Martin 'mar21' Halfar
 */

package kirin.graphics;

import java.util.ArrayList;
import java.util.List;

import kirin.graphics.render.CompositeEntity;
import kirin.graphics.render.RenderableEntity;
import kirin.graphics.shape.Shape;

public class RenderHandler {

	private Canvas target = null;
	private List<RenderableEntity> batch = new ArrayList<RenderableEntity>();

	private int callCount;
	
	/**
	 * @throws IllegalArgumentException
	 */
	public RenderHandler() {
		this(Canvas.getInstance());
	}
	
	/**
	 * 
	 * @param interlayer Canvas to be used as interlayer
	 * @throws IllegalArgumentException
	 */
	public RenderHandler(Canvas interlayer) {
		this.target = interlayer;
		if (this.target == null) {
			throw new IllegalArgumentException("Invalid target");
		}
		
		flush();
		this.callCount = 0;
	}
	
	/**
	 * Returns used interlayer
	 * @return Interlayer canvas
	 */
	public Canvas getTarget() {
		return target;
	}
	
	/**
	 * Adds object to render queue
	 * @param object RenderableEntity
	 */
	public void add(RenderableEntity object) {
		batch.add(object);
	}
	
	/**
	 * Removes object from queue
	 * @param object RenderableEntity
	 */
	public boolean remove(RenderableEntity object) {
		return batch.remove(object);
	}
	
	
	/**
	 * Removes object at index from queue
	 * @param object Index of RenderableEntity
	 */
	public RenderableEntity remove(int index) {
		return batch.remove(index);
	}
	
	/**
	 * Get most recent count of rendered objects
	 * @return object count
	 */
	public int count() {
		return callCount;
	}
	
	/**
	 * Render all objects in queue
	 */
	public void render() {
		this.callCount = 0;
		this.target.clear();
		for (RenderableEntity obj : batch) {
			render(obj);
		}
	}
	
	/**
	 * Hierarchical render call of object
	 * @param obj RenderableEntity to be rendered
	 */
	private void render(RenderableEntity obj) {
		if (obj instanceof CompositeEntity) {
			for (RenderableEntity sub : ((CompositeEntity) obj).getObjects()) {
				render(sub);
			}
		} else {
			this.callCount++;
			if (obj instanceof Shape) {
				Shape shape = (Shape) obj;
				
				if (shape.hasFill()) {
					this.target.fill(shape);
				}
				
				if (shape.hasStroke()) {
					this.target.stroke(shape);
				}
			}
		}
	}
	
	/**
	 * Clear render queue
	 */
	public void flush() {
		batch.clear();
	}
	
}
