/**
 * @author Hiraishin Software
 * @author Martin 'mar21' Halfar
 */

package kirin.graphics;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;
import kirin.graphics.render.CompositeObject;
import kirin.graphics.render.RenderableObject;
import kirin.graphics.shape.Shape;
import kirin.graphics.transition.Transition;
import kirin.graphics.transition.TransitionScript;
import kirin.input.InputManager;

public class RenderHandler {

	public static final String RENDERER_NAME = "KirinJ V01.B009";
	
	private Canvas canvas = null;
	private List<RenderableObject> objects = new ArrayList<RenderableObject>();
	private List<TransitionScript> scripts = new ArrayList<TransitionScript>();

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
	public void add(TransitionScript... scripts) {
		for (TransitionScript script : scripts) {			
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
	public boolean remove(TransitionScript script) {
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
		this.canvas.fill(Integer.toString((int) entity.getX()), 0, 9 * rowIter, 9, Color.BLACK);
		this.canvas.fill(Integer.toString((int) entity.getY()), 30, 9 * rowIter, 9, Color.BLACK);
		
		if (entity instanceof CompositeObject) {
			this.canvas.fill(Integer.toHexString(entity.hashCode()), 60, 9 * rowIter, 9, Color.BLUEVIOLET);
			this.canvas.fill(entity.getClass().getSimpleName(), 120, 9 * rowIter++, 9, Color.BLUEVIOLET);
			for (RenderableObject subEnt : ((CompositeObject) entity).getObjects()) {
				rowIter = showEntityDebugInfo(subEnt, rowIter);
			}
		} else {
			this.canvas.fill(Integer.toHexString(entity.hashCode()), 60, 9 * rowIter, 9, Color.BLACK);
			this.canvas.fill(entity.getClass().getSimpleName(), 120, 9 * rowIter++, 9, Color.BLACK);
		}
		
		return rowIter;
	}
	
	/**
	 * 
	 * @param script
	 * @param transition
	 * @param rowIter
	 * @return
	 */
	private int showTransitionDebugInfo(TransitionScript script, Transition transition, int rowIter) {
		this.canvas.fill(transition.getClass().getSimpleName(), 250, 9 * rowIter, 9, (transition.equals(script.getCurrentAnimation()) && script.isRunning() ? Color.BLUEVIOLET : Color.BLACK));
		this.canvas.fill("" + transition.stepLength(), 320, 9 * rowIter, 9, (transition.equals(script.getCurrentAnimation()) && script.isRunning() ? Color.BLUEVIOLET : Color.BLACK));
		this.canvas.fill(transition.toString(), 360, 9 * rowIter++, 9, (transition.equals(script.getCurrentAnimation()) && script.isRunning() ? Color.BLUEVIOLET : Color.BLACK));
		return rowIter;
	}
	
	/**
	 * 
	 * @param script
	 * @param rowIter
	 * @return
	 */
	private int showTransitionScriptDebugInfo(TransitionScript script, int rowIter) {
		this.canvas.fill(Integer.toHexString(script.hashCode()), 200, 9 * rowIter, 9, Color.BLACK);
		this.canvas.fill(script.getLaunchConfiguration().toString(), 250, 9 * rowIter, 9, Color.BLACK);
		this.canvas.fill("R: " + script.isRunning() 
					+ " \tL: " + script.isLooping() 
					+ " \tC: " + script.hasAttached()
					+ " \tA: " + script.size() 
					+ "   \tI: " + (script.getIndex() + 1)
					+ "     \tT: " + script.getTicks()
					, 320, 9 * rowIter++, 9, Color.BLACK);
		
		for (Transition transition : script.getAnimations()) {
			rowIter = showTransitionDebugInfo(script, transition, rowIter);
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
		
		for (TransitionScript animation : scripts) {
			animation.invokeUpdate();
		}

		for (RenderableObject obj : objects) {
			recursionRender(obj);
		}
		
		if (this.showDebugInfo) {
			this.canvas.fill(RENDERER_NAME + " (" + this.canvas.getClass().getName() + ")", 0, 0, 9, Color.BLACK);
			this.canvas.fill("Frame time: " + this.delta + " ms", 200, 0, 9, Color.ORANGERED);
			this.canvas.fill("Renderer \tQueue: " + objects.size() + "     \tCalls: " + this.callCount, 0, 18, 9, Color.ORANGERED);
			this.canvas.fill("Scripts: " + scripts.size(), 200, 18, 9, Color.ORANGERED);
			
			int rowIter = 4;
			for (RenderableObject ent : objects) {
				rowIter = showEntityDebugInfo(ent, rowIter);
			}
			
			rowIter = 4;
			for (TransitionScript transition : scripts) {
				rowIter = showTransitionScriptDebugInfo(transition, rowIter);
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
