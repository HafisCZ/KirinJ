package kirin.input;

import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import kirin.graphics.Canvas;
import kirin.input.adapter.KeyEventAdapter;
import kirin.input.adapter.MouseEventAdapter;

@SuppressWarnings("restriction")
public class InputHandler {
	
	private KeyEventAdapter adapter;
	private MouseEventAdapter mouseAdapter;
	
	public InputHandler(Canvas canvas) {
		this.adapter = canvas.getKeyAdapter();
		this.mouseAdapter = canvas.getMouseAdapter();
	}
	
	public boolean isKeyHeld(KeyCode key) {
		return adapter.isHeld(key);
	}
	
	public boolean isKeyPressed(KeyCode key) {
		return adapter.isPressed(key);
	}

	public boolean isMouseButtonHeld(MouseButton key) {
		return mouseAdapter.isHeld(key);
	}

	public boolean isMouseButtonPressed(MouseButton key) {
		return mouseAdapter.isPressed(key);
	}

	public double getLastMouseX() {
		return mouseAdapter.lastX();
	}
	
	public double getLastMouseY() {
		return mouseAdapter.lastY();
	}

	public void updateAdapters() {
		this.adapter.update();
		this.mouseAdapter.update();
	}
	
}
