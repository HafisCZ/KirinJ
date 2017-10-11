/**
 * @author Hiraishin Software
 * @author Martin 'mar21' Halfar
 */

package kirin.graphics;

import java.util.ArrayList;
import java.util.List;

import kirin.graphics.animation.AnimationSequence;
import kirin.graphics.render.CompositeObject;
import kirin.graphics.render.RenderableObject;
import kirin.graphics.shape.Shape;
import kirin.util.Colors;

public class RenderHandler {

	public static final String RENDERER_NAME = "mar21.kirin.core.v01.b005";
	
	private Canvas target = null;
	private List<RenderableObject> objects = new ArrayList<RenderableObject>();
	private List<AnimationSequence> animations = new ArrayList<AnimationSequence>();

	private int callCount, frameCount;
	private boolean debug;
	
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
		this.frameCount = 0;
		this.debug = false;
	}
	
	/**
	 * Returns used interlayer
	 * @return Interlayer canvas
	 */
	public Canvas getTarget() {
		return target;
	}
	
	/**
	 * Change state of debug
	 * @param debug Debug state
	 */
	public void debug(boolean debug) {
		this.debug = debug;
	}
	
	/**
	 * @return Debug state
	 */
	public boolean isDebugged() {
		return this.debug;
	}
	
	/**
	 * Adds object to render queue
	 * @param object RenderableEntity
	 */
	public void add(RenderableObject object) {
		if (!objects.contains(object)) {
			objects.add(object);
		}
	}
	
	/**
	 * Adds animation sequence to render queue
	 * @param animation AnimationSequence
	 */
	public void add(AnimationSequence animation) {
		if (!animations.contains(animation)) {
			animations.add(animation);
		}
	}
	
	/**
	 * Removes object from queue
	 * @param object RenderableEntity
	 * @return Removed object
	 */
	public boolean remove(RenderableObject object) {
		return objects.remove(object);
	}
	
	/**
	 * Removes animation from 
	 * @param animation AnimationSequence
	 * @return Removed object
	 */
	public boolean remove(AnimationSequence animation) {
		return animations.remove(animation);
	}
	
	/**
	 * Get most recent count of rendered objects
	 * @return Object count
	 */
	public int count() {
		return callCount;
	}
	
	/**
	 * Get count of rendered frames in total
	 * @return Total count
	 */
	public int total() {
		return frameCount;
	}
	
	/**
	 * 
	 * @param entity
	 * @param rowIter
	 * @return
	 */
	private int showEntityDebugInfo(RenderableObject entity, int rowIter) {
		this.target.fill(Integer.toString((int) entity.getX()), 0, 9 * rowIter, 9, Colors.GRAY_CHARCOAL);
		this.target.fill(Integer.toString((int) entity.getY()), 30, 9 * rowIter, 9, Colors.GRAY_CHARCOAL);
		this.target.fill(entity.getClass().getName(), 60, 9 * rowIter++, 9, Colors.GREEN_FOLIAGE);
		
		if (entity instanceof CompositeObject) {
			for (RenderableObject subEnt : ((CompositeObject) entity).getObjects()) {
				rowIter = showEntityDebugInfo(subEnt, rowIter);
			}
		}
		
		return rowIter;
	}
	
	/**
	 * 
	 * @param animation
	 * @param rowIter
	 * @return
	 */
	private int showAnimationSequenceDebugInfo(AnimationSequence animation, int rowIter) {
		if (animation.getAnimationCount() > 0 && animation.isRunning()) {
			this.target.fill(animation.getCurrentAnimation().getClass().getSimpleName(), 200, 9 * rowIter, 9, Colors.PURPLE_RED);
		} else {
			this.target.fill("None", 200, 9 * rowIter, 9, Colors.PURPLE_RED);
		}
		
		this.target.fill(animation.getLaunchConfiguration().toString(), 250, 9 * rowIter, 9, Colors.GREEN_FOLIAGE);
		this.target.fill("R: " + animation.isRunning() 
					+ " \tC: " + animation.onRepeat() 
					+ " \tA: " + animation.getAnimationCount() 
					+ "   \tI: " + (animation.getStepIndex() + 1)
					+ "     \tT: " + animation.getStepCount()
					, 320, 9 * rowIter++, 9, Colors.GREEN_FOLIAGE);
		
		return rowIter;
	}
	
	/**
	 * Render all objects in queue
	 */
	public void render() {
		this.callCount = 0;
		this.frameCount++;
		this.target.clear();
		
		for (AnimationSequence animation : animations) {
			animation.update();
		}

		for (RenderableObject obj : objects) {
			render(obj);
		}
		
		if (this.debug) {
			this.target.fill(RENDERER_NAME, 0, 0, 9, Colors.PURPLE_DEEP);
			this.target.fill("Frames: " + this.frameCount, 200, 0, 9, Colors.RED_DEEP);
			this.target.fill("Renderer \tQueue: " + objects.size() + "     \tCalls: " + this.callCount, 0, 18, 9, Colors.RED_DEEP);
			this.target.fill("AnimationSequences \tQueue: " + animations.size(), 200, 18, 9, Colors.RED_DEEP);
			
			int rowIter = 4;
			for (RenderableObject ent : objects) {
				rowIter = showEntityDebugInfo(ent, rowIter);
			}
			
			rowIter = 4;
			for (AnimationSequence animation : animations) {
				rowIter = showAnimationSequenceDebugInfo(animation, rowIter);
			}
		}
	}
	
	/**
	 * Hierarchical render call of object
	 * @param obj RenderableEntity to be rendered
	 */
	private void render(RenderableObject obj) {
		if (obj instanceof CompositeObject) {
			for (RenderableObject sub : ((CompositeObject) obj).getObjects()) {
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
		objects.clear();
		animations.clear();
	}
	
}
