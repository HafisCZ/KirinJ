package kirin.graphics.animation.type;

import kirin.graphics.animation.Animation;
import kirin.graphics.render.EditableObject;

public class Motion extends Animation {
	
	private double dx, dy;
	
	/**
	 * 
	 * @param dx	x of vector
	 * @param dy	y of vector
	 * @param stepLength	Span of action
	 */
	public Motion(double dx, double dy, int stepLength) {
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
