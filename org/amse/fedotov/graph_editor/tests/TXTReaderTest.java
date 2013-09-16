package org.amse.fedotov.graph_editor.tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import junit.framework.TestCase;

import org.amse.fedotov.graph_editor.exception.BadInputException;
import org.amse.fedotov.graph_editor.model.IGraph;
import org.amse.fedotov.graph_editor.reader.IFileReader;
import org.amse.fedotov.graph_editor.reader.TXTFileReader;
import org.amse.fedotov.graph_editor.settings.Settings;
import org.amse.fedotov.graph_editor.writer.IFileWriter;
import org.amse.fedotov.graph_editor.writer.TXTFileWriter;
import org.amse.fedotov.graph_editor.writer.XMLFileWriter;

@SuppressWarnings("unused")
public class TXTReaderTest extends AbstractTest {
	
	@Override
	protected String getPath() {
		return super.getPath() + "txtreader" + File.separatorChar;
	}
	
	@Override
	protected String getExt() {
		if (Settings.firstVertexIndex > 0) {
			return super.getExt() + "1";
		} else {
			return super.getExt();
		}
	}
	
	
	public void test0() {
		String filename = "test0";
		File outFile = null;
		try {
			TXTFileReader fr = new TXTFileReader(new File(getPath() + filename + ".gtxt"));
			IGraph graphReaded = fr.read();
			outFile = writeGraph(filename, graphReaded);
		} catch (Exception ex) {

		}
		TestCase.assertTrue("Empty graph is readed incorrect", isEqualFiles(filename));
		outFile.delete();
	}
	
	public void test1() {
		String filename = "test1";
		File outFile = null;
		try {
			TXTFileReader fr = new TXTFileReader(new File(getPath() + filename + ".gtxt"));
			IGraph graphReaded = fr.read();
			outFile = writeGraph(filename, graphReaded);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		TestCase.assertTrue("When several spaces and newlines is added graph is readed bad", isEqualFiles(filename));
		outFile.delete();
	}
	
	public void test2() {
		String filename = "test2";
		File outFile = null;
		try {
			TXTFileReader fr = new TXTFileReader(new File(getPath() + filename + ".gtxt"));
			IGraph graphReaded = fr.read();
			outFile = writeGraph(filename, graphReaded);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		TestCase.assertTrue("Graph with one vertex is readed incorrect", isEqualFiles(filename));
		outFile.delete();
	}

	public void test3() {
		String filename = "test3";
		File outFile = null;
		try {
			TXTFileReader fr = new TXTFileReader(new File(getPath() + filename + ".gtxt"));
			IGraph graphReaded = fr.read();
			outFile = writeGraph(filename, graphReaded);
		} catch (Exception ex) {

		}
		TestCase.assertTrue("Graph with one edge is readed incorrect", isEqualFiles(filename));
		outFile.delete();
	}
	
	public void test4() {
		String filename = "test4";
		File outFile = null;
		try {
			TXTFileReader fr = new TXTFileReader(new File(getPath() + filename + ".gtxt"));
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
			TXTFileReader fr = new TXTFileReader(new File(getPath() + filename + ".gtxt"));
			IGraph graphReaded = fr.read();
			outFile = writeGraph(filename, graphReaded);
		} catch (Exception ex) {

		}
		TestCase.assertTrue("Graph with one edge is readed incorrect", isEqualFiles(filename));
		outFile.delete();
	}
	
	public void test6() {
		String filename = "test6";
		Exception ex = getException(filename);
		boolean isOK = (ex instanceof BadInputException && 
				ex.getMessage().equals("Vertices don't form integer sequence nor 0, 1, ..., V - 1, nor 1, 2, ..., V"));
		TestCase.assertTrue("BadInputException isn't thrown", isOK);
	}
	
	public void test7() {
		String filename = "test7";
		Exception ex = getException(filename);
		boolean isOK = (ex instanceof BadInputException && 
				ex.getMessage().equals("Index of vertices must be integer"));
		TestCase.assertTrue("BadInputException isn't thrown or has wrong message", isOK);
	}

	public void test8() {
		String filename = "test8";
		Exception ex = getException(filename);
		boolean isOK = (ex instanceof BadInputException && 
				ex.getMessage().equals("File not found"));
		TestCase.assertTrue("File not found exception isn't thrown", isOK);
	}
	
	public void test9() {
		String filename = "test9";
		Exception ex = getException(filename);
		boolean isOK = (ex instanceof BadInputException && 
				ex.getMessage().equals("Number of vertices or edges must be integer"));
		TestCase.assertTrue("BadInputException isn't thrown or has wrong message when input file has noninteger tokens", isOK);
	}
	
	public void test10() {
		String filename = "test10";
		Exception ex = getException(filename);
		boolean isOK = (ex instanceof BadInputException && 
				ex.getMessage().equals("Input file is empty"));
		TestCase.assertTrue("BadInputException isn't thrown or has wrong message when input file doesn't contaion information about number of vertices or edges", isOK);
	}
	
	public void test11() {
		String filename = "test11";
		Exception ex = getException(filename);
		boolean isOK = (ex instanceof BadInputException && 
				ex.getMessage().equals("There isn't number of vertices or number of edges in input file"));
		TestCase.assertTrue("BadInputException isn't thrown or has wrong message when input file doesn't contaion information about number of vertices or edges", isOK);
	}

	public void test12() {
		String filename = "test12";
		Exception ex = getException(filename);
		boolean isOK = (ex instanceof BadInputException && 
				ex.getMessage().equals("There isn't number of vertices or number of edges in input file"));
		TestCase.assertTrue("BadInputException isn't thrown or has wrong message when input file doesn't contaion information about number of vertices or edges", isOK);
	}

	public void test13() {
		String filename = "test13";
		File outFile = null;
		IGraph graphReaded = readGraph(filename);
		outFile = writeGraph(filename, graphReaded);
		TestCase.assertTrue("Graph is readed incorrect", isEqualFiles(filename));
		outFile.delete();
	}

	public void test14() {
		String filename = "test14";
		File outFile = null;
		IGraph graphReaded = readGraph(filename);
		outFile = writeGraph(filename, graphReaded);
		TestCase.assertTrue("Graph is readed incorrect", isEqualFiles(filename));
		outFile.delete();
	}
	
	public void test15() {
		String filename = "test15";
		Exception ex = getException(filename);
		boolean isOK = (ex instanceof BadInputException && 
				ex.getMessage().equals("Vertices don't form integer sequence nor 0, 1, ..., V - 1, nor 1, 2, ..., V"));
		TestCase.assertTrue("BadInputException isn't thrown", isOK);
	}
	
	public void test16() {
		String filename = "test16";
		File outFile = null;
		IGraph graphReaded = readGraph(filename);
		outFile = writeGraph(filename, graphReaded);
		TestCase.assertTrue("Graph is readed incorrect", isEqualFiles(filename));
		outFile.delete();
	}

	public void test17() {
		String filename = "test17";
		Exception ex = getException(filename);
		boolean isOK = (ex instanceof BadInputException && 
				ex.getMessage().equals("Vertices don't form integer sequence nor 0, 1, ..., V - 1, nor 1, 2, ..., V"));
		TestCase.assertTrue("BadInputException isn't thrown", isOK);
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
	
	// Returns null if some exception is catched
	private IGraph readGraph(String filename) {
		IGraph graph = null;
		try {
			IFileReader fr = new TXTFileReader(new File(getPath() + filename + ".gtxt"));
			graph = fr.read();
		} catch (Exception ex) {

		}
		return graph;
	}

	private Exception getException(String filename) {
		try {
			IFileReader fr = new TXTFileReader(new File(getPath() + filename + ".gtxt"));
			IGraph graphReaded = fr.read();
		} catch (Exception ex) {
			return ex;
		}
		return null;
	}
	
}