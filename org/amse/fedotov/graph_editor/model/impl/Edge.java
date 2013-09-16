package org.amse.fedotov.graph_editor.model.impl;

import org.amse.fedotov.graph_editor.model.*;

final class Edge implements IEdge {
	
	private final IVertex mySource;
	private final IVertex myTarget;

	public IVertex getSource() {
		return mySource;
	}

	public IVertex getTarget() {
		return myTarget;
	}
	
	public Edge(IVertex source, IVertex target) {
		mySource = source;
		myTarget = target;
	}
		
	// @Override
	public String toString() {
		return "Edge: " + mySource + ", " + myTarget;
	}

	public double distance(int x, int y) {
		return java.awt.geom.Line2D.ptSegDist(mySource.getX(), mySource.getY(), 
				myTarget.getX(), myTarget.getY(), x, y);
	}

}
