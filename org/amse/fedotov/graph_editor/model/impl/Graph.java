package org.amse.fedotov.graph_editor.model.impl;

import org.amse.fedotov.graph_editor.exception.BadInputException;
import org.amse.fedotov.graph_editor.model.*;
import org.amse.fedotov.graph_editor.settings.Settings;

import java.util.*;

/**
 * Implementation of IGraph interface.
 * @author Pavel Fedotov
 * @see Graph
 */
final public class Graph implements IGraph {
	
	private final List<IVertex> myVertices;
	private final List<IEdge> myEdges;
	
	/**
	 * Value of first vertex index.   
	 * When it needed to use it is automatically seted to 0 or 1.
	 */
	private int myFirstIndex = -1;
	
	public Graph() {
		myVertices = new ArrayList<IVertex>();
		myEdges = new ArrayList<IEdge>();
	}
	
	public IVertex addVertex(int x, int y) {
		IVertex vertex = new Vertex(x, y);
		vertex.setName(getNameAddingVertex());
		myVertices.add(vertex);
		return vertex;
	}
	
	public IVertex addVertex(int x, int y, String id) {
		if (isCorrect(id)) {
			IVertex vertex = addVertex(x, y);
			vertex.setName(id);
			return vertex;
		} else {
			throw new BadInputException("Can't create graph with vertices with the same id");
		}
	}
	
	public IVertex addVertex(IVertex vertex) {
		myVertices.add(vertex);
		return vertex;
	}
	
	public IEdge addEdge(IVertex source, IVertex target) {
		IEdge edge = new Edge(source, target);
		myEdges.add(edge);
		return edge;
	}

	public List<IVertex> vertices() {
		return Collections.unmodifiableList(myVertices);
	}
	
	public List<IEdge> edges() {
		return Collections.unmodifiableList(myEdges);
	}

	public void removeEdge(IEdge edge) {
		myEdges.remove(edge);
	}

	public void removeVertex(IVertex vertex) {
		myVertices.remove(vertex);
		for (Iterator<IEdge> it = myEdges.listIterator(); it.hasNext(); ) {
			IEdge e = it.next();
			if (e.getSource().equals(vertex) || e.getTarget().equals(vertex)) {
				it.remove();
			}
		}
	}
	
	// It doesn't check the source and the target in the graph.
	public boolean canAddEdge(IVertex source, IVertex target) {
		if (source == null || target == null) {
			return false;
		}
		if (source.equals(target)) {
			return false;
		}
		for (IEdge edge: edges()) {
			if ((edge.getSource().equals(source) && edge.getTarget().equals(target)) ||
					(edge.getSource().equals(target) && edge.getTarget().equals(source))) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public String toString() {
		return vertices().toString() + "\n" + edges().toString();
	}

	public IVertex getVertex(String id) {
		for (int i = 0; i < myVertices.size(); i++) {
			if (myVertices.get(i).getName().equals(id)) {
				return myVertices.get(i);
			}
		}
		return null;
	}
	
	/**
	 * This method checks if it is allow to add vertex with this id to the graph. 
	 * @param id identifier of adding vertex.
	 * @return <code>true</code> if there is no vertex with this id in the graph, <code>false</code> otherwise. 
	 */
	private boolean isCorrect(String id) {
		for (IVertex vertex : vertices()) {
			if (vertex.getName().equals(id)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Renames vertices so that they have <i>good</i> identifiers (numbers 
	 * <code>firstIndex</code>, <code>firstIndex + 1</code>, ... , 
	 * <code>numberVertices - 1 + firstIndex</code>).
	 * For example, if <code>firstIndex = 1</code> then vertices will
	 * have numbers 1, 2, ... , <code>numberVertices</code>.
	 * It renames vertices in the way so maximal number of vertices keep their names.    
	 */
	public void renameVertices() {
//		if (myFirstIndex == -1) {
//			myFirstIndex = Settings.firstVertexIndex;
//		}
		myFirstIndex = Settings.firstVertexIndex;
		for (int i = 0; i < myVertices.size(); i++) {
			if (!isGoodID(myVertices.get(i).getName())) {
				myVertices.get(i).setName(getNameAddingVertex());
			}
		}
	}

	public boolean isCorrectNumbers() {
//		if (myFirstIndex == -1) {
//			myFirstIndex = Settings.firstVertexIndex;
//		}
		myFirstIndex = Settings.firstVertexIndex;
		for (int i = 0; i < myVertices.size(); i++) {
			if (!isGoodID(myVertices.get(i).getName())) {
				return false;
			}
		}
		return true;
	}
	
	public List<String> getVerticesNames() {
		List<String> verticesNames = new ArrayList<String>();
		for (IVertex vertex : vertices()) {
			verticesNames.add(vertex.getName());
		}
		return verticesNames;
	}
	
	/**
	 * This method checks if the id of the vertex is correct for the graph.
	 * This method is launched only when <code>myFirstIndex >= 0</code>  
	 * @return <code>true</code> if the id correct, <code>false</code> otherwise.
	 */
	private boolean isGoodID(String id) {
		int index;
		try {
			index = Integer.parseInt(id);
		} catch (NumberFormatException ex) {
			return false;
		}
		return (myFirstIndex <= index && index < myVertices.size() + myFirstIndex);
	}

	public int getFirstIndex() {
		return myFirstIndex;
	}

	public void setFirstIndex(int firstIndex) {
		myFirstIndex = firstIndex;
	}
	
	/**
	 * Returns least correct index of adding vertex. 
	 * @return least correct index of adding vertex.
	 */
	private String getNameAddingVertex() {
		int index = myFirstIndex;
		if (index == -1) {
			index = Settings.firstVertexIndex;
		}
		while (true) {
			IVertex vertex = getVertex(String.valueOf(index));
			if (vertex == null) {
				return String.valueOf(index);
			} 
			index++;
		}
	}

	private HashMap<IVertex, Boolean> myIsVisited;
	private IGraph myComponent;
	
	public List<IGraph> components() {
		List<IGraph> components = new ArrayList<IGraph>();
		myIsVisited = new HashMap<IVertex, Boolean>();
		for (IVertex v : vertices()) {
			myIsVisited.put(v, false);
		}
		for (IVertex v : vertices()) {
			if (!myIsVisited.get(v)) {
				myComponent = new Graph();
				myComponent.addVertex(v);
				myIsVisited.put(v, true);
				dfs(v);
				components.add(myComponent);
			}
		}
		return components;
	}
	
	private void dfs(IVertex v) {
		for (IVertex vertex : adjacents(v)) {
			if (!myIsVisited.get(vertex)) {
				myIsVisited.put(vertex, true);
				myComponent.addVertex(vertex);
				myComponent.addEdge(v, vertex);
				dfs(vertex);
			}
		}
	}

	public List<IVertex> adjacents(IVertex vertex) {
		// TODO rewrite it!!!
		// This method works very slow!!! (O(E) for each query)
		List<IVertex> adjacents = new ArrayList<IVertex>();
		for (IEdge edge : edges()) {
			if (edge.getSource().equals(vertex)) {
				adjacents.add(edge.getTarget());
			} else if (edge.getTarget().equals(vertex)) {
				adjacents.add(edge.getSource());
			}
		}
		return adjacents;
	}

}
