/**
 * @author Hiraishin Software
 * @author Martin 'mar21' Halfar
 */

package kirin.graphics;

import java.util.ArrayList;
import java.util.List;

import kirin.graphics.animation.Animation;
import kirin.graphics.animation.AnimationScript;
import kirin.graphics.render.CompositeObject;
import kirin.graphics.render.RenderableObject;
import kirin.graphics.shape.Shape;
import kirin.input.InputManager;
import kirin.util.Colors;

public class RenderHandler {

	public static final String RENDERER_NAME = "mar21.kirin.v01.b008";
	
	private Canvas canvas = null;
	private List<RenderableObject> objects = new ArrayList<RenderableObject>();
	private List<AnimationScript> scripts = new ArrayList<AnimationScript>();

	private InputManager input = null;
	
	private long previousNano = System.nanoTime();
	private long delta = 0;
	private int callCount = 0;
	private int frameCount = 0;
	private boolean showDebugInfo = false;
	
	/**
	 * @param width	Preferred window width
	 * @param height	Preferred window height
	 * @throws IllegalArgumentException
	 */
	public RenderHandler(double width, double height) {
		Canvas.requestResolution(width, height);
		this.canvas = Canvas.getInstance();

		flush();
	}
	
	/**
	 * @throws IllegalArgumentException
	 */
	public RenderHandler() {
		this.canvas = Canvas.getInstance();

		flush();
	}
	
	/**
	 * Returns used interlayer
	 * @return Interlayer canvas
	 */
	public Canvas getCanvas() {
		return canvas;
	}
	
	/**
	 * Change state of debug
	 * @param debug Debug state
	 */
	public void showDebugOverlay(boolean debug) {
		this.showDebugInfo = debug;
	}
	
	/**
	 * @return Debug state
	 */
	public boolean isShowingDebugOverlay() {
		return this.showDebugInfo;
	}
	
	/**
	 * Adds objects to queue
	 * @param objects RenderableObjects
	 */
	public void add(RenderableObject... objects) {
		for (RenderableObject obj : objects) {
			if (obj != null && !this.objects.contains(obj)) {
				this.objects.add(obj);
			}
		}
	}
	
	/**
	 * Adds scripts to queue
	 * @param scripts AnimationScript
	 */
	public void add(AnimationScript... scripts) {
		for (AnimationScript script : scripts) {			
			if (script != null && !this.scripts.contains(script)) {
				this.scripts.add(script);
			}
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
	 * Removes script from 
	 * @param animation AnimationScript
	 * @return Removed object
	 */
	public boolean remove(AnimationScript script) {
		return scripts.remove(script);
	}
	
	/**
	 * Get most recent count of rendered objects
	 * @return Object count
	 */
	public int countCalls() {
		return callCount;
	}
	
	/**
	 * Get count of rendered frames in total
	 * @return Total count
	 */
	public int countFrames() {
		return frameCount;
	}
	
	/**
	 * 
	 * @param entity
	 * @param rowIter
	 * @return
	 */
	private int showEntityDebugInfo(RenderableObject entity, int rowIter) {
		this.canvas.fill(Integer.toString((int) entity.getX()), 0, 9 * rowIter, 9, Colors.BLACK);
		this.canvas.fill(Integer.toString((int) entity.getY()), 30, 9 * rowIter, 9, Colors.BLACK);
		
		if (entity instanceof CompositeObject) {
			this.canvas.fill(Integer.toHexString(entity.hashCode()), 60, 9 * rowIter, 9, Colors.ORANGE_VIBRANT);
			this.canvas.fill(entity.getClass().getSimpleName(), 120, 9 * rowIter++, 9, Colors.ORANGE_VIBRANT);
			for (RenderableObject subEnt : ((CompositeObject) entity).getObjects()) {
				rowIter = showEntityDebugInfo(subEnt, rowIter);
			}
		} else {
			this.canvas.fill(Integer.toHexString(entity.hashCode()), 60, 9 * rowIter, 9, Colors.BLACK);
			this.canvas.fill(entity.getClass().getSimpleName(), 120, 9 * rowIter++, 9, Colors.BLACK);
		}
		
		return rowIter;
	}
	
	/**
	 * 
	 * @param script
	 * @param animation
	 * @param rowIter
	 * @return
	 */
	private int showAnimationDebugInfo(AnimationScript script, Animation animation, int rowIter) {
		this.canvas.fill(animation.getClass().getSimpleName(), 250, 9 * rowIter, 9, (animation.equals(script.getCurrentAnimation()) && script.isRunning() ? Colors.ORANGE_VIBRANT : Colors.BLACK));
		this.canvas.fill("" + animation.stepLength(), 320, 9 * rowIter, 9, (animation.equals(script.getCurrentAnimation()) && script.isRunning() ? Colors.ORANGE_VIBRANT : Colors.BLACK));
		this.canvas.fill(animation.toString(), 360, 9 * rowIter++, 9, (animation.equals(script.getCurrentAnimation()) && script.isRunning() ? Colors.ORANGE_VIBRANT : Colors.BLACK));
		return rowIter;
	}
	
	/**
	 * 
	 * @param script
	 * @param rowIter
	 * @return
	 */
	private int showAnimationScriptDebugInfo(AnimationScript script, int rowIter) {
		this.canvas.fill(Integer.toHexString(script.hashCode()), 200, 9 * rowIter, 9, Colors.BLACK);
		this.canvas.fill(script.getLaunchConfiguration().toString(), 250, 9 * rowIter, 9, Colors.BLACK);
		this.canvas.fill("R: " + script.isRunning() 
					+ " \tL: " + script.isLooping() 
					+ " \tC: " + script.hasAttached()
					+ " \tA: " + script.size() 
					+ "   \tI: " + (script.getIndex() + 1)
					+ "     \tT: " + script.getTicks()
					, 320, 9 * rowIter++, 9, Colors.BLACK);
		
		for (Animation animation : script.getAnimations()) {
			rowIter = showAnimationDebugInfo(script, animation, rowIter);
		}
		
		return rowIter;
	}
	
	/**
	 * Render all objects in queue
	 */
	public void render() {
		long currentNano = System.nanoTime();
		this.delta = (currentNano - this.previousNano) / 1_000_000;
		this.previousNano = currentNano;
		
		this.callCount = 0;
		this.frameCount++;
		this.canvas.clear();
		
		for (AnimationScript animation : scripts) {
			animation.invokeUpdate();
		}

		for (RenderableObject obj : objects) {
			recursionRender(obj);
		}
		
		if (this.showDebugInfo) {
			this.canvas.fill(RENDERER_NAME, 0, 0, 9, Colors.BLACK);
			this.canvas.fill("Frame time: " + this.delta + " ms", 200, 0, 9, Colors.RED_DEEP);
			this.canvas.fill("Renderer \tQueue: " + objects.size() + "     \tCalls: " + this.callCount, 0, 18, 9, Colors.RED_DEEP);
			this.canvas.fill("Scripts: " + scripts.size(), 200, 18, 9, Colors.RED_DEEP);
			
			int rowIter = 4;
			for (RenderableObject ent : objects) {
				rowIter = showEntityDebugInfo(ent, rowIter);
			}
			
			rowIter = 4;
			for (AnimationScript animation : scripts) {
				rowIter = showAnimationScriptDebugInfo(animation, rowIter);
			}
		}
	}
	
	/**
	 * Hierarchical render call of object
	 * @param obj RenderableEntity to be rendered
	 */
	private void recursionRender(RenderableObject obj) {
		if (obj instanceof CompositeObject) {
			for (RenderableObject sub : ((CompositeObject) obj).getObjects()) {
				recursionRender(sub);
			}
		} else {
			this.callCount++;
			if (obj instanceof Shape) {
				Shape shape = (Shape) obj;
				
				if (shape.hasFill()) {
					this.canvas.fill(shape);
				}
				
				if (shape.hasStroke()) {
					this.canvas.stroke(shape);
				}
			} else if (obj instanceof Sprite) {
				this.canvas.fill((Sprite) obj); 
			} else {
				throw new IllegalArgumentException("Incompatible object: " + obj.getClass().getName());
			}
		}
	}
	
	/**
	 * Clear render queue
	 */
	public void flush() {
		objects.clear();
		scripts.clear();
	}
	
	public InputManager requestInputManager() {
		if (this.input == null) {
			this.input = new InputManager(this);
		}
		
		return this.input;
	}
}
