package kirin.graphics;

import java.awt.Color;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import kirin.graphics.render.RenderableEntity;
import kirin.input.KeyEventAdapter;
import kirin.input.MouseAdapter;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

@SuppressWarnings("restriction")
public class Canvas extends Application implements Runnable {
	
	private static Canvas canvas;
	private String[] args;
	
	private CanvasPane canvasPane;
	private KeyEventAdapter adapter;
	private MouseAdapter mouseAdapter;
	
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
			mouseAdapter = new MouseAdapter();
			
			Scene scene = new Scene(canvasPane, canvasPane.getWidth(), canvasPane.getHeight());
			scene.addEventFilter(KeyEvent.KEY_PRESSED, adapter);
			scene.addEventFilter(KeyEvent.KEY_RELEASED, adapter);
			scene.addEventFilter(MouseEvent.MOUSE_PRESSED, mouseAdapter);
			scene.addEventFilter(MouseEvent.MOUSE_RELEASED, mouseAdapter);
			
			stage.setScene(scene);	
			stage.setTitle("V01.B005 @ Hiraishin Software");
			
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
	
	public static Canvas getInstance() {
		if (canvas == null) {
			launchAsync();
		}
		return canvas;
	}
	
	public KeyEventAdapter getKeyAdapter() {
		return this.adapter;
	}
	
	public MouseAdapter getMouseAdapter() {
		return this.mouseAdapter;
	}
	
	public void setBackgroundColor(Color color) {
		canvasPane.setBackgroundColor(color);
	}
	
	public Color getBackgroundColor() {
		return canvasPane.getBackgroundColor();
	}

	public void clear() {
		canvasPane.clear();
	}
	
	public void fill(RenderableEntity obj) {
		canvasPane.fill(obj);
	}
	
	public void stroke(RenderableEntity obj) {
		canvasPane.stroke(obj);
	}
	
	public double getWidth() {
		return canvasPane.getWidth();
	}
	
	public double getHeight() {
		return canvasPane.getHeight();
	}
}
