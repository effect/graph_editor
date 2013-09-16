package org.amse.fedotov.graph_editor.reader;

import java.io.File;
import java.io.FileNotFoundException;

import javax.xml.parsers.DocumentBuilderFactory;

import org.amse.fedotov.graph_editor.exception.BadInputException;
import org.amse.fedotov.graph_editor.model.IGraph;
import org.amse.fedotov.graph_editor.model.IVertex;
import org.amse.fedotov.graph_editor.model.impl.Graph;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * XML-file reader for the graph.
 * Used encoding - UTF-8.  
 * @author Pavel Fedotov
 *
 */
public class XMLFileReader implements IFileReader {
	
	private Node myDocument;
	
	public XMLFileReader(File file) {
		try {
			myDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
		} catch (FileNotFoundException ex) {
			throw new BadInputException("File not found");
		} catch (Exception ex) {
			throw new BadInputException("Bad XML file");
		} 
	}
	
	public IGraph read() {
		if (myDocument == null) { 
			throw new BadInputException("Bad XML file");
		}
		IGraph graph = new Graph();
		myDocument = myDocument.getFirstChild();
		NodeList children = myDocument.getChildNodes();
		int number = children.getLength(); 
		for (int i = 0; i < number; i++) {
			Node child = children.item(i);
			if (child.getNodeName() == "vertex") {
				String id = child.getAttributes().getNamedItem("id").getNodeValue();
				int x = Integer.parseInt(child.getAttributes().getNamedItem("x").getNodeValue());
				int y = Integer.parseInt(child.getAttributes().getNamedItem("y").getNodeValue());
				graph.addVertex(x, y, id);
			} else if (child.getNodeName() == "edge") {
				String sourceID = child.getAttributes().getNamedItem("source").getNodeValue();
				IVertex source = graph.getVertex(sourceID);
				String targetID = child.getAttributes().getNamedItem("target").getNodeValue();
				IVertex target = graph.getVertex(targetID);
				if (graph.canAddEdge(source, target)) {
					graph.addEdge(source, target);
				} else {
					throw new BadInputException("Incorrect structure of the graph");
				}
			} else {
				if (child.getNodeName().trim() != "#text") {
					throw new BadInputException("Bad XML file");
				}
			}
		}
		return graph;
	}
}
