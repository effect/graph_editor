package org.amse.fedotov.graph_editor.layouters;

import org.amse.fedotov.graph_editor.model.IGraph;

/**
 * This interface represents layouts that allow recounting coordinates of vertices.
 * @author Pavel Fedotov
 *
 */
public interface ILayouter {
	
	/**
	 * Minimal distance value from vertex to border of rectangle.    
	 */
	public final int BORDER = 20;

	/**
	 * This method lay out the graph on rectangle with given sides. 
	 * It calculates coordinates of vertices of the graph.
	 * It is supposed that the graph has at least one vertex.
	 * @param graph the graph.
	 * @param width width of rectangle.
	 * @param height height of rectangle.
	 */
	public void calculateCoordinates(IGraph graph, int width, int height);
}
