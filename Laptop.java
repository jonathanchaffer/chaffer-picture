
/**
 * Laptop.java
 * Author: Jonathan Chaffer
 * Date: January 22, 2018
 * Version: 1.0
 * 
 * A class that models a laptop.
 *
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Laptop extends DeskObject {
	private int width;
	private int height;
	private static final int depth = 10;

	// constructors
	/**
	 * Constructor for objects of class Laptop.
	 * 
	 * @param x
	 *            The x-position.
	 * @param y
	 *            The y-position.
	 * @param width
	 *            The width.
	 * @param height
	 *            The height.
	 */
	public Laptop(int x, int y, int width, int height) {
		super(x, y);
		this.width = width;
		this.height = height;
	}

	// overrides
	/**
	 * Checks whether the laptop contains the mouse.
	 * 
	 * @return true If the laptop contains the mouse.
	 * @return false If the laptop does not contain the mouse.
	 */
	@Override
	boolean containsMouse() {
		return (getMouseX() >= getX() && getMouseX() <= getX() + width)
				&& (getMouseY() >= getY() && getMouseY() <= getY() + height);
	}

	/**
	 * Draws the laptop.
	 * 
	 * @param g
	 *            Graphics object to use.
	 */
	@Override
	void draw(Graphics g) {
		// draw the laptop itself
		g.setColor(new Color(214, 214, 214));
		int offset = depth;
		while (offset >= 0) {
			g.fillRoundRect(getX() + offset, getY() + offset, width, height, 30, 30);
			if ((int) Math.round(depth / 2) == offset) {
				g.setColor(Color.gray);
				g.fillRoundRect(getX() + offset, getY() + offset, width, height, 30, 30);
				g.setColor(new Color(232, 232, 232));
			}
			offset -= 1;
		}
		g.setColor(new Color(214, 214, 214));
		g.drawRoundRect(getX() + offset, getY() + offset, width, height, 30, 30);

		// draw the apple logo
		Image apple = new ImageIcon("apple.png").getImage();
		g.drawImage(apple, getX() + (int) Math.round((width * 0.5)) - 25,
				getY() + (int) Math.round((height * 0.5)) - 25, null);
	}
}
