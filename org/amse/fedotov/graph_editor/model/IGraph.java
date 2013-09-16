package org.amse.fedotov.graph_editor.model;

import java.util.List;

/**
 * This interface represents graph.
 * @author Pavel Fedotov
 * @see org.amse.fedotov.graph_editor.model.impl.Graph
 */
public interface IGraph {
	
	/**
	 * Returns list of vertices of the graph.
	 * @return list of vertices of the graph.
	 */
	public List<IVertex> vertices();
	
	/**
	 * Adds new vertex to the graph.
	 * @param x the x-coordinate of new vertex.
	 * @param y the y-coordinate of new vertex.
	 * @return new vertex.
	 */
	public IVertex addVertex(int x, int y);
	
	/**
	 * Adds new vertex to the graph.
	 * @param x the x-coordinate of new vertex.
	 * @param y the y-coordinate of new vertex.
	 * @param id the identificator of new vertex.
	 * @return new vertex.
	 * @see org.amse.fedotov.graph_editor.model.impl.Graph#addVertex
	 */
	public IVertex addVertex(int x, int y, String id);
	
	/**
	 * Adds new vertex to the graph.
	 * It doesn't create new object.
	 * @param vertex adding vertex.
	 * @return new vertex.
	 */
	public IVertex addVertex(IVertex vertex);
	
	/**
	 * Removes the vertex from the graph.
	 * If <code>vertex</code> is <code>null</code> 
	 * no exception is thrown and no action is performed.
	 * @param vertex the removing vertex.
	 */
	public void removeVertex(IVertex vertex);
	
	/**
	 * Returns list of edges of the graph.
	 * @return list of edges of the graph. 
	 */
	public List<IEdge> edges();
	
	/**
	 * Adds new edge to the graph.
	 * @param source the source of the edge.
	 * @param target the target of the edge.
	 * @return new edge.
	 */
	public IEdge addEdge(IVertex source, IVertex target);
	
	/**
	 * Removes the edge from the graph.
	 * If <code>edge</code> is <code>null</code> 
	 * no exception is thrown and no action is performed.
	 * @param edge the removing edge.
	 */
	public void removeEdge(IEdge edge);
	
	/**
	 * Returns <code>true</code> if can add edge between two vertecies, 
	 * <code>false</code> otherwise. 
	 * @param source vertex.
	 * @param target vertex.
	 * @return <code>true</code> if can add edge between two vertecies, 
	 * <code>false</code> otherwise.  
	 */
	public boolean canAddEdge(IVertex source, IVertex target);
	
	/**
	 * Returns the vertex with the index from the vertices list.
	 * @param id identificator of the vertex.
	 * @return the vertex.
	 */
	public IVertex getVertex(String id);
	
	/**
	 * Renames vertices so that they have <i>good</i> identifiers (numbers 
	 * <code>firstIndex</code>, <code>firstIndex + 1</code>, ... , 
	 * <code>numberVertices - 1 + firstIndex</code>).
	 * For example, if <code>firstIndex = 1</code> then vertices will
	 * have numbers 1, 2, ... , <code>numberVertices</code>.    
	 */
	public void renameVertices();
	
	/**
	 * Checks is all vertices have <i>good</i> identifiers.  
	 * @return <code>true</code> if at least one vertex is needed to change number, 
	 * <code>false</code> if nothing is needed to recount.
	 * @see IGraph#renameVertices
	 */
	public boolean isCorrectNumbers();

	/**
	 * Returns the value of index of first vertex in the graph.
	 * @return firstIndex
	 */
	public int getFirstIndex();

	/**
	 * Sets the value of index of first vertex in the graph.
	 * @param firstIndex 0 or 1.
	 */
	public void setFirstIndex(int firstIndex);

	/**
	 * Returns graph's components.
	 * @return graph's components.
	 */
	public List<IGraph> components();

	/**
	 * Returns list of adjacent vertices to the vertex.
	 * @param vertex the vertex.
	 * @return list of adjacent vertices.
	 */
	public List<IVertex> adjacents(IVertex vertex);
	
	/**
	 * Returns list of vertices names.
	 * Order of names is same as order of vertices in the graph.
	 * @return list of vertices names.
	 */
	public List<String> getVerticesNames();
}