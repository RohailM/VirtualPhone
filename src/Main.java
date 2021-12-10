import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main {

	public static void main(String[] args) {
		/*Frame screen = new Frame(true, false, 450, 720, "Virtual Phone");
		screen.set_icon_of_frame(screen, "src/images/phone_icon.png")
		*/
		
		// Creating a basic GUI window, to which components can be added onto
		JFrame screen = new JFrame();
		
		// makes it so frame is visible and can be seen
		screen.setVisible(true);
		
		// adding a title to the application that displays at the top of the window
		screen.setTitle("Virtual Phone");

		// using javax.swing.ImageIcon and creating an ImageIcon
		ImageIcon phoneIcon = new ImageIcon("src/images/phone_icon.png");
		
		// change icon of frame to phoneIcon
		screen.setIconImage(phoneIcon.getImage());
		
		// setting the size of the frame to 720x720, first parameter is x-dimension and second parameter is y-dimension
		screen.setSize(450,720);
		
		// making it so when the x icon, which is located at top right of frame, is clicked, the application is exited.
		screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// prevents users from resizing the frame
		screen.setResizable(false);	
	}

}
