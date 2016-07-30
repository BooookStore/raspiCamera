package panels;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import layoutTest.StateManager;

/**
 * メニュー画面のクラスです。
 * ほとんど変えるところはないと思います。
 *
 * @author Sugita Ikuto
 *
 */
public class MenuPanel extends BasePanel {

	/**
	 * Create the panel.
	 */
	public MenuPanel(StateManager sm) {
		super(sm);
		setLayout(new BorderLayout(0, 0));

		JPanel mainPanel = new JPanel();
		add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new GridLayout(1, 0, 0, 0));

		JButton cameraButton = new JButton("CAMERA");
		cameraButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sm.changeState(StateManager.CAMERA);
			}
		});
		cameraButton.setFont(new Font("MS UI Gothic", Font.BOLD, 16));
		mainPanel.add(cameraButton);

		JButton AlbumButton = new JButton("ALBUM");
		AlbumButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sm.changeState(StateManager.ALBUM);
			}
		});
		AlbumButton.setFont(new Font("MS UI Gothic", Font.BOLD, 16));
		mainPanel.add(AlbumButton);

		JButton quitButton = new JButton("QUIT");
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		quitButton.setFont(new Font("MS UI Gothic", Font.BOLD, 16));
		add(quitButton, BorderLayout.SOUTH);

	}

}
