package panels;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import layoutTest.StateManager;

public class AlbumPanel extends BasePanel {

	/*
	 * 写真が保存されているフォルダへのパス 自身の環境に合わせて書き換えてください。
	 * 
	 * TODO : 相対パスの方が良いのではないか？
	 */
	public static final String DIRECTORY = "C:\\Users\\Sugita Ikuto\\Desktop\\raspiCamera\\resource";

	// 写真を格納する配列
	private BufferedImage[] photos;
	// photosの要素番号を格納する変数
	private int index;

	// 写真を表示するパネル
	private PhotoPanel photoPanel;

	/**
	 * Create the panel.
	 */
	public AlbumPanel(StateManager sm) {
		super(sm);

		this.index = 0;

		setLayout(new BorderLayout(0, 0));

		JPanel bottomPanel = new JPanel();
		add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.setLayout(new GridLayout(1, 0, 0, 0));

		JButton menuButton = new JButton("MENU");
		menuButton.setFont(new Font("MS UI Gothic", Font.BOLD, 16));
		menuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sm.changeState(StateManager.MENU);
			}
		});
		bottomPanel.add(menuButton);

		JButton quitButton = new JButton("QUIT");
		quitButton.setFont(new Font("MS UI Gothic", Font.BOLD, 16));
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		bottomPanel.add(quitButton);

		JButton nextButton = new JButton(">");
		nextButton.setFont(new Font("MS UI Gothic", Font.BOLD, 16));
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				index++;
				if (index > photos.length - 1)
					index = 0;
				photoPanel.draw(photos[index]);
			}
		});
		add(nextButton, BorderLayout.EAST);

		JButton prevButton = new JButton("<");
		prevButton.setFont(new Font("MS UI Gothic", Font.BOLD, 16));
		prevButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				index--;
				if (index < 0)
					index = photos.length - 1;
				photoPanel.draw(photos[index]);
			}
		});
		add(prevButton, BorderLayout.WEST);

		JPanel topPanel = new JPanel();
		add(topPanel, BorderLayout.NORTH);
		topPanel.setLayout(new GridLayout(0, 2, 0, 0));

		JButton rotateButton = new JButton("ROTATE(clockwise)");
		rotateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {

					}
				});
			}
		});
		rotateButton.setFont(new Font("MS UI Gothic", Font.BOLD, 16));
		topPanel.add(rotateButton);

		JButton deleteButton = new JButton("DELETE");
		deleteButton.setFont(new Font("MS UI Gothic", Font.BOLD, 16));
		topPanel.add(deleteButton);

		photoPanel = new PhotoPanel();
		add(photoPanel, BorderLayout.CENTER);

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					loadPhotos();
				} catch (IOException e) {
					e.printStackTrace();

				}
			}
		});
	}

	/**
	 * /bin/resource/かJPEG写真を読み込み、 PhotoPanelに描画する画像を渡します。
	 * 
	 * @throws IOException
	 */
	private void loadPhotos() throws IOException {
		try {
			File directory;
			directory = new File(getClass().getResource("../resource").toURI());
			File files[] = directory.listFiles(new FileFilter() {
				@Override
				public boolean accept(File file) {
					if ("jpg".equals(getSuffix(file.getName())))
						return true;
					return false;
				}
			});

			photos = new BufferedImage[files.length];
			for (int i = 0; i < files.length; i++) {
				photos[i] = ImageIO.read(files[i]);
			}
			photoPanel.draw(photos[index]);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ファイル名から拡張子だけを取り出し返します。
	 * 
	 * @param ファイル名
	 * @return 拡張子
	 */
	private static String getSuffix(String fileName) {
		int lastIndex = fileName.lastIndexOf(".");
		return fileName.substring(lastIndex + 1);
	}

}
