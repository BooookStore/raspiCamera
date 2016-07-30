package panels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class PhotoPanel extends JPanel {

	private BufferedImage image;

	public void draw(BufferedImage image){
		this.image=image;
		repaint();
	}


	@Override
	public void paint(Graphics g){
		//パネルの大きさに合わせて表示（変更しても良い）
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
	}
}
