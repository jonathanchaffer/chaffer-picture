import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Laptop extends DeskObject {
	private int width;
	private int height;
	private static final int depth = 10;

	// constructors
	public Laptop(int x, int y, int width, int height) {
		super(x, y);
		this.width = width;
		this.height = height;
	}

	// overrides
	@Override
	boolean containsMouse() {
		return (getMouseX() >= getX() && getMouseX() <= getX() + width)
				&& (getMouseY() >= getY() && getMouseY() <= getY() + height);
	}

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
