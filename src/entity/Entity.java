package entity;

import java.util.Random;

import utils.Vec2d;

public abstract class Entity implements graphics.render.RenderableObject {
	
	public static final Random RANDOM = new Random();
	
	protected static final double SIZE = 20;
	protected static final double SPEED = 1;
	
	protected double x, y;
	
	public Entity(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public void update() {
		
	}
	
	protected boolean collidesWithBorder(double x, double y) {
		return (x - SIZE / 2 < 0 || x + SIZE / 2 > CANVAS.getWidth() || y - SIZE / 2 < 0 || y + SIZE / 2 > CANVAS.getHeight());
	}
	
	public void move(Vec2d vec) {
		if (collidesWithBorder(this.x + vec.x() * SPEED, this.y + vec.y() * SPEED)) {
			vec.invert();
		}
		
		this.x += vec.x() * SPEED;
		this.y += vec.y() * SPEED;
	}
	
	public double getX() {
		return this.x;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public double getY() {
		return this.y;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public double getWidth() {
		return SIZE;
	}
	
	public void setWidth(double width) {
		
	}
	
	public double getHeight() {
		return SIZE;
	}
	
	public void setHeight(double height) {
		
	}
}
