import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
// 50% Rohail, 50% Furqaan
// this class is an extension of JFrame and is also the frame which holds the phone
public class Frame extends JFrame {
	
	Frame (String title, int x, int y, boolean resize) {
		super(title); // creating frame with the parameter title as title of frame
		setSize(x, y); // setting frames dimension to whatever arguments are given on Main.java
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // terminate the program when this frame is closed
		setResizable(resize); // disables resizing of frame
		alignCenter(this); //method created below, centers the frame on user's screen
		this.setIconOfFrame("src/images/phone_icon.png"); // method created below, sets icon of frame to the argument
		this.getContentPane().setBackground(new Color(21,63,130)); // sets background of frame to darkblue

		Panel phone = new Panel();
		phone.setLayout(new BoxLayout(phone, BoxLayout.Y_AXIS));
		phone.setBounds(25,30,390,640);
		phone.setOpaque(false);
		
		JPanel appArea = new JPanel();
		appArea.setBounds(55, 125, 330, 430);
		appArea.setLayout(new GridLayout(4, 3, 15, 25));
		appArea.setBackground(new Color(224, 79, 79));
		
		// initializing apps with both their names and icons 
		App weather = new App("Weather", "src/images/weather_app.png");
		App mcRonalds = new App("McRonalds", "src/images/mcronalds_app.png");
		App currencyConverter = new App("Currency", "src/images/currency_app.png");
		App notepad = new App("Notepad", "src/images/notepad_app.png");
		App clock = new App("Clock", "src/images/clock_app.png");
		App stopwatch = new App("Stopwatch", "src/images/stopwatch_app.png");
		App calculator = new App("Calculator", "src/images/calculator_app.png");
		App memory = new App("Memory", "src/images/memory_app.png");
		
		// adding all apps to the panel
		appArea.add(weather);
		appArea.add(mcRonalds);
		appArea.add(currencyConverter);
		appArea.add(notepad);
		appArea.add(clock);
		appArea.add(stopwatch);
		appArea.add(calculator);
		appArea.add(memory);
		
		// placeholder apps
		JButton nine = new JButton("9");
		nine.setVisible(false);
		appArea.add(nine);

		JButton ten = new JButton("10");
		ten.setVisible(false);
		appArea.add(ten);

		JButton eleven = new JButton("11");
		eleven.setVisible(false);
		appArea.add(eleven);

		JButton twelve = new JButton("12");
		twelve.setVisible(false);
		appArea.add(twelve);

		this.setLayout(null); // manual placement of all components
		this.add(appArea);
		this.add(phone);

		this.requestFocusInWindow();
	}
	
	/*
	 * This method takes a frame as a parameter, and centers said frame on the user's screen.
	 * 
	 * @param frame	the frame to be centered
	 * @return void	doesn't need to return anything
	 */
	public void alignCenter(JFrame frame) {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
	}
	
	/*
	 * This method sets the icon of a frame to the argument that is specified.
	 * 
	 * @param path	path to the image that frame's icon should be set to
	 * @return void	 simply sets icon of frame, no need to return anything
	 */
	public void setIconOfFrame(String path) {
		ImageIcon icon = new ImageIcon(path);
		this.setIconImage(icon.getImage());
	}
	
}
