package layoutTest;

import java.awt.Container;

import javax.swing.JFrame;

import panels.AlbumPanel;
import panels.BasePanel;
import panels.CameraPanel;
import panels.MenuPanel;

/**
 * パネルの管理をするクラス。 親となるJFrameクラスのインスタンスを知っており、
 * このStateManagerが管理する全てのパネルクラスは、このStateManagerを知っている。
 * 管理するパネルクラスからchangeState(int)が呼ばれると、
 * 引数に対応するパネルのインスタンスを作成し、親のContentPaneをそのインスタンスに差し替えます。
 *
 * @author Sugita Ikuto
 *
 */
public class StateManager {

	// TODO : 削除予定
	// 定数（管理するパネル毎に宣言）
	public static final int MENU = 0;
	public static final int CAMERA = 1;
	public static final int ALBUM = 2;

	private JFrame parent;// 親となるJFrameクラスのインスタンス
	private BasePanel currentPanel;// 現在表示しているパネル TODO : 削除予定
	private int currentNum; // TODO : 削除予定

	public StateManager(JFrame parent) {
		this.parent = parent;
		changeState(MENU);// 始めはMenuPanelを表示したい
	}

	/**
	 * 表示するパネルを切り替えます。 このメソッドは通常、StateManagerクラスのインスタンスが管理するパネルから呼ばれるべきです。
	 *　TODO : 削除予定
	 * @param num
	 *            StateManagerクラスの定数を使用してください。
	 */
	public void changeState(int num) {
		unloadState();
		currentNum = num;
		loadState();
		parent.setContentPane(currentPanel);
		parent.revalidate();// 画面の更新
	}

	/**
	 * 表示しているContainerを変更します。
	 * 
	 * @param panel
	 */
	public void changePanel(Container container){
		parent.setContentPane(container);
		parent.revalidate();
	}
	
	//TODO : 削除予定
	private void loadState() {
		switch (currentNum) {
		case MENU:
			currentPanel = new MenuPanel(this);
			break;
		case CAMERA:
			currentPanel = new CameraPanel(this);
			break;
		case ALBUM:
			currentPanel = new AlbumPanel(this);
			break;
		}
	}

	// TODO : 削除予定
	private void unloadState() {
		currentPanel = null;
	}

}
