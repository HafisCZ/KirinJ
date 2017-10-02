package graphics.render;

import java.util.Vector;

import graphics.shapes.Rectangle;

public class RenderBatch {

	private Canvas target = null;
	private Vector<RenderableObject> batch = new Vector<RenderableObject>();
	
	public RenderBatch(Canvas target) {
		this.target = target;
		if (this.target == null) {
			throw new IllegalArgumentException("Invalid target");
		}
		
		empty();
	}
	
	public Canvas getTarget() {
		return target;
	}
	
	public void add(RenderableObject object) {
		batch.addElement(object);
	}
	
	public boolean remove(RenderableObject object) {
		return batch.remove(object);
	}
	
	public RenderableObject remove(int index) {
		return batch.remove(index);
	}
	
	public void render() {
		for (RenderableObject obj : batch) {
			render(obj);
		}
	}
	
	private void render(RenderableObject obj) {
		if (obj instanceof CompositeRenderableObject) {
			for (RenderableObject sub : ((CompositeRenderableObject) obj).getObjects()) {
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
