package org.amse.fedotov.graph_editor.settings;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.amse.fedotov.graph_editor.exception.BadInputException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Settings {
	
	/**
	 * Value of index of first vertex in a graph.
	 */
	public static int firstVertexIndex = 1;
	
	/**
	 * If <code>printVerticesNames == true</code> then vertices names outputed.
	 */
	public static boolean printVerticesNames = true;
	
	public static int frameWidth = 800;
	
	public static int frameHeight = 600;
	
	public static int frameXLocation = 100;
	
	public static int frameYLocation = 100;
	
	final private static File myFile = new File(System.getProperty("user.home") + 
			File.separatorChar + ".GraphEditor.ini");
	
	private static Node createDocument() {
		try {
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
			Element root = doc.createElement("graph-editor");
			Element e;
			e = doc.createElement("frame");
			e.setAttribute("width", String.valueOf(frameWidth));
			e.setAttribute("height", String.valueOf(frameHeight));
			e.setAttribute("x-location", String.valueOf(frameXLocation));
			e.setAttribute("y-location", String.valueOf(frameYLocation));
			root.appendChild(e);
			
			e = doc.createElement("first-vertex");
			e.setAttribute("index", String.valueOf(firstVertexIndex));
			root.appendChild(e);
			
			e = doc.createElement("vertices-names");
			e.setAttribute("print", String.valueOf(printVerticesNames));
			root.appendChild(e);
			
			return root;
		} catch (ParserConfigurationException ex) {
			throw new BadInputException("Bad xml file creator");
		}
	}
	
	public static void writeSettings() {
		try {
			java.io.FileWriter fw = new java.io.FileWriter(myFile);
			TransformerFactory factory = TransformerFactory.newInstance();
			factory.setAttribute("indent-number", new Integer(4));
			Transformer tr = factory.newTransformer();
			tr.setOutputProperty(OutputKeys.INDENT, "yes");
			tr.transform(new DOMSource(createDocument()), new StreamResult(fw));
			fw.close();
		} catch (TransformerException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public static void readSettings() {
		try {
			Node document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(myFile);
			document = document.getFirstChild();
			NodeList children = document.getChildNodes();
			int number = children.getLength(); 
			for (int i = 0; i < number; i++) {
				Node child = children.item(i);
				if (child.getNodeName() == "frame") {
					frameWidth = Integer.parseInt(child.getAttributes().getNamedItem("width").getNodeValue());
					frameHeight = Integer.parseInt(child.getAttributes().getNamedItem("height").getNodeValue());
					frameXLocation = Integer.parseInt(child.getAttributes().getNamedItem("x-location").getNodeValue());
					frameYLocation = Integer.parseInt(child.getAttributes().getNamedItem("y-location").getNodeValue());
				} else if (child.getNodeName() == "first-vertex") {
					firstVertexIndex = Integer.parseInt(child.getAttributes().getNamedItem("index").getNodeValue());
				} else if (child.getNodeName() == "vertices-names") {
					printVerticesNames = Boolean.parseBoolean(child.getAttributes().getNamedItem("print").getNodeValue());
				} else {
					if (child.getNodeName().trim() != "#text") {
						throw new BadInputException("Bad XML file");
					}
				}
			}
		} catch (FileNotFoundException ex) {
			// if file doesn't exist use default values
		} catch (Exception ex) {
			// if some exception has been thrown then use default values
		}
	}

	
}
