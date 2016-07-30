package layoutTest;

import javax.swing.JFrame;

public class LayoutTest {

	public static void main(String[] args) {
		JFrame frame = new JFrame("test");

		StateManager sm = new StateManager(frame);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.pack();
	}

}
