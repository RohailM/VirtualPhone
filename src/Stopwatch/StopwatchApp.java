package Stopwatch;
// 100% Done By Rohail
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// This class is an extension of the JFrame class and implements an ActionListener to listen for input by the user.
// A basic stopwatch with all essential functionality.
public class StopwatchApp extends JFrame implements ActionListener {
	
	JLabel time;
	JButton startButton;
	JButton resetButton;
	
	int seconds;
	int minutes;
	int hours;
	
	int elapsed = 0;
	boolean started = false;
	
	// timer that runs task every 1000ms or 1s
	Timer timer = new Timer(1000, new ActionListener() {
		// this method increments elapsed by 1000 every second, and updates all other variables
		public void actionPerformed(ActionEvent e) {
			elapsed += 1000;
			seconds = elapsed / 1000;
			minutes = seconds / 60;
			hours = minutes / 60;
			time.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
		}
		
	});
	
	public StopwatchApp() {
		this.setTitle("Stopwatch");
		this.setIconImage(new ImageIcon("src/images/stopwatch_icon.png").getImage());
		this.setSize(400, 400);
		this.getContentPane().setBackground(new Color(165, 207, 201));
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null); // moves window to center of screen
		this.setLayout(null);

		time = new JLabel();
		time.setText("00:00:00");
		time.setBounds(100, 100, 200, 100);
		time.setFont(new Font("Verdana", Font.PLAIN, 20));
		time.setBorder(BorderFactory.createBevelBorder(1));
		time.setOpaque(true);
		time.setHorizontalAlignment(SwingConstants.CENTER);
		
		startButton = new JButton("Start");
		startButton.setBounds(100, 200, 100, 50);
		startButton.setFont(new Font("Ink Free", Font.PLAIN, 20));
		startButton.setFocusable(false);
		startButton.addActionListener(this);
		
		resetButton = new JButton("Reset");
		resetButton.setBounds(200, 200, 100, 50);
		resetButton.setFont(new Font("Ink Free", Font.PLAIN, 20));
		resetButton.setFocusable(false);
		resetButton.addActionListener(this);
		
		this.add(time);
		this.add(startButton);
		this.add(resetButton);		
		
	}

	// This method is called when an action is performed inside the app, it checks where the action originated from, and does the corresponding task.
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == startButton) {
			if (started == false) {
				started = true;
				startButton.setText("Stop");
				startTimer();
			}
			else {
				started = false;
				startButton.setText("Start");
				pauseTimer();
			}
		}
		
		if (e.getSource() == resetButton) {
			started = false;
			startButton.setText("Start");
			resetTimer();
		}
		
	}
	
	// this method simply starts the timer
	void startTimer() {
		timer.start();
	}
	
	// this method pauses the timer
	void pauseTimer() {
		timer.stop();
	}
	
	// this method resets the timer, and sets elapsed to 0
	void resetTimer() {
		timer.stop();
		elapsed = 0;
		time.setText("00:00:00");
	}
}
    
