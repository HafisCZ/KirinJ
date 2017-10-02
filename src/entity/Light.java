package entity;

import java.awt.Color;

import graphics.shapes.Ellipse;
import utils.Vec2d;

public class Light extends Entity {
	
	private static final Color COLOR_SPREAD = new Color(255, 255, 0, 50);
	private static final Color COLOR_SOURCE = new Color(255, 255, 0, 100);
	
	private static final int DEG_DIRECTION_SPREAD = 80;
	private static final int DIRECTION_RAND_FRAMECOUNT = 100;
	
	private int moveCycle = DIRECTION_RAND_FRAMECOUNT;
	private Vec2d direction = new Vec2d(RANDOM.nextInt(360));
	
	private boolean state = false;
	private double radius;
	
	public Light(double radius) {
		this(50 + RANDOM.nextInt((int)CANVAS.getWidth() - 100), 50 + RANDOM.nextInt((int)CANVAS.getHeight() - 100), radius);
	}
	
	public Light(double x, double y, double radius) {
		super(x, y);
		this.radius = radius;
	}

	@Override
	public Light draw() {	
		if (state) {
			CANVAS.fill(new Ellipse(x - radius, y - radius, 2 * radius, 2 * radius, COLOR_SPREAD));
		}
		CANVAS.fill(new Ellipse(x - SIZE / 2, y - SIZE / 2, SIZE, SIZE, COLOR_SOURCE));
		return this;
	}
	
	public void update() {
		if (moveCycle == DIRECTION_RAND_FRAMECOUNT) {
			direction = new Vec2d(direction.angle() + RANDOM.nextInt(DEG_DIRECTION_SPREAD) - DEG_DIRECTION_SPREAD / 2);
			moveCycle = 0;
		} else {
			moveCycle++;
		}
		
		move(direction);
	}

	public double getRadius() {
		return this.radius;
	}
	
	public boolean getState() {
		return this.state;
	}
	
	public void setState(boolean state) {
		this.state = state;
	}
	
	public void toggleState() {
		this.state = !this.state;
	}
}
