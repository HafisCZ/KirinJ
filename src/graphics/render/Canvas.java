package graphics.render;

import java.awt.Color;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

@SuppressWarnings("restriction")
public class Canvas extends Application implements Runnable {
	
	private static Canvas canvas;
	private String[] args;
	
	private CanvasPane canvasPane;
	
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
			
			Scene scene = new Scene(canvasPane, canvasPane.getWidth(), canvasPane.getHeight());
			stage.setTitle("");
			stage.setScene(scene);
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
