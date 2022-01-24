package McRonalds;
// 100% Done By Rohail
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import javax.imageio.ImageIO;
import javax.swing.*;

// This class is an extension of the JFrame class and represents the McRonald App. An app with 3 items available for order.
public class McRonaldsApp extends JFrame {
	
	JLabel output = new JLabel();
	JPanel orderPanel = new JPanel();
	JPanel orderOptions = new JPanel();
	JTextField input;
	
	String[] menu = {"Big Mac", "Happy Meal", "Pop"};
	ArrayList<String> order = new ArrayList<String>();
	boolean orderStatus;
	char userChoice;
	int count = 0;
	
    double bigMacPrice = 5.69;
    double happyMealPrice = 4.79;
    double sodaPrice = 1.49;
	
	public McRonaldsApp() {
		try {
			this.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("src/images/order_station.png")))));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.setTitle("McRonalds");
        this.setIconImage(new ImageIcon("src/images/mcronalds_app.png").getImage());
		this.setSize(318, 774);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
		this.setLocationRelativeTo(null); // moves window to center of screen
		
		orderPanel.setBackground(new Color(255,255,255));
		orderPanel.setBounds(65, 55, 185, 330);
		orderPanel.setLayout(new GridBagLayout());
		
		output.setText("<html>Welcome To McRonalds!<br>Would you like to create an order? (y/n)</html>");
		output.setFont(new Font("MV Boli", Font.BOLD, 9));
		
		orderOptions.setBackground(new Color(56,59,57));
		orderOptions.setPreferredSize(new Dimension(318, 64));
		orderOptions.setLayout(new FlowLayout(FlowLayout.LEADING, 3, 5));
		
		JButton add = new JButton("Add");
		JButton remove = new JButton("Remove");
		JButton cancel = new JButton("Cancel");
		JButton confirm = new JButton("Confirm");
		
		input = new JTextField();
		input.setPreferredSize(new Dimension(290, 20));
		input.addActionListener(new ActionListener() {
			// fetches user's input in textfield, and if its equal to "Y" creates new order
			public void actionPerformed(ActionEvent e) {
				userChoice = getInput();
			    System.out.println(userChoice);
			    if (userChoice == 'Y' && count == 0) {
			    	orderStatus = true;
			    	createOrder();
			    	count++;
			    }
			}
		});
		
		JFrame frame = this;
		
		add.addActionListener(new ActionListener() {
			// this method is called when the add button is clicked. it adds the item mentioned to the order, so long as it is available in the menu and there is an active order.
			public void actionPerformed(ActionEvent e) {
				if (orderStatus == false) {
					JOptionPane.showMessageDialog(frame, "There is no order. Did you create an order?", "Error", JOptionPane.ERROR_MESSAGE);					
				}
				else if (!checkIfInMenu(input.getText())) {
					JOptionPane.showMessageDialog(frame, "That item is not in the menu. Try again!", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else {
					order.add(input.getText());	
					output.setText("<html>Your Order:<br>Big Mac [" + countFrequency(order, "Big Mac") + "] $" + determinePrice(order, "Big Mac") + "<br>Happy Meal [" + countFrequency(order, "Happy Meal") + "] $" + determinePrice(order, "Happy Meal") + "<br>Pop [" + countFrequency(order, "Pop") + "] $" + determinePrice(order, "Pop") + "</html>");
				}
			}
		});
		
		remove.addActionListener(new ActionListener() {
			// this method is called when the remove button is clicked. it removes the item specified, so long as that item exists on the menu, there is at least 1 of that item in the order, and there is an active order.
			public void actionPerformed(ActionEvent e) {
				if (orderStatus == false) {
					JOptionPane.showMessageDialog(frame, "There is no order. Did you create an order?", "Error", JOptionPane.ERROR_MESSAGE);					
				}
				else if (!checkIfInMenu(input.getText())) {
					JOptionPane.showMessageDialog(frame, "That item is not in the menu. Try again!", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else if (countFrequency(order, input.getText()) == 0) {
					JOptionPane.showMessageDialog(frame, "You don't have any of this item on your order!", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else {
					order.remove(order.indexOf(input.getText()));
					output.setText("<html>Your Order:<br>Big Mac [" + countFrequency(order, "Big Mac") + "] $" + determinePrice(order, "Big Mac") + "<br>Happy Meal [" + countFrequency(order, "Happy Meal") + "] $" + determinePrice(order, "Happy Meal") + "<br>Pop [" + countFrequency(order, "Pop") + "] $" + determinePrice(order, "Pop") + "</html>");
				}
			}
		});
		
		cancel.addActionListener(new ActionListener() {
			// this method is called when the cancel button is clicked. it cancels the current order, and resets the text
			public void actionPerformed(ActionEvent e) {
				if (orderStatus == false) {
					JOptionPane.showMessageDialog(frame, "There is no order to cancel. Did you create an order?", "Error", JOptionPane.ERROR_MESSAGE);					
				}
				else {
					orderStatus = false;
					count = 0;
					JOptionPane.showMessageDialog(frame, "Order has been cancelled!", "Success", JOptionPane.ERROR_MESSAGE);					
					output.setText("<html>Welcome To McRonalds!<br>Would you like to create an order? (y/n)</html>");
					output.setFont(new Font("MV Boli", Font.BOLD, 9));
				}
			}
		});
		
		confirm.addActionListener(new ActionListener() {
			// this method is called when the confirm button is clicked. it checks if there is an order and if said order has at least 1 item. if these conditions are met, a pop up appears asking the user to select a tip amount. then, it sets the output to include the receipt with all costs
			public void actionPerformed(ActionEvent e) {
				if (orderStatus == false) {
					JOptionPane.showMessageDialog(frame, "There is no order to cancel. Did you create an order?", "Error", JOptionPane.ERROR_MESSAGE);					
				}
				else if (countFrequency(order, "Big Mac") == 0 && countFrequency(order, "Happy Meal") == 0 && countFrequency(order, "Pop") == 0) {
					JOptionPane.showMessageDialog(frame, "You must have at least 1 item in your order to confirm.", "Error", JOptionPane.ERROR_MESSAGE);					
				}
				else {
					final ImageIcon tipIcon = new ImageIcon("src/images/tip_icon.png");
					Object[] tipOptions = {"None", "5%", "10%", "15%", "25%"};
					String tipChoice = (String)JOptionPane.showInputDialog(frame, "Choose tip amount:", "Tipping Time", JOptionPane.PLAIN_MESSAGE, tipIcon, tipOptions, "None");
					System.out.println(tipChoice);
					
					double subTotal = round(determinePrice(order, "Big Mac") + determinePrice(order, "Happy Meal") + determinePrice(order, "Pop"));
                    double tax = round(subTotal * 0.13);
					double tip = calculateTip(tipChoice, subTotal);
					double total = round(subTotal + tax + tip);
					
					output.setText("<html>Your Order:<br>Big Mac [" + countFrequency(order, "Big Mac") + "] $" + determinePrice(order, "Big Mac") + "<br>Happy Meal [" + countFrequency(order, "Happy Meal") + "] $" + determinePrice(order, "Happy Meal") + "<br>Pop [" + countFrequency(order, "Pop") + "] $" + determinePrice(order, "Pop") + "<br><br>Subtotal: $" + subTotal + "<br>Tax: $"+ tax + "<br>Tip: $" + tip + "<br>Total: $" + total + "<br><br>Your order will<br>arrive shortly...</html>");
				}
			}
		});
		
		orderOptions.add(add);
		orderOptions.add(remove);
		orderOptions.add(cancel);
		orderOptions.add(confirm);
		orderOptions.add(input);
		
		orderPanel.add(output);
		this.add(orderPanel);
		this.setLayout(new BorderLayout());
		this.add(orderOptions, BorderLayout.PAGE_END);
		
	}
	
	/*
	 * This method retrieves the first character from input textfield
	 * 
	 * @param	None
	 * @return	char	the first character
	 */
	public char getInput() {
		return Character.toUpperCase(input.getText().charAt(0));
	}
	
	/*
	 * This method creates an order
	 * 
	 * @param	None
	 * @return	void	simply sets text and font
	 */
	public void createOrder() {
		output.setText("<html>Your Order:<br>Big Mac [0]<br>Happy Meal [0]<br>Pop [0]</html>");
		output.setFont(new Font("Arial", 1, 16));	
	}
	
	/*
	 * This method checks whether an item is on the menu.
	 * 
	 * @param	item	the item to be checked
	 * @return	boolean		whether the item is on menu or not
	 */
	public boolean checkIfInMenu(String item) {
		for (String foodItem : menu) {
			if (item.equals(foodItem)) {
				return true;
			}
		}
		return false;
	}
	
	/*
	 * This method counts how many times an item appears in an ArrayList
	 * 
	 * @param	order	the arraylist that will be checked		item	the item whose appearance will be counted
	 * @return	int 	the amount of times the item appeared
	 */
    public int countFrequency(ArrayList<String> order, String item) {
        int occurrences = Collections.frequency(order, item);
        return occurrences;
    }
    
    /*
     * This method determines the total price of an item.
     * 
     * @param	order	used to check quantity of item		item	the item whose total price is to be found
     * @return	double	returns total price of specifed item
     */
    public double determinePrice(ArrayList<String> order, String item) {
        int quantity = countFrequency(order, item);
        double cost = 0;
        
        if (item.equals("Big Mac")) {
            cost = bigMacPrice * quantity;
        }
        else if (item.equals("Happy Meal")) {
            cost = happyMealPrice * quantity;
        }
        else if (item.equals("Pop")) {
            cost = sodaPrice * quantity;
        }
        
        return round(cost);
    }
    
    /*
     * This method calculates the tip owed by customer.
     * 
     * @param	tipChoice	the percentage selected by user		subTotal	subtotal of order
     * @return	double	the tip
     */
    public double calculateTip(String tipChoice, double subTotal) {
        double tipRate = 0;
        
        if (tipChoice.equals("None")) {
            tipRate = 0;
        }
        else if (tipChoice.equals("5%")) {
            tipRate = 0.05;
        }                    
        else if (tipChoice.equals("10%")) {
            tipRate = 0.1;
        }
        else if (tipChoice.equals("15%")) {
            tipRate = 0.15;
        }
        else if(tipChoice.equals("25%")) {
        	tipRate = 0.25;
        }
        
        double tipAmount = round(subTotal * tipRate);
        return tipAmount;
    }
    
    /*
     * This method rounds the argument to 2 decimal places.
     * 
     * @param	a	the number to be rounded
     * @return	double	"a" rounded to 2 decimal places
     */
    public static double round (double a) {
        double roundOff = Math.round(a * 100.0) / 100.0;
        return roundOff;
    }
	
}
