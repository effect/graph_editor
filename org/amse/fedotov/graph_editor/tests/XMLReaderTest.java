package org.amse.fedotov.graph_editor.tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.io.PrintWriter;

import org.amse.fedotov.graph_editor.exception.BadInputException;
import org.amse.fedotov.graph_editor.model.*;
import org.amse.fedotov.graph_editor.reader.XMLFileReader;
import org.amse.fedotov.graph_editor.settings.Settings;
import org.amse.fedotov.graph_editor.writer.IFileWriter;
import org.amse.fedotov.graph_editor.writer.XMLFileWriter;
import org.xml.sax.SAXParseException;

import junit.framework.TestCase;;

@SuppressWarnings("unused")
public class XMLReaderTest extends AbstractTest {
	
	@Override
	protected String getPath() {
		return super.getPath() + "xmlreader" + File.separatorChar;
	}
	
	public void test0() {
		String filename = "test0";
		File outFile = null;
		try {
			XMLFileReader fr = new XMLFileReader(new File(getPath() + filename + ".gxml"));
			IGraph graphReaded = fr.read();
			outFile = writeGraph(filename, graphReaded);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		TestCase.assertTrue("Empty graph is readed incorrect", isEqualFiles(filename));
		outFile.delete();
	}
	
	public void test1() {
		String filename = "test1";
		File outFile = null;
		try {
			XMLFileReader fr = new XMLFileReader(new File(getPath() + filename + ".gxml"));
			IGraph graphReaded = fr.read();
			outFile = writeGraph(filename, graphReaded);
		} catch (Exception ex) {

		}
		TestCase.assertTrue("Graph with one vertex is readed incorrect", isEqualFiles(filename));
		outFile.delete();
	}
	
	public void test2() {
		String filename = "test2";
		File outFile = null;
		try {
			XMLFileReader fr = new XMLFileReader(new File(getPath() + filename + ".gxml"));
			IGraph graphReaded = fr.read();
			outFile = writeGraph(filename, graphReaded);
		} catch (Exception ex) {

		}
		TestCase.assertTrue("Graph with two vertecies is readed incorrect", isEqualFiles(filename));
		outFile.delete();
	}

	public void test3() {
		String filename = "test3";
		File outFile = null;
		try {
			XMLFileReader fr = new XMLFileReader(new File(getPath() + filename + ".gxml"));
			IGraph graphReaded = fr.read();
			outFile = writeGraph(filename, graphReaded);
		} catch (Exception ex) {

		}
		TestCase.assertTrue("Graph with one edge is readed incorrect", isEqualFiles(filename));
		outFile.delete();
	}

	public void test5() {
		String filename = "test5";
		File outFile = null;
		try {
			XMLFileReader fr = new XMLFileReader(new File(getPath() + filename + ".gxml"));
			IGraph graphReaded = fr.read();
			outFile = writeGraph(filename, graphReaded);
		} catch (Exception ex) {

		}
		TestCase.assertTrue("Graph is readed incorrect", isEqualFiles(filename));
		outFile.delete();
	}
	
	public void test6() {
		String filename = "test6";
		Exception ex = getException(filename);
		boolean isOK = ex.getMessage().equals("Bad XML file");
		TestCase.assertTrue("Bad XML file exception isn't thrown", isOK);
	}
	
	public void test7() {
		String filename = "test7";
		Exception ex = getException(filename);
		boolean isOK = ex.getMessage().equals("Incorrect structure of the graph");
		TestCase.assertTrue("Incorrect structure of the graph exception isn't thrown", isOK);
	}

	public void test8() {
		String filename = "test8";
		Exception ex = getException(filename);
		boolean isOK = ex.getMessage().equals("File not found");
		TestCase.assertTrue("File not found exception isn't thrown", isOK);
	}
	
	public void test9() {
		String filename = "test9";
		Exception ex = getException(filename);
		boolean isOK = ex.getMessage().equals("Bad XML file");
		TestCase.assertTrue("Bad XML file exception isn't thrown", isOK);
	}
	
	public void test10() {
		String filename = "test10";
		Exception ex = getException(filename);
		boolean isOK = ex.getMessage().equals("Incorrect structure of the graph");
		TestCase.assertTrue("Incorrect structure of the graph exception isn't thrown", isOK);
	}
	
	public void test11() {
		String filename = "test11";
		Exception ex = getException(filename);
		boolean isOK = ex.getMessage().equals("Incorrect structure of the graph");
		TestCase.assertTrue("Incorrect structure of the graph exception isn't thrown", isOK);
	}
	
	public void test12() {
		String filename = "test12";
		Exception ex = getException(filename);
		boolean isOK = ex.getMessage().equals("Incorrect structure of the graph");
		TestCase.assertTrue("Incorrect structure of the graph exception isn't thrown", isOK);
	}
	
	public void test13() {
		String filename = "test13";
		Exception ex = getException(filename);
		boolean isOK = ex.getMessage().equals("Can't create graph with vertices with the same id");
		TestCase.assertTrue("Can't create graph with vertices with the same id exception isn't thrown", isOK);
	}
	
	public void test14() {
		String filename = "test14";
		File outFile = null;
		IGraph graphReaded = readGraph(filename);
		outFile = writeGraph(filename, graphReaded);
		TestCase.assertTrue("Graph with removed vertices is readed incorrect", isEqualFiles(filename));
		outFile.delete();
	}

	public void test15() {
		String filename = "test15";
		File outFile = null;
		IGraph graphReaded = readGraph(filename);
		outFile = writeGraph(filename, graphReaded);
		TestCase.assertTrue("Graph with removed vertices is readed incorrect", isEqualFiles(filename));
		outFile.delete();
	}
	
	public void test16() {
		String filename = "test16";
		Exception ex = getException(filename);
		boolean isOK = ex.getMessage().equals("Bad XML file");
		TestCase.assertTrue("Bad XML file exception isn't thrown", isOK);
	}
	
	public void test17() {
		String filename = "test17";
		Exception ex = getException(filename);
		boolean isOK = ex.getMessage().equals("Bad XML file");
		TestCase.assertTrue("Bad XML file exception isn't thrown", isOK);
	}

	
	private Exception getException(String filename) {
		try {
			XMLFileReader fr = new XMLFileReader(new File(getPath() + filename + ".gxml"));
			IGraph graphReaded = fr.read();
		} catch (Exception ex) {
			return ex;
		}
		return null;
	}
	
	// Returns null if some exception is catched
	private IGraph readGraph(String filename) {
		IGraph graph = null;
		try {
			XMLFileReader fr = new XMLFileReader(new File(getPath() + filename + ".gxml"));
			graph = fr.read();
		} catch (Exception ex) {

		}
		return graph;
	}
	
	private File writeGraph(String filename, IGraph graph) {
		File outFile = new File(getPath() + filename + ".out");
		try {
			IFileWriter fw = new XMLFileWriter(graph);
			fw.write(outFile);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return outFile;
	}
}