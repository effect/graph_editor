package org.amse.fedotov.graph_editor.view;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import org.amse.fedotov.graph_editor.settings.Settings;

@SuppressWarnings("serial")
class ExportSettingsAction extends AbstractAction {

	private String myName = "Export Settings...";
	private GraphPanel myPanel;
	
	ExportSettingsAction(GraphPanel panel) {
		putValue(NAME, myName);
		myPanel = panel;
	}

	public void actionPerformed(ActionEvent e) {
		Object[] settingsList = {0, 1};
		Integer res = (Integer) JOptionPane.showInputDialog(null, "Select value of the first vertex index", 
				"Export Settings", JOptionPane.PLAIN_MESSAGE, 
				null, settingsList, Settings.firstVertexIndex);
		if (res != null) {
			Settings.firstVertexIndex = res;
			myPanel.repaint();
		} 
	}

}
