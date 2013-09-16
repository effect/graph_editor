package org.amse.fedotov.graph_editor.model;

/**
 * This interface represents vertex.
 * @author Pavel Fedotov
 * @see Vertex
 */
public interface IVertex {
	
	public static final int RADIUS = 4;
	
	/**
	 * Returns x-coordinate of vertex.
	 * @return x-coordinate of vertex.
	 */
	int getX();
	
	/**
	 * Sets x-coordinate of vertex.
	 * @param x x-coordinate;
	 */
	void setX(int x);


	/**
	 * Returns y-coordinate of vertex.
	 * @return y-coordinate of vertex.
	 */
	int getY();
	
	/**
	 * Sets y-coordinate of vertex.
	 * @param y y-coordinate;
	 */
	void setY(int y);

	
	/**
	 * Moves the vertex.
	 * @param x new x-coordinate of the vertex.
	 * @param y new y-coordinate of the vertex.
	 */
	void moveTo(int x, int y);
	
	/**
	 * Returns name of the vertex.
	 * @return name of the vertex.
	 */
	String getName();
	
	/**
	 * Sets name of the vertex.
	 * @param name of the vertex.
	 */
	void setName(String name);
	
	/**
	 * Returns distance from center of vertex to point.
	 * @param x x-coordinate of point.
	 * @param y y-coordinate of point.
	 * @return distance from center of vertex to point.
	 */
	double distance(int x, int y);
	
}
