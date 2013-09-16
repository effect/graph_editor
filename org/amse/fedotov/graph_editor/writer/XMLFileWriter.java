package org.amse.fedotov.graph_editor.writer;

import org.amse.fedotov.graph_editor.exception.BadInputException;
import org.amse.fedotov.graph_editor.model.IEdge;
import org.amse.fedotov.graph_editor.model.IGraph;
import org.amse.fedotov.graph_editor.model.IVertex;

import org.w3c.dom.*;

import javax.xml.parsers.*;

import java.io.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * XML-file writer for the graph.
 * Used encoding - UTF-8.  
 * @author Pavel Fedotov
 *
 */
public class XMLFileWriter implements IFileWriter {
	
	private IGraph myGraph;
	
	private Node myDocument;
	
	public XMLFileWriter(IGraph graph) {
		try {
			myGraph = graph;
			myDocument = createDocument();
		} catch (ParserConfigurationException ex) {
			throw new BadInputException("Bad graph");
		}
	}
	
	private Node createDocument() throws ParserConfigurationException {
		Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		Element root = doc.createElement("graph");
		for (IVertex vertex : myGraph.vertices()) {
			Element e = doc.createElement("vertex");
			e.setAttribute("id", vertex.getName());
			e.setAttribute("x", ((Integer)vertex.getX()).toString());
			e.setAttribute("y", ((Integer)vertex.getY()).toString());
			root.appendChild(e);
		}
		for (IEdge edge : myGraph.edges()) {
			Element e = doc.createElement("edge");
			e.setAttribute("source", edge.getSource().getName());
			e.setAttribute("target", edge.getTarget().getName());
			root.appendChild(e);
		}
		return root;
	}
	
	public void write(File file) throws IOException {
		try {
			java.io.FileWriter fw = new java.io.FileWriter(file);
			TransformerFactory factory = TransformerFactory.newInstance();
			factory.setAttribute("indent-number", new Integer(4));
			Transformer tr = factory.newTransformer();
			tr.setOutputProperty(OutputKeys.INDENT, "yes");
			tr.transform(new DOMSource(myDocument), new StreamResult(fw));
			fw.close();
		} catch (TransformerException ex) {
			ex.printStackTrace();
		}
	}
}
