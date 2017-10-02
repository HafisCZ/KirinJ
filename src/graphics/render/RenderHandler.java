package graphics.render;

import java.util.Vector;

import graphics.shapes.Rectangle;

public class RenderHandler {

	private Canvas target = null;
	private Vector<RenderableEntity> batch = new Vector<RenderableEntity>();
	
	public RenderHandler(Canvas target) {
		this.target = target;
		if (this.target == null) {
			throw new IllegalArgumentException("Invalid target");
		}
		
		empty();
	}
	
	public Canvas getTarget() {
		return target;
	}
	
	public void add(RenderableEntity object) {
		batch.addElement(object);
	}
	
	public boolean remove(RenderableEntity object) {
		return batch.remove(object);
	}
	
	public RenderableEntity remove(int index) {
		return batch.remove(index);
	}
	
	public void render() {
		for (RenderableEntity obj : batch) {
			render(obj);
		}
	}
	
	private void render(RenderableEntity obj) {
		if (obj instanceof CompositeEntity) {
			for (RenderableEntity sub : ((CompositeEntity) obj).getObjects()) {
				render(sub);
			}
		} else {
			obj.draw();
		}
	}
	
	public void empty() {
		batch.removeAllElements();
		batch.addElement(new Rectangle(0, 0, target.getWidth(), target.getHeight(), target.getBackgroundColor()));
	}
	
}
