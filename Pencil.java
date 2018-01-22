
/**
 * Pencil.java
 * Author: Jonathan Chaffer
 * Date: January 22, 2018
 * Version: 1.0
 * 
 * A class that models a pencil.
 *
 */

import java.awt.Color;
import java.awt.Graphics;

public class Pencil extends DeskObject {
	private int length;
	private Color color;
	private static final int width = 8;

	// constructors
	/**
	 * Constructor for objects of class Pencil.
	 * 
	 * @param x
	 *            The x-position.
	 * @param y
	 *            The y-position.
	 * @param length
	 *            The length of the pencil.
	 * @param color
	 *            The color of the pencil.
	 */
	public Pencil(int x, int y, int length, Color color) {
		super(x, y);
		this.length = length;
		this.color = color;
	}

	// overrides
	/**
	 * Checks whether the pencil contains the mouse.
	 * 
	 * @return true If the pencil contains the mouse.
	 * @return false If the pencil does not contain the mouse.
	 */
	@Override
	boolean containsMouse() {
		return (getMouseX() >= getX() && getMouseX() <= getX() + width)
				&& (getMouseY() >= getY() && getMouseY() <= getY() + length + 20);
	}

	/**
	 * Draws the pencil.
	 * 
	 * @param g
	 *            Graphics object to use.
	 */
	@Override
	void draw(Graphics g) {
		// draw shaft
		g.setColor(color);
		g.fillRect(getX(), getY(), width, length);

		// draw tip
		g.setColor(new Color(221, 205, 184));
		g.fillPolygon(new int[] { getX(), getX() + width, getX() + (int) Math.round(.5 * width) },
				new int[] { getY() + length, getY() + length, getY() + length + 20 }, 3);

		// draw lead
		g.setColor(Color.black);
		g.fillPolygon(
				new int[] { getX() + (int) Math.round(.25 * width), getX() + (int) Math.round(.75 * width),
						getX() + (int) Math.round(.5 * width) },
				new int[] { getY() + length + 10, getY() + length + 10, getY() + length + 20 }, 3);

		// draw eraser
		g.setColor(new Color(255, 140, 178));
		g.fillRect(getX(), getY(), width, 10);
		g.setColor(new Color(29, 175, 0));
		g.fillRect(getX(), getY() + 10, width, 15);
	}

}
