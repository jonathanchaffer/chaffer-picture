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
	@Override
	boolean containsMouse() {
		return (getMouseX() >= getX() - 0.5 * diameter && getMouseX() <= getX() + 0.5 * diameter)
				&& (getMouseY() >= getY() - 0.5 * diameter && getMouseY() <= getY() + 0.5 * diameter);
	}

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
	public void spin() {
		if (spinning) {
			if (angle < 360) {
				angle += 10;
			} else {
				angle = 0;
			}
		}
	}

	public void toggle() {
		System.out.println("toggled");
		if (spinning) {
			spinning = false;
		} else {
			spinning = true;
		}
	}

	// private classes
	private class SpinHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			spin();
		}
	}
}