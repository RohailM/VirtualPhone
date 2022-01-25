import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

import Calculator.CalculatorApp;
import Clock.ClockApp;
import Currency.CurrencyApp;
import McRonalds.McRonaldsApp;
import Memory.MemoryApp;
import Stopwatch.StopwatchApp;
import Weather.*;
import notepad.NotepadApp;
// 100% Rohail
// This class is an extension of the JButton class, and is being used to model the apps of the phone and simplify their creation process. This class requires 2 arguments to be created, them being nameOfApp (text on JButton) and pathToIcon (used to get icon of JButton)
// It implements ActionListener so it can listen to when the app (button) is clicked.
public class App extends JButton implements ActionListener {
	String appName;
	BufferedImage icon;
	
	public App(String nameOfApp, String pathToIcon) {
		super(nameOfApp);
		setHorizontalTextPosition(AbstractButton.CENTER); // aligns the text to center of button horizontally
		setVerticalTextPosition(AbstractButton.BOTTOM); // aligns the text to bottom of button
		setFocusable(false);
	
		try {
			icon = ImageIO.read(new File(pathToIcon));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		setIcon(getScaledIcon(icon, 0.1)); // method created below, 10x smaller image
		
		this.addActionListener(this); // actionListener can be found below
		
		appName = nameOfApp;
	}
	
	/*
	 * This method resizes an image by the given scale.
	 * 
	 * @param	image	the image to be resized		scale 	the scale of which the image ought to be resized
	 * @return	ImageIcon	returns the resized image
	 */
    private ImageIcon getScaledIcon(final Image image, final double scale)
    {
        ImageIcon scaledIcon = new ImageIcon(image)
        {
            public int getIconWidth()
            {
                return (int)(image.getWidth(null) * scale);
            }
  
            public int getIconHeight()
            {
                return (int)(image.getHeight(null) * scale);
            }
  
            public void paintIcon(Component c, Graphics g, int x, int y)
            {
                g.drawImage(image, x, y, getIconWidth(), getIconHeight(), c);
            }
        };
        return scaledIcon;
    }
    
    // action listener for when button is pressed
    public void actionPerformed(ActionEvent e) {
    	// control flow based on the app that is clicked
    	if (this.appName == "Weather") {
    		EventQueue.invokeLater(new Runnable() {
    			public void run() {
    				try {
    					WeatherApp window = new WeatherApp(new City( new Weather("Mississauga")));
    					window.setVisible(true);
    					window.requestFocusInWindow();
    				} catch (Exception e) {
    					e.printStackTrace();
    				}
    			}
    		});
    	}
    	
    	else if (this.appName == "Currency") {
    		EventQueue.invokeLater(new Runnable() {
    			public void run() {
    				try {
    					CurrencyApp window = new CurrencyApp();
    					window.setVisible(true);
    					window.requestFocusInWindow();
    				} catch (Exception e) {
    					e.printStackTrace();
    				}
    			}
    		});
    	}
    	
    	else if (this.appName == "McRonalds") {
    		EventQueue.invokeLater(new Runnable() {
    			public void run() {
    				try {
    					McRonaldsApp window = new McRonaldsApp();
    					window.setVisible(true);
    					window.requestFocusInWindow();
    				} catch (Exception e) {
    					e.printStackTrace();
    				}
    			}
    		});    		
    	}
    	
    	else if (this.appName == "Notepad") {
    		EventQueue.invokeLater(new Runnable() {
    			public void run() {
    				try {
    					NotepadApp window = new NotepadApp();
    					window.setVisible(true);
    					window.requestFocusInWindow();
    				} catch (Exception e) {
    					e.printStackTrace();
    				}
    			}
    		});  
    	}
    	
    	else if (this.appName == "Clock") {
    		EventQueue.invokeLater(new Runnable() {
    			public void run() {
    				try {
    		    		ClockApp window = new ClockApp();
    		    		window.setVisible(true);
    					window.requestFocusInWindow();
    				} catch (Exception e) {
    					e.printStackTrace();
    				}
    			}
    		});
    	}
    	
    	else if (this.appName == "Stopwatch") {
    		EventQueue.invokeLater(new Runnable() {
    			public void run() {
    				try {
    					StopwatchApp window = new StopwatchApp();
    					window.setVisible(true);
    					window.requestFocusInWindow();
    				} catch (Exception e) {
    					e.printStackTrace();
    				}
    			}
    		});
    	}
    	
    	else if (this.appName == "Calculator") {
    		EventQueue.invokeLater(new Runnable() {
    			public void run() {
    				try {
    					CalculatorApp window = new CalculatorApp();
    					window.setVisible(true);
    					window.requestFocusInWindow();
    				} catch (Exception e) {
    					e.printStackTrace();
    				}
    			}
    		});
    	}
    	
    	else if (this.appName == "Memory") {
    		EventQueue.invokeLater(new Runnable() {
    			public void run() {
    				try {
    					MemoryApp window = new MemoryApp();
    					window.setVisible(true);
    					window.requestFocusInWindow();
    				} catch (Exception e) {
    					e.printStackTrace();
    				}
    			}
    		});
    	}
    	
    }
}
