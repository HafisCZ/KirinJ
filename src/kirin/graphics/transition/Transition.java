package kirin.graphics.transition;

import kirin.graphics.render.EditableObject;

public abstract class Transition {
	
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
	protected Transition(int stepLength) {
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
	public abstract boolean update(EditableObject entity, double ix, double iy, double iw, double ih, boolean supressTick);
	
	public String toString() {
		return "null";
	}
}
