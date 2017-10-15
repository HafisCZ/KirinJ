package kirin.graphics.transition.type;

import kirin.graphics.render.EditableObject;
import kirin.graphics.transition.Transition;

public class Delay extends Transition {

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
