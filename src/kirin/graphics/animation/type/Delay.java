package kirin.graphics.animation.type;

import kirin.graphics.animation.Animation;
import kirin.graphics.render.EditableObject;

public class Delay extends Animation {

	/**
	 * 
	 * @param stepLength Span of action
	 */
	public Delay(int stepLength) {
		super(stepLength);
	}

	@Override
	public boolean update(EditableObject entity, double ix, double iy, double iw, double ih, boolean supressTick) {
		if (time > stepLength) {
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
	}
	
	public String getString() {
		return "D: " + this.stepLength;
	}

}
