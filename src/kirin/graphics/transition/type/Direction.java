package kirin.graphics.transition.type;

import kirin.graphics.render.EditableObject;
import kirin.graphics.transition.Transition;

public class Direction extends Transition {
	
	private double dx, dy;
	
	/**
	 * 
	 * @param dx	x of vector
	 * @param dy	y of vector
	 * @param stepLength	Span of action
	 */
	public Direction(double dx, double dy, int stepLength) {
		super(stepLength);
		this.dx = dx;
		this.dy = dy;
	}

	@Override
	public boolean update(EditableObject entity, double ix, double iy, double iw, double ih, boolean supressTick) {
		if (this.time <= this.stepLength) {
			entity.move(dx, dy);
			if (!supressTick) {
				this.time++;
			}
			return false;
		} else {
			if (!supressTick) {
				this.time = 1;
			}
			return true;
		}
	}
	
	public String toString() {
		return "D: " + this.dx + " " + this.dy;
	}
}
