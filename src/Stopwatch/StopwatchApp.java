package Stopwatch;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StopwatchApp extends JFrame implements ActionListener {
	
	JLabel time;
	JButton startButton;
	JButton resetButton;
	
	int seconds;
	int minutes;
	int hours;
	
	int elapsed = 0;
	boolean started = false;
	
	Timer timer = new Timer(1000, new ActionListener() {
		
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
	
	void startTimer() {
		timer.start();
	}
	
	void pauseTimer() {
		timer.stop();
	}
	
	void resetTimer() {
		timer.stop();
		elapsed = 0;
		time.setText("00:00:00");
	}
}
    
