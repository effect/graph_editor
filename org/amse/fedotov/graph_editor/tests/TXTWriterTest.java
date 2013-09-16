package org.amse.fedotov.graph_editor.tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import java.io.IOException;
import java.io.PrintWriter;

import org.amse.fedotov.graph_editor.model.*;
import org.amse.fedotov.graph_editor.model.impl.*;
import org.amse.fedotov.graph_editor.settings.Settings;
import org.amse.fedotov.graph_editor.writer.TXTFileWriter;


import junit.framework.TestCase;;

@SuppressWarnings("unused")
public class TXTWriterTest extends AbstractTest {
	
	@Override
	protected String getPath() {
		return super.getPath() + "txtwriter" + File.separatorChar;
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
		File outFile = new File(getPath() + filename + ".out");
		try {
			IGraph graph = new Graph();
			graph.renameVertices();
			TXTFileWriter fw = new TXTFileWriter(graph);
			fw.write(outFile);
		} catch (Exception ex) {

		}
		TestCase.assertTrue("Empty graph is writed incorrect", isEqualFiles(filename));
		outFile.delete();
	}
	
	public void test1() {
		String filename = "test1";
		File outFile = new File(getPath() + filename + ".out");
		try {
			IGraph graph = new Graph();
			graph.addVertex(5, 3);
			graph.renameVertices();
			TXTFileWriter fw = new TXTFileWriter(graph);
			fw.write(outFile);
		} catch (Exception ex) {

		}
		TestCase.assertTrue("Graph with one vertex is writed incorrect", isEqualFiles(filename));
		outFile.delete();
	}

	public void test2() {
		String filename = "test2";
		File outFile = new File(getPath() + filename + ".out");
		try {
			IGraph graph = new Graph();
			graph.addVertex(5, 3);
			graph.addVertex(10, 2);
			graph.renameVertices();
			TXTFileWriter fw = new TXTFileWriter(graph);
			fw.write(outFile);
		} catch (Exception ex) {

		}
		TestCase.assertTrue("Graph with two vertices is writed incorrect", isEqualFiles(filename));
		outFile.delete();
	}

	public void test3() {
		String filename = "test3";
		File outFile = new File(getPath() + filename + ".out");
		try {
			IGraph graph = new Graph();
			IVertex v0 = graph.addVertex(5, 3);
			IVertex v1 = graph.addVertex(10, 2);
			graph.addEdge(v0, v1);
			graph.renameVertices();
			TXTFileWriter fw = new TXTFileWriter(graph);
			fw.write(outFile);
		} catch (Exception ex) {

		}
		TestCase.assertTrue("Graph with one edge is writed incorrect", isEqualFiles(filename));
		outFile.delete();
	}
	
	public void test4() {
		String filename = "test4";
		File outFile = new File(getPath() + filename + ".out");
		try {
			IGraph graph = new Graph();
			IVertex v0 = graph.addVertex(5, 3);
			IVertex v1 = graph.addVertex(10, 2);
			graph.addEdge(v0, v1);
			IVertex v2 = graph.addVertex(7, 1);
			IVertex v3 = graph.addVertex(7, 4);
			IVertex v4 = graph.addVertex(34, 3);
			graph.addEdge(v4, v1);
			graph.addEdge(v3, v4);
			graph.renameVertices();
			TXTFileWriter fw = new TXTFileWriter(graph);
			fw.write(outFile);
		} catch (Exception ex) {
	
		}
		TestCase.assertTrue("Graph is writed incorrect", isEqualFiles(filename));
		outFile.delete();
	}

	public void test5() {
		String filename = "test5";
		File outFile = new File(getPath() + filename + ".out");
		try {
			IGraph graph = new Graph();
			IVertex v0 = graph.addVertex(200, 7);
			IVertex v1 = graph.addVertex(2, 27);
			IVertex v2 = graph.addVertex(239, 239);
			IVertex v3 = graph.addVertex(11, 13);
			IEdge e0 = graph.addEdge(v0, v1);
			IEdge e1 = graph.addEdge(v3, v0);
			IEdge e2 = graph.addEdge(v1, v2);
			IEdge e3 = graph.addEdge(v2, v3);
			graph.removeVertex(v2);
			graph.removeVertex(v0);	
			graph.renameVertices();
			TXTFileWriter fw = new TXTFileWriter(graph);
			fw.write(outFile);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		TestCase.assertTrue("Graph is writed incorrect after removing vertices", isEqualFiles(filename));
		outFile.delete();
	}
	
	public void test6() {
		String filename = "test6";
		File outFile = new File(getPath() + filename + ".out");
		try {
			IGraph graph = new Graph();
			IVertex v0 = graph.addVertex(200, 7);
			IVertex v1 = graph.addVertex(2, 27);
			IVertex v2 = graph.addVertex(239, 239);
			IVertex v3 = graph.addVertex(11, 13);
			IEdge e0 = graph.addEdge(v0, v1);
			IEdge e1 = graph.addEdge(v3, v0);
			IEdge e2 = graph.addEdge(v1, v2);
			IEdge e3 = graph.addEdge(v2, v3);
			graph.removeVertex(v0);
			graph.removeVertex(v1);		
			graph.renameVertices();
			TXTFileWriter fw = new TXTFileWriter(graph);
			fw.write(outFile);
		} catch (Exception ex) {
			
		}
		TestCase.assertTrue("Graph is writed incorrect after removing vertices", isEqualFiles(filename));
		outFile.delete();
	}	

}
