package kirin.graphics.transition.type;

import javafx.scene.paint.Color;
import kirin.graphics.render.EditableObject;
import kirin.graphics.shape.Shape;
import kirin.graphics.transition.Transition;

public class Coloring extends Transition {

	public Color fill, stroke;
	
	/**
	 * 
	 * @param fill	New fill for shape
	 * @param stroke	New stroke for shape
	 */
	public Coloring(Color fill, Color stroke) {
		this(fill, stroke, 0);
	}
	
	/**
	 * 
	 * @param fill	New fill for shape
	 * @param stroke	New stroke for shape
	 * @param stepLength	Span of action
	 * @throws IllegalArgumentException
	 */
	public Coloring(Color fill, Color stroke, int stepLength) {
		super(stepLength);
		this.fill = fill;
		this.stroke = stroke;
	}

	@Override
	public boolean update(EditableObject entity, double ix, double iy, double iw, double ih, boolean supressTick) {
		if (!(entity instanceof Shape)) {
			throw new IllegalArgumentException("AnimationSequence.Animation.Coloring compatible only with objects derived from Shape class");
		}
		
		if (time > stepLength) {
			if (!this.fill.equals(null)) {
				((Shape) entity).setFill(this.fill);
			}
			if (!this.stroke.equals(null)) {
				((Shape) entity).setStroke(this.stroke);
			}
			if (!supressTick) {
				this.time = 1;
			}
			return true;
		} else {
			if (!supressTick) {
				this.time++;
			}
			return false;
		}		
	}
	
	public String toString() {
		return "F :" + this.fill.toString() + " \tS: " + this.stroke.toString();
	}

}
