package kirin.input;

import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import kirin.graphics.RenderHandler;
import kirin.input.adapter.EventAdapter;

public class InputManager {
	
	private EventAdapter adapter;
	
	/**
	 * 
	 * @param render RenderHandler
	 */
	public InputManager(RenderHandler render) {
		this.adapter = render.getCanvas().requestEventAdapter();
	}
	
	/** 
	 * @param key KeyCode
	 * @return True if keyboard button is held down (continual)
	 */
	public boolean isKeyHeld(KeyCode key) {
		return adapter.isHeld(key);
	}
	
	/**
	 * 
	 * @param key KeyCode
	 * @return True if keyboard button is pressed (on click / release only once)
	 */
	public boolean isKeyPressed(KeyCode key) {
		return adapter.isPressed(key);
	}

	/**
	 * 
	 * @param key MouseButton
	 * @return True if mouse button is held down (continual)
	 */
	public boolean isMouseButtonHeld(MouseButton key) {
		return adapter.isHeld(key);
	}

	/**
	 * 
	 * @param key MouseButton
	 * @return True if mouse button is pressed (on click / release only once)
	 */
	public boolean isMouseButtonPressed(MouseButton key) {
		return adapter.isPressed(key);
	}

	/**
	 * 
	 * @return Current mouse y coordinate
	 */
	public double getMouseX() {
		return adapter.getX();
	}
	
	/**
	 * 
	 * @return Current mouse y coordinate
	 */
	public double getMouseY() {
		return adapter.getY();
	}

	/**
	 * Update adapters
	 */
	public void update() {
		this.adapter.update();
	}
	
}
