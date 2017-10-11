package kirin.graphics.animation;

import kirin.graphics.render.EditableObject;

public class AnimationSequence {
	
	public static enum Launch {
		ON_DEMAND, AUTO
	}

	private EditableObject[] objects;
	private Animation[] animations;
	private double[][] data;
	
	private int time = 1, stepIndex = 0;
	private boolean running = false, repeat = false;
	private Launch launch;

	/**
	 * 
	 * @param object	Object to be animated
	 * @param animations	Animations
	 * @param repeat	Repeat forever
	 * @param launch	Type of launch
	 */
	public AnimationSequence(EditableObject object, Animation[] animations, boolean repeat, Launch launch) {
		this(new EditableObject[] { object }, animations, repeat, launch);
	}
	
	/**
	 * 
	 * @param objects	Objects to be animated
	 * @param animations	Animations
	 * @param repeat	Repeat forever
	 * @param launch	Type of launch
	 */
	public AnimationSequence(EditableObject[] objects, Animation[] animations, boolean repeat, Launch launch) {
		this.objects = objects;
		this.animations = animations;
		
		this.repeat = repeat;
		this.launch = launch;
		if (launch.equals(Launch.AUTO)) {
			this.running = true;
		}
		
		this.data = new double[objects.length][4];
		updateObjectData();
	}
	
	/**
	 * Update object data
	 */
	private void updateObjectData() {
		for (int i = 0; i < objects.length; i++) {
			this.data[i] = new double[] {
				this.objects[i].getX(),
				this.objects[i].getY(),
				this.objects[i].getWidth(),
				this.objects[i].getHeight()
			};
		}
	}

	/**
	 * True if AnimationSequence is on repeat
	 */
	public boolean onRepeat() {
		return repeat;
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
		return this.animations[this.stepIndex];
	}
	
	/**
	 * Start AnimationSequence which was created with ON_DEMAND launch option
	 */
	public void run() {
		if (!this.running && this.launch.equals(Launch.ON_DEMAND)) {
			this.running = true;
		}
	}
	
	/**
	 * Set AnimationSequence to repeat
	 */
	public AnimationSequence repeat(boolean repeat) {
		this.repeat = repeat;
		return this;
	}
	
	/**
	 * Update current Animation
	 */
	public void update() {
		if (stepIndex >= animations.length) {
			stepIndex = 0;
			time = 1;
			if (!onRepeat()) {
				this.running = false;
			}
		}
		
		if (stepIndex < animations.length && this.running) {
			boolean advance = false;
			for (int i = 0; i < objects.length; i++) {
				advance |= animations[stepIndex].update(objects[i], data[i][0], data[i][1], data[i][2], data[i][3]);
			}
			
			if (advance) {
				this.stepIndex++;
				updateObjectData();
				update();
			} else {
				this.time++;
			}
		}
	}
	
	/**
	 * Count of Animations
	 */
	public int getAnimationCount() {
		return this.animations.length;
	}
	
	/**
	 * Count of update calls from render method
	 */
	public int getStepCount() {
		return this.time;
	}
	
	/**
	 * Index of current Animation
	 */
	public int getStepIndex() {
		return this.stepIndex;
	}
}
