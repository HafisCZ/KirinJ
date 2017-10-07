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
	
	public static Direction getDirection(double angle) {
		switch ((int) angle % 360) {
			case 0:
				return EAST;
			case 45:
				return NORTHEAST;
			case 90:
				return NORTH;
			case 135:
				return NORTHWEST;
			case 180:
				return WEST;
			case 225:
				return SOUTHWEST;
			case 270:
				return SOUTH;			
			case 315:
				return SOUTHEAST;
			default:
				return EAST;
		}
	}
	
	public static Direction getInverted(Direction direction) {
		return getDirection(direction.getAngle() + 180);
	}
	
	public static Direction getNextRight(Direction direction) {
		return getDirection(direction.getAngle() + 270);
	}
	
	public static Direction getNextLeft(Direction direction) {
		return getDirection(direction.getAngle() + 90);
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
