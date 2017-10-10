package kirin.graphics.animation.type;

import kirin.graphics.render.EditableEntity;

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
	public boolean update(EditableEntity entity, double ix, double iy, double iw, double ih) {
		if (this.time <= this.stepLength) {
			entity.move(dx, dy);
			this.time++;
			return false;
		} else {
			this.time = 1;
			return true;
		}
	}
}