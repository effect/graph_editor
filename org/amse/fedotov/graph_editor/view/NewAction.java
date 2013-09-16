package org.amse.fedotov.graph_editor.view;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.amse.fedotov.graph_editor.model.impl.Graph;

@SuppressWarnings("serial")
class NewAction extends AbstractAction {
	
	private GraphPanel myPanel;
	
	NewAction(GraphPanel panel) {
		putValue(NAME, "New");
		myPanel = panel;
	}

	public void actionPerformed(ActionEvent e) {
		if (myPanel.askSave()) {
			myPanel.setGraph(new Graph());
			myPanel.setFilename(null);
		}
	}

}
