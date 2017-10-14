package kirin.graphics;

import javafx.scene.image.Image;
import kirin.graphics.render.EditableObject;
import kirin.graphics.render.RenderableObject;

@SuppressWarnings("restriction")
public class Sprite implements RenderableObject, EditableObject {

	private double x, y, width, height;
	private Image image;
	
	public Sprite(String path, double x, double y, double width, double height) {
		this(new Image(path), x, y, width, height);
	}
	
	public Sprite(Image image, double x, double y, double width, double height) {
		this.image = image;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	@Override
	public double getX() {
		return this.x;
	}

	@Override
	public double getY() {
		return this.y;
	}

	@Override
	public double getWidth() {
		return this.width;
	}

	@Override
	public double getHeight() {
		return this.height;
	}
	
	public Image getImage() {
		return this.image;
	}

	public boolean isInside(double x, double y) {
		return (x >= this.x && x <= this.x + this.width && y >= this.y && y <= this.y + this.height);
	}

	@Override
	public void setX(double x) {
		this.x = x;
	}

	@Override
	public void setY(double y) {
		this.y = y;
	}

	@Override
	public void moveX(double dx) {
		this.x += dx;
	}

	@Override
	public void moveY(double dy) {
		this.y += dy;
	}

	@Override
	public void setWidth(double width) {
		this.width = width;
	}

	@Override
	public void setHeight(double height) {
		this.height = height;
	}
}
