package Memory;
// 100% Rohail
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.Timer;

// this class is an extension of the JFrame class and implements both an ItemListener and an ActionListener to listen for user input.
// a 3x4 memory game, where you match the cards
public class MemoryApp extends JFrame implements ItemListener, ActionListener{

private final JToggleButton[] tiles;
private JMenuItem pause, resume;
private JToggleButton tb1, tb2;
private JLabel display;
private Timer displayTimer, flipTimer;
private int tilesSelected;
private long elapsed;
private boolean aboutClicked;
private Instant start;

public MemoryApp() {
	// Setting Up The Frame
	this.setTitle("Memory Game");
	this.setSize(700,500);
	this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	this.setIconImage(new ImageIcon("src/images/memory_flashcard.png").getImage());
	this.setLocationRelativeTo(null);
	
	JPanel panel = new JPanel(new GridLayout(3,4,2,2));
	JMenuBar jmb = new JMenuBar();

	//Creating the action menu
	JMenu action = new JMenu("Action");
	JMenu gameTimer = new JMenu("Game Timer");
	pause = new JMenuItem("Pause", 'P');
	resume = new JMenuItem("Resume", 'R');
	JMenuItem reveal = new JMenuItem("Reveal", 'R');
	JMenuItem exit = new JMenuItem("Exit", 'X');

	//Creating the help menu
	JMenu help = new JMenu("Help");
	JMenuItem viewHelp = new JMenuItem("View Help...",'H');
	JMenuItem about = new JMenuItem("About",'A');

	//Adding specifications to components
	action.setMnemonic('A');
	gameTimer.setMnemonic('T');
	pause.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK));
	resume.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));
	help.setMnemonic('H');
	pause.setEnabled(false);
	resume.setEnabled(false);

	//Adding contents to proper submenus
	gameTimer.add(pause);
	gameTimer.add(resume);
	action.add(gameTimer);
	action.add(reveal);
	action.addSeparator();
	action.add(exit);
	help.add(viewHelp);
	help.addSeparator();
	help.add(about);
	jmb.add(action);
	jmb.add(help);
	this.setJMenuBar(jmb);

	// Adding action listeners for the menu items
	pause.addActionListener(this);
	resume.addActionListener(this);

    reveal.addActionListener(this);
    exit.addActionListener(this);
    viewHelp.addActionListener(this);
    about.addActionListener(this);

// Adding the initial time display.
display = new JLabel("00:00:00", SwingConstants.CENTER);
this.add(display, BorderLayout.NORTH);

// Array containing icons.
ImageIcon[] pictures = new ImageIcon[]{new ImageIcon(("src/images/memory_flashcard1.jpg")), 
    new ImageIcon(("src/images/memory_flashcard2.jpg")),
    new ImageIcon(("src/images/memory_flashcard3.jpg")),
    new ImageIcon(("src/images/memory_flashcard4.jpg")),
    new ImageIcon(("src/images/memory_flashcard5.jpg")),
    new ImageIcon(("src/images/memory_flashcard6.jpg")),
    new ImageIcon(("src/images/memory_flashcard.png"))};

// Array containing identifiers for each tile
String[] commandID = {"src/images/memory_flashcard1.jpg", "2,png","3,png", "4,png", "src/images/memory_flashcard5.jpg", "6,png"};

// Adding JToggleButtons to array list and shuffling
ArrayList<JToggleButton> arrlist = new ArrayList<>(12); 
tiles = new JToggleButton[12];
int commandIDindex = 0, picturesIndex=0;    

for (int i = 0; i<12; commandIDindex++, picturesIndex++, i+=2)
{   
    // Setting icons for each pair of tiles.
    tiles[i] = new JToggleButton(pictures[6]);
    tiles[i].setSelectedIcon(pictures[picturesIndex]);
    tiles[i].setActionCommand(commandID[commandIDindex]);
    tiles[i+1] = new JToggleButton(pictures[6]);
    tiles[i+1].setSelectedIcon(pictures[picturesIndex]);
    tiles[i+1].setActionCommand(commandID[commandIDindex]);
    tiles[i].setDisabledIcon(pictures[picturesIndex]);
    tiles[i+1].setDisabledIcon(pictures[picturesIndex]);   
    arrlist.add(tiles[i]);
    arrlist.add(tiles[i+1]);
}
Collections.shuffle(arrlist);

// Adding the shuffled list to the display and array.
for (int i = 0; i < 12; i++)
{
    tiles[i]=arrlist.get(i);
    tiles[i].addItemListener(this);
    panel.add(tiles[i]);
}

// Creating a timer to flip unmatched tile pairs to the game tile 
// after 1.5 seconds.
flipTimer = new Timer (1500, ae -> {
    tb1.setSelected(false);
    tb2.setSelected(false);
    flipTimer.stop();
});      

// Creating a timer to display the elapsed time
start = Instant.now();
displayTimer = new Timer(1000, ae -> {  
    elapsed = Duration.between(start, Instant.now()).getSeconds();
    display.setText(String.format("%02d:%02d:%02d", elapsed/3600,
        (elapsed%3600)/60, elapsed%60));  
});

this.add(panel, BorderLayout.CENTER);
this.setVisible(true);       
}

public void actionPerformed(ActionEvent ae) {
	String comStr = ae.getActionCommand();

	if (comStr.equals("Pause")) {   
		pause.setEnabled(false);
		displayTimer.stop();
		resume.setEnabled(true);
	}
	
	else if (comStr.equals("Resume")){
		restartTimer();
	}
	
	else if (comStr.equals("Reveal")) {
		if (displayTimer.isRunning()) {
			pause.setEnabled(false);
			resume.setEnabled(false);
			displayTimer.stop();
		}
		for (int i = 0; i < 12; i++) {
			tiles[i].setEnabled(false);
		}
	}
	
	else if (comStr.equals("Exit")) {
		System.exit(0);
	}
	
	else if (comStr.equals("View Help...")) {
		if (displayTimer.isRunning()) {
			// Disable 'pause' and 'resume' when timer is not running
			pause.setEnabled(false);
			resume.setEnabled(false);
			displayTimer.stop();
		}
    
		// The timer will be clicked
		aboutClicked = true;
		JOptionPane.showMessageDialog(this, "When a game image is clicked, it will turn into the \n" + "actual image of the tile. Once a second tile is \n" + "clicked, both tiles remain revealed if the first \n" + "tile matches the second; otherwise, they will be \n" + "flipped.", "Help", JOptionPane.PLAIN_MESSAGE);
	}
	
	else if (comStr.equals("About")) {
		if (displayTimer.isRunning())
			displayTimer.stop();

		JOptionPane.showMessageDialog(this, "Match Pairs is a memory game "+ "where the user \nmatches pairs of tiles.", "About", JOptionPane.INFORMATION_MESSAGE,  new ImageIcon("src/images/memory_flashcard.png"));
    
		if (tilesSelected!=0 && tilesSelected!=12)
			restartTimer();       
	}
}

public void restartTimer() {
	start = Instant.now();
	long pastElapsed = elapsed;
	// Display the duration from (when the timer is restarted -> current time)+the previous stored time 
	displayTimer = new Timer(1000, t -> {
    long newElapsed = Duration.between(start, Instant.now()).getSeconds();
    display.setText(String.format("%02d:%02d:%02d", (newElapsed+pastElapsed)/3600, ((newElapsed+pastElapsed)%3600)/60, (newElapsed+pastElapsed)%60));  
    // Update 'elapsed' to include the new elapsedTime
    elapsed = newElapsed+pastElapsed;
	});
	// Enable 'pause' and 'resume' when timer is running
	displayTimer.start();
	pause.setEnabled(true);
	resume.setEnabled(false);
	aboutClicked=false;
}

@Override
public void itemStateChanged(ItemEvent ie) {
	// Prevents user from selecting tiles while the flipTimer is running. 
	if (flipTimer.isRunning())
	{
		((JToggleButton)ie.getItem()).setSelected(false);
		return;
	}

	boolean allFlipped=true;
	tilesSelected++;
	if(aboutClicked) {
		aboutClicked=false;
		restartTimer();
	}
	// Starts the display timer when the first tile is clicked
	if (tilesSelected==1) {
		start = Instant.now();
		displayTimer.start();
		pause.setEnabled(true);
		resume.setEnabled(false);
	}
	
    if (tilesSelected%2!=0)
        tb1 = (JToggleButton)ie.getItem();
    else
    {          
        tb2 = (JToggleButton)ie.getItem();
        if (!(tb1.getActionCommand()).equals(tb2.getActionCommand()))
            flipTimer.start();
        else // Disable the tiles if they match.
        {               
            tb1.setSelected(false);                                 
            tb2.setSelected(false);        
            tb1.setEnabled(false);         
            tb2.setEnabled(false);    
        }
        }

        for (int i=0; i<12; i++)
        {
            // If there is at least one tile enabled, the tiles are not all flipped
            if (tiles[i].isEnabled()) 
                allFlipped=false;
        }       
        
        if (allFlipped)
        {
            displayTimer.stop();
            System.out.println(tilesSelected);
            // Disable 'pause' and 'resume' when timer is not running
            pause.setEnabled(false);
            resume.setEnabled(false);
        }

}

}

