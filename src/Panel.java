import java.awt.*;
import javax.swing.*;

public class Panel extends JPanel {
	
	private Color backgroundColor;
	private int cornerRadius;
	
	JLabel label;
	
	public Panel(int radius, Color bgColor) {
		super();
		cornerRadius = radius;
		backgroundColor = bgColor;
	}
	
	protected void drawCircle(Graphics g, int x, int y, int r) {
		  x = x-(r/2);
		  y = y-(r/2);
		  g.fillOval(x,y,r,r);
	}
	
	protected void setPhoneBackground(Graphics g, Color color) {
		Graphics2D graphics = (Graphics2D) g;
		graphics.setPaint(color);
		graphics.fillRect(0, 75, 390, 475);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Dimension arcs = new Dimension(cornerRadius, cornerRadius);
		int height = this.getHeight();
		int width = this.getWidth();
		Graphics2D graphics = (Graphics2D) g;
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		// drawing the outline of the panel
		// checking if background color has value
		if (backgroundColor != null) {
			graphics.setColor(backgroundColor); // if it does, sets background color to that value
		} else {
			graphics.setColor(getBackground()); // if it doesn't, leaves background as it is
		}
		
		graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height); // paint background
		graphics.setColor(getForeground());
		graphics.drawRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height); // paint border
		
		graphics.setPaint(new Color(0, 0, 0));
		graphics.fillRect(0, 550, 390, 90);
		graphics.fillRect(0, 0, 390, 75);
		
		// adding home button
		graphics.setPaint(new Color(51, 51, 51));
		this.drawCircle(g, 200, 600, 50);
		this.drawCircle(g, 140, 32, 10);
		graphics.fillRoundRect(165, 27, 80, 10, 10, 10);
		
		this.setPhoneBackground(g, new Color(224, 79, 79));

	}
	

}
