import java.awt.*;
import javax.swing.*;

public class Panel extends JPanel {
	
	private Color backgroundColor;
	private int cornerRadius;
	
	JLabel label;
	
	public Panel() {
		
	}
	
	protected void drawCircle(Graphics g, int x, int y, int r) {
		  x = x-(r/2);
		  y = y-(r/2);
		  g.fillOval(x,y,r,r);
	}
	
	protected void setPhoneBackground(Graphics g, Color color) {
		Graphics2D graphics = (Graphics2D) g;
		graphics.setPaint(color);
		graphics.fillRect(0, 65, 390, 485);
	}
	
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
