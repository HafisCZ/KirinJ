package kirin.graphics.animation;

import kirin.graphics.animation.type.Animation;
import kirin.graphics.render.EditableEntity;

public class AnimationSequence {
	
	public static enum Launch {
		ON_DEMAND, AUTO
	}

	private EditableEntity entity;
	private Animation[] animationSteps;
	private int time, stepIndex;
	private boolean running = false, repeat = false;
	private Launch launch;
	private double x, y, w, h;
	
	/**
	 * 
	 * @param entity	Entity managed by Animation
	 * @param steps		Array of individual Animation steps
	 * @param repeat	True if steps should repeat
	 * @param launch	ON_DEMAND or AUTO
	 */
	public AnimationSequence(EditableEntity entity, Animation[] steps, boolean repeat, Launch launch) {
		this.entity = entity;
		this.animationSteps = steps;
		this.time = 1;
		this.stepIndex = 0;
		this.x = entity.getX();
		this.y = entity.getY();
		this.w = entity.getWidth();
		this.h = entity.getHeight();
		
		this.repeat = repeat;
		this.launch = launch;
		
		if (launch.equals(Launch.AUTO)) {
			this.running = true;
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
		return this.animationSteps[this.stepIndex];
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
		if (stepIndex >= animationSteps.length) {
			stepIndex = 0;
			time = 1;
			if (!onRepeat()) {
				this.running = false;
			}
		}
		
		if (stepIndex < animationSteps.length && this.running) {
			if (animationSteps[stepIndex].update(entity, x, y, w, h)) {
				this.stepIndex++;
				this.x = entity.getX();
				this.y = entity.getY();
				this.w = entity.getWidth();
				this.h = entity.getHeight();
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
		return this.animationSteps.length;
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
