import java.awt.Color;
import java.awt.Graphics;

public class Pencil extends DeskObject {
	private int length;
	private Color color;
	private static final int width = 8;

	// constructors
	public Pencil(int x, int y, int length, Color color) {
		super(x, y);
		this.length = length;
		this.color = color;
	}

	// overrides
	@Override
	boolean containsMouse() {
		return (getMouseX() >= getX() && getMouseX() <= getX() + width)
				&& (getMouseY() >= getY() && getMouseY() <= getY() + length + 20);
	}

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
