package kirin.util.math;

public class Vec2d {
	
	private double dx, dy;
	
	public Vec2d(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}
	
	public Vec2d(double angle) {
		double rads = Math.toRadians(angle);
		this.dx = Math.cos(rads);
		this.dy = Math.sin(rads);
	}
	
	public Vec2d normalize() {
		double len = length();
		if (len != 0) {
			this.dx /= len;
			this.dy /= len;
		}

		return this;
	}
	
	public double angle() {
		return Math.toDegrees(Math.atan2(this.dy, this.dx));
	}
	
	public Vec2d invert() {
		return multiply(-1);
	}
	
	public Vec2d multiply(double fact) {
		this.dx *= fact;
		this.dy *= fact;
		return this;
	}

	public double length() {
		return Math.sqrt(dx * dx + dy * dy);
	}
	
	public double x() {
		return this.dx;
	}
	
	public double y() {
		return this.dy;
	}
	
	public static Vec2d getMultiplied(Vec2d vec, double fact) {
		return new Vec2d(vec.dx * fact, vec.dy * fact);
	}
	
	public static Vec2d getInverted(Vec2d vec) {
		return new Vec2d(-vec.dx, -vec.dy);
	}
	
	public static Vec2d getNormalized(Vec2d vec) {
		double len = vec.length();
		if (len == 0) {
			return new Vec2d(0, 0);
		} else {
			return new Vec2d(vec.dx / len, vec.dy / len);
		}
	}
}
