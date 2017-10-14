package kirin.graphics;

import java.awt.Color;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import kirin.graphics.shape.Shape;
import kirin.input.adapter.KeyEventAdapter;
import kirin.input.adapter.MouseEventAdapter;

@SuppressWarnings("restriction")
public class Canvas extends Application implements Runnable {
	
	private static Canvas canvas;
	private String[] args;
	
	private CanvasPane canvasPane;
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
			canvasPane = new CanvasPane(prefWidth, prefHeight);
			canvas = this;
			
			Group root = new Group(canvasPane);
			Scene scene = new Scene(root, canvasPane.getWidth(), canvasPane.getHeight());
		
			this.keyAdapter = new KeyEventAdapter();
			this.mouseAdapter = new MouseEventAdapter();
			scene.addEventHandler(KeyEvent.ANY, keyAdapter);
			scene.addEventHandler(MouseEvent.ANY, mouseAdapter);
			
			stage.setScene(scene);
			stage.sizeToScene();
			stage.setResizable(false);
			
			stage.setTitle("KirinJ");
			stage.requestFocus();
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
	 * Get attached mouse adapter
	 * @return Attached MouseAdapter
	 */
	public MouseEventAdapter getMouseAdapter() {
		return this.mouseAdapter;
	}
	
	/**
	 * Set background color of canvas
	 * @param color Background color
	 */
	public void setBackgroundColor(Color color) {
		canvasPane.setBackgroundColor(color);
	}

	/**
	 * Get background color of canvas
	 * @return Background color
	 */
	public Color getBackgroundColor() {
		return canvasPane.getBackgroundColor();
	}

	/**
	 * Clear canvas
	 */
	public void clear() {
		canvasPane.clear();
	}
	
	/**
	 * Draw text onto canvas
	 * @param text	Text to be drawn
	 * @param x	Top left x
	 * @param y	Top left y
	 * @param size	Text size
	 * @param fill	Text color
	 */
	public void fill(String text, double x, double y, int size, Color fill) {
		canvasPane.fill(text, x, y, size, fill);
	}
	
	/**
	 * Fill shape
	 * @param obj	Shape
	 */
	public void fill(Shape obj) {
		canvasPane.fill(obj);
	}
	
	/**
	 * Stroke shape
	 * @param obj	Shape
	 */
	public void stroke(Shape obj) {
		canvasPane.stroke(obj);
	}
	
	/**
	 * 
	 * @param sprite
	 */
	public void fill(Sprite sprite) {
		canvasPane.fill(sprite);
	}
	
	/**
	 * 
	 * @return Canvas width
	 */
	public double getWidth() {
		return canvasPane.getWidth();
	}
	
	/**
	 * 
	 * @return Canvas height
	 */
	public double getHeight() {
		return canvasPane.getHeight();
	}
}
