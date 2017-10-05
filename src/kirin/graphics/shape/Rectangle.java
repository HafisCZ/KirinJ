package kirin.graphics.shape;

import java.awt.Color;

public class Rectangle extends Shape {

	public Rectangle(double x, double y, double size) {
		super(x, y, size, size);
	}
	
	public Rectangle(double x, double y, double width, double height) {
		super(x, y, width, height, Color.BLACK);
	}
	
	public Rectangle(double x, double y, double width, double height, Color fill) {
		super(x, y, width, height, fill);
	}
	
	public Rectangle(double x, double y, double width, double height, Color fill, Color stroke) {
		super(x, y, width, height, fill, stroke);
	}
}
