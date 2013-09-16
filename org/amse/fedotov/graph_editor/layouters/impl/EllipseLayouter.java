package org.amse.fedotov.graph_editor.layouters.impl;

import org.amse.fedotov.graph_editor.exception.BadInputException;
import org.amse.fedotov.graph_editor.layouters.ILayouter;
import org.amse.fedotov.graph_editor.model.IGraph;
import org.amse.fedotov.graph_editor.model.IVertex;

class EllipseLayouter implements ILayouter {

	public void calculateCoordinates(IGraph graph, int width, int height) {
		int v = graph.vertices().size();
		if (v == 1) {
			IVertex vertex = graph.vertices().get(0);
			vertex.setX(width / 2);
			vertex.setY(height / 2);
			return;
		}
		width -= 2 * BORDER;
		height -= 2 * BORDER;
		if (width <= 0 ||  height <= 0) {
			throw new BadInputException("Layouting area is too small");
		}
		int a = width / 2;
		int b = height / 2;
		for (int i = 0; i < v; i++) {
			IVertex vertex = graph.vertices().get(i);
			vertex.setX(a + BORDER + (int)Math.round((a * Math.cos(2 * Math.PI * i / v))));
			vertex.setY(b + BORDER + (int)Math.round((b * Math.sin(2 * Math.PI * i / v))));
		}
	}

}
