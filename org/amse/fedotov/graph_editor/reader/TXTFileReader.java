package org.amse.fedotov.graph_editor.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import org.amse.fedotov.graph_editor.exception.BadInputException;
import org.amse.fedotov.graph_editor.model.IGraph;
import org.amse.fedotov.graph_editor.model.IVertex;
import org.amse.fedotov.graph_editor.model.impl.Graph;
import org.amse.fedotov.graph_editor.settings.Settings;

public class TXTFileReader implements IFileReader {

	StringTokenizer myST;
	IGraph myGraph;
	BufferedReader myReader;
	
	public TXTFileReader(File file) {
		try {
			myReader = new BufferedReader(new FileReader(file));
			myST = new StringTokenizer(myReader.readLine());
		} catch (NullPointerException ex) {
			throw new BadInputException("Input file is empty");
		} catch (FileNotFoundException ex) {
			throw new BadInputException("File not found");
		} catch (IOException ex) {
			throw new BadInputException("Bad input file");
		}
	}

	public IGraph read() {
		try {
			myGraph = new Graph();
			int numVertices = nextInt();
			int numEdges = nextInt();
			for (int i = 0; i < numEdges; i++) {
				IVertex source = getCorrectVertex(nextString(), numVertices);
				IVertex target = getCorrectVertex(nextString(), numVertices);
				if (myGraph.canAddEdge(source, target)) {
					myGraph.addEdge(source, target);
				} else {
					throw new BadInputException("Incorrect structure of the graph");
				}
			}
			// Set firstIndex
			if (myGraph.getFirstIndex() == -1) {
				myGraph.setFirstIndex(Settings.firstVertexIndex);
			}
			// Add isolated vertices
			for (int i = myGraph.getFirstIndex(); i < numVertices + myGraph.getFirstIndex(); i++) {
				if (myGraph.getVertex(String.valueOf(i)) == null) {
					myGraph.addVertex(0, 0, String.valueOf(i));
				}
			}
			return myGraph;
		} catch (NumberFormatException ex) {
			throw new BadInputException("Number of vertices or edges must be integer");
		} catch (IOException ex) {
			throw new BadInputException("Bad input file");
		} catch (NullPointerException ex) {
			throw new BadInputException("There isn't number of vertices or number of edges in input file");
		}
	}
	
	private int nextInt() throws IOException, NumberFormatException {
		while (!myST.hasMoreTokens()) {
			myST = new StringTokenizer(myReader.readLine());
		}
		return Integer.parseInt(myST.nextToken());
	}
	
	private String nextString() throws IOException {
		while (!myST.hasMoreTokens()) {
			myST = new StringTokenizer(myReader.readLine());
		}
		return myST.nextToken();
	}
	
	/**
	 * Returns vertex with this id if it is correct for the graph. 
	 * @param id vertex identifier.
	 * @param numVertices number of vertices in the graph.
	 * @return vertex with this id.
	 * @throws BadInputException if this id incorrect.
	 */
	private IVertex getCorrectVertex(String id, int numVertices) {
		int idNumber;
		try {
			idNumber = Integer.parseInt(id);
		} catch (NumberFormatException ex) {
			throw new BadInputException("Index of vertices must be integer");
		}
		IVertex vertex = null;
		if (idNumber >= 0 && idNumber <= numVertices) {
			if (idNumber == 0) {
				if (myGraph.getFirstIndex() == 1) {
					throw new BadInputException("Vertices don't form integer sequence nor 0, 1, ..., V - 1, nor 1, 2, ..., V");
				} else {
					myGraph.setFirstIndex(0);
				}
			}
			if (idNumber == numVertices) {
				if (myGraph.getFirstIndex() == 0) {
					throw new BadInputException("Vertices don't form integer sequence nor 0, 1, ..., V - 1, nor 1, 2, ..., V");
				} else {
					myGraph.setFirstIndex(1);
				}
			}
			vertex = myGraph.getVertex(id);
			if (vertex == null) {
				vertex = myGraph.addVertex(0, 0, id);
			}
			return vertex;
		} else {
			throw new BadInputException("Vertices don't form integer sequence nor 0, 1, ..., V - 1, nor 1, 2, ..., V");
		}
	}

}
