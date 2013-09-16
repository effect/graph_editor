package org.amse.fedotov.graph_editor.view;

import java.awt.event.MouseEvent;

import org.amse.fedotov.graph_editor.model.IVertex;

/**
 * This mode allows user adding or moving vertecies.
 * @author Pavel Fedotov
 *
 */
@SuppressWarnings("serial")
class AddVertexMode extends AbstractMode {

	private IVertex myMovingVertex;
	
	AddVertexMode(GraphPanel panel) {
		super(panel);
	}

	@Override
	protected String getName() {
		return "Add/move vertex";
	}
	
	@Override
	protected String getIconName() {
		return "addvertex.gif";
	}
	
	@Override
	protected String getDescription() {
		return "Add/move vertex";
	}
	
	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		IVertex clickedVertex = getNearVertex(e.getX(), e.getY());
		if (clickedVertex != null) {
			myMovingVertex = clickedVertex;
		} else {
			myMovingVertex = getPanel().getGraph().addVertex(e.getX(), e.getY());
			getPanel().setSaved(false);
		}
		super.mouseMoved(e);
		getPanel().updatePanel();
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		super.mouseMoved(e);
		if (myMovingVertex != null) {
			myMovingVertex.moveTo(getPanel().getCorrectX(e.getX()), getPanel().getCorrectY(e.getY()));
			getPanel().setSaved(false);
			getPanel().updatePanel();
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		myMovingVertex = null;
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		super.mouseMoved(e);
	}
	
}
