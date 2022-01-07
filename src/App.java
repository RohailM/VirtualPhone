import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class App extends JButton {
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
}
