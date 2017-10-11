package kirin.graphics.animation.type;

import java.awt.Color;

import kirin.graphics.animation.Animation;
import kirin.graphics.render.EditableObject;
import kirin.graphics.shape.Shape;

public class Coloring extends Animation {

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
	public boolean update(EditableObject entity, double ix, double iy, double iw, double ih) {
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
			this.time = 1;
			return true;
		} else {
			this.time++;
			return false;
		}		
	}

}
