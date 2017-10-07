package kirin.graphics;

import java.awt.Color;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Font;
import kirin.graphics.shape.Arc;
import kirin.graphics.shape.Ellipse;
import kirin.graphics.shape.Rectangle;
import kirin.graphics.shape.Shape;
import kirin.graphics.shape.Triangle;

@SuppressWarnings("restriction")
public class CanvasPane extends Pane {
	
	private final static Color DEFAULT_BG_COLOR = Color.WHITE;
	private Color backgroundColor;
	
	private final javafx.scene.canvas.Canvas canvas;
	private GraphicsContext graphicsContext;

	public CanvasPane(double width, double height) {
		super();
		
		setWidth(width);
		setHeight(height);
		
		canvas = new javafx.scene.canvas.Canvas(width, height);
		graphicsContext = canvas.getGraphicsContext2D();
		setBackgroundColor(DEFAULT_BG_COLOR);
		getChildren().add(canvas);
	}
	
	public Color getBackgroundColor() {
		return backgroundColor;
	}
	
	public void setBackgroundColor(Color color) {
		backgroundColor = color;
		clear();
	}

	public void clear() {
		graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
	}
	
	public void fill(Shape obj) {
		fill(obj, obj.getFill());
	}
	
	public void stroke(Shape obj) {
		stroke(obj, obj.getStroke());
	}
	
	public void fill(String text, double x, double y, Color fillColor) {
		graphicsContext.setFill(getColor(fillColor));
		graphicsContext.setFont(new Font("arial", 15));
		graphicsContext.fillText(text, x, y + graphicsContext.getFont().getSize());
	}
	
	public void fill(Shape obj, Color fillColor) {
		graphicsContext.setFill(getColor(fillColor));
		
		if (obj instanceof Rectangle) {
			Rectangle retypedObj = (Rectangle) obj;
			graphicsContext.fillRect(retypedObj.getX(), retypedObj.getY(), retypedObj.getWidth(), retypedObj.getHeight());
		} else if (obj instanceof Triangle) {
			double[][] points = ((Triangle) obj).getVertices();
			graphicsContext.fillPolygon(points[0], points[1], points[0].length);
		} else if (obj instanceof Ellipse) {
			Ellipse retypedObj = (Ellipse) obj;
			graphicsContext.fillOval(retypedObj.getX(), retypedObj.getY(), retypedObj.getWidth(), retypedObj.getHeight());
		} else if (obj instanceof Arc) {
			Arc retypedObj = (Arc) obj;
			graphicsContext.fillArc(retypedObj.getX(), retypedObj.getY(), retypedObj.getWidth(), retypedObj.getHeight(), retypedObj.getAngleStart(), retypedObj.getAngleExtent(), ArcType.ROUND);
		}
	}
	
	public void stroke(Shape obj, Color strokeColor) {
		graphicsContext.setStroke(getColor(strokeColor));
		
		if (obj instanceof Rectangle) {
			Rectangle retypedObj = (Rectangle) obj;
			graphicsContext.strokeRect(retypedObj.getX(), retypedObj.getY(), retypedObj.getWidth(), retypedObj.getHeight());
		} else if (obj instanceof Triangle) {
			double[][] points = ((Triangle) obj).getVertices();
			graphicsContext.strokePolygon(points[0], points[1], points[0].length);
		} else if (obj instanceof Ellipse) {
			Ellipse retypedObj = (Ellipse) obj;
			graphicsContext.strokeOval(retypedObj.getX(), retypedObj.getY(), retypedObj.getWidth(), retypedObj.getHeight());
		} else if (obj instanceof Arc) {
			Arc retypedObj = (Arc) obj;
			graphicsContext.strokeArc(retypedObj.getX(), retypedObj.getY(), retypedObj.getWidth(), retypedObj.getHeight(), retypedObj.getAngleStart(), retypedObj.getAngleExtent(), ArcType.ROUND);
		}
	}
	
	private static Paint getColor(Color color) {
		return javafx.scene.paint.Color.rgb(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha() / 255.);
	}
}
