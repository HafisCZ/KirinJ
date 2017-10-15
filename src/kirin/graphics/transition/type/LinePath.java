package kirin.graphics.transition.type;

import kirin.graphics.render.EditableObject;
import kirin.graphics.transition.Transition;

public class LinePath extends Transition {
	
	private double x, y;
	private TransitionType type;
	
	/**
	 * 
	 * @param x	Target x
	 * @param y	Target y
	 * @param stepLength	Span of action
	 * @param type	Type of transition
	 */
	public LinePath(double x, double y, int stepLength, TransitionType type) {
		super(stepLength);
		this.x = x;
		this.y = y;
		this.type = type;
	}

	@Override
	public boolean update(EditableObject entity, double ix, double iy, double iw, double ih, boolean supressTick) {
		switch (type) {
			case FAST:
				if (time > stepLength) {
					entity.setPosition(x, y);
					if (!supressTick) {
						this.time = 1;
					}
					return true;
				} else {
					if (!supressTick) {
						this.time++;
					}
					return false;
				}
			case SMOOTH:
				if (time <= stepLength) {
					entity.move((x - ix) / stepLength, (y - iy) / stepLength);
					if (!supressTick) {
						this.time++;
					}
					return false;
				} else {
					entity.setPosition(x, y);
					if (!supressTick) {
						this.time = 1;
					}
					return true;
				}
			default:
				return true;
		}
	}
	
	public String toString() {
		return "T: " + this.x + " " + this.y + " \tT: " + this.type.toString();
	}
}
