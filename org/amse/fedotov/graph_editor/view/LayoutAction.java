package org.amse.fedotov.graph_editor.view;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import org.amse.fedotov.graph_editor.exception.BadInputException;
import org.amse.fedotov.graph_editor.layouters.ILayouter;

@SuppressWarnings("serial")
class LayoutAction extends AbstractActionWithIcon {
	
	private GraphPanel myGraphPanel;
	private ILayouter myLayout;
	
	LayoutAction(GraphPanel graphPanel, ILayouter layouter, String description, String iconName) {
		myGraphPanel = graphPanel;
		myLayout = layouter;
		putValue(SHORT_DESCRIPTION, description);
		putValue(SMALL_ICON, new ImageIcon(this.getClass().getResource(getIconPath() + iconName)));
	}
	
	protected GraphPanel getPanel() {
		return myGraphPanel;
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			myLayout.calculateCoordinates(getPanel().getGraph(), 
					getPanel().getWidth(), getPanel().getHeight());
			myGraphPanel.setSaved(false);
		} catch (BadInputException ex) {
			JOptionPane.showMessageDialog(null,	ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		getPanel().updatePanel();
	}
	
	@Override
	public boolean isEnabled() {
		return getPanel().getGraph().vertices().size() > 0;
	}

}
