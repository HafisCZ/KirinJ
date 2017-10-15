package kirin.input.adapter;

import java.util.HashMap;
import java.util.Map;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;

public class KeyEventAdapter implements EventHandler<KeyEvent> {

	private Map<KeyCode, Boolean> keyMap = new HashMap<KeyCode, Boolean>();
	private Map<KeyCode, Boolean> keyMapPrev = new HashMap<KeyCode, Boolean>();
	
	@Override
	public void handle(KeyEvent evt) {
		if (evt.getEventType().equals(KeyEvent.KEY_PRESSED)) {
			keyMap.put(evt.getCode(), true);
		} else if (evt.getEventType().equals(KeyEvent.KEY_RELEASED)) {
			keyMap.put(evt.getCode(), false);
		}
	}
	
	public void update() {
		for (Map.Entry<KeyCode, Boolean> entry : keyMap.entrySet()) {
			keyMapPrev.put(entry.getKey(), entry.getValue());
		}
	}
	
	public boolean isHeld(KeyCode key) {
		if (keyMap.containsKey(key)) {
			return keyMap.get(key);
		} else {
			return false;
		}
	}
	
	public boolean wasHeld(KeyCode key) {
		if (keyMapPrev.containsKey(key)) {
			return keyMapPrev.get(key);
		} else {
			return false;
		}
	}
	
	public boolean isPressed(KeyCode key) {
		return (isHeld(key) & !wasHeld(key));
	}

}
