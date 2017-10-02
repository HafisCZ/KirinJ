import graphics.render.Canvas;
import graphics.render.CompositeEntity;
import graphics.render.MoveableEntity;
import graphics.render.RenderableEntity;
import graphics.shapes.Arc;
import graphics.shapes.Ellipse;
import utils.Colors;
import utils.Direction8;

public class Pacman implements CompositeEntity, MoveableEntity {

	private static final Canvas CANVAS = Canvas.getInstance();
	
	private static final double SIZE = 30, SIZE_OF_EYE_PORTION = 0.15, ANGLE = 40;
	
	private double x, y;
	private Direction8 direction;
	private RenderableEntity[] entities = new RenderableEntity[2]; 
	
	public Pacman(int x, int y, Direction8 direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
		entities[0] = new Arc(x, y, SIZE, SIZE, Colors.YELLOW_BRIGHT, direction.celemVzad(), 360 - ANGLE, true);
		entities[1] = new Ellipse(getEyeX(), getEyeY(), SIZE_OF_EYE_PORTION * SIZE);
	}

	public Direction8 getDirection() {
		return this.direction;
	}

	public void setDirection(Direction8 direction) {
		this.direction = direction;
		update();
	}
	
	private void update() {
		((Arc) entities[0]).setPosition(this.x, this.y);
		((Arc) entities[0]).setDirection(direction.celemVzad());
		((Ellipse) entities[1]).setPosition(getEyeX(), getEyeY());
	}
	
	public void setX(double x) {
		this.x = x;
		update();
	}
  
    public double getX() {
	    return this.x;
    }

    public void setY(double y) {
    	this.y = y;
    	update();
    }
    
    public double getY() {
	    return this.y;
    }
 
	public void moveX(double dx) {
		this.x += dx;
		update();
	}
	
	public void moveY(double dy) {
		this.y += dy;
		update();
	}
    
    private double getEyeX() {
    	switch(this.direction) {
    		case EAST:
    			return (this.x + SIZE / 2 - SIZE_OF_EYE_PORTION * SIZE / 2 + SIZE_OF_EYE_PORTION * SIZE / 4);
    		case WEST:
    			return (this.x + SIZE - SIZE_OF_EYE_PORTION * SIZE - SIZE / 2 + SIZE_OF_EYE_PORTION * SIZE / 2 - SIZE_OF_EYE_PORTION * SIZE / 4);
    		case NORTH:
    		case SOUTH:
    			return (this.x + SIZE / 4);
    		default:
    			return 0;
    	}
    }
	
    private double getEyeY() {
    	switch(this.direction) {
	    	case EAST:
	    	case WEST:
	    		return (this.y + SIZE / 4);
	    	case NORTH:
	    		return (this.y + SIZE - SIZE_OF_EYE_PORTION * SIZE - SIZE / 2 + SIZE_OF_EYE_PORTION * SIZE / 2 - SIZE_OF_EYE_PORTION * SIZE / 4);
	    	case SOUTH:
	    		return (this.y + SIZE / 2 - SIZE_OF_EYE_PORTION * SIZE / 2 + SIZE_OF_EYE_PORTION * SIZE / 4);
    		default:
    			return 0;
    	}
    }
	
	public RenderableEntity[] getObjects() {
		return this.entities;
	}
	
	public Pacman draw() {
		CANVAS.fill(entities[0]);
		CANVAS.stroke(entities[0]);
		CANVAS.fill(entities[1]);
		return this;
	}

	public void setWidth(double width) {
		throw new UnsupportedOperationException();
	}

	public void setHeight(double height) {
		throw new UnsupportedOperationException();
	}
}
