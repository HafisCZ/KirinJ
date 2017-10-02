import java.awt.Color;

import graphics.render.CompositeRenderableObject;
import graphics.render.RenderableObject;
import graphics.shapes.Rectangle;
import graphics.shapes.Triangle;
import utils.Colors;

public class House implements CompositeRenderableObject {
	
	private RenderableObject[] shps= new RenderableObject[16];

	private Color color1 = Colors.ORANGE_VIBRANT;
	private Color color2 = Colors.RED_BRIGHT;
	private Color color3 = Colors.RED_BRICK;

	private Color color4 = Colors.BROWN_COFFEE;
	private Color color5 = Colors.TERRA_COTTA;
	private Color color6 = Colors.IVORY;

	private double x, y, scale;

	public House(double x, double y) {
		this(x, y, 1);
	}

	public House(double x, double y, double scale) {
		this.x = x;
		this.y = y;
		this.scale = scale;
		drawHouse();
	}

	private void drawDoor(double x, double y, double width, double height) {
		shps[3] = new Rectangle(x - 1, y - height - 1, width + 2, height + 1, color4); // Ram
		shps[4] = new Rectangle(x, y - height, width, height, color5); // Dvere
		shps[5] = new Rectangle(x, y - height / 2, 3, 2, color4); // Klika
	}

	private void drawWindow(double x, double y, double pane_width, double pane_height, int shift) {
		shps[shift] = new Rectangle(x - 1, y - 1, 2 * pane_width + 3, 2 * pane_height + 3, color4); // Ram
		shps[shift + 1] = new Rectangle(x, y, pane_width, pane_height, color6); // Pane TL
		shps[shift + 2] = new Rectangle(x + pane_width + 1, y, pane_width, pane_height, color6); // Pane TR
		shps[shift + 3] = new Rectangle(x, y + pane_height + 1, pane_width, pane_height, color6); // Pane BL
		shps[shift + 4] = new Rectangle(x + pane_width + 1, y + pane_height + 1, pane_width, pane_height, color6); // Pane BR
	}
	
	public House drawHouse() {
		shps[0] = new Rectangle(x, y - 50 * scale, 80 * scale, 50 * scale, color1); // Steny
		shps[1] = new Rectangle(x + 60 * scale, y - 75 * scale, 5 * scale, 15 * scale, color2); // Komin
		shps[2] = new Triangle(x, y - 80 * scale, 80 * scale, 30 * scale, color3); // Strecha

		drawDoor(x + 10 * scale, y, 15 * scale, 30 * scale);
		drawWindow(x + 40 * scale, y - 30 * scale, 5 * scale, 10 * scale, 6);
		drawWindow(x + 60 * scale, y - 30 * scale, 5 * scale, 10 * scale, 11);

		return this;
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
		return 80;
	}

	@Override
	public double getHeight() {
		return 80;
	}

	@Override
	public RenderableObject[] getObjects() {
		return this.shps;
	}

}
