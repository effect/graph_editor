package org.amse.fedotov.graph_editor.layouters.impl;

import java.util.Random;

import org.amse.fedotov.graph_editor.exception.BadInputException;
import org.amse.fedotov.graph_editor.layouters.ILayouter;
import org.amse.fedotov.graph_editor.model.IGraph;
import org.amse.fedotov.graph_editor.model.IVertex;

class RandomLayouter implements ILayouter{

	public void calculateCoordinates(IGraph graph, int width, int height) {
		width -= 2 * BORDER;
		height -= 2 * BORDER;
		if (width <= 0 || height <= 0) {
			throw new BadInputException("Layouting area is too small");
		}
		int v = graph.vertices().size();
		Random random = new Random();
		for (int i = 0; i < v; i++) {
			IVertex vertex = graph.vertices().get(i);
			vertex.setX(BORDER + random.nextInt(width));
			vertex.setY(BORDER + random.nextInt(height));
		}
	}

}
