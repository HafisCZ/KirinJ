package kirin.input;

import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import kirin.graphics.RenderHandler;
import kirin.input.adapter.KeyEventAdapter;
import kirin.input.adapter.MouseEventAdapter;

public class InputManager {
	
	private KeyEventAdapter adapter;
	private MouseEventAdapter mouseAdapter;
	
	/**
	 * 
	 * @param render RenderHandler
	 */
	public InputManager(RenderHandler render) {
		this.adapter = render.getCanvas().getKeyAdapter();
		this.mouseAdapter = render.getCanvas().getMouseAdapter();
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
		return mouseAdapter.isHeld(key);
	}

	/**
	 * 
	 * @param key MouseButton
	 * @return True if mouse button is pressed (on click / release only once)
	 */
	public boolean isMouseButtonPressed(MouseButton key) {
		return mouseAdapter.isPressed(key);
	}

	/**
	 * 
	 * @return Current mouse y coordinate
	 */
	public double getMouseX() {
		return mouseAdapter.getX();
	}
	
	/**
	 * 
	 * @return Current mouse y coordinate
	 */
	public double getMouseY() {
		return mouseAdapter.getY();
	}

	/**
	 * Update adapters
	 */
	public void updateAdapters() {
		this.adapter.update();
		this.mouseAdapter.update();
	}
	
}
