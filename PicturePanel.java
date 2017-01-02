/**
 * PicturePanel.java
 * Author: Chuck Cusack
 * Date: August 22, 2007
 * Version: 2.0
 * 
 * Modified August 22, 2008
 *
 *An almost blank picture.
 *It just draws a few things.
 *
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

/**
 * A class draws a picture on a panel
 *
 */
public class PicturePanel extends JPanel 
{
    // A sample field.  I just keep track of number of mouse clicks.
    int numberOfClicks;
    
    /**
     * Get stuff ready so when paintComponent is called, it can draw stuff.
     * Depending on how complicated you want to get, your constructor may be blank.
     */
    public PicturePanel() 
    {
        // If you want to handle mouse events, you will need the following
        // 3 lines of code.  Just leave them as is and modify the methods
        // with "mouse" in the name toward the end of the class.
        // If you don't want to deal with mouse events, delete these lines.
        MouseHandler mh=new MouseHandler();
        addMouseListener(mh);
        addMouseMotionListener(mh);
        
        // Initialize number of mouse clicks to 0.
        numberOfClicks=0;
    }

    /**
     * This method is called whenever the applet needs to be drawn.
     * This is the most important method of this class, since without
     * it, we don't see anything.
     * 
     * This is the method where you will most likely do all of your coding.
     */
    public void paintComponent(Graphics g) 
    {
        // Always place this as the first line in paintComponent.
        super.paintComponent(g);
        
        // Some random things.  Remove and replace with your code.
	    g.setColor(Color.red);
	    g.fillRect(10,10,10,10);  
	    
	    g.setColor(Color.blue);
	    g.drawOval(100,200,300,200); 
	    
	    g.setColor(new Color(200,123,54));
	    g.drawString("Hello",200,300);
	    
	    
	    g.setColor(new Color(23,220,121));
	    g.fill3DRect(250, 50, 50, 100, true);
 
	    // Draw a string which tells how many mouse clicks
	    g.setColor(Color.black);
	    g.setFont(new Font("Times Roman",Font.BOLD,24));
        g.drawString("Number of clicks is "+numberOfClicks,40,40);
    }
    //------------------------------------------------------------------------------------------
    
     //---------------------------------------------------------------
    // A class to handle the mouse events for the applet.
    // This is one of several ways of handling mouse events.
    // If you do not want/need to handle mouse events, delete the following code.
    //
    private class MouseHandler extends MouseAdapter implements MouseMotionListener
    {
      
        public void mouseClicked(MouseEvent e) 
        {
            // Increment the number of mouse clicks
            numberOfClicks++;
            // After making any changes that will affect the way the screen is drawn,
            // you have to call repaint.
            repaint();
        }

        public void mouseEntered(MouseEvent e)
        {
        }
        public void mouseExited(MouseEvent e) 
        {
        }        
        public void mousePressed(MouseEvent e) 
        {
            
        }        
        public void mouseReleased(MouseEvent e) 
        {
        }
        public void mouseMoved(MouseEvent e) 
        {
        }
        public void mouseDragged(MouseEvent e) 
        {
        }
    }
    // End of MouseHandler class
    
} // Keep this line--it is the end of the PicturePanel class