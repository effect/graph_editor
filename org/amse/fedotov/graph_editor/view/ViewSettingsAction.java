package org.amse.fedotov.graph_editor.view;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.amse.fedotov.graph_editor.settings.Settings;

@SuppressWarnings("serial")
class ViewSettingsAction extends AbstractAction {

	private String myName = "Show Vertices Labels";
	private GraphPanel myPanel;
	
	ViewSettingsAction(GraphPanel panel) {
		putValue(NAME, myName);
		myPanel = panel;
	}

	public void actionPerformed(ActionEvent e) {
		Settings.printVerticesNames = !Settings.printVerticesNames;
		myPanel.repaint();
	}

}
