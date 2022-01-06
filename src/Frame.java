import java.awt.*;
import javax.swing.*;

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
		
		Container pane = this.getContentPane();
		pane.setLayout(null);
		pane.setBackground(new Color(21,63,130));
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0,0,450,720);
		
		Panel phone = new Panel();
		phone.setLayout(new BoxLayout(phone, BoxLayout.Y_AXIS));
		phone.setBounds(25,30,390,640);
		phone.setOpaque(false);
		
		JPanel appArea = new JPanel();
		appArea.setBounds(55, 125, 330, 430);
		appArea.setLayout(new GridLayout(4, 4, 80, 80));
		appArea.setBackground(new Color(224, 79, 79));
		appArea.add(new JButton("1"));
		appArea.add(new JButton("2"));
		appArea.add(new JButton("3"));
		appArea.add(new JButton("4"));
		appArea.add(new JButton("5"));
		appArea.add(new JButton("6"));
		appArea.add(new JButton("7"));
		appArea.add(new JButton("8"));
		appArea.add(new JButton("9"));
		appArea.add(new JButton("10"));
		appArea.add(new JButton("11"));
		appArea.add(new JButton("12"));
		appArea.add(new JButton("13"));
		appArea.add(new JButton("14"));
		appArea.add(new JButton("15"));
		appArea.add(new JButton("16"));
		
		// appArea.setVisible(true);

		layeredPane.add(appArea);
		layeredPane.add(phone);
		
		pane.add(layeredPane);
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
