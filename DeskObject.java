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
	public DeskObject(int x, int y) {
		super(null);
		this.x = x;
		this.y = y;
	}

	// static methods
	public static void setMousePointer(Point p) {
		mousePointer = p;
	}

	// abstract methods
	abstract boolean containsMouse();

	abstract void draw(Graphics g);

	// mutators
	public void anchor() {
		anchorX = getMouseX() - x;
		anchorY = getMouseY() - y;
		beingDragged = true;
	}

	public void release() {
		beingDragged = false;
	}

	public void reposition() {
		if (beingDragged) {
			x = getMouseX() - anchorX;
			y = getMouseY() - anchorY;
		}
	}

	// accessors
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getMouseX() {
		return (int) mousePointer.getX();
	}

	public int getMouseY() {
		return (int) mousePointer.getY();
	}

}
