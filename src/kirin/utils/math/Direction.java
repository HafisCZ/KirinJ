package kirin.utils.math;

public enum Direction {
	EAST (0),
	NORTHEAST (45),
	NORTH (90),
	NORTHWEST (135),
	WEST (180),
	SOUTHWEST (225),
	SOUTH (270),
	SOUTHEAST (315);
	
	private double angle;
	
	private Direction(double angle) {
		this.angle = angle;
	}
	
	/**
	 * Get normalized vector from direction
	 * @return Directional vector
	 */
	public Vec2d toVector() {
		return new Vec2d(this.angle);
	}
	
	/**
	 * Turn 180 degrees
	 */
	public Direction invert() {
		this.angle = (this.angle + 180) % 360;
		return this;
	}
	
	/**
	 * Turn left
	 */
	public Direction sub90() {
		this.angle = (this.angle + 270) % 360;
		return this;
	}
	
	/**
	 * Turn right
	 */
	public Direction add90() {
		this.angle = (this.angle + 90) % 360;
		return this;
	}
	
	public double getAngle() {
		return this.angle;
	}
}
