import graphics.render.CompositeRenderableObject;
import graphics.render.MoveableObject;
import graphics.render.RenderableObject;
import graphics.shapes.Arc;
import graphics.shapes.Ellipse;
import utils.Colors;
import utils.Direction8;


/*******************************************************************************
 * Kozusznik Jan
 * Copyright (c) 2014 All Right Reserved, http://www.kozusznik.cz
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 ******************************************************************************/

/**
 * @author Jan Kožusznik
 * @version 0.1
 */
public class Pacman implements CompositeRenderableObject, MoveableObject {

  private static final int SIZE = 30;

  private static final double SIZE_OF_EYE_PORTION = 0.15;

  private static final int ANGLE = 40;

  private Direction8 direction;

  private Ellipse eye;
  private Arc head;
  
  private int x, y;

  public Pacman(int x, int y, Direction8 direction) {
    this.x = x;
    this.y = y;
    this.direction = direction;
    this.head = new Arc(x, y, SIZE, SIZE, Colors.YELLOW_BRIGHT, direction.celemVzad(), computeAngle(), true);
    this.eye = new Ellipse(getEyeX(), getEyeY(), getEyeSize(), getEyeSize(), Colors.BLACK);
    draw();
  }
  
  public Direction8 getDirection() {
	  return this.direction;
  }

  public void setDirection(Direction8 direction8) {
    this.direction = direction8;
    //this.head.setDirection(direction.celemVzad());
    //this.eye.setPosition(getEyeX(), getEyeY());
  }
  
  public void setX(double x) {
	  setPosition(x, this.y);
  }
  
  public double getX() {
	  return this.x;
  }
  
  public double getY() {
	  return this.y;
  }
  
  public void setY(double y) {
	  setPosition(this.x, y);
  }

  public void moveX(double step) {
    setPosition(this.x + step, this.y);
  }

  public void moveY(double step) {
    setPosition(this.x, this.y + step);
  }

  private int getEyeSize() {
    return (int) getEyeSizeD();
  }

  private double getEyeSizeD() {
    return SIZE_OF_EYE_PORTION * SIZE;
  }

  private int computeAngle() {
    return 360 - ANGLE;
  }

  private int getEyeX() {
    switch (this.direction) {
      case EAST:
        return (int) (this.x + SIZE / 2 - getEyeSize() / 2 + getEyeSize() / 4);
      case WEST:
        return (int) (this.x + SIZE - getEyeSize() - SIZE / 2 + getEyeSize() / 2
            - getEyeSize() / 4);
      case NORTH:
      case SOUTH:
        return (int) (this.x + SIZE / 4);
      default:
        return 0;
    }

  }

  private int getEyeY() {
    switch (this.direction) {
      case EAST:
      case WEST:
        return (int) (this.y + SIZE / 4);
      case NORTH:
        return (int) (this.y + SIZE - getEyeSize() - SIZE / 2 + getEyeSize() / 2
            - getEyeSize() / 4);
      case SOUTH:
        return (int) (this.y + SIZE / 2 - getEyeSize() / 2 + getEyeSize() / 4);
      default:
        return 0;
    }

  }

	public double getWidth() {
		return SIZE;
	}
	
	public double getHeight() {
		return SIZE;
	}
	
	public RenderableObject[] getObjects() {
		return new RenderableObject[]{this.head, this.eye};
	}
	
	public Pacman draw() {
		CANVAS.fill(this.head);
		CANVAS.stroke(this.head);
		CANVAS.fill(this.eye);
		return this;
	}

	public void setWidth(double width) {
		// TODO Auto-generated method stub
		
	}

	public void setHeight(double height) {
		// TODO Auto-generated method stub
		
	}
	
}
