import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

public class Frame extends JFrame {
	
	JPanel panel;
	JLabel label;

	Frame (String title, int x, int y, boolean resize) {
		super(title);
		setSize(x, y);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(resize);
		
		alignCenter(this);
		this.setIconOfFrame("src/images/phone_icon.png");
		
		Container pane = this.getContentPane();
		pane.setLayout(null);
		pane.setBackground(new Color(21,63,130));
		
		Panel phone = new Panel();
		phone.setLayout(new BoxLayout(phone, BoxLayout.Y_AXIS));
		phone.setBounds(25,30,390,640);
		phone.setOpaque(false);
		
		JPanel appArea = new JPanel();
		appArea.setBounds(55, 125, 330, 430);
		appArea.setLayout(new GridLayout(4, 3, 15, 25));
		appArea.setBackground(new Color(224, 79, 79));
		
		App weather = new App("Weather", "src/images/weather_app.png");
		App mcRonalds = new App("McRonalds", "src/images/mcronalds_app.png");
		App currencyConverter = new App("Currency", "src/images/currency_app.png");
		App notepad = new App("Notepad", "src/images/notepad_app.png");
		App clock = new App("Clock", "src/images/clock_app.png");
		App stopwatch = new App("Stopwatch", "src/images/stopwatch_app.png");
		App calculator = new App("Calculator", "src/images/calculator_app.png");
		
		appArea.add(weather);
		appArea.add(mcRonalds);
		appArea.add(currencyConverter);
		appArea.add(notepad);
		appArea.add(clock);
		appArea.add(stopwatch);
		appArea.add(calculator);
		appArea.add(new JButton("8"));
		appArea.add(new JButton("9"));
		appArea.add(new JButton("10"));
		appArea.add(new JButton("11"));
		appArea.add(new JButton("12"));

		pane.add(appArea);
		pane.add(phone);

		this.requestFocusInWindow();
	}
	
	public void alignCenter(JFrame frame) {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
	}
	
	public void setIconOfFrame(String path) {
		ImageIcon icon = new ImageIcon(path);
		this.setIconImage(icon.getImage());
	}
	
}
