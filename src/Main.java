
// main class, no explanation needed
public class Main {

	public static void main(String[] args) {
		Frame window = new Frame("Virtual Phone", 450, 720, false); // creates a new frame with all necessary arguments
		window.setVisible(true); // makes frame visible
		window.requestFocusInWindow(); // makes it so focus isn't on any components of frame
	}

}
