package org.amse.fedotov.graph_editor.view;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

@SuppressWarnings("serial")
class ExitAction extends AbstractAction  {
	
	private GraphPanel myPanel;

	ExitAction(GraphPanel panel) {
		super("Exit");
		myPanel = panel;
	}
	
	public void actionPerformed(ActionEvent e) {
		myPanel.exit();
	}

}

