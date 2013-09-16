package org.amse.fedotov.graph_editor.view;

import java.awt.event.MouseEvent;

import org.amse.fedotov.graph_editor.model.IEdge;
import org.amse.fedotov.graph_editor.model.IVertex;

@SuppressWarnings("serial")
class RemoveMode extends AbstractMode {
	
	private IEdge myNearEdge;
		
	RemoveMode(GraphPanel panel) {
		super(panel);
	}

	@Override
	protected String getName() {
		return "Remove vertex/edge";
	}
	
	@Override
	protected String getIconName() {
		return "remove.gif";
	}
	
	@Override
	protected String getDescription() {
		return "Remove vertex/edge";
	}

	@Override
	public boolean isEnabled() {
		return !getPanel().getGraph().vertices().isEmpty();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		IVertex clickedVertex = getNearVertex(e.getX(), e.getY());
		getPanel().getGraph().removeVertex(clickedVertex);
		getPanel().unselect(clickedVertex);
		
		IEdge clickedEdge = getNearEdge(e.getX(), e.getY());
		getPanel().getGraph().removeEdge(clickedEdge);
		getPanel().unselect(clickedEdge);
		
		getPanel().setSaved(false);
		getPanel().updatePanel();
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		super.mouseMoved(e);
		getPanel().unselect(myNearEdge);
		myNearEdge = null;
		if (getNearVertex(e.getX(), e.getY()) == null) {
			IEdge nearestEdge = nearestEdge(e.getX(), e.getY());
			if (isNear(nearestEdge, e.getX(), e.getY())) {
				myNearEdge = nearestEdge;
				getPanel().select(nearestEdge);
			}
		}
		getPanel().updatePanel();
	}
		
	/**
	 * Returns the nearest edge.
	 * @param x x-coordinate of click.
	 * @param y y-coordinate of click.
	 * @return the nearest edge or <code>null</code> if the nearest edge doesn't at a short distance.
	 */
	private IEdge getNearEdge(int x, int y) {
		IEdge nearestEdge = nearestEdge(x, y);
		if (isNear(nearestEdge, x, y)) {
			return nearestEdge;
		}
		return null;
	}
	
	/**
	 * Returns the nearest edge to the point.
	 * @param x x-coordinate.
	 * @param y y-coordinate.
	 * @return the nearest edge.
	 */
	private IEdge nearestEdge(int x, int y) {
		IEdge nearestEdge = null;
		for (IEdge edge: getPanel().getGraph().edges()) {
			if (nearestEdge == null ||
					edge.distance(x, y) < nearestEdge.distance(x, y)) {
				nearestEdge = edge;
			}
		}
		return nearestEdge;
	}

	private boolean isNear(IEdge edge, int x, int y) {
		if (edge == null) {
			return false;
		}
		return edge.distance(x, y) <= GraphPanel.NEAR;
	}
	
}
