package kirin.graphics.transition;

import java.util.ArrayList;
import java.util.List;

import kirin.graphics.render.EditableObject;

public class TransitionScript {
	
	public static enum Launch {
		ON_DEMAND, AUTO
	}

	private List<TransitionScript> attached = new ArrayList<TransitionScript>();
	private EditableObject[] objects;
	private Transition[] transitions;
	private double[][] data;
	
	private int tick = 1, animation = 0;
	private boolean running = false, looping = false;
	private Launch launch;

	/**
	 * 
	 * @param object	Object to be animated
	 * @param animations	Animations
	 * @param repeat	Repeat forever
	 * @param launch	Type of launch
	 */
	public TransitionScript(EditableObject object, Transition[] animations, boolean looping, Launch launch) {
		this(new EditableObject[] { object }, animations, looping, launch);
	}
	
	/**
	 * 
	 * @param objects	Objects to be animated
	 * @param animations	Animations
	 * @param repeat	Repeat forever
	 * @param launch	Type of launch
	 */
	public TransitionScript(EditableObject[] objects, Transition[] animations, boolean looping, Launch launch) {
		this.objects = objects;
		this.transitions = animations;
		
		this.looping = looping;
		this.launch = launch;
		if (launch.equals(Launch.AUTO)) {
			this.running = true;
		}
		
		this.data = new double[objects.length][4];
		updateObjectData();
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean hasAttached() {
		return (this.attached.size() > 0);
	}
	
	/**
	 * 
	 */
	public void detachAll() {
		this.attached.clear();
	}
	
	/**
	 * Remove attached animation
	 * @param seq	AnimationSequence
	 * @return
	 */
	public boolean detach(TransitionScript seq) {
		return this.attached.remove(seq);
	}
	
	/**
	 * Attach animation after end of this one
	 * @param seq	AnimationSequence
	 */
	public void attach(TransitionScript seq) {
		if (this.looping || seq.launch.equals(Launch.AUTO)) {
			throw new IllegalArgumentException();
		}

		this.attached.add(seq);
	}
	
	/**
	 * Update object data
	 */
	private void updateObjectData() {
		for (int i = 0; i < objects.length; i++) {
			this.data[i][0] = objects[i].getX();
			this.data[i][1] = objects[i].getY();
			this.data[i][2] = objects[i].getWidth();
			this.data[i][3] = objects[i].getHeight();
		}
	}

	/**
	 * True if AnimationSequence is on repeat
	 */
	public boolean isLooping() {
		return looping;
	}
	
	/**
	 * True if AnimationSequence is running
	 */
	public boolean isRunning() {
		return this.running | isAnyAttachedRunning();
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isRunningIsolated() {
		return this.running;
	}
	
	/**
	 * Get type of launch option
	 */
	public Launch getLaunchConfiguration() {
		return this.launch;
	}
	
	/**
	 * 
	 * @return Current animation
	 */
	public Transition getCurrentAnimation() {
		return this.transitions[this.animation];
	}
	
	/**
	 * Start AnimationSequence which was created with ON_DEMAND launch option
	 */
	public void execute() {
		if (!isRunning() && this.launch.equals(Launch.ON_DEMAND)) {
			this.running = true;
		}
	}
	
	/**
	 * Set AnimationSequence to repeat
	 */
	public void looping(boolean looping) {
		this.looping = looping;
	}
	
	/**
	 * Update current Animation
	 */
	public void invokeUpdate() {
		if (animation >= transitions.length) {
			animation = 0;
			tick = 1;
			if (!isLooping()) {
				this.running = false;
				for (TransitionScript seq : attached) {
					seq.execute();
					seq.invokeUpdate();
				}
			}
		}
		
		if (animation < transitions.length && this.running) {
			boolean advance = false;
			for (int i = 0; i < objects.length; i++) {
				advance |= transitions[animation].update(objects[i], data[i][0], data[i][1], data[i][2], data[i][3], i < objects.length - 1);
			}
			
			if (advance) {
				this.animation++;
				updateObjectData();
				invokeUpdate();
			} else {
				this.tick++;
			}
		}
	}
	
	/**
	 * Count of Animations
	 */
	public int size() {
		return this.transitions.length;
	}
	
	/**
	 * Count of update calls from render method
	 */
	public int getTicks() {
		return this.tick;
	}
	
	/**
	 * Index of current Animation
	 */
	public int getIndex() {
		return this.animation;
	}
	
	/**
	 * 
	 * @return
	 */
	public EditableObject[] getControlledObjects() {
		return this.objects;
	}
	
	/**
	 * 
	 * @return
	 */
	public Transition[] getAnimations() {
		return this.transitions;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isAnyAttachedRunning() {
		boolean running = false;
		for (TransitionScript script : attached) {
			running |= script.isRunning();
			if (script.hasAttached()) {
				running |= script.isAnyAttachedRunning();
			}
		}
		return running;
	}
}
