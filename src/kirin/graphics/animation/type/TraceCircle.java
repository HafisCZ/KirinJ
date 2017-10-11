package kirin.graphics.animation.type;

import kirin.graphics.animation.Animation;
import kirin.graphics.render.EditableObject;
import kirin.util.math.Vec2d;

public class TraceCircle extends Animation {

	private double cx, cy, angle;
	
	public TraceCircle(double cx, double cy, double angle, int stepLength) {
		super(stepLength);
		this.cx = cx;
		this.cy = cy;
		this.angle = angle;
	}

	@Override
	public boolean update(EditableObject entity, double ix, double iy, double iw, double ih) {
		double orgX = ix + iw / 2;
		double orgY = iy + ih / 2;
		double len = Math.sqrt(Math.pow(cx - orgX, 2) + Math.pow(cy - orgY, 2));
		Vec2d vec = new Vec2d(entity.getCenterX() - cx, entity.getCenterY() - cy).normalize();
		
		if (this.time <= this.stepLength) {
			Vec2d vec2 = new Vec2d(vec.angle() + angle / this.stepLength);
			entity.move((vec.x() - vec2.x()) * (vec.length() - len), (vec.y() - vec2.y()) * (vec.length() - len));
			this.time++;
			return false;
		} else {
			Vec2d vec2 = new Vec2d(new Vec2d(orgX - cx, orgY - cy).angle() + angle);
			entity.move((vec.x() - vec2.x()) * (vec.length() - len), (vec.y() - vec2.y()) * (vec.length() - len));
			this.time = 1;
			return true;
		}
	}

}
