package panels;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import layoutTest.StateManager;

/**
 * 写真を撮るときのクラスです。
 * 現時点では、あまり変更の必要はないかと思います。
 */
public class CameraPanel extends BasePanel {


	/**
	 * Create the panel.
	 */
	public CameraPanel(StateManager sm) {
		super(sm);

		setLayout(new BorderLayout(0, 0));

		JPanel bottomPanel = new JPanel();
		add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.setLayout(new GridLayout(0, 2, 0, 0));

		JButton menuButton = new JButton("MENU");
		menuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sm.changeState(StateManager.MENU);
			}
		});
		menuButton.setFont(new Font("MS UI Gothic", Font.BOLD, 16));
		bottomPanel.add(menuButton);

		JButton quitButton = new JButton("QUIT");
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		quitButton.setFont(new Font("MS UI Gothic", Font.BOLD, 16));
		bottomPanel.add(quitButton);

		JLabel previewLabel = new JLabel("preview");
		previewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(previewLabel, BorderLayout.CENTER);

	}

}
