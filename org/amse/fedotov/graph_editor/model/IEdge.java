package org.amse.fedotov.graph_editor.model;

/**
 * This interface represents edge.
 * @author Pavel Fedotov
 * @see Edge
 */
public interface IEdge {
	/**
	 * Returns source of the edge.
	 * @return source of the edge.
	 */
	IVertex getSource();
	
	/**
	 * Returns target of the edge.
	 * @return target of the edge.
	 */
	IVertex getTarget();

	/**
	 * Returns distance between the edge and the point. 
	 * @param x x-coordinate of point.
	 * @param y y-coordinate of point.
	 * @return distance between the edge and the point.
	 */
	double distance(int x, int y);
}
