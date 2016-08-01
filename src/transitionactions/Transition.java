package transitionactions;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import layoutTest.StateManager;

public class Transition implements ActionListener {

	// TODO : 削除予定
	private StateManager sm;
	
	private Container transTo;
	
	/**
	 * TODO : sm 引数を削除予定
	 */
	public Transition(StateManager sm,Container transTo) {
		this.sm = sm;
		this.transTo = transTo;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		sm.changePanel(transTo);
	}

}
