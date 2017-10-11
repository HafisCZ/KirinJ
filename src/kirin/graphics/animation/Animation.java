package kirin.graphics.animation;

import kirin.graphics.render.EditableObject;

public abstract class Animation {
	
	/**
	 *	Transition type
	 */
	public static enum TransitionType {
		FAST, SMOOTH
	}
	
	protected int stepLength;
	protected int time;
	
	/**
	 * 
	 * @param stepLength	Span of action
	 */
	protected Animation(int stepLength) {
		this.stepLength = stepLength;
		this.time = 1;
	}
	
	/**
	 * 
	 * @return Span of action
	 */
	public int stepLength() {
		return this.stepLength;
	}
	
	/**
	 * 
	 * @param entity	Entity managed by Animation
	 * @param ix	Entity original x
	 * @param iy	Entity original y
	 * @param iw	Entity original width
	 * @param ih	Entity original height
	 * @return	End of Animation
	 */
	public abstract boolean update(EditableObject entity, double ix, double iy, double iw, double ih);
}
