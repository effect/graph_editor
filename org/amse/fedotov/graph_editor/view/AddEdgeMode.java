package org.amse.fedotov.graph_editor.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import org.amse.fedotov.graph_editor.model.IVertex;

/**
 * This mode allows user adding edges.
 * It also adds vertex if user wants to add edge with empty target vertex. 
 * @author Pavel Fedotov
 *
 */
@SuppressWarnings("serial")
class AddEdgeMode extends AbstractMode {
	
	private IVertex mySourceVertex;
	private IVertex myTargetVertex;
	
	private IVertex myFakeSourceVertex;
	private IVertex myFakeTargetVertex;
	private int myCurrentX;
	private int myCurrentY;

	AddEdgeMode(GraphPanel panel) {
		super(panel);
	}

	@Override
	protected String getName() {
		return "Add edge";
	}
	
	@Override
	protected String getIconName() {
		return "addedge.gif";
	}
	
	@Override
	protected String getDescription() {
		return "Add edge";
	}
	
	@Override
	public boolean isEnabled() {
		return getPanel().getGraph().vertices().size() >= 2;
	}
	
	void setFakeEdge(IVertex source, int x, int y) {
		if (source == null) {
			getPanel().unselect(myFakeSourceVertex);
		} else {
			getPanel().select(source);
		}
		myFakeSourceVertex = source;
		myCurrentX = x;
		myCurrentY = y;
	}
	
	void setFakeEdge(IVertex source, IVertex target, int x, int y) {
		setFakeEdge(source, x, y);
		if (target == null) {
			getPanel().unselect(myFakeTargetVertex);
		} else {
			getPanel().select(target);
		}
		myFakeTargetVertex = target;
	}

	@Override
	protected void paintFakeElements(Graphics g) {
		if (myFakeTargetVertex != null) {
			g.setColor(Color.BLUE);
			g.drawLine(myFakeSourceVertex.getX(), myFakeSourceVertex.getY(), 
					myFakeTargetVertex.getX(), myFakeTargetVertex.getY());
		} else {
			if (myFakeSourceVertex != null) {
				g.setColor(Color.BLACK);
				g.drawLine(myFakeSourceVertex.getX(), myFakeSourceVertex.getY(), myCurrentX, myCurrentY);
				g.drawOval(myCurrentX - GraphPanel.RADIUS, myCurrentY - GraphPanel.RADIUS, 
						2 * GraphPanel.RADIUS + 1, 2 * GraphPanel.RADIUS + 1); 
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		mySourceVertex = null;
		myTargetVertex = null;
		IVertex clickedVertex = getNearVertex(e.getX(), e.getY());
		if (clickedVertex != null) {
			mySourceVertex = clickedVertex;
		}
		getPanel().updatePanel();
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		if (mySourceVertex == null) {
			return;
		}
		myTargetVertex = null;
		int x = getPanel().getCorrectX(e.getX());
		int y = getPanel().getCorrectY(e.getY());
		setFakeEdge(null, null, x, y);
		IVertex nearestVertex = getNearVertex(e.getX(), e.getY());
		if (nearestVertex != null) {
			myTargetVertex = nearestVertex;
			if (getPanel().getGraph().canAddEdge(mySourceVertex, myTargetVertex)) {
				setFakeEdge(mySourceVertex, myTargetVertex, x, y);
			} else {
				myTargetVertex = null;
				setFakeEdge(mySourceVertex, x, y);
			}
		} else {
			setFakeEdge(mySourceVertex, x, y);
		}
		getPanel().updatePanel();
	}
	
	@Override
	public void mouseReleased(MouseEvent  e) {
		if (mySourceVertex == null) {
			return;
		}
		if (myTargetVertex != null) {
			getPanel().getGraph().addEdge(mySourceVertex, myTargetVertex);
		} else {
			myTargetVertex = getPanel().getGraph().addVertex(myCurrentX, myCurrentY);
			if (getPanel().getGraph().canAddEdge(mySourceVertex, myTargetVertex)) {
				getPanel().getGraph().addEdge(mySourceVertex, myTargetVertex);
			} else {
				getPanel().getGraph().removeVertex(myTargetVertex);
			}
		}
		mySourceVertex = null;
		myTargetVertex = null;
		setFakeEdge(null, null, e.getX(), e.getY());
		getPanel().setSaved(false);
		getPanel().updatePanel();
	}
	
}
