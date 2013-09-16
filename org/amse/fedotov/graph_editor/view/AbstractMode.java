package org.amse.fedotov.graph_editor.view;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.event.*;

import org.amse.fedotov.graph_editor.model.IVertex;

abstract class AbstractMode extends AbstractActionWithIcon implements MouseInputListener {

	private GraphPanel myPanel;
	
	private IVertex myNearVertex;
	
	AbstractMode(GraphPanel panel) {
		myPanel = panel;
		putValue(SMALL_ICON, new ImageIcon(this.getClass().getResource(getIconPath() + getIconName())));
		putValue(SHORT_DESCRIPTION, getDescription());
	}
	
	protected GraphPanel getPanel() {
		return myPanel;
	}
	
	abstract protected String getName();
	
	abstract protected String getIconName();
	
	abstract protected String getDescription();
		
	protected void paintFakeElements(Graphics g) {
	}
	
	/**
	 * Selects the nearest vertex.
	 */
	public void mouseMoved(MouseEvent e) {
		getPanel().unselect(myNearVertex);
		myNearVertex = null;
		IVertex nearestVertex = nearestVertex(e.getX(), e.getY());
		if (isNear(nearestVertex, e.getX(), e.getY())) {
			myNearVertex = nearestVertex;
			getPanel().select(myNearVertex);
		}
		getPanel().updatePanel();
	}
	
	/**
	 * Returns the nearest vertex.
	 * @param x x-coordinate of click.
	 * @param y y-coordinate of click.
	 * @return the nearest vertex or <code>null</code> if the nearest vertex doesn't at a short distance.
	 */
	protected IVertex getNearVertex(int x, int y) {
		IVertex nearestVertex = nearestVertex(x, y);
		if (isNear(nearestVertex, x, y)) {
			return nearestVertex;
		}
		return null;	
	}

	/**
	 * Returns the nearest vertex to the point.
	 * @param x x-coordinate.
	 * @param y y-coordinate.
	 * @return the nearest vertex. 
	 */
	protected IVertex nearestVertex(int x, int y) {
		IVertex nearestVertex = null;
		for (IVertex vertex: getPanel().getGraph().vertices()) {
			if (nearestVertex == null || 
					vertex.distance(x, y) < nearestVertex.distance(x, y)) {
				nearestVertex = vertex;
			}
		}
		return nearestVertex;
	}
	
	protected boolean isNear(IVertex vertex, int x, int y) {
		if (vertex == null) {
			return false;
		}
		return vertex.distance(x, y) <= GraphPanel.NEAR; 
	}
	
	public void actionPerformed(ActionEvent e) {
		getPanel().changeListeners(this);
	}

	public void mousePressed(MouseEvent event) {
	}

	public void mouseReleased(MouseEvent event) {
	}

	public void mouseClicked(MouseEvent event) {
	}

	public void mouseEntered(MouseEvent event) {
	}

	public void mouseExited(MouseEvent event) {
	}

	public void mouseDragged(MouseEvent event) {
	}
}	
