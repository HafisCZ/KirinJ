package kirin.graphics.shape;

import java.awt.Color;

public class Ellipse extends Shape {
  
	public Ellipse(double x, double y, double size) {
		super(x, y, size, size);
	}
	
	public Ellipse(double x, double y, double height, double width) {
		super(x, y, width, height, Color.BLACK);
	}
	
	public Ellipse(double x, double y, double height, double width, Color fill) {
		super(x, y, width, height, fill);
	}
	
	public Ellipse(double x, double y, double height, double width, Color fill, Color stroke) {
		super(x, y, width, height, fill, stroke);
	}
}
