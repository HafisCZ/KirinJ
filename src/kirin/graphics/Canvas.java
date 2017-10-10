package kirin.graphics;

import java.awt.Color;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import kirin.graphics.shape.Shape;
import kirin.input.adapter.KeyEventAdapter;
import kirin.input.adapter.MouseEventAdapter;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

@SuppressWarnings("restriction")
public class Canvas extends Application implements Runnable {
	
	private static Canvas canvas;
	private String[] args;
	
	private CanvasPane canvasPane;
	private KeyEventAdapter adapter;
	private MouseEventAdapter mouseAdapter;
	
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
			canvasPane = new CanvasPane(750, 500);
			canvas = this;
			
			adapter = new KeyEventAdapter();
			mouseAdapter = new MouseEventAdapter();
			
			Scene scene = new Scene(canvasPane, canvasPane.getWidth(), canvasPane.getHeight());
			scene.addEventHandler(KeyEvent.ANY, adapter);
			scene.addEventHandler(MouseEvent.ANY, mouseAdapter);

			stage.setScene(scene);
			stage.sizeToScene();
			stage.setResizable(false);
			
			stage.setTitle("KirinJ V01.B005");
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
	
	/**
	 * Get existing instance of canvas or create new
	 * @return Instance
	 */
	public static Canvas getInstance() {
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
		return this.adapter;
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
