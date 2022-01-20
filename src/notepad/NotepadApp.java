package notepad;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;



public class NotepadApp extends JFrame implements ActionListener {

	
	JTextArea textEditor;
	JScrollPane scrolling;
	JSpinner fontSizeSpinner;
	JComboBox fontStyle;
	
	JMenuBar menu;
	JMenu fileMenu;
	JMenuItem openItem;
	JMenuItem saveItem;
	JMenuItem exitItem;
	JLabel label;
	JLabel label2;
	
	public NotepadApp(){
		this.setTitle("Notes");
        this.setIconImage(new ImageIcon("src/images/notepad_icon.png").getImage());
		this.setSize(400,700);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(new FlowLayout());
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.DARK_GRAY);
		textEditor = new JTextArea();
		textEditor.setLineWrap(true);
		textEditor.setWrapStyleWord(true);
		textEditor.setFont(new Font("Times New Roman",Font.PLAIN,20));
		
		
		
		scrolling = new JScrollPane(textEditor);
		scrolling.setPreferredSize(new Dimension(300,650));
		scrolling.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		label = new JLabel("size");
		label.setForeground(Color.white);
		
		fontSizeSpinner = new JSpinner();
		fontSizeSpinner.setPreferredSize(new Dimension(50,25));
		fontSizeSpinner.setValue(20);
		fontSizeSpinner.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				textEditor.setFont(new Font(textEditor.getFont().getFamily(),Font.PLAIN,(int)fontSizeSpinner.getValue()));
			}
			
		
		});
		
		
		String[] style = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		fontStyle = new JComboBox<String>(style);
		fontStyle.addActionListener(this);
		fontStyle.setSelectedItem(0);
		
		
// ---------------------- menu bar-----------//
		menu = new JMenuBar();
		fileMenu = new JMenu("Options");
		openItem = new JMenuItem("Open Text");
		saveItem = new JMenuItem("Save");
		exitItem = new JMenuItem("Exit");
		
		
		openItem.addActionListener(this);
		saveItem.addActionListener(this);
		exitItem.addActionListener(this);
		
		fileMenu.add(openItem);
		fileMenu.add(saveItem);
		fileMenu.add(exitItem);
		menu.add(fileMenu);
		
		
// ---------------------- menu bar-----------//
		
		this.setJMenuBar(menu);	
		this.add(fontStyle);
		this.add(fontSizeSpinner);
		this.add(label);
		this.add(scrolling);
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource()==fontStyle) {
			textEditor.setFont(new Font((String)fontStyle.getSelectedItem(),Font.PLAIN,textEditor.getFont().getSize()));
		}
		if (e.getSource()==openItem) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File("."));
			/*/FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt","html");
			fileChooser.setFileFilter(filter);*/
			
			
			int response = fileChooser.showOpenDialog(null);
			if (response == JFileChooser.APPROVE_OPTION) {
				File file = new File (fileChooser.getSelectedFile().getAbsolutePath());
				Scanner fileIn = null;
				
				try {
					fileIn = new Scanner(file);
					if (file.isFile()) {
						while(fileIn.hasNextLine()) {
							String line = fileIn.nextLine()+"\n";
							textEditor.append(line);
						}
					}
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				finally {
					fileIn.close();
				}
				
			}
		}
		if (e.getSource()==saveItem) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File("."));
			
			int response = fileChooser.showSaveDialog(null);
			
			if (response == JFileChooser.APPROVE_OPTION) {
				File file;
				PrintWriter fileOut = null;
				
				file = new File(fileChooser.getSelectedFile().getAbsolutePath());
				try {
					fileOut = new PrintWriter(file);
					fileOut.println(textEditor.getText());
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				finally {
					fileOut.close();
					
				}
				
			}
			
		}
		if (e.getSource()==exitItem) {
			System.exit(0);
	
}
	}
	

}