package kirin.input;

import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import kirin.graphics.Canvas;

@SuppressWarnings("restriction")
public class InputHandler {
	
	private KeyEventAdapter adapter;
	private MouseAdapter mouseAdapter;
	
	public InputHandler(Canvas canvas) {
		this.adapter = canvas.getKeyAdapter();
		this.mouseAdapter = canvas.getMouseAdapter();
	}
	
	public boolean isKeyHeld(KeyCode key) {
		return adapter.isHeld(key);
	}
	
	public boolean isKeyHeld(String key) {
		return adapter.isHeld(KeyCode.getKeyCode(key));
	}
	
	public boolean isKeyPressed(KeyCode key) {
		return adapter.isPressed(key);
	}
	
	public boolean isKeyPressed(String key) {
		return adapter.isPressed(KeyCode.getKeyCode(key));
	}
	
	public boolean isMouseButtonHeld(MouseButton key) {
		return mouseAdapter.isHeld(key);
	}
	
	public boolean isMouseButtonHeld(String key) {
		return mouseAdapter.isHeld(MouseButton.valueOf(key));
	}
	
	public boolean isMouseButtonPressed(MouseButton key) {
		return mouseAdapter.isPressed(key);
	}
	
	public boolean isMouseButtonPressed(String key) {
		return mouseAdapter.isPressed(MouseButton.valueOf(key));
	}
	
	public void updateKeyEventAdapter() {
		this.adapter.update();
		this.mouseAdapter.update();
	}
	
	
}
