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
import javax.swing.SwingWorker;

import layoutTest.StateManager;

public class AlbumPanel extends BasePanel {

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
				SwingUtilities.invokeLater(new UpdatePhotoPanel());
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
				SwingUtilities.invokeLater(new UpdatePhotoPanel());
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

		// TODO : Workerスレッドを使用するように変更。
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
	 * /bin/resource/からJPEG写真を読み込み、 PhotoPanelに描画する画像を渡します。
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
					return file.getName().endsWith(".jpg");
				}
			});

			photos = new BufferedImage[files.length];
			for (int i = 0; i < files.length; i++) {
				photos[i] = ImageIO.read(files[i]);
			}
			// TODO : runLatorで、実行するよう変更。
			photoPanel.draw(photos[index]);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 写真をロードするクラスです。
	 * 
	 * @see SwingWorker
	 * @author honyaryousuke
	 *
	 */
	private class LoadPotos extends SwingWorker<BufferedImage[], Void> {

		/**
		 * 写真の読み込み先のディレクトリ
		 */
		private File directory;

		/**
		 * 写真の読み込み先ディレクトリを指定するコンストラクタ
		 * 
		 * @param directory
		 *            写真の読み込み先
		 */
		public LoadPotos(File directory) {
			super();
			this.directory = directory;
		}

		/**
		 * バックグラウンドプロセス
		 * 
		 * @return 読み込んだ写真
		 * @throws Exception
		 */
		@Override
		protected BufferedImage[] doInBackground() throws Exception {

			File files[] = directory.listFiles(new FileFilter() {
				@Override
				public boolean accept(File file) {
					return file.getName().endsWith(".jpg");
				}
			});

			photos = new BufferedImage[files.length];
			for (int i = 0; i < files.length; i++) {
				photos[i] = ImageIO.read(files[i]);
			}
			// TODO : runLatorで、実行するよう変更。
			photoPanel.draw(photos[index]);

			return null;
		}

		@Override
		protected void done() {
			super.done();
		}

	}

	/**
	 * 表示している写真を更新します。このクラスはRunnbleを実装しており、{@link SwingUtilities#invokeLater(Runnable)}によって実行されなければならないことに注意してください。
	 * 
	 * @author honyaryousuke
	 *
	 */
	private class UpdatePhotoPanel implements Runnable {

		/**
		 * 写真を表示している {@link AlbumPanel#photoPanel} を、 {@link AlbumPanel#index}
		 * に基づいて更新します。
		 */
		@Override
		public void run() {
			photoPanel.draw(photos[index]);
		}

	}
}
