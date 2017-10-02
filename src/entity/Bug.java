package entity;

import java.awt.Color;

import graphics.shapes.Ellipse;
import utils.Colors;
import utils.Vec2d;

public class Bug extends Entity {
	
	private static final Color COLOR = Colors.BROWN_COFFEE;
	
	private static final int DEG_DIRECTION_SPREAD = 80;
	private static final int DIRECTION_RAND_FRAMECOUNT = 100;
	
	private int moveCycle = DIRECTION_RAND_FRAMECOUNT;
	private Vec2d direction = new Vec2d(RANDOM.nextInt(360));
	
	public Bug() {
		super(50 + RANDOM.nextInt((int)CANVAS.getWidth() - 100), 50 + RANDOM.nextInt((int)CANVAS.getHeight() - 100));
	}
	
	public Bug(double x, double y) {
		super(x, y);
	}

	@Override
	public Bug draw() {
		CANVAS.fill(new Ellipse(x - SIZE / 2, y - SIZE / 2, SIZE, SIZE, COLOR));
		return this;
	}
	
	public void update(Light l) {
		double distance = Math.sqrt((l.x - this.x) * (l.x - this.x) + (l.y - this.y) * (l.y - this.y));
		if (l.getState() && distance < l.getRadius()) {
			direction = new Vec2d(l.x - this.x, l.y - this.y).normalize().invert();
		} else if (moveCycle == DIRECTION_RAND_FRAMECOUNT) {
			direction = new Vec2d(direction.angle() + RANDOM.nextInt(DEG_DIRECTION_SPREAD) - DEG_DIRECTION_SPREAD / 2);
			moveCycle = 0;
		} else {
			moveCycle++;
		}
		
		move(direction);
	}
}
