package kirin.graphics.animation;

import java.util.ArrayList;
import java.util.List;

import kirin.graphics.render.EditableObject;

public class AnimationScript {
	
	public static enum Launch {
		ON_DEMAND, AUTO
	}

	private List<AnimationScript> attached = new ArrayList<AnimationScript>();
	private EditableObject[] objects;
	private Animation[] animations;
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
	public AnimationScript(EditableObject object, Animation[] animations, boolean looping, Launch launch) {
		this(new EditableObject[] { object }, animations, looping, launch);
	}
	
	/**
	 * 
	 * @param objects	Objects to be animated
	 * @param animations	Animations
	 * @param repeat	Repeat forever
	 * @param launch	Type of launch
	 */
	public AnimationScript(EditableObject[] objects, Animation[] animations, boolean looping, Launch launch) {
		this.objects = objects;
		this.animations = animations;
		
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
	public boolean detach(AnimationScript seq) {
		return this.attached.remove(seq);
	}
	
	/**
	 * Attach animation after end of this one
	 * @param seq	AnimationSequence
	 */
	public void attach(AnimationScript seq) {
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
	public Animation getCurrentAnimation() {
		return this.animations[this.animation];
	}
	
	/**
	 * Start AnimationSequence which was created with ON_DEMAND launch option
	 */
	public void execute() {
		if (!this.running && this.launch.equals(Launch.ON_DEMAND)) {
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
		if (animation >= animations.length) {
			animation = 0;
			tick = 1;
			if (!isLooping()) {
				this.running = false;
				for (AnimationScript seq : attached) {
					seq.execute();
					seq.invokeUpdate();
				}
			}
		}
		
		if (animation < animations.length && this.running) {
			boolean advance = false;
			for (int i = 0; i < objects.length; i++) {
				advance |= animations[animation].update(objects[i], data[i][0], data[i][1], data[i][2], data[i][3], i < objects.length - 1);
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
		return this.animations.length;
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
	public Animation[] getAnimations() {
		return this.animations;
	}
}
