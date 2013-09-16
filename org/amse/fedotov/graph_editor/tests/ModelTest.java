package org.amse.fedotov.graph_editor.tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.amse.fedotov.graph_editor.exception.BadInputException;
import org.amse.fedotov.graph_editor.model.*;
import org.amse.fedotov.graph_editor.model.impl.*;
import org.amse.fedotov.graph_editor.settings.Settings;

import com.sun.corba.se.impl.javax.rmi.CORBA.Util;

import junit.framework.TestCase;;

@SuppressWarnings("unused")
public class ModelTest extends AbstractTest {
	
	@Override
	protected String getPath() {
		return super.getPath() + "model" + File.separatorChar;
	}
	
	@Override
	protected String getExt() {
		if (Settings.firstVertexIndex > 0) {
			return super.getExt() + "1";
		} else {
			return super.getExt();
		}
	}
	
	/*
	 * test0 - test9 test addVertex / removeVertex methods
	 */
	public void test0() {
		String filename = "test0";
		IGraph graph = new Graph();
		File outFile = printToFile(filename, graph);
		TestCase.assertTrue("Empty graph is created incorrect", isEqualFiles(filename));
		outFile.delete();
	}
	
	public void test1() {
		String filename = "test1";
		IGraph graph = new Graph();
		graph.addVertex(2, 3);
		File outFile = printToFile(filename, graph);
		TestCase.assertTrue("addVertex method works incorrect. First vertex is added wrong", isEqualFiles(filename));
		outFile.delete();
	}
	
	public void test2() {
		String filename = "test2";
		IGraph graph = new Graph();
		graph.addVertex(2, 3);
		graph.addVertex(1, 1);
		File outFile = printToFile(filename, graph);
		TestCase.assertTrue("addVertex method works incorrect. Second vertex is added wrong", isEqualFiles(filename));
		outFile.delete();
	}
	
	public void test3() {
		String filename = "test3";
		IGraph graph = new Graph();
		graph.addVertex(2, 3);
		graph.addVertex(1, 1);
		graph.addVertex(5, 7);
		graph.addVertex(3, 100);
		graph.addVertex(23, 2);
		File outFile = printToFile(filename, graph);
		TestCase.assertTrue("addVertex method works incorrect", isEqualFiles(filename));
		outFile.delete();
	}
	
	public void test4() {
		String filename = "test4";
		IGraph graph = new Graph();
		IVertex v0 = graph.addVertex(2, 3);
		graph.removeVertex(v0);
		File outFile = printToFile(filename, graph);
		TestCase.assertTrue("removeVertex method works incorrect", isEqualFiles(filename));
		outFile.delete();
	}

	public void test5() {
		String filename = "test5";
		IGraph graph = new Graph();
		IVertex v0 = graph.addVertex(2, 3);
		graph.removeVertex(v0);
		graph.addVertex(7, 3);
		File outFile = printToFile(filename, graph);
		TestCase.assertTrue("addVertex method works incorrect after removing vertex", isEqualFiles(filename));
		outFile.delete();
	}
	
	public void test6() {
		String filename = "test6";
		IGraph graph = new Graph();
		IVertex v0 = graph.addVertex(2, 3);
		graph.removeVertex(v0);
		IVertex v1 = graph.addVertex(3, 5);
		IVertex v2 = graph.addVertex(5, 2);
		graph.removeVertex(v2);
		File outFile = printToFile(filename, graph);
		TestCase.assertTrue("removeVertex method works incorrect", isEqualFiles(filename));
		outFile.delete();
	}
	
	public void test7() {
		String filename = "test7";
		IGraph graph = new Graph();
		IVertex v0 = graph.addVertex(2, 3);
		graph.removeVertex(v0);
		graph.removeVertex(v0);
		File outFile = printToFile(filename, graph);
		TestCase.assertTrue("If removing vertex doesn't exist in graph removeVertex method works incorrect", isEqualFiles(filename));
		outFile.delete();
	}

	public void test8() {
		String filename = "test8";
		IGraph graph = new Graph();
		IVertex vertex0 = graph.addVertex(1, 1);
		IVertex vertex1 = graph.addVertex(2, 5);
		IVertex vertex2 = graph.addVertex(9, 3);
		graph.removeVertex(vertex1);
		IVertex vertex3 = graph.addVertex(4, 4);
		graph.removeVertex(vertex2);
		File outFile = printToFile(filename, graph);
		TestCase.assertTrue("removeVertex method works incorrect", isEqualFiles(filename));
		outFile.delete();
	}
	
	public void test9() {
		String filename = "test9";
		IGraph graph = new Graph();
		IVertex vertex0 = graph.addVertex(1, 1);
		graph.removeVertex(null);
		File outFile = printToFile(filename, graph);
		TestCase.assertTrue("If removing vertex = null removeVertex method works incorrect with", isEqualFiles(filename));
		outFile.delete();
	}
	
	/*
	 * test10-test17 test addEdge / removeEdge / addVertex / removeVertex methods
	 */
	public void test10() {
		String filename = "test10";
		IGraph graph = new Graph();
		IVertex v0 = graph.addVertex(0, 0);
		IVertex v1 = graph.addVertex(1, 1);
		graph.addEdge(v0, v1);
		File outFile = printToFile(filename, graph);
		TestCase.assertTrue("addEdge method works incorrect", isEqualFiles(filename));
		outFile.delete();
	}
	
	public void test11() {
		String filename = "test11";
		IGraph graph = new Graph();
		IVertex v0 = graph.addVertex(0, 0);
		IVertex v1 = graph.addVertex(1, 1);
		IVertex v2 = graph.addVertex(2, 2);
		graph.addEdge(v0, v1);
		graph.addEdge(v2, v1);
		graph.addEdge(v0, v2);
		File outFile = printToFile(filename, graph);
		TestCase.assertTrue("addEdge method works incorrect", isEqualFiles(filename));
		outFile.delete();
	}
	
	public void test12() {
		String filename = "test12";
		IGraph graph = new Graph();
		IVertex v0 = graph.addVertex(0, 0);
		IVertex v1 = graph.addVertex(1, 1);
		IVertex v2 = graph.addVertex(2, 2);
		graph.addEdge(v0, v1);
		graph.addEdge(v2, v1);
		graph.addEdge(v0, v2);
		IVertex v3 = graph.addVertex(3, 3);
		File outFile = printToFile(filename, graph);
		TestCase.assertTrue("addVertex method works incorrect after adding edges", isEqualFiles(filename));
		outFile.delete();
	}
	
	public void test13() {
		String filename = "test13";
		IGraph graph = new Graph();
		IVertex v0 = graph.addVertex(0, 0);
		IVertex v1 = graph.addVertex(1, 1);
		graph.addEdge(v0, v1);
		graph.removeVertex(v0);
		File outFile = printToFile(filename, graph);
		TestCase.assertTrue("removeVertex method works incorrect after adding edge to the vertex", isEqualFiles(filename));
		outFile.delete();
	}
	
	public void test14() {
		String filename = "test14";
		IGraph graph = new Graph();
		IVertex v0 = graph.addVertex(0, 0);
		IVertex v1 = graph.addVertex(1, 1);
		IEdge e0 = graph.addEdge(v0, v1);
		graph.removeEdge(e0);
		File outFile = printToFile(filename, graph);
		TestCase.assertTrue("removeEdge method works incorrect", isEqualFiles(filename));
		outFile.delete();
	}

	public void test15() {
		String filename = "test15";
		IGraph graph = new Graph();
		IVertex v0 = graph.addVertex(0, 0);
		IVertex v1 = graph.addVertex(1, 1);
		IVertex v2 = graph.addVertex(2, 2); 
		IEdge e0 = graph.addEdge(v0, v1);
		IEdge e1 = graph.addEdge(v2, v1);
		graph.removeEdge(e0);
		graph.addEdge(v0, v2);
		graph.addEdge(v1, v0);
		File outFile = printToFile(filename, graph);
		TestCase.assertTrue("removeEdge method works incorrect", isEqualFiles(filename));
		outFile.delete();
	}
	
	public void test16() {
		String filename = "test16";
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
		File outFile = printToFile(filename, graph);
		TestCase.assertTrue("removeVertex method works incorrect", isEqualFiles(filename));
		outFile.delete();
	}
	
	public void test17() {
		String filename = "test17";
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
		File outFile = printToFile(filename, graph);
		TestCase.assertTrue("removeVertex method works incorrect", isEqualFiles(filename));
		outFile.delete();
	}
	
	/*
	 * test18-test25 test canAddEdge method
	 */
	public void test18() {
		String filename = "test18";
		IGraph graph = new Graph();
		IVertex v0 = graph.addVertex(0, 0);
		TestCase.assertFalse("canAddEdge method works incorrect. It allows connecting vertex to itself", 
				graph.canAddEdge(v0, v0));	
	}

	public void test19() {
		String filename = "test19";
		IGraph graph = new Graph();
		IVertex v0 = graph.addVertex(0, 0);
		IVertex v1 = graph.addVertex(1, 1);
		TestCase.assertTrue("canAddEdge method works incorrect. It doesn't allow connecting two separate vertecies", 
				graph.canAddEdge(v0, v1));	
	}

	public void test20() {
		String filename = "test20";
		IGraph graph = new Graph();
		IVertex v0 = graph.addVertex(0, 0);
		IVertex v1 = graph.addVertex(1, 1);
		graph.addEdge(v0, v1);
		TestCase.assertFalse("canAddEdge method works incorrect. It allows connecting two connected vertecies", 
				graph.canAddEdge(v0, v1));
	}
	
	public void test21() {
		String filename = "test21";
		IGraph graph = new Graph();
		IVertex v0 = graph.addVertex(0, 0);
		IVertex v1 = graph.addVertex(1, 1);
		graph.addEdge(v0, v1);
		TestCase.assertFalse("canAddEdge method works incorrect. It allows connecting two connected vertecies", 
				graph.canAddEdge(v1, v0));
	}
	
	public void test22() {
		String filename = "test22";
		IGraph graph = new Graph();
		IVertex v0 = graph.addVertex(0, 0);
		IVertex v1 = graph.addVertex(1, 1);
		IEdge e0 = graph.addEdge(v0, v1);
		graph.removeEdge(e0);
		TestCase.assertTrue("canAddEdge method works incorrect. It doesn't allow connecting two disconnected vertecies", 
				graph.canAddEdge(v0, v1));
	}
	
	public void test23() {
		String filename = "test23";
		IGraph graph = new Graph();
		IVertex v0 = graph.addVertex(0, 0);
		IVertex v1 = graph.addVertex(1, 1);
		IVertex v2 = graph.addVertex(2, 2);
		IEdge e0 = graph.addEdge(v0, v1);
		IEdge e1 = graph.addEdge(v2, v0);
		IEdge e2 = graph.addEdge(v1, v2);
		graph.removeVertex(v1);
		TestCase.assertTrue("canAddEdge method works incorrect. It doesn't allow connecting two separate vertecies", 
				graph.canAddEdge(v0, v1));
	}

	public void test24() {
		String filename = "test24";
		IGraph graph = new Graph();
		IVertex v0 = graph.addVertex(0, 0);
		IVertex v1 = graph.addVertex(1, 1);
		IVertex v2 = graph.addVertex(2, 2);
		IEdge e0 = graph.addEdge(v0, v1);
		IEdge e1 = graph.addEdge(v2, v0);
		IEdge e2 = graph.addEdge(v1, v2);
		graph.removeVertex(v1);
		TestCase.assertFalse("canAddEdge method works incorrect. It allows connecting two connected vertecies", 
				graph.canAddEdge(v0, v2));
	}
	
	public void test25() {
		String filename = "test25";
		IGraph graph = new Graph();
		IVertex v0 = graph.addVertex(0, 0);
		IVertex v1 = graph.addVertex(1, 1);
		IVertex v2 = graph.addVertex(2, 2);
		IEdge e0 = graph.addEdge(v0, v1);
		IEdge e1 = graph.addEdge(v2, v0);
		IEdge e2 = graph.addEdge(v1, v2);
		graph.removeVertex(v1);
		TestCase.assertTrue("canAddEdge method works incorrect. It doesn't allow connecting two separate vertecies", 
				graph.canAddEdge(v1, v2));
	}
	
	public void test26() {
		String filename = "test26";
		Exception e = null;
		try {
			Graph graph = new Graph();
			IVertex v0 = graph.addVertex(0, 0, "0");
			IVertex v1 = graph.addVertex(1, 1, "0");
		} catch (Exception ex) {
			e = ex;
		}
		boolean isOK = (e instanceof BadInputException && 
				e.getMessage() == "Can't create graph with vertices with the same id");
		TestCase.assertTrue("addVertex method works wrong. It allows to add the vertex with id that already in the graph", isOK);
	}

	public void test27() {
		String filename = "test27";
		Exception e = null;
		File outFile = null;
		try {
			Graph graph = new Graph();
			IVertex v0 = graph.addVertex(0, 0, "0");
			IVertex v2 = graph.addVertex(0, 0, "-1");
			outFile = printToFile(filename, graph);
		} catch (Exception ex) {
			e = ex;
		}
		boolean isOK = (e == null && isEqualFiles(filename));
		TestCase.assertTrue("addVertex method works wrong. It doesn't allow to add the vertex with coordinates that already in the graph", isOK);
		outFile.delete();
	}
	
	public void test28() {
		String filename = "test28";
		Exception e = null;
		File outFile = null;
		try {
			Graph graph = new Graph();
			IVertex v0 = graph.addVertex(0, 0, "0");
			IVertex v1 = graph.addVertex(1, 1, "1");
			IVertex v2 = graph.addVertex(2, 2);
			outFile = printToFile(filename, graph);
		} catch (Exception ex) {
			e = ex;
		}
		boolean isOK = (e == null && isEqualFiles(filename)); 
		TestCase.assertTrue("getNameAddingVertex method works wrong", isOK);
		outFile.delete();
	}

	public void test29() {
		String filename = "test29";
		Exception e = null;
		File outFile = null;
		try {
			Graph graph = new Graph();
			IVertex v1 = graph.addVertex(1, 1, "1");
			IVertex v2 = graph.addVertex(2, 2);
			outFile = printToFile(filename, graph);
		} catch (Exception ex) {
			e = ex;
		}
		boolean isOK = (e == null && isEqualFiles(filename)); 
		TestCase.assertTrue("getNameAddingVertex method works wrong", isOK);
		outFile.delete();
	}
	
	public void test30() {
		String filename = "test30";
		Exception e = null;
		File outFile = null;
		try {
			Graph graph = new Graph();
			IVertex v1 = graph.addVertex(1, 1, "0");
			IVertex v2 = graph.addVertex(2, 2);
			outFile = printToFile(filename, graph);
		} catch (Exception ex) {
			e = ex;
		}
		boolean isOK = (e == null && isEqualFiles(filename)); 
		TestCase.assertTrue("getNameAddingVertex method works wrong", isOK);
		outFile.delete();
	}
	
	public void test31() {
		String filename = "test31";
		Exception e = null;
		File outFile = null;
		try {
			Graph graph = new Graph();
			IVertex v0 = graph.addVertex(0, 0);
			IVertex v1 = graph.addVertex(1, 1);
			IVertex v2 = graph.addVertex(2, 2);
			graph.removeVertex(v0);
			IVertex v3 = graph.addVertex(3, 3);
			outFile = printToFile(filename, graph);
		} catch (Exception ex) {
			e = ex;
		}
		boolean isOK = (e == null && isEqualFiles(filename)); 
		TestCase.assertTrue("getNameAddingVertex method works wrong", isOK);
		outFile.delete();
	}
	
	public void test32() {
		String filename = "test32";
		Exception e = null;
		File outFile = null;
		try {
			Graph graph = new Graph();
			IVertex v0 = graph.addVertex(0, 0, "a");
			IVertex v1 = graph.addVertex(1, 1, "b");
			IVertex v2 = graph.addVertex(2, 2, "1");
			IVertex v3 = graph.addVertex(3, 3);
			outFile = printToFile(filename, graph);
		} catch (Exception ex) {
			e = ex;
		}
		boolean isOK = (e == null && isEqualFiles(filename)); 
		TestCase.assertTrue("getNameAddingVertex method works wrong", isOK);
		outFile.delete();
	}	
	
	public void test33() {
		String filename = "test33";
		Exception e = null;
		File outFile = null;
		try {
			Graph graph = new Graph();
			IVertex v0 = graph.addVertex(0, 0, "a");
			IVertex v1 = graph.addVertex(1, 1, "b");
			IVertex v2 = graph.addVertex(2, 2, "1");
			IVertex v3 = graph.addVertex(3, 3);
			outFile = printToFile(filename, graph);
		} catch (Exception ex) {
			e = ex;
		}
		boolean isOK = (e == null && isEqualFiles(filename)); 
		TestCase.assertTrue("getNameAddingVertex method works wrong", isOK);
		outFile.delete();
	}	
	
	public void test34() {
		String filename = "test34";
		Graph graph = new Graph();
		graph.renameVertices();
		File outFile = printToFile(filename, graph);
		boolean isOK = (isEqualFiles(filename)); 
		TestCase.assertTrue("recountVertices method works wrong for empty graph", isOK);
		outFile.delete();
	}
	
	public void test35() {
		String filename = "test35";
		Graph graph = new Graph();
		IVertex v0 = graph.addVertex(0, 0);
		IVertex v1 = graph.addVertex(1, 1);
		IVertex v2 = graph.addVertex(2, 2);
		graph.renameVertices();
		File outFile = printToFile(filename, graph);
		boolean isOK = (isEqualFiles(filename)); 
		TestCase.assertTrue("recountVertices method works wrong", isOK);
		outFile.delete();
	}
	
	public void test36() {
		String filename = "test36";
		Graph graph = new Graph();
		IVertex v0 = graph.addVertex(0, 0, "a");
		IVertex v1 = graph.addVertex(1, 1, "b");
		IVertex v2 = graph.addVertex(2, 2, "c");
		graph.renameVertices();
		File outFile = printToFile(filename, graph);
		boolean isOK = (isEqualFiles(filename)); 
		TestCase.assertTrue("recountVertices method works wrong when all identifiers are nonnumbers", isOK);
		outFile.delete();
	}
	
	public void test37() {
		String filename = "test37";
		Graph graph = new Graph();
		IVertex v0 = graph.addVertex(0, 0, "a");
		IVertex v1 = graph.addVertex(1, 1, "0");
		IVertex v2 = graph.addVertex(2, 2, "c");
		IVertex v3 = graph.addVertex(3, 3, "1");
		graph.renameVertices();
		File outFile = printToFile(filename, graph);
		boolean isOK = (isEqualFiles(filename)); 
		TestCase.assertTrue("recountVertices method works wrong when several vertices have good identifiers", isOK);
		outFile.delete();
	}
	
	public void test38() {
		String filename = "test38";
		Graph graph = new Graph();
		IVertex v0 = graph.addVertex(0, 0, "a");
		IVertex v1 = graph.addVertex(1, 1, "0");
		IVertex v2 = graph.addVertex(2, 2, "c");
		IVertex v3 = graph.addVertex(3, 3, "1");
		graph.addEdge(v0, v1);
		graph.addEdge(v3, v1);
		graph.addEdge(v0, v2);
		graph.renameVertices();
		File outFile = printToFile(filename, graph);
		boolean isOK = (isEqualFiles(filename)); 
		TestCase.assertTrue("recountVertices method works wrong in graph with edges", isOK);
		outFile.delete();
	}
	
	public void test39() {
		String filename = "test39";
		Graph graph = new Graph();
		IVertex v0 = graph.addVertex(0, 0, "a");
		IVertex v1 = graph.addVertex(1, 1, "10");
		IVertex v2 = graph.addVertex(2, 2, "c");
		IVertex v3 = graph.addVertex(3, 3, "3");
		graph.renameVertices();
		File outFile = printToFile(filename, graph);
		boolean isOK = (isEqualFiles(filename)); 
		TestCase.assertTrue("recountVertices method works wrong when several vertices have good identifiers", isOK);
		outFile.delete();
	}
	
	public void test40() {
		String filename = "test40";
		Graph graph = new Graph();
		IVertex v0 = graph.addVertex(0, 0);
		List<IVertex> a = graph.adjacents(v0);
		File outFile = printToFile(filename, a);
		boolean isOK = (isEqualFiles(filename)); 
		TestCase.assertTrue("adjacents method works wrong for graph with one vertex", isOK);
		outFile.delete();
	}

	public void test41() {
		String filename = "test41";
		Graph graph = new Graph();
		IVertex v0 = graph.addVertex(0, 0);
		IVertex v1 = graph.addVertex(1, 1);
		List<IVertex> a = graph.adjacents(v0);
		File outFile = printToFile(filename, a);
		boolean isOK = (isEqualFiles(filename)); 
		TestCase.assertTrue("adjacents method works wrong for graph without vertices", isOK);
		outFile.delete();
	}
	
	public void test42() {
		String filename = "test42";
		Graph graph = new Graph();
		IVertex v0 = graph.addVertex(0, 0);
		IVertex v1 = graph.addVertex(1, 1);
		IEdge e0 = graph.addEdge(v0, v1);
		List<IVertex> a = graph.adjacents(v0);
		File outFile = printToFile(filename, a);
		boolean isOK = (isEqualFiles(filename)); 
		TestCase.assertTrue("adjacents method works wrong for graph with one edge", isOK);
		outFile.delete();
	}
		
	public void test44() {
		String filename = "test44";
		Graph graph = new Graph();
		IVertex v0 = graph.addVertex(0, 0);
		IVertex v1 = graph.addVertex(1, 1);
		graph.addEdge(v0, v1);
		graph.removeVertex(v0);
		graph.addVertex(v0);
		List<IVertex> a = graph.adjacents(v0);
		File outFile = printToFile(filename, a);
		boolean isOK = (isEqualFiles(filename)); 
		TestCase.assertTrue("addVertex method works wrong", isOK);
		outFile.delete();
	}
	
	public void test45() {
		String filename = "test45";
		Graph graph = new Graph();
		IVertex v0 = graph.addVertex(0, 0);
		IVertex v1 = graph.addVertex(1, 1);
		IVertex v2 = graph.addVertex(2, 2);
		IVertex v3 = graph.addVertex(3, 3);
		IEdge e1 = graph.addEdge(v3, v1);
		IEdge e0 = graph.addEdge(v0, v1);
		List<IVertex> a = graph.adjacents(v1);
		File outFile = printToFile(filename, a);
		boolean isOK = (isEqualFiles(filename)); 
		TestCase.assertTrue("adjacents method works wrong", isOK);
		outFile.delete();
	}
	
	public void test46() {
		String filename = "test46";
		Graph graph = new Graph();
		IVertex v0 = graph.addVertex(0, 0);
		IVertex v1 = graph.addVertex(1, 1);
		IVertex v2 = graph.addVertex(2, 2);
		IVertex v3 = graph.addVertex(3, 3);
		IEdge e1 = graph.addEdge(v3, v1);
		IEdge e0 = graph.addEdge(v0, v1);
		IEdge e2 = graph.addEdge(v3, v2);
		List<IVertex> a = graph.adjacents(v1);
		File outFile = printToFile(filename, a);
		boolean isOK = (isEqualFiles(filename)); 
		TestCase.assertTrue("adjacents method works wrong", isOK);
		outFile.delete();
	}
	
	public void test47() {
		String filename = "test47";
		Graph graph = new Graph();
		IVertex v0 = graph.addVertex(0, 0);
		List<IGraph> c = graph.components();
		File outFile = printToFile(filename, c);
		boolean isOK = (isEqualFiles(filename)); 
		TestCase.assertTrue("components method works wrong graph with one vertex", isOK);
		outFile.delete();
	}

	public void test48() {
		String filename = "test48";
		Graph graph = new Graph();
		IVertex v0 = graph.addVertex(0, 0);
		IVertex v1 = graph.addVertex(1, 1);
		List<IGraph> c = graph.components();
		File outFile = printToFile(filename, c);
		boolean isOK = (isEqualFiles(filename)); 
		TestCase.assertTrue("components method works wrong for graph without edges", isOK);
		outFile.delete();
	}

	public void test49() {
		String filename = "test49";
		Graph graph = new Graph();
		IVertex v0 = graph.addVertex(0, 0);
		IVertex v1 = graph.addVertex(1, 1);
		IEdge e0 = graph.addEdge(v0, v1);
		List<IGraph> c = graph.components();
		File outFile = printToFile(filename, c);
		boolean isOK = (isEqualFiles(filename)); 
		TestCase.assertTrue("components method works wrong for graph with one edge", isOK);
		outFile.delete();
	}

	public void test50() {
		String filename = "test50";
		Graph graph = new Graph();
		IVertex v0 = graph.addVertex(0, 0);
		IVertex v1 = graph.addVertex(1, 1);
		IVertex v2 = graph.addVertex(2, 2);
		IVertex v3 = graph.addVertex(3, 3);
		IEdge e1 = graph.addEdge(v3, v1);
		IEdge e0 = graph.addEdge(v0, v1);
		List<IGraph> c = graph.components();
		File outFile = printToFile(filename, c);
		boolean isOK = (isEqualFiles(filename)); 
		TestCase.assertTrue("components method works wrong", isOK);
		outFile.delete();
	}

	public void test51() {
		String filename = "test51";
		Graph graph = new Graph();
		IVertex v0 = graph.addVertex(0, 0);
		IVertex v1 = graph.addVertex(1, 1);
		IVertex v2 = graph.addVertex(2, 2);
		IVertex v3 = graph.addVertex(3, 3);
		IEdge e1 = graph.addEdge(v3, v1);
		IEdge e0 = graph.addEdge(v0, v1);
		IEdge e2 = graph.addEdge(v3, v2);
		List<IGraph> c = graph.components();
		File outFile = printToFile(filename, c);
		boolean isOK = (isEqualFiles(filename)); 
		TestCase.assertTrue("components method works wrong", isOK);
		outFile.delete();
	}

	public void test52() {
		String filename = "test52";
		Graph graph = new Graph();
		IVertex v0 = graph.addVertex(0, 0);
		IVertex v1 = graph.addVertex(1, 1);
		IVertex v2 = graph.addVertex(2, 2);
		IVertex v3 = graph.addVertex(3, 3);
		IVertex v4 = graph.addVertex(4, 4);
		IVertex v5 = graph.addVertex(5, 5);
		IEdge e1 = graph.addEdge(v3, v1);
		IEdge e0 = graph.addEdge(v0, v1);
		IEdge e2 = graph.addEdge(v3, v2);
		IEdge e3 = graph.addEdge(v5, v4);
		List<IGraph> c = graph.components();
		File outFile = printToFile(filename, c);
		boolean isOK = (isEqualFiles(filename)); 
		TestCase.assertTrue("components method works wrong", isOK);
		outFile.delete();
	}

}
