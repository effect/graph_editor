package org.amse.fedotov.graph_editor.view;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import org.amse.fedotov.graph_editor.settings.Settings;

@SuppressWarnings("serial")
class SettingsMenu extends JMenu {

	SettingsMenu(GraphPanel panel) {
		super("Settings");

		JCheckBoxMenuItem viewSettingsAction = new JCheckBoxMenuItem(new ViewSettingsAction(panel));
		viewSettingsAction.setSelected(Settings.printVerticesNames);
		add(viewSettingsAction);
		add(new JMenuItem(new ExportSettingsAction(panel)));
	}

}
