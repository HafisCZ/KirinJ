package kirin.input.adapter;

import java.util.HashMap;
import java.util.Map;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.MouseButton;

@SuppressWarnings("restriction")
public class MouseEventAdapter implements EventHandler<MouseEvent> {

	private double x = 0, y = 0;
	private Map<MouseButton, Boolean> buttonMap = new HashMap<MouseButton, Boolean>();
	private Map<MouseButton, Boolean> buttonMapPrev = new HashMap<MouseButton, Boolean>();
	
	@Override
	public void handle(MouseEvent evt) {
		if (evt.getEventType().equals(MouseEvent.MOUSE_PRESSED)) {
			buttonMap.put(evt.getButton(), true);
		} else if (evt.getEventType().equals(MouseEvent.MOUSE_RELEASED)) {
			buttonMap.put(evt.getButton(), false);
		}
		
		this.x = evt.getX();
		this.y = evt.getY();
	}
	
	public void update() {
		for (Map.Entry<MouseButton, Boolean> entry : buttonMap.entrySet()) {
			buttonMapPrev.put(entry.getKey(), entry.getValue());
		}
	}
	
	public boolean isHeld(MouseButton key) {
		if (buttonMap.containsKey(key)) {
			return buttonMap.get(key);
		} else {
			return false;
		}
	}
	
	public boolean wasHeld(MouseButton key) {
		if (buttonMapPrev.containsKey(key)) {
			return buttonMapPrev.get(key);
		} else {
			return false;
		}
	}
	
	public boolean isPressed(MouseButton key) {
		return (isHeld(key) & !wasHeld(key));
	}
	
	public double lastX() {
		return this.x;
	}
	
	public double lastY() {
		return this.y;
	}

}
