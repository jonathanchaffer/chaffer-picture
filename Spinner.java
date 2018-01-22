
/**
 * Spinner.java
 * Author: Jonathan Chaffer
 * Date: January 22, 2018
 * Version: 1.0
 * 
 * A class that models a fidget spinner.
 *
 */

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Spinner extends DeskObject {
	private static int diameter = 60;
	private static int bearingSize = 30;
	private Color color;
	private int angle;
	private boolean spinning;
	private Timer timer;

	// constructors
	/**
	 * Constructor for objects of class Spinner.
	 * 
	 * @param x
	 *            The x-position.
	 * @param y
	 *            The y-position.
	 * @param color
	 *            The color.
	 */
	public Spinner(int x, int y, Color color) {
		super(x, y);
		this.color = color;
		angle = 0;
		spinning = true;

		SpinHandler sh = new SpinHandler();
		timer = new Timer(50, sh);
		timer.start();
	}

	// overrides
	/**
	 * Checks whether the spinner contains the mouse.
	 * 
	 * @return true If the spinner contains the mouse.
	 * @return false If the spinner does not contain the mouse.
	 */
	@Override
	boolean containsMouse() {
		return (getMouseX() >= getX() - 0.5 * diameter && getMouseX() <= getX() + 0.5 * diameter)
				&& (getMouseY() >= getY() - 0.5 * diameter && getMouseY() <= getY() + 0.5 * diameter);
	}

	/**
	 * Draws the spinner.
	 * 
	 * @param g
	 *            Graphics object to use.
	 */
	@Override
	void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(10));

		g.setColor(color);

		// draw bearings
		g.setColor(color);

		g2.drawOval(
				(int) (Math.round(getX() - 0.5 * diameter - 0.5 * bearingSize)
						+ .5 * diameter * Math.cos(Math.toRadians(angle + 0))),
				(int) (Math.round(getY() - 0.5 * diameter - 0.5 * bearingSize)
						+ .5 * diameter * Math.sin(Math.toRadians(angle + 0))),
				bearingSize, bearingSize);
		g2.drawOval(
				(int) (Math.round(getX() - 0.5 * diameter - 0.5 * bearingSize)
						+ .5 * diameter * Math.cos(Math.toRadians(angle + 120))),
				(int) (Math.round(getY() - 0.5 * diameter - 0.5 * bearingSize)
						+ .5 * diameter * Math.sin(Math.toRadians(angle + 120))),
				bearingSize, bearingSize);
		g2.drawOval(
				(int) (Math.round(getX() - 0.5 * diameter - 0.5 * bearingSize)
						+ .5 * diameter * Math.cos(Math.toRadians(angle + 240))),
				(int) (Math.round(getY() - 0.5 * diameter - 0.5 * bearingSize)
						+ .5 * diameter * Math.sin(Math.toRadians(angle + 240))),
				bearingSize, bearingSize);

		// draw center
		g2.drawOval((int) Math.round(getX() - 0.5 * diameter - 0.5 * bearingSize),
				(int) Math.round(getY() - 0.5 * diameter - 0.5 * bearingSize), bearingSize, bearingSize);
		g2.fillOval((int) Math.round(getX() - 0.5 * diameter - 0.5 * bearingSize),
				(int) Math.round(getY() - 0.5 * diameter - 0.5 * bearingSize), bearingSize, bearingSize);
		g2.setStroke(new BasicStroke(2));
		g2.setColor(color.darker());
		g2.drawOval((int) Math.round(getX() - 0.5 * diameter - 0.5 * (bearingSize * .8)),
				(int) Math.round(getY() - 0.5 * diameter - 0.5 * (bearingSize * .8)),
				(int) Math.round(bearingSize * .8), (int) Math.round(bearingSize * .8));
	}

	// mutators
	/**
	 * If spinning is true, add 10 to angle. If angle exceeds 360, set it back
	 * to 0.
	 */
	public void spin() {
		if (spinning) {
			if (angle < 360) {
				angle += 10;
			} else {
				angle = 0;
			}
		}
	}

	/**
	 * If spinning is true, set it to false. If spinning is false, set it to
	 * true.
	 */
	public void toggle() {
		if (spinning) {
			spinning = false;
		} else {
			spinning = true;
		}
	}

	// private classes
	/**
	 * Class that handles timer events on the Spinner.
	 */
	private class SpinHandler implements ActionListener {
		/**
		 * Handler for timer event. Calls the spinner's spin() method.
		 * 
		 * @param e
		 *            The ActionEvent.
		 */
		public void actionPerformed(ActionEvent e) {
			spin();
		}
	}
}