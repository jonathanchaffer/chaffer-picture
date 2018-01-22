
/**
 * DeskObject.java
 * Author: Jonathan Chaffer
 * Date: January 22, 2018
 * Version: 1.0
 * 
 * An abstract class for objects on the desk.
 *
 */

import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

import javax.swing.JPanel;

public abstract class DeskObject extends JPanel {
	private static Point mousePointer;
	private int x;
	private int y;
	private int anchorX;
	private int anchorY;
	private boolean beingDragged;
	protected static Random r;

	// constructors
	/**
	 * Constructor for objects of class DeskObject.
	 * 
	 * @param x
	 *            The x-position.
	 * @param y
	 *            The y-position.
	 */
	public DeskObject(int x, int y) {
		super(null);
		this.x = x;
		this.y = y;
	}

	// static methods
	/**
	 * Assigns Point p to the static variable mousePointer.
	 * 
	 * @param p
	 */
	public static void setMousePointer(Point p) {
		mousePointer = p;
	}

	// abstract methods
	/**
	 * Abstract method to be overridden by subclasses.
	 * 
	 * @return true If the desk object contains the mouse.
	 * @return false If the desk object does not contain the mouse.
	 */
	abstract boolean containsMouse();

	/**
	 * Abstract method to be overridden by subclasses. Draws the desk object.
	 * 
	 * @param g
	 *            Graphics object to use.
	 */
	abstract void draw(Graphics g);

	// mutators
	/**
	 * Sets anchor points on the desk object. Sets beingDragged to true.
	 */
	public void anchor() {
		anchorX = getMouseX() - x;
		anchorY = getMouseY() - y;
		beingDragged = true;
	}

	/**
	 * Sets beingDragged to false.
	 */
	public void release() {
		beingDragged = false;
	}

	/**
	 * If beingDragged is true, desk object follows mouse pointer. Anchor values
	 * offset the object from the mouse position based on where they were
	 * originally anchored.
	 */
	public void reposition() {
		if (beingDragged) {
			x = getMouseX() - anchorX;
			y = getMouseY() - anchorY;
		}
	}

	// accessors
	/**
	 * @return The x-position.
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return The y-position.
	 */
	public int getY() {
		return y;
	}

	/**
	 * @return The mouse's x-position.
	 */
	public int getMouseX() {
		return (int) mousePointer.getX();
	}

	/**
	 * @return The mouse's y-position.
	 */
	public int getMouseY() {
		return (int) mousePointer.getY();
	}

}
