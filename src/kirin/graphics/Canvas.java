package kirin.graphics;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import kirin.graphics.shape.Arc;
import kirin.graphics.shape.Ellipse;
import kirin.graphics.shape.Rectangle;
import kirin.graphics.shape.Shape;
import kirin.graphics.shape.Triangle;
import kirin.input.adapter.KeyEventAdapter;
import kirin.input.adapter.MouseEventAdapter;

public class Canvas extends Application implements Runnable {
	
	private static Canvas canvas;
	private String[] args;
	
	private Group root;
	private javafx.scene.canvas.Canvas fxCanvas;
	private GraphicsContext fxContext;
	private KeyEventAdapter keyAdapter;
	private MouseEventAdapter mouseAdapter;
	
	private static double prefWidth = getDefaultWidth();
	private static double prefHeight = getDefaultHeight();
	
	public Canvas() {
		
	}
	
	public Canvas(String[] args) {
		this.args = args;
	}
	
	@Override
	public void run() {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) {
		synchronized (Canvas.class) {
			fxCanvas = new javafx.scene.canvas.Canvas(prefWidth, prefHeight);
			fxContext = fxCanvas.getGraphicsContext2D();
			
			canvas = this;
			
			root = new Group(fxCanvas);
			Scene scene = new Scene(root, prefWidth, prefHeight);
			
			this.keyAdapter = new KeyEventAdapter();
			this.mouseAdapter = new MouseEventAdapter();
			scene.addEventHandler(KeyEvent.ANY, keyAdapter);
			scene.addEventHandler(MouseEvent.ANY, mouseAdapter);
			
			stage.setTitle("KirinJ");
			stage.setScene(scene);
			stage.sizeToScene();
			
			stage.setResizable(false);
			stage.setAlwaysOnTop(true);
			stage.requestFocus();
			stage.show();

			stage.show();
			
			Canvas.class.notifyAll();
		}
	}
	
	public static void launchAsync(String... args) {
		new Thread(new Canvas(args)).start();
		synchronized (Canvas.class) {
			while (canvas == null) {
				try {
					Canvas.class.wait();
				} catch (InterruptedException e) {
					return;
				}
			}
		}
	}
	
	public static double getDefaultWidth() {
		return 750;
	}
	
	public static double getDefaultHeight() {
		return 500;
	}
	
	public static void requestResolution(double width, double height) {
		prefWidth = width;
		prefHeight = height;
	}
	
	/**
	 * Get existing instance of canvas or create new
	 * @return Instance
	 */
	protected static Canvas getInstance() {
		if (canvas == null) {
			launchAsync();
		}
		return canvas;
	}
	
	/**
	 * Get attached key adapter
	 * @return Attached KeyAdapter
	 */
	public KeyEventAdapter getKeyAdapter() {
		return this.keyAdapter;
	}
	
	/**
	 * 
	 * @param nodes
	 */
	public void removeNode(Node... nodes) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				for (Node node : nodes) {
					root.getChildren().remove(node);
				}
			}
		});
	}
	
	/**
	 * 
	 * @param nodes
	 */
	public void addNode(Node... nodes) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				for (Node node : nodes) {
					root.getChildren().add(node);
				}
			}
		});
	}
	
	/**
	 * Get attached mouse adapter
	 * @return Attached MouseAdapter
	 */
	public MouseEventAdapter getMouseAdapter() {
		return this.mouseAdapter;
	}

	/**
	 * Clear canvas
	 */
	public void clear() {
		fxContext.setFill(Color.WHITE);
		fxContext.fillRect(0, 0, prefWidth, prefHeight);
	}

	/**
	 * 
	 * @param text
	 * @param x
	 * @param y
	 * @param size
	 * @param color
	 */
	public void fill(String text, double x, double y, double size, Color color) {
		fxContext.setFill(color);
		fxContext.setFont(new Font("arial", size));
		fxContext.fillText(text, x, y + size);
	}
	
	/**
	 * 
	 * @param color
	 * @return
	 */
	public static Paint getColor(java.awt.Color color) {
		return javafx.scene.paint.Color.rgb(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha() / 255.0);
	}
	
	/**
	 * 
	 * @param sprite
	 */
	public void fill(Sprite sprite) {
		fxContext.drawImage(sprite.getImage(), sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
	}
	
	/**
	 * 
	 * @param obj
	 */
	public void fill(Shape obj) {
		fxContext.setFill(obj.hasFill() ? obj.getFill() : Color.BLACK);

		if (obj instanceof Rectangle) {
			Rectangle retypedObj = (Rectangle) obj;
			fxContext.fillRect(retypedObj.getX(), retypedObj.getY(), retypedObj.getWidth(), retypedObj.getHeight());
		} else if (obj instanceof Triangle) {
			double[][] points = ((Triangle) obj).getVertices();
			fxContext.fillPolygon(points[0], points[1], points[0].length);
		} else if (obj instanceof Ellipse) {
			Ellipse retypedObj = (Ellipse) obj;
			fxContext.fillOval(retypedObj.getX(), retypedObj.getY(), retypedObj.getWidth(), retypedObj.getHeight());
		} else if (obj instanceof Arc) {
			Arc retypedObj = (Arc) obj;
			fxContext.fillArc(retypedObj.getX(), retypedObj.getY(), retypedObj.getWidth(), retypedObj.getHeight(), retypedObj.getAngleStart(), retypedObj.getAngleExtent(), ArcType.ROUND);
		}
	}
	
	/**
	 * 
	 * @param obj
	 */
	public void stroke(Shape obj) {
		fxContext.setStroke(obj.hasStroke() ? obj.getStroke() : Color.BLACK);
		
		if (obj instanceof Rectangle) {
			Rectangle retypedObj = (Rectangle) obj;
			fxContext.strokeRect(retypedObj.getX(), retypedObj.getY(), retypedObj.getWidth(), retypedObj.getHeight());
		} else if (obj instanceof Triangle) {
			double[][] points = ((Triangle) obj).getVertices();
			fxContext.strokePolygon(points[0], points[1], points[0].length);
		} else if (obj instanceof Ellipse) {
			Ellipse retypedObj = (Ellipse) obj;
			fxContext.strokeOval(retypedObj.getX(), retypedObj.getY(), retypedObj.getWidth(), retypedObj.getHeight());
		} else if (obj instanceof Arc) {
			Arc retypedObj = (Arc) obj;
			fxContext.strokeArc(retypedObj.getX(), retypedObj.getY(), retypedObj.getWidth(), retypedObj.getHeight(), retypedObj.getAngleStart(), retypedObj.getAngleExtent(), ArcType.ROUND);
		}
	}
	
	public double getWidth() {
		return prefWidth;
	}
	

	public double getHeight() {
		return prefHeight;
	}
}
