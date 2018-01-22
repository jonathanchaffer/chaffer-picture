import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class DuctTape extends DeskObject {
	private int diameter;

	// constructors
	public DuctTape(int x, int y, int diameter) {
		super(x, y);
		this.diameter = diameter;
	}
	
	// overrides
	@Override
	public boolean containsMouse() {
		return (getMouseX() >= getX() - diameter / 3 && getMouseX() <= getX() + diameter / 1.2 + 10)
				&& (getMouseY() >= getY() - diameter / 3 && getMouseY() <= getY() + diameter / 1.2 + 10);
	}

	@Override
	public void draw(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(2));

		int tapeOffset = 10;
		while (tapeOffset > 0) {
			// draw one layer of tape
			ArrayList<Color> tapeColors = new ArrayList<Color>();
			tapeColors.add(Color.gray);
			tapeColors.add(Color.lightGray);
			int drawDiameter = 0;
			int tapeColorIdx = 0;
			while (drawDiameter <= diameter / 2) {
				// the tape itself
				g.setColor(tapeColors.get(tapeColorIdx));
				drawDiameter += 2;
				g2.drawOval((int) Math.round(getX() - drawDiameter / 2) + tapeOffset,
						(int) Math.round(getY() - drawDiameter / 2) + tapeOffset,
						(int) Math.round((diameter) / 2) + drawDiameter,
						(int) Math.round((diameter) / 2) + drawDiameter);
				// the inner cardboard part
				g.setColor(Color.darkGray);
				g2.drawOval(getX() + tapeOffset, getY() + tapeOffset, (int) Math.round((diameter) / 2),
						(int) Math.round((diameter) / 2));
				if (tapeColorIdx < tapeColors.size() - 1) {
					tapeColorIdx += 1;
				} else {
					tapeColorIdx = 0;
				}
			}
			// the outer edge
			g.setColor(Color.darkGray);
			g2.drawOval((int) Math.round(getX() - drawDiameter / 2) + tapeOffset,
					(int) Math.round(getY() - drawDiameter / 2) + tapeOffset,
					(int) Math.round((diameter) / 2) + drawDiameter, (int) Math.round((diameter) / 2) + drawDiameter);

			tapeOffset -= 1;
		}
	}
}
