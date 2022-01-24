package Clock;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Timer;

import javax.imageio.ImageIO;
import javax.swing.*;

public class ClockApp extends JFrame {
	
	Calendar calendar;
	Date currTime;
	
	SimpleDateFormat timeFormat;
	SimpleDateFormat hour;
	SimpleDateFormat minute;
	SimpleDateFormat dateFormat;
	
	URL linkToClock;
	BufferedImage pictureOfClock;
	BufferedImage pictureOfClockScaled;
	
	Font customFont;
	JLabel clockLabel;
	JLabel timeLabel;
	
	String time;
	String date;
	
	public ClockApp() {
		this.setTitle("Clock");
		this.setIconImage(new ImageIcon("src/images/clock_icon.png").getImage());
		this.setSize(450, 500);
		this.getContentPane().setBackground(new Color(255, 255, 255));
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null); // moves window to center of screen
		
		timeFormat = new SimpleDateFormat("hh:mm:ss a");
		hour = new SimpleDateFormat("hh");
		minute = new SimpleDateFormat("mm");
		dateFormat = new SimpleDateFormat("EEEE, MMMM dd yyyy");
		
		try {
		    //create the font to use. Specify the size!
		    customFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/fonts/16Segments-Basic.otf")).deriveFont(16f);
		    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		    //register the font
		    ge.registerFont(customFont);
		} catch (IOException e) {
		    e.printStackTrace();
		} catch(FontFormatException e) {
		    e.printStackTrace();
		}
		
		timeLabel = new JLabel();
		timeLabel.setFont(customFont);
		timeLabel.setForeground(new Color(255, 0, 0));
		timeLabel.setBackground(Color.white);
		timeLabel.setOpaque(true);
		timeLabel.setHorizontalTextPosition(JLabel.CENTER);
		timeLabel.setVerticalTextPosition(JLabel.TOP);
		timeLabel.setHorizontalAlignment(JLabel.CENTER);
		timeLabel.setIconTextGap(20);
		
		this.add(timeLabel);
		this.setVisible(true);
		updateTime();
	}
	
	private static BufferedImage scale(final BufferedImage before, final double scale, final int type) {
	    int w = before.getWidth();
	    int h = before.getHeight();
	    int w2 = (int) (w * scale);
	    int h2 = (int) (h * scale);
	    BufferedImage after = new BufferedImage(w2, h2, before.getType());
	    AffineTransform scaleInstance = AffineTransform.getScaleInstance(scale, scale);
	    AffineTransformOp scaleOp = new AffineTransformOp(scaleInstance, type);
	    scaleOp.filter(before, after);
	    return after;
	}
	
	public void updateTime() {
		TimerTask task = new TimerTask() {
			public void run() {
				currTime = Calendar.getInstance().getTime();
				time = timeFormat.format(currTime);
				date = dateFormat.format(currTime);
				timeLabel.setText("<html>" + time + "<br>" + date + "<html>");
				
				try {
					linkToClock = new URL("https://res.cloudinary.com/demo/image/upload/$minute_" + minute.format(currTime) + "/$hour_" + hour.format(currTime) + "/$ma_$minute_div_60_mul_360/$ha_$hour_div_12_mul_360/l_clock_example:small/a_$ha_add_$ma_div_12/fl_layer_apply/l_clock_example:big/a_$ma/fl_layer_apply/w_550,h_550,c_crop/q_auto/clock_example/clock.png");
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				}
				
				try {
					pictureOfClock = ImageIO.read(linkToClock);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				pictureOfClockScaled = scale(pictureOfClock, 0.5, BufferedImage.TYPE_INT_ARGB);
				timeLabel.setIcon(new ImageIcon(pictureOfClockScaled));
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
	
		};
		
		Timer timer = new Timer();
		timer.schedule(task, 0, 1000);
	}
	
}