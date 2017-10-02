package graphics.render;

import java.awt.Color;

public interface RenderableObject {

	public static final Canvas CANVAS = Canvas.getInstance();
	
	public RenderableObject draw();
	
	public default Color getColor() {
		return Color.WHITE;
	}
	
	public default Color getStrokeColor() {
		return Color.BLACK;
	}
	
	public double getX();
	public double getY();
	public double getWidth();
	public double getHeight();
}
