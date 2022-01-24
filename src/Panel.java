import java.awt.*;
import javax.swing.*;
// 70% Rohail, 30% Furqaan
// panel holding the phone itself, this class was creating so we could paint on the phone
public class Panel extends JPanel {
	
	JLabel label;
	
	public Panel() {
		
	}
	
	/*
	 * This method draws a circle at the specified location with the given radius.
	 * 
	 * @param	x	x-coord of center of circle 	y	y-coord of center of circle		radius	radius of circle to be drawn
	 * @return	void doesn't need to return a value, it just draws a circle
	 */
	protected void drawCircle(Graphics g, int x, int y, int r) {
		  x = x-(r/2);
		  y = y-(r/2);
		  g.fillOval(x,y,r,r);
	}
	
	/*
	 * This method sets the background of the phone itself to the color specified.
	 * 
	 * @param	color	the color the background should be set to
	 * @return	void	no value is to be returned
	 */
	protected void setPhoneBackground(Graphics g, Color color) {
		Graphics2D graphics = (Graphics2D) g;
		graphics.setPaint(color);
		graphics.fillRect(0, 65, 390, 485);
	}
	
	/*
	 * This method is called when the object is created, and paints the aesthetics of the phone, such as the home button.
	 * 
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D graphics = (Graphics2D) g;
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		graphics.setPaint(new Color(0, 0, 0));
		graphics.fillRect(0, 0, 390, 65); // rectangle at top
		graphics.fillRect(0, 550, 390, 90); // rectangle at bottom
		
		// adding home button
		graphics.setPaint(new Color(51, 51, 51));
		this.drawCircle(g, 200, 600, 50);
		this.drawCircle(g, 140, 32, 10);
		graphics.fillRoundRect(165, 27, 80, 10, 10, 10);
		
		this.setPhoneBackground(g, new Color(224, 79, 79));

	}
	

}
