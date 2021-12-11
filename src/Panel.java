import java.awt.*;
import javax.swing.*;

public class Panel extends JPanel {
	private Color backgroundColor;
	private int cornerRadius;
	
	public Panel(int radius, Color bgColor) {
		super();
		cornerRadius = radius;
		backgroundColor = bgColor;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Dimension arcs = new Dimension(cornerRadius, cornerRadius);
		int height = this.getHeight();
		int width = this.getWidth();
		Graphics2D graphics = (Graphics2D) g;
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		// drawing the outline of the phone
		// checking if background color has value
		if (backgroundColor != null) {
			graphics.setColor(backgroundColor); // if it does, sets background color to that value
		} else {
			graphics.setColor(getBackground()); // if it doesn't, leaves background as it is
		}
		
		graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height); // paint background
		graphics.setColor(getForeground());
		graphics.drawRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height); // paint border
	}
}
