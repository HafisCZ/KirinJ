package kirin.util.math;

public class Vec2d {
	
	private double dx, dy;
	
	/**
	 * Create new vector from coordinates
	 * @param dx
	 * @param dy
	 */
	public Vec2d(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}
	
	/**
	 * Create new vector from angle
	 * @param angle Angle in degrees, 0 equals EAST
	 */
	public Vec2d(double angle) {
		double rads = Math.toRadians((angle >= 0 ? angle : angle + 360));
		this.dx = Math.cos(rads);
		this.dy = Math.sin(rads);
	}
	
	/**
	 * Normalize vector
	 * @return itself
	 */
	public Vec2d normalize() {
		double len = length();
		if (len != 0) {
			this.dx /= len;
			this.dy /= len;
		}

		return this;
	}
	
	/**
	 * 
	 * @return Angle of vector
	 */
	public double angle() {
		double angle = Math.toDegrees(Math.atan2(this.dy, this.dx));
		return (angle >= 0 ? angle : angle + 360);
	}
	
	/**
	 * Invert vector
	 * @return itself
	 */
	public Vec2d invert() {
		return multiply(-1);
	}
	
	/**
	 * Multiply vector by number
	 * @param fact	Multiplier
	 * @return itself
	 */
	public Vec2d multiply(double fact) {
		this.dx *= fact;
		this.dy *= fact;
		return this;
	}

	/**
	 * 
	 * @return Size of vector
	 */
	public double length() {
		return Math.sqrt(dx * dx + dy * dy);
	}
	
	/**
	 * 
	 * @return x
	 */
	public double x() {
		return this.dx;
	}
	
	/**
	 * 
	 * @return y
	 */
	public double y() {
		return this.dy;
	}
	
	/**
	 * Multiplies vector
	 * @param vec Vector to be multiplied
	 * @param fact	Multiplier
	 * @return	New multiplied vector
	 */
	public static Vec2d getMultiplied(Vec2d vec, double fact) {
		return new Vec2d(vec.dx * fact, vec.dy * fact);
	}
	
	/**
	 * Inverts vector
	 * @param vec Vector to be inverted
	 * @return	New inverted vector
	 */
	public static Vec2d getInverted(Vec2d vec) {
		return new Vec2d(-vec.dx, -vec.dy);
	}
	
	/**
	 * Normalizes vector
	 * @param vec Vector to be normalized
	 * @return	New normalized vector
	 */
	public static Vec2d getNormalized(Vec2d vec) {
		double len = vec.length();
		if (len == 0) {
			return new Vec2d(0, 0);
		} else {
			return new Vec2d(vec.dx / len, vec.dy / len);
		}
	}
}
