package org.amse.fedotov.graph_editor.layouters.impl;

import org.amse.fedotov.graph_editor.layouters.ILayouter;
import org.amse.fedotov.graph_editor.model.IGraph;
import org.amse.fedotov.graph_editor.model.IVertex;

import java.util.*;

class ComponentLayouter implements ILayouter {

	public void calculateCoordinates(IGraph graph, int width, int height) {
		List<IGraph> components = graph.components();
		int numComponents = components.size();
		int numCols = (int)Math.ceil(Math.sqrt(numComponents));
		int numRows = numCols;
		while ((numRows - 1) * numCols >= numComponents) {
			numRows--;
		}
		ILayouter layouter = new EllipseLayouter();
		for (int k = 0; k < components.size(); k++) {
			IGraph component = components.get(k);
			layouter.calculateCoordinates(component, width / numCols, height / numRows);
			int i = k / numCols;
			int j = k % numCols;
			for (IVertex vertex : component.vertices()) {
				vertex.setX(vertex.getX() + j * width / numCols);
				vertex.setY(vertex.getY() + i * height / numRows);
			}
		}
	}

}
