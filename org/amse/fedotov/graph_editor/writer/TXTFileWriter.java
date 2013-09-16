package org.amse.fedotov.graph_editor.writer;

import java.io.File;
import java.io.IOException;

import org.amse.fedotov.graph_editor.model.IEdge;
import org.amse.fedotov.graph_editor.model.IGraph;

/**
 * This class allows writing the graph to the file as edge list.
 * @author Pavel Fedotov
 *
 */
public class TXTFileWriter implements IFileWriter {
	
	private IGraph myGraph;

	/**
	 * Constructs TXTFileWriter for the graph.
	 * It is supposed that the graph has <i>good</i> vertex numbers.
	 * @see IGraph#recountVertices
	 * @param graph
	 */
	public TXTFileWriter(IGraph graph) {
		myGraph = graph;
	}
	
	public void write(File file) throws IOException {
		java.io.FileWriter fw = new java.io.FileWriter(file);
		fw.write(((Integer)myGraph.vertices().size()).toString() + " " + 
				((Integer)myGraph.edges().size()).toString() + "\n");
		for (IEdge edge : myGraph.edges()) {
			fw.write(edge.getSource().getName() + " " + edge.getTarget().getName() + "\n");
		}
		fw.close();
	}
	
}
