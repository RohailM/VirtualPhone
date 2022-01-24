package Calculator;
// 100% done by Rohail
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.lang.Math;

// this class is an extension of the JFrame class
// a calculator app with all the basic functions, has cool ui made by me :D
public class CalculatorApp extends JFrame {
	
	JTextField display; // declaring variable for display textfield
	JPanel buttonHolder; // declaring variable for the panel that will hold all calculator buttons
	JButton clrButton, sqrButton, sqrtButton, negButton, sevButton, eigButton, ninButton, addButton, fouButton, fivButton, sixButton, subButton, oneButton, twoButton, thrButton, mulButton, decButton, zerButton, eqButton, divButton; // declaring all function buttons

	Color white = new Color(255,255,255);
	Color red = new Color(210,104,103);
	Color green = new Color(26,83,90);
	
	double num1, num2, result = 0;
	char operator;
	
	public CalculatorApp() {
		this.setTitle("Calculator"); // setting title of frame to "Calculator"
		this.setIconImage(new ImageIcon("src/images/calculator_app.png").getImage());
		this.setSize(410, 600); // setting size of frame to 410x600px
		this.getContentPane().setBackground(new Color(25,28,34));
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // when closed, it only closes calculator frame and not phone frame
		this.setLocationRelativeTo(null); // moves window to center of screen
		
		display = new JTextField("0"); // initializing the text field with a value of 0
		display.setBounds(20, 60, 350, 70); // setting position and dimensions of display screen
		display.setEditable(false); // making it so user can't type in textfield
		display.setFont(new Font("Comic Sans MS", Font.PLAIN, 33)); // setting font of textfield to Comic Sans MS with size of 33px
		display.setHorizontalAlignment(SwingConstants.RIGHT); // sets alignment for text to right
		this.add(display);
		
		// initButton method can be found below, made this method for convenience purposes
		clrButton = initButton("CLR", 20, 160, green, event -> {
			display.setText("0");
		});
		
		sqrButton = initButton("SQR", 110, 160, green, event -> {
			double temp = Double.parseDouble(display.getText());
			double tempSquared = temp * temp;
			display.setText(String.valueOf(tempSquared));
		});
		
		sqrtButton = initButton("√", 200, 160, green, event -> {
			double temp = Double.parseDouble(display.getText());
			double tempRoot = Math.sqrt(temp);
			display.setText(String.valueOf(tempRoot));
		});
		
		negButton = initButton("+/-", 20, 480, green, event -> {
			double temp = Double.parseDouble(display.getText());
			double tempNeg = temp * -1;
			display.setText(String.valueOf(tempNeg));
		});
		
		sevButton = initButton("7", 20, 240, white, event -> {
			checkDisplay();
			display.setText(display.getText().concat("7"));
		});
		
		eigButton = initButton("8", 110, 240, white, event -> {
			checkDisplay();
			display.setText(display.getText().concat("8"));
		});
		
		ninButton = initButton("9", 200, 240, white, event -> {
			checkDisplay();
			display.setText(display.getText().concat("9"));
		});
		
		addButton = initButton("+", 290, 160, red, event -> {
			num1 = Double.parseDouble(display.getText());
			operator = '+';
			display.setText("");
		});
		
		fouButton = initButton("4", 20, 320, white, event -> {
			checkDisplay();
			display.setText(display.getText().concat("4"));
		});
		
		fivButton = initButton("5", 110, 320, white, event -> {
			checkDisplay();
			display.setText(display.getText().concat("5"));
		});
		
		sixButton = initButton("6", 200, 320, white, event -> {
			checkDisplay();
			display.setText(display.getText().concat("6"));
		});
		
		subButton = initButton("-", 290, 240, red, event -> {
			num1 = Double.parseDouble(display.getText());
			operator = '-';
			display.setText("");
		});
		
		oneButton = initButton("1", 20, 400, white, event -> {
			checkDisplay();
			display.setText(display.getText().concat("1"));
		});
		
		twoButton = initButton("2", 110, 400, white, event -> {
			checkDisplay();
			display.setText(display.getText().concat("2"));
		});
		
		thrButton = initButton("3", 200, 400, white, event -> {
			checkDisplay();
			display.setText(display.getText().concat("3"));
		});
		
		mulButton = initButton("x", 290, 320, red, event -> {
			num1 = Double.parseDouble(display.getText());
			operator = '*';
			display.setText("");
		});
		
		decButton = initButton(".", 200, 480, green, event -> {
			display.setText(display.getText().concat("."));
		});
		
		zerButton = initButton("0", 110, 480, white, event -> {
			checkDisplay();
			display.setText(display.getText().concat("0"));
		});
		
		eqButton = initButton("=", 290, 480, red, event -> {
			num2 = Double.parseDouble(display.getText());
			display.setText(String.valueOf(calcResult()));
			num1 = result;
		});
		
		divButton = initButton("÷", 290, 400, red, event -> {
			num1 = Double.parseDouble(display.getText());
			operator = '/';
			display.setText("");
		});
		
		this.setLayout(null);
	}
	
	/*
	 * This method initializes a button with the given parameters, and adds it to the frame afterwards.
	 * 
	 * @param	label	the text of the button	x	the x position of the button	y	the y position of the button	event	the button's actionListener (what the button does when its pressed)
	 * @return	returns the JButton fully customized
	 */
	private JButton initButton(String label, int x, int y, Color textColor, ActionListener event) {
		JButton button = new JButton(label);
		button.setBackground(new Color(38,41,49));
		button.setBounds(x, y, 80, 70);
		button.setFocusable(false);
		button.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		button.setForeground(textColor);
		button.addActionListener(event);
		this.add(button);
		
		return button;
	}
	
	// simple method to check if display has 0 as first digit, and if it does, it clears the textfield
	private void checkDisplay() {
		if (display.getText().indexOf("0") == 0) {
			display.setText("");
		}
	}
	
	/*
	 * This method uses the values stored in the variables num1 and num2 and combines them with the operator stored in the variable operator.
	 * 
	 * @param no parameters, uses instance variables
	 * @return returns the answer of the expression
	 */
	private double calcResult() {
		switch(operator) {
			case '+':
				return num1 + num2;
			case '-':
				return num1 - num2;
			case '*':
				return num1 * num2;
			case '/':
				return num1 / num2;
			default:
				return num2;
		}
	}
	
}
