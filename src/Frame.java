import javax.swing.*;

public class Frame extends JFrame {
	
	JPanel panel;
	JLabel label;

	Frame (String title, int x, int y) {
		super(title);
		setSize(x, y);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	/*
	public void set_icon_of_frame(JFrame window, String path) {
		this.icon_of_frame = path; 
		ImageIcon icon = new ImageIcon(path);
		window.setIconImage(icon.getImage());
	}
	*/
}
