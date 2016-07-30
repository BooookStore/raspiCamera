package panels;

import java.awt.Dimension;

import javax.swing.JPanel;

import layoutTest.StateManager;

public abstract class BasePanel extends JPanel {
	
	protected StateManager sm;

	public BasePanel(StateManager sm) {
		this.sm = sm;
		setPreferredSize(new Dimension(800, 480));
	}
}
