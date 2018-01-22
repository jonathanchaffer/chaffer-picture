import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Notebook extends DeskObject {
	private int width;
	private int height;
	private Color color;
	private String title;

	// constructors
	public Notebook(int x, int y, int width, int height, Color color, String title) {
		super(x, y);
		this.width = width;
		this.height = height;
		this.color = color;
		this.title = title;
	}

	// overrides
	@Override
	public boolean containsMouse() {
		return (getMouseX() >= getX() && getMouseX() <= getX() + width)
				&& (getMouseY() >= getY() && getMouseY() <= getY() + height);
	}

	@Override
	public void draw(Graphics g) {

		// draw pages
		ArrayList<Color> pageColors = new ArrayList<Color>();
		pageColors.add(Color.white);
		pageColors.add(Color.lightGray);
		int pageColorIdx = 0;
		int pageOffset = 5;
		while (pageOffset > 0) {
			g.setColor(pageColors.get(pageColorIdx));
			g.fillRect(getX() + pageOffset, getY() + pageOffset, width, height);
			pageOffset -= 1;
			if (pageColorIdx < pageColors.size() - 1) {
				pageColorIdx += 1;
			} else {
				pageColorIdx = 0;
			}
		}

		// draw cover
		g.setColor(color);
		g.fillRect(getX(), getY(), width, height);

		// draw binding
		int bindingY = 0;
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(2));
		while (bindingY < height) {
			g.setColor(Color.gray);
			g2.drawOval(getX() - 4, getY() + bindingY, 8, 4);
			g.setColor(color);
			g.fillRect(getX(), getY() + bindingY + 2, 5, 2);
			bindingY += 6;
		}

		// draw label
		g.setColor(Color.white);
		g.fillRect((int) Math.round(getX() + 0.25 * width), (int) Math.round(getY() + 0.1 * height),
				(int) Math.round(0.5 * width), (int) Math.round(0.2 * height));
		g.setColor(Color.black);
		g.setFont(new Font("Courier New", 1, (int) Math.round(0.5 * width / (title.length() * .6))));
		g.drawString(title, (int) Math.round(getX() + 0.25 * width), (int) Math.round(getY() + 0.225 * height));
	}

	// accessors
	public Color getColor() {
		return color;
	}
}
