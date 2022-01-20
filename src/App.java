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

import Currency.CurrencyApp;
import McRonalds.McRonaldsApp;
import Weather.*;


public class App extends JButton implements ActionListener {
	String appName;
	BufferedImage icon;
	
	public App(String nameOfApp, String pathToIcon) {
		super(nameOfApp);
		setHorizontalTextPosition(AbstractButton.CENTER);
		setVerticalTextPosition(AbstractButton.BOTTOM);
	
		try {
			icon = ImageIO.read(new File(pathToIcon));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		setIcon(getScaledIcon(icon, 0.1));
		
		this.addActionListener(this);
		
		appName = nameOfApp;
	}
	
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
    
    public void actionPerformed(ActionEvent e) {
    	if (this.appName == "Weather") {
    		EventQueue.invokeLater(new Runnable() {
    			public void run() {
    				try {
    					WeatherApp window = new WeatherApp(new Controller(new Model( new Weather("Mississauga"))));
    					window.frame.setLocation(1920/2-window.frame.getSize().width/2, 1080/2-window.frame.getSize().height/2);
    					window.frame.setVisible(true);
    					window.frame.requestFocusInWindow();
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
    					window.setLocation(1920/2-window.getSize().width/2, 1080/2-window.getSize().height/2);
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
    					window.setLocation(1920/2-window.getSize().width/2, 1080/2-window.getSize().height/2);
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
