package org.amse.fedotov.graph_editor.view;

import javax.swing.AbstractAction;

abstract class AbstractActionWithIcon extends AbstractAction {

	private final String myPath = "/org/amse/fedotov/graph_editor/view/icons/";
	
	protected String getIconPath() {
		return myPath;
	}
}
