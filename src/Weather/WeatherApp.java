package Weather;
// 100% Rohail
import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

// This class extends the JFrame class, and is used to create instances of the app. A weather app that can retrieve the weather of any city.
public class WeatherApp extends JFrame {

	public JFrame frame;
	private JTextField cityField;
	private City city;
	private String currentTemp;
	private String longDescription;

	/**
	 * Create the application.
	 */
	public WeatherApp(City c) {
		frame = this;
		this.city = c;
		initialize(); // method created below
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame.setTitle("Weather Forecast");
		frame.setIconImage(new ImageIcon("src/images/weather_icon.png").getImage());
		frame.setResizable(false);
		frame.setBounds(100, 100, 355, 600);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null); // moves window to center of screen
		
		JPanel first = new JPanel();
		//frame.getContentPane().add(first, "home");
		JPanel cards = new JPanel(new CardLayout()); 
		cards.add(first, "home");
		frame.getContentPane().add(cards);
		first.setLayout(null);
		
		ImageIcon icon = new ImageIcon("src/images/blueBack.png");
		
		cityField = new JTextField();
		cityField.setForeground(new Color(128, 128, 128));
		cityField.setText("Search for a city...");
		cityField.setBounds(22, 292, 315, 50);
		first.add(cityField);
		cityField.setColumns(10);
		// when user doesn't have focus on text field, it shows "Search for a city...", when focus is gained it sets the textfield to empty
		cityField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
			    cityField.setText(""); 
			}

			public void focusLost(FocusEvent e) {
				if(cityField.getText().length() == 0) {
					cityField.setText("Search for a city...");
				}
			}
		});
		
		JLabel weatherLbl = new JLabel("Looking for the weather?");
		weatherLbl.setHorizontalAlignment(SwingConstants.CENTER);
		weatherLbl.setForeground(new Color(255, 255, 255));
		weatherLbl.setFont(new Font("Avenir Next", Font.BOLD, 22));
		weatherLbl.setBounds(22, 179, 315, 65);
		first.add(weatherLbl);
		
		JLabel greetingLbl = new JLabel("Hey there!");
		greetingLbl.setFont(new Font("Avenir Next", Font.BOLD, 53));
		greetingLbl.setForeground(new Color(255, 255, 255));
		greetingLbl.setHorizontalAlignment(SwingConstants.CENTER);
		greetingLbl.setBounds(12, 85, 331, 106);
		first.add(greetingLbl);
		
		
		JButton showWeather = new JButton("Let's go!");
		showWeather.setFont(new Font("Avenir Next", Font.BOLD, 15));
		showWeather.setBounds(92, 435, 170, 42);
		first.add(showWeather);
		JLabel background = new JLabel(icon);
		background.setBounds(0, 0, 355, 578);
		first.add(background);
		
		
		
		//---------------------------SECOND SCREEN --------------------------------------
		JPanel second = new JPanel();
		//frame.getContentPane().add(second, "weatherDisplay");
		cards.add(second, "weatherDisplay");
		second.setLayout(null);
		
		JLabel currentTempLbl = new JLabel("11");
		currentTempLbl.setHorizontalAlignment(SwingConstants.CENTER);
		currentTempLbl.setFont(new Font("Avenir Next", Font.BOLD, 99));
		currentTempLbl.setForeground(new Color(255, 255, 255));
		currentTempLbl.setBounds(6, 111, 330, 168);
		second.add(currentTempLbl);
		
		JLabel tempLbl = new JLabel("Temperature:");
		tempLbl.setHorizontalAlignment(SwingConstants.CENTER);
		tempLbl.setForeground(new Color(255, 255, 255));
		tempLbl.setFont(new Font("Avenir Next", Font.BOLD, 18));
		tempLbl.setBounds(30, 39, 306, 60);
		second.add(tempLbl);
		
		JLabel tempDescriptionText = new JLabel("It's");
		tempDescriptionText.setForeground(new Color(255, 255, 255));
		tempDescriptionText.setHorizontalAlignment(SwingConstants.CENTER);
		tempDescriptionText.setFont(new Font("Avenir Next", Font.BOLD, 24));
		tempDescriptionText.setBounds(30, 291, 289, 45);
		second.add(tempDescriptionText);
		
		JLabel tempBackground = new JLabel("New label");
		tempBackground.setIcon(new ImageIcon("src/images/blueBack1.jpg"));
		tempBackground.setBounds(0, 0, 355, 578);
		second.add(tempBackground);
		
		showWeather.addActionListener(new ActionListener()
	    {
				// when showWeather button is pressed, it gets the city from textfield, and checks if its a valid city
		       public void actionPerformed(ActionEvent e)
		       {
		    	   String city = cityField.getText();
		    	   if(validCity(city)) {
		    		   currentTemp = getTemp();
		    		   longDescription = getDesc().substring(0, 1).toUpperCase() + getDesc().substring(1);
		    		   tempLbl.setText("Current tempertaure in " + cityField.getText()+ ":"  );
		    		   currentTempLbl.setText(currentTemp + "C°");
		    		   tempDescriptionText.setText(longDescription);
		    		   
		    		   
		    		   CardLayout cardLayout = (CardLayout) cards.getLayout();
		    		   cardLayout.show(cards, "weatherDisplay");
		    		 
		    	   }else {
		    		   JOptionPane.showMessageDialog(frame, "Please Enter a valid City");
		    	   }
		    	   
		    	   }
		       
		       });
		
		
		}
	
	/*
	 * This method initializes the class city with the given argument, and then returns a field (boolean) that says whether the city is valid.
	 * 
	 * @param	s	the city to check
	 * @return	boolean		whether or not the city is real
	 */
	public boolean validCity (String s) {
		this.city.init(s);
		return this.city.valid;
	}
	
	/*
	 * This method accesses the getCurrentTemp method of the class city, in order to retrieve its temperature.
	 * 
	 * @param	none, in the above method we already initialized an object of city class
	 * @return	String		a string that contains the temperature of the city
	 */
	public String getTemp() {
		return city.getCurrentTemp();
	}
	
	/*
	 * This method accesses the getLongDescription method of the class city, in order to retrieve a description of the city's weather condition.
	 * 
	 * @param	none, in the validCity method, we already initialized an object of the class city, so we can simply access its methods
	 * @return	String	a string that contains a long description of the city's current climate
	 */
	public String getDesc() {
		return this.city.getLongDescription();
	}
	
}
