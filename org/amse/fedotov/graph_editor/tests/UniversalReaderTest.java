package org.amse.fedotov.graph_editor.tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import junit.framework.TestCase;

import org.amse.fedotov.graph_editor.exception.BadInputException;
import org.amse.fedotov.graph_editor.model.IGraph;
import org.amse.fedotov.graph_editor.reader.IFileReader;
import org.amse.fedotov.graph_editor.reader.TXTFileReader;
import org.amse.fedotov.graph_editor.reader.UniversalReader;
import org.amse.fedotov.graph_editor.settings.Settings;
import org.amse.fedotov.graph_editor.writer.IFileWriter;
import org.amse.fedotov.graph_editor.writer.TXTFileWriter;
import org.amse.fedotov.graph_editor.writer.XMLFileWriter;

@SuppressWarnings("unused")
public class UniversalReaderTest extends AbstractTest {
	
	@Override
	protected String getPath() {
		return super.getPath() + "universal_reader" + File.separatorChar;
	}
	
	@Override
	protected String getExt() {
		return super.getExt();
	}
	
	
	public void test0() {
		String filename = "test0.gxml";
		Exception ex = getException(filename);
		boolean isOK = ex.getMessage().equals("File not found");
		TestCase.assertTrue("File not found exception isn't thrown", isOK);
	}
	
	public void test1() {
		String filename = "test1.gxml";
		Exception ex = getException(filename);
		boolean isOK = ex.getMessage().equals("Bad input file format");
		TestCase.assertTrue("Bad input file format exception isn't thrown", isOK);
	}
	
	public void test2() {
		String filename = "test2";
		File outFile = null;
		IGraph graph = readGraph(filename + ".gtxt");
		outFile = writeGraph(filename, graph);
		TestCase.assertTrue("GTXT file is readed incorrect", isEqualFiles(filename));
		outFile.delete();
	}

	public void test3() {
		String filename = "test3";
		File outFile = null;
		IGraph graph = readGraph(filename + ".gxml");
		outFile = writeGraph(filename, graph);
		TestCase.assertTrue("GXML file is readed incorrect", isEqualFiles(filename));
		outFile.delete();
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
		try {
			UniversalReader fr = new UniversalReader(new File(getPath() + filename));
			IGraph graph = fr.getReader().read();
			return graph;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	private Exception getException(String filename) {
		try {
			UniversalReader fr = new UniversalReader(new File(getPath() + filename));
			IGraph graphReaded = fr.getReader().read();
		} catch (Exception ex) {
			return ex;
		}
		return null;
	}
	
}