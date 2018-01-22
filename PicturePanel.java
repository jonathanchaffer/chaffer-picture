
/**
 * PicturePanel.java
 * Author: Chuck Cusack
 * Author: Jonathan Chaffer
 * Date: August 22, 2007
 * Version: 3.0
 * 
 * Modified January 22, 2018
 *
 * An interactive picture of a desk.
 *
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * A class that draws a picture on a panel.
 */
public class PicturePanel extends JPanel {
	ArrayList<DeskObject> deskObjects = new ArrayList<DeskObject>();

	/**
	 * Get stuff ready so when paintComponent is called, it can draw stuff.
	 * Depending on how complicated you want to get, your constructor may be
	 * blank.
	 */
	public PicturePanel() {
		MouseHandler mh = new MouseHandler();
		addMouseListener(mh);
		addMouseMotionListener(mh);

		Timer timer = new Timer(1, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				repaint();
			}
		});
		timer.start();

		// add some desk objects
		deskObjects.add(new Notebook(500, 100, 200, 250, Color.red, "csci235"));
		deskObjects.add(new Notebook(50, 200, 200, 250, Color.green, "comm280"));
		deskObjects.add(new Notebook(600, 300, 200, 250, Color.blue, "art105"));
		deskObjects.add(new Notebook(300, 500, 100, 150, Color.orange, "planner"));
		deskObjects.add(new DuctTape(300, 200, 100));
		deskObjects.add(new Pencil(900, 400, 150, Color.yellow));
		deskObjects.add(new Pencil(875, 450, 125, Color.black));
		deskObjects.add(new Laptop(830, 50, 350, 250));
		deskObjects.add(new Spinner(1000, 600, Color.red));
		deskObjects.add(new Spinner(1100, 500, Color.cyan));
	}

	/**
	 * This method is called whenever the applet needs to be drawn. This is the
	 * most important method of this class, since without it, we don't see
	 * anything.
	 */
	public void paintComponent(Graphics g) {

		// Always place this as the first line in paintComponent.
		super.paintComponent(g);

		// add the background
		Image bg = new ImageIcon("desk.jpg").getImage();
		g.drawImage(bg, 0, 0, null);

		// draw name
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.blue);
		g2.setFont(new Font("Arial", Font.BOLD, 18));

		FontMetrics fontMetrics = g2.getFontMetrics();
		String s = "Jonathan Chaffer";
		g2.drawString(s, 1260 - fontMetrics.stringWidth(s), 680);

		// draw instructions
		g2.drawString("Instructions:", 20, 20);

		g2.setFont(new Font("Arial", Font.PLAIN, 18));
		g2.drawString("Drag objects to move them around.", 20, 40);
		g2.drawString("Click anywhere to add a random new object.", 20, 60);
		g2.drawString("Shift-click an object to remove it.", 20, 80);
		g2.drawString("Right-click a spinner to start or stop it.", 20, 100);

		// draw all desk objects
		for (DeskObject deskObject : deskObjects) {
			deskObject.draw(g);
		}

	}

	/**
	 * Class that handles mouse events on the PicturePanel.
	 */
	private class MouseHandler extends MouseAdapter implements MouseMotionListener {
		Random r = new Random();

		/**
		 * Handler for mouse clicked. Shift-click removes a desk object.
		 * Right-click starts/stops a spinner.
		 * 
		 * @param e
		 *            The MouseEvent.
		 */
		public void mouseClicked(MouseEvent e) {
			if (e.isShiftDown()) {
				Iterator<DeskObject> it = deskObjects.iterator();
				while (it.hasNext()) {
					if (it.next().containsMouse()) {
						it.remove();
					}
				}
			} else if (e.getButton() == e.BUTTON3) {
				for (DeskObject deskObject : deskObjects) {
					if (deskObject.containsMouse()) {
						if (deskObject instanceof Spinner) {
							Spinner spinner = (Spinner) deskObject;
							spinner.toggle();
						}
					}
				}
			} else {
				DeskObject deskObjectToAdd = null;
				switch (new Random().nextInt(5)) {
				case 0:
					deskObjectToAdd = new Notebook(r.nextInt(1260), r.nextInt(700), r.nextInt(300) + 50,
							r.nextInt(500) + 50, new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)),
							"a notebook");
					break;
				case 1:
					deskObjectToAdd = new DuctTape(r.nextInt(1260), r.nextInt(700), r.nextInt(100) + 50);
					break;
				case 2:
					deskObjectToAdd = new Pencil(r.nextInt(1260), r.nextInt(700), r.nextInt(300) + 50,
							new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)));
					break;
				case 3:
					deskObjectToAdd = new Laptop(r.nextInt(1260), r.nextInt(700), r.nextInt(250) + 100,
							r.nextInt(150) + 100);
					break;
				case 4:
					deskObjectToAdd = new Spinner(r.nextInt(1260), r.nextInt(700),
							new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)));
				}
				deskObjects.add((DeskObject) deskObjectToAdd);
			}
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}

		/**
		 * Handler for mouse pressed. Puts an anchor on a desk object.
		 * 
		 * @param e
		 *            The MouseEvent.
		 */
		public void mousePressed(MouseEvent e) {
			DeskObject.setMousePointer(e.getPoint());
			for (DeskObject deskObject : deskObjects) {
				if (deskObject.containsMouse()) {
					deskObject.anchor();
				}
			}
		}

		/**
		 * Handler for mouse released. Releases an anchor on all desk objects.
		 * 
		 * @param e
		 *            The MouseEvent.
		 */
		public void mouseReleased(MouseEvent e) {
			for (DeskObject deskObject : deskObjects) {
				deskObject.release();
			}
		}

		public void mouseMoved(MouseEvent e) {
		}

		/**
		 * Handler for mouse dragged. Repositions all desk objects when mouse is
		 * dragged.
		 * 
		 * @param e
		 *            The MouseEvent.
		 */
		public void mouseDragged(MouseEvent e) {
			DeskObject.setMousePointer(e.getPoint());
			for (DeskObject deskObject : deskObjects) {
				deskObject.reposition();
			}
		}
	}
}