package kirin.util.math;

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
	
	public double getAngle() {
		return this.angle;
	}
}
