package kirin.graphics.shape;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import kirin.util.Direction;

public class Triangle extends Shape {

	protected Direction direction;

	/**
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Triangle(double x, double y, double width, double height) {
		this(x, y, width, height, Color.BLACK, Direction.NORTH);
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param color
	 */
	public Triangle(double x, double y, double width, double height, Color color) {
		this(x, y, width, height, color, Direction.NORTH);
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param direction
	 */
	public Triangle(double x, double y, double width, double height, Direction direction) {
		this(x, y, width, height, Color.BLACK, direction);
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param color
	 * @param direction
	 */
	public Triangle(double x, double y, double width, double height, Color color, Direction direction) {
		super(x, y, width, height, color);
		this.direction = direction;
	}
	
	/**
	 * 
	 * @return Direction
	 */
	public Direction getDirection() {
		return this.direction;
	}
	
	/**
	 * 
	 * @return Arrays of coordinates
	 */
	public double[][] getVertices() {
		double[] xPoints = null;
	    double[] yPoints = null;

	    switch (this.direction) {
		    case EAST:
		        xPoints = new double[]{this.x, this.x + (width), this.x};
		        yPoints = new double[]{this.y, this.y + (height / 2), this.y + height};
		        break;
	
		    case NORTHEAST:
		        xPoints = new double[]{this.x, this.x + width, this.x + width};
		        yPoints = new double[]{this.y, this.y, this.y + height};
		        break;
	
		    case NORTH:
		        xPoints = new double[]{this.x, this.x + (width / 2), this.x + width};
		        yPoints = new double[]{this.y + height, this.y, this.y + height};
		        break;
	
		    case NORTHWEST:
		        xPoints = new double[]{this.x, this.x, this.x + width};
		        yPoints = new double[]{this.y + height, this.y, this.y};
		        break;
	
		    case WEST:
		        xPoints = new double[]{this.x, this.x + width, this.x + width};
		        yPoints = new double[]{this.y + (height / 2), this.y, this.y + height};
		        break;
	
		    case SOUTHWEST:
		        xPoints = new double[]{this.x, this.x, this.x + width};
		        yPoints = new double[]{this.y, this.y + height, this.y + height};
		        break;
	
		    case SOUTH:
		        xPoints = new double[]{this.x, this.x + (width / 2), this.x + width};
		        yPoints = new double[]{this.y, this.y + height, this.y,};
		        break;
	
		    case SOUTHEAST:
		        xPoints = new double[]{this.x, this.x + width, this.x + width};
		        yPoints = new double[]{this.y + height, this.y + height, this.y};
		        break;
	    }
	    
	    return new double[][]{xPoints, yPoints};
	}

	@Override
	public Node getUnderlayingNode() {
		// TODO Auto-generated method stub
		return null;
	}
}
