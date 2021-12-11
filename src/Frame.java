import java.awt.*;
import javax.swing.*;
import java.awt.Color;

public class Frame extends JFrame {
	
	JPanel panel;
	JLabel label;

	Frame (String title, int x, int y, boolean resize) {
		super(title);
		setSize(x, y);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(resize);
		
		this.alignCenter();
		this.setIconOfFrame("src/images/phone_icon.png");
		
		Container pane = getContentPane();
		pane.setLayout(null);
		pane.setBackground(new Color(21,63,130));
		
		JPanel phoneOutline = new Panel(31, new Color(255,255,255));
		phoneOutline.setBounds(23,30,395,630);
		phoneOutline.setOpaque(false);
		pane.add(phoneOutline);
	}
	
	public void alignCenter() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	}
	
	public void setIconOfFrame(String path) {
		ImageIcon icon = new ImageIcon(path);
		this.setIconImage(icon.getImage());
	}
	/*
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawRoundRect(30, 50, 390, 650, 50, 50);
	}
	*/
	
}
