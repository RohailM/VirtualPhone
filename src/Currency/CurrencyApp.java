package Currency;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class CurrencyApp extends JFrame implements ActionListener {
    
    JComboBox<String> comboBox;
    private String selectedCurrency;
    private double amount;

    public CurrencyApp(){
         try {
                this.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("src/images/bank.png")))));
            } catch (IOException e) {
                e.printStackTrace();
            }
        this.setTitle("Currency Converter");
        this.setIconImage(new ImageIcon("src/images/currency_app.png").getImage());
        this.setPreferredSize(new Dimension(400, 325));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new FlowLayout());
        this.setResizable(false);
        alignCenter(this);
        
        selectedCurrency = "USD";
        
     Border blackline = BorderFactory.createLineBorder(Color.black);
        
        
        Container pane = this.getContentPane();
        pane.setBounds(50,50,70,70);
        ((JComponent) pane).setBorder(blackline);
        
        String s = "                             ";
        JTextField amountField = new JTextField(s);
        amountField.setSize(400, 200);
        
        String[] currency = {"USD","GBP","YEN","CHN", "PKR"};
        comboBox = new JComboBox<String>(currency);
        comboBox.setBounds(50, 50, 200, 70);
        
        JLabel Label = new JLabel("Enter the amount of CAD");
        Label.setBounds(50, 50, 100 ,100);
        JLabel label2 = new JLabel ("Choose your required currency");
        Label.setBounds(50, 50, 100 ,100);
        comboBox.addActionListener(this);
        AutoCompleteDecorator.decorate(comboBox);
        JButton button = new JButton ("Convert");
        
        JPanel base = new JPanel ();
        base.setSize(7000, 100);
        base.add(Label, BorderLayout.LINE_START);
        base.add(amountField, BorderLayout.LINE_END);
        base.setBorder(blackline);
        this.add(base, BorderLayout.PAGE_START);
        JPanel height = new JPanel(new BorderLayout());
        height.setBorder(blackline);
        height.add(label2, BorderLayout.LINE_START);
        height.add(comboBox, BorderLayout.LINE_END);
        this.add(height, BorderLayout.CENTER);
        JPanel but = new JPanel (new BorderLayout());
        but.add(button);
        this.add(but);
        
        JPanel reqAmount = new JPanel(                             );
        reqAmount.setSize(4000, 200);
        reqAmount.setBorder(blackline);
        JLabel answer = new JLabel("ANSWER");
        reqAmount.add(answer);
        this.add(reqAmount);
        
		button.addActionListener(new ActionListener()
	    {
		       public void actionPerformed(ActionEvent e)
		       {
		    	   amount = Double.parseDouble(amountField.getText());
		    	   System.out.println(amount + " to be converted into " + selectedCurrency);
		    	   
		    	   try {
		    		   double convertRate = Double.parseDouble(Files.readAllLines(Paths.get("src/currencies.txt")).get(findIndex(currency, selectedCurrency)));
		    		   double converted = amount * convertRate;
		    		   answer.setText(converted + selectedCurrency);
		    	   } catch (IOException e1) {
		    		   e1.printStackTrace();
		    	   }
		       }
		
		
		});
        
        this.pack();
        this.setVisible(true);
    }
        


    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== comboBox) {
            selectedCurrency = comboBox.getSelectedItem().toString();
        }
    }
    
	public void alignCenter(JFrame frame) {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
	}
	
	public int findIndex(String[] array, String searchingFor) {
		for (int i = 0; i < array.length; i++) {
			if (array[i].equals(searchingFor)) {
				return i;
			}
		}
		return -1;
	}
}