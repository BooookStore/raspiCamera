package transitionactions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import layoutTest.StateManager;
import panels.AlbumPanel;

public class TransitionAlbum implements ActionListener {

	// TODO : 削除予定
	private StateManager sm;
	
	/**
	 * TODO : sm 引数を削除予定
	 */
	public TransitionAlbum(StateManager sm) {
		this.sm = sm;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		sm.changePanel(new AlbumPanel(sm));
	}

}
