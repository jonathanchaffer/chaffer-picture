import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

/**
 * InteractivePicture draws a picture in a JFrame and lets you interact with it
 * in some way.
 */
public class InteractivePicture {
	// The desired dimensions of the main window
	// You may change these numbers, but don't make them too large--if they are
	// too large then
	// your application will not work well on smaller monitors.
	private int width = 1280;
	private int height = 720;
	// You may also change the title here.
	String myTitle = "Jon's Desk";
	// ------------------------------------------------------------------------------
	// ------------------------------------------------------------------------------
	// DO NOT CHANGE ANYTHING BELOW HERE.
	// ------------------------------------------------------------------------------
	// ------------------------------------------------------------------------------

	/**
	 * The default constructor. In this case, we don't want it to do anything.
	 */
	public InteractivePicture() {
	}

	// -------------------------------------------------------------
	// The rest of the class fields and methods allow us
	// to run this as a stand alone application.

	// The graphical components
	private PicturePanel mainPanel;

	/**
	 * The main method, which allows us to run the application. In other words,
	 * this is the method that is called when you run it as a Java application.
	 */
	public static void main(String[] args) {
		InteractivePicture myPicture = new InteractivePicture();
		myPicture.makeFrame();
	}

	/**
	 * Create the drawing panel and various other components that we need for
	 * our program.
	 */
	public void makeFrame() {

		JFrame theFrame = new JFrame();
		theFrame.setTitle(myTitle);
		theFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		;

		// Instantiate the main drawing panel
		mainPanel = new PicturePanel();

		// Place all of the graphical components on the main window
		Container cont = theFrame.getContentPane();
		// cont.setLayout(new BorderLayout());
		cont.add(mainPanel, BorderLayout.CENTER);

		// Finish setting up the main window
		theFrame.setBackground(Color.white);
		theFrame.pack();
		theFrame.setSize(new Dimension(width, height));
		theFrame.setVisible(true);
	}
}