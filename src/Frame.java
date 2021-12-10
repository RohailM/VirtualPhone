import java.awt.*;
import javax.swing.*;
import java.awt.Color;

public class Frame extends JFrame {
	
	JPanel panel;
	JLabel label;

	Frame (String title, int x, int y, boolean resize, boolean visible) {
		super(title);
		setSize(x, y);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(resize);
		setVisible(visible);
		getContentPane().setBackground(new Color(21,63,130));
	}
	
	public void alignCenter() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	}
	
	public void setIconOfFrame(String path) {
		ImageIcon icon = new ImageIcon(path);
		this.setIconImage(icon.getImage());
	}
	
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawRoundRect(30, 50, 390, 650, 50, 50);
	}
}
