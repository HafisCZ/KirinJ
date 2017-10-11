package kirin.graphics.animation.type;

import kirin.graphics.animation.Animation;
import kirin.graphics.render.EditableObject;

public class Scaling extends Animation {
	
	private double w, h;
	private TransitionType type;
	
	/**
	 * 
	 * @param w	Target width
	 * @param h	Target height
	 * @param stepLength	Span of action
	 * @param type Type of transition
	 */
	public Scaling(double w, double h, int stepLength, TransitionType type) {
		super(stepLength);
		this.w = w;
		this.h = h;
		this.type = type;
	}

	@Override
	public boolean update(EditableObject entity, double ix, double iy, double iw, double ih) {
		switch (type) {
			case FAST:
				if (time > stepLength) {
					entity.move((iw - w) / 2, (ih - h) / 2);
					entity.setDimensions(w, h);
					this.time = 1;
					return true;
				} else {
					this.time++;
					return false;
				}
			case SMOOTH:
				if (time <= stepLength) {
					entity.move((iw - w) / 2 / stepLength, (ih - h) / 2 / stepLength);
					entity.setWidth(entity.getWidth() - (ih - h) / stepLength);
					entity.setHeight(entity.getHeight() - (ih - h) / stepLength);
					this.time++;
					return false;
				} else {
					entity.setDimensions(w, h);
					this.time = 1;
					return true;
				}
			default:
				return true;
		}
	}
}
