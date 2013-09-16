package org.amse.fedotov.graph_editor.tests;

import java.io.File;

import junit.framework.TestCase;

import org.amse.fedotov.graph_editor.exception.BadInputException;
import org.amse.fedotov.graph_editor.layouters.impl.LayouterFactory;
import org.amse.fedotov.graph_editor.model.IGraph;
import org.amse.fedotov.graph_editor.model.IVertex;
import org.amse.fedotov.graph_editor.model.impl.Graph;
import org.amse.fedotov.graph_editor.settings.Settings;

@SuppressWarnings("unused")
public class LayouterTest extends AbstractTest {

	@Override
	protected String getPath() {
		return super.getPath() + "layouter" + File.separatorChar;
	}
		
	/*
	 * test0 - test7 test EllipseLayouter
	 */
	public void test0() {
		String filename = "test0";
		int width = 100;
		int height = 200;
		IGraph graph = new Graph();
		IVertex vertex0 = graph.addVertex(0, 0);
		new LayouterFactory().getEllipseLayouter().calculateCoordinates(graph, width, height);
		File outFile = printToFile(filename, graph);
		TestCase.assertTrue("EllipseLayouter lays out graph out of rectangle", isInRectangle(graph, width, height));
		TestCase.assertTrue("EllipseLayouter lays out graph with one vertex wrong", isEqualFiles(filename));
		outFile.delete();
	}
	
	public void test1() {
		String filename = "test1";
		int width = 100;
		int height = 200;
		IGraph graph = new Graph();
		IVertex vertex0 = graph.addVertex(0, 0);
		IVertex vertex1 = graph.addVertex(1, 1);
		new LayouterFactory().getEllipseLayouter().calculateCoordinates(graph, width, height);
		File outFile = printToFile(filename, graph);
		TestCase.assertTrue("EllipseLayouter lays out graph out of rectangle", isInRectangle(graph, width, height));
		TestCase.assertTrue("EllipseLayouter lays out graph with two vertices wrong", isEqualFiles(filename));
		outFile.delete();
	}
	
	public void test2() {
		String filename = "test2";
		int width = 30;
		int height = 15;
		IGraph graph = new Graph();
		IVertex vertex0 = graph.addVertex(0, 0);
		IVertex vertex1 = graph.addVertex(1, 1);
		boolean isOK = false;
		try {
			new LayouterFactory().getEllipseLayouter().calculateCoordinates(graph, width, height);
		} catch (BadInputException ex) {
			isOK = (ex.getMessage().equals("Layouting area is too small"));
		}
		TestCase.assertTrue("EllipseLayouter doesn't throw exception when layouting area is too small", isOK);
	}
	
	public void test3() {
		String filename = "test3";
		int width = 125;
		int height = 125;
		IGraph graph = new Graph();
		IVertex vertex0 = graph.addVertex(0, 0);
		IVertex vertex1 = graph.addVertex(1, 1);
		graph.addEdge(vertex0, vertex1);
		new LayouterFactory().getEllipseLayouter().calculateCoordinates(graph, width, height);
		File outFile = printToFile(filename, graph);
		TestCase.assertTrue("EllipseLayouter lays out graph out of rectangle", isInRectangle(graph, width, height));
		TestCase.assertTrue("EllipseLayouter lays out graph with two connected vertices wrong", isEqualFiles(filename));
		outFile.delete();
	}
	
	public void test4() {
		String filename = "test4";
		int width = 100;
		int height = 200;
		IGraph graph = new Graph();
		IVertex vertex0 = graph.addVertex(0, 0);
		IVertex vertex1 = graph.addVertex(0, 0);
		IVertex vertex2 = graph.addVertex(0, 0);
		new LayouterFactory().getEllipseLayouter().calculateCoordinates(graph, width, height);
		File outFile = printToFile(filename, graph);
		TestCase.assertTrue("EllipseLayouter lays out graph out of rectangle", isInRectangle(graph, width, height));
		TestCase.assertTrue("EllipseLayouter lays out graph with three vertices wrong", isEqualFiles(filename));
		outFile.delete();
	}
	
	public void test5() {
		String filename = "test5";
		int width = 100;
		int height = 200;
		IGraph graph = new Graph();
		IVertex vertex0 = graph.addVertex(0, 0);
		IVertex vertex1 = graph.addVertex(0, 0);
		IVertex vertex2 = graph.addVertex(0, 0);
		IVertex vertex3 = graph.addVertex(0, 0);
		new LayouterFactory().getEllipseLayouter().calculateCoordinates(graph, width, height);
		File outFile = printToFile(filename, graph);
		TestCase.assertTrue("EllipseLayouter lays out graph out of rectangle", isInRectangle(graph, width, height));
		TestCase.assertTrue("EllipseLayouter lays out graph wrong", isEqualFiles(filename));
		outFile.delete();
	}
	
	public void test6() {
		String filename = "test6";
		int width = 100;
		int height = 200;
		IGraph graph = new Graph();
		IVertex vertex0 = graph.addVertex(0, 0);
		IVertex vertex1 = graph.addVertex(0, 0);
		IVertex vertex2 = graph.addVertex(0, 0);
		IVertex vertex3 = graph.addVertex(0, 0);
		IVertex vertex4 = graph.addVertex(0, 0);
		new LayouterFactory().getEllipseLayouter().calculateCoordinates(graph, width, height);
		File outFile = printToFile(filename, graph);
		TestCase.assertTrue("EllipseLayouter lays out graph out of rectangle", isInRectangle(graph, width, height));
		TestCase.assertTrue("EllipseLayouter lays out graph wrong", isEqualFiles(filename));
		outFile.delete();
	}

	public void test7() {
		String filename = "test7";
		int width = 100;
		int height = 200;
		IGraph graph = new Graph();
		IVertex vertex0 = graph.addVertex(0, 0);
		IVertex vertex1 = graph.addVertex(0, 0);
		IVertex vertex2 = graph.addVertex(0, 0);
		IVertex vertex3 = graph.addVertex(0, 0);
		IVertex vertex4 = graph.addVertex(0, 0);
		IVertex vertex5 = graph.addVertex(0, 0);
		graph.addEdge(vertex2, vertex4);
		graph.addEdge(vertex3, vertex0);
		new LayouterFactory().getEllipseLayouter().calculateCoordinates(graph, width, height);
		File outFile = printToFile(filename, graph);
		TestCase.assertTrue("EllipseLayouter lays out graph out of rectangle", isInRectangle(graph, width, height));
		TestCase.assertTrue("EllipseLayouter lays out graph wrong", isEqualFiles(filename));
		outFile.delete();
	}

	/*
	 * test8 - test15 test RandomLayouter
	 */
	public void test8() {
		String filename = "test8";
		int width = 100;
		int height = 200;
		IGraph graph = new Graph();
		IVertex vertex0 = graph.addVertex(0, 0);
		new LayouterFactory().getRandomLayouter().calculateCoordinates(graph, width, height);
		TestCase.assertTrue("RandomLayouter lays out graph out of rectangle", isInRectangle(graph, width, height));
	}
	
	public void test9() {
		String filename = "test9";
		int width = 100;
		int height = 200;
		IGraph graph = new Graph();
		IVertex vertex0 = graph.addVertex(0, 0);
		IVertex vertex1 = graph.addVertex(1, 1);
		new LayouterFactory().getRandomLayouter().calculateCoordinates(graph, width, height);
		TestCase.assertTrue("RandomLayouter lays out graph out of rectangle", isInRectangle(graph, width, height));
	}
	
	public void test10() {
		String filename = "test10";
		int width = 30;
		int height = 15;
		IGraph graph = new Graph();
		IVertex vertex0 = graph.addVertex(0, 0);
		IVertex vertex1 = graph.addVertex(1, 1);
		boolean isOK = false;
		try {
			new LayouterFactory().getRandomLayouter().calculateCoordinates(graph, width, height);
		} catch (Exception ex) {
			isOK = (ex instanceof BadInputException) && 
					(ex.getMessage().equals("Layouting area is too small"));
		}
		TestCase.assertTrue("RandomLayouter doesn't throw exception when layouting area is too small", isOK);
	}
	
	public void test11() {
		String filename = "test11";
		int width = 125;
		int height = 125;
		IGraph graph = new Graph();
		IVertex vertex0 = graph.addVertex(0, 0);
		IVertex vertex1 = graph.addVertex(1, 1);
		graph.addEdge(vertex0, vertex1);
		new LayouterFactory().getRandomLayouter().calculateCoordinates(graph, width, height);
		TestCase.assertTrue("RandomLayouter lays out graph out of rectangle", isInRectangle(graph, width, height));
	}
	
	public void test12() {
		String filename = "test12";
		int width = 100;
		int height = 200;
		IGraph graph = new Graph();
		IVertex vertex0 = graph.addVertex(0, 0);
		IVertex vertex1 = graph.addVertex(0, 0);
		IVertex vertex2 = graph.addVertex(0, 0);
		new LayouterFactory().getRandomLayouter().calculateCoordinates(graph, width, height);
		TestCase.assertTrue("RandomLayouter lays out graph out of rectangle", isInRectangle(graph, width, height));
	}
	
	public void test13() {
		String filename = "test13";
		int width = 100;
		int height = 200;
		IGraph graph = new Graph();
		IVertex vertex0 = graph.addVertex(0, 0);
		IVertex vertex1 = graph.addVertex(0, 0);
		IVertex vertex2 = graph.addVertex(0, 0);
		IVertex vertex3 = graph.addVertex(0, 0);
		new LayouterFactory().getRandomLayouter().calculateCoordinates(graph, width, height);
		TestCase.assertTrue("RandomLayouter lays out graph out of rectangle", isInRectangle(graph, width, height));
	}
	
	public void test14() {
		String filename = "test14";
		int width = 100;
		int height = 200;
		IGraph graph = new Graph();
		IVertex vertex0 = graph.addVertex(0, 0);
		IVertex vertex1 = graph.addVertex(0, 0);
		IVertex vertex2 = graph.addVertex(0, 0);
		IVertex vertex3 = graph.addVertex(0, 0);
		IVertex vertex4 = graph.addVertex(0, 0);
		new LayouterFactory().getRandomLayouter().calculateCoordinates(graph, width, height);
		TestCase.assertTrue("RandomLayouter lays out graph out of rectangle", isInRectangle(graph, width, height));
	}

	public void test15() {
		String filename = "test15";
		int width = 100;
		int height = 200;
		IGraph graph = new Graph();
		IVertex vertex0 = graph.addVertex(0, 0);
		IVertex vertex1 = graph.addVertex(0, 0);
		IVertex vertex2 = graph.addVertex(0, 0);
		IVertex vertex3 = graph.addVertex(0, 0);
		IVertex vertex4 = graph.addVertex(0, 0);
		IVertex vertex5 = graph.addVertex(0, 0);
		graph.addEdge(vertex2, vertex4);
		graph.addEdge(vertex3, vertex0);
		new LayouterFactory().getRandomLayouter().calculateCoordinates(graph, width, height);
		TestCase.assertTrue("RandomLayouter lays out graph out of rectangle", isInRectangle(graph, width, height));
	}
	
	/*
	 * test16 - test28 test ComponentLayouter
	 */
	public void test16() {
		String filename = "test16";
		int width = 100;
		int height = 200;
		IGraph graph = new Graph();
		IVertex vertex0 = graph.addVertex(0, 0);
		new LayouterFactory().getComponentLayouter().calculateCoordinates(graph, width, height);
		File outFile = printToFile(filename, graph);
		TestCase.assertTrue("ComponentLayouter lays out graph out of rectangle", isInRectangle(graph, width, height));
		TestCase.assertTrue("ComponentLayouter lays out graph with one vertex wrong", isEqualFiles(filename));
		outFile.delete();
	}
	
	public void test17() {
		String filename = "test17";
		int width = 100;
		int height = 200;
		IGraph graph = new Graph();
		IVertex vertex0 = graph.addVertex(0, 0);
		IVertex vertex1 = graph.addVertex(1, 1);
		new LayouterFactory().getComponentLayouter().calculateCoordinates(graph, width, height);
		File outFile = printToFile(filename, graph);
		TestCase.assertTrue("ComponentLayouter lays out graph out of rectangle", isInRectangle(graph, width, height));
		TestCase.assertTrue("ComponentLayouter lays out graph with two separate vertices wrong", isEqualFiles(filename));
		outFile.delete();
	}
	
	public void test18() {
		String filename = "test18";
		int width = 30;
		int height = 15;
		IGraph graph = new Graph();
		IVertex vertex0 = graph.addVertex(0, 0);
		IVertex vertex1 = graph.addVertex(1, 1);
		new LayouterFactory().getComponentLayouter().calculateCoordinates(graph, width, height);
		File outFile = printToFile(filename, graph);
		TestCase.assertTrue("ComponentLayouter lays out graph out of rectangle", isInRectangle(graph, width, height));
		TestCase.assertTrue("ComponentLayouter lays out graph with two vertices on small area wrong", isEqualFiles(filename));
		outFile.delete();
	}
	
	public void test19() {
		String filename = "test19";
		int width = 125;
		int height = 125;
		IGraph graph = new Graph();
		IVertex vertex0 = graph.addVertex(0, 0);
		IVertex vertex1 = graph.addVertex(1, 1);
		graph.addEdge(vertex0, vertex1);
		new LayouterFactory().getComponentLayouter().calculateCoordinates(graph, width, height);
		File outFile = printToFile(filename, graph);
		TestCase.assertTrue("ComponentLayouter lays out graph out of rectangle", isInRectangle(graph, width, height));
		TestCase.assertTrue("ComponentLayouter lays out graph with two connected vertices wrong", isEqualFiles(filename));
		outFile.delete();
	}
	
	public void test20() {
		String filename = "test20";
		int width = 100;
		int height = 200;
		IGraph graph = new Graph();
		IVertex vertex0 = graph.addVertex(0, 0);
		IVertex vertex1 = graph.addVertex(0, 0);
		IVertex vertex2 = graph.addVertex(0, 0);
		new LayouterFactory().getComponentLayouter().calculateCoordinates(graph, width, height);
		File outFile = printToFile(filename, graph);
		TestCase.assertTrue("ComponentLayouter lays out graph out of rectangle", isInRectangle(graph, width, height));
		TestCase.assertTrue("ComponentLayouter lays out graph with three separate vertices wrong", isEqualFiles(filename));
		outFile.delete();
	}
	
	public void test21() {
		String filename = "test21";
		int width = 100;
		int height = 200;
		IGraph graph = new Graph();
		IVertex vertex0 = graph.addVertex(0, 0);
		IVertex vertex1 = graph.addVertex(0, 0);
		IVertex vertex2 = graph.addVertex(0, 0);
		IVertex vertex3 = graph.addVertex(0, 0);
		new LayouterFactory().getComponentLayouter().calculateCoordinates(graph, width, height);
		File outFile = printToFile(filename, graph);
		TestCase.assertTrue("ComponentLayouter lays out graph out of rectangle", isInRectangle(graph, width, height));
		TestCase.assertTrue("ComponentLayouter lays out graph with separate vertices wrong", isEqualFiles(filename));
		outFile.delete();
	}
	
	public void test22() {
		String filename = "test22";
		int width = 100;
		int height = 200;
		IGraph graph = new Graph();
		IVertex vertex0 = graph.addVertex(0, 0);
		IVertex vertex1 = graph.addVertex(0, 0);
		IVertex vertex2 = graph.addVertex(0, 0);
		IVertex vertex3 = graph.addVertex(0, 0);
		IVertex vertex4 = graph.addVertex(0, 0);
		new LayouterFactory().getComponentLayouter().calculateCoordinates(graph, width, height);
		File outFile = printToFile(filename, graph);
		TestCase.assertTrue("ComponentLayouter lays out graph out of rectangle", isInRectangle(graph, width, height));
		TestCase.assertTrue("ComponentLayouter lays out graph with separate vertices wrong", isEqualFiles(filename));
		outFile.delete();
	}

	public void test23() {
		String filename = "test23";
		int width = 100;
		int height = 200;
		IGraph graph = new Graph();
		IVertex vertex0 = graph.addVertex(0, 0);
		IVertex vertex1 = graph.addVertex(0, 0);
		IVertex vertex2 = graph.addVertex(0, 0);
		IVertex vertex3 = graph.addVertex(0, 0);
		IVertex vertex4 = graph.addVertex(0, 0);
		IVertex vertex5 = graph.addVertex(0, 0);
		new LayouterFactory().getComponentLayouter().calculateCoordinates(graph, width, height);
		File outFile = printToFile(filename, graph);
		TestCase.assertTrue("ComponentLayouter lays out graph out of rectangle", isInRectangle(graph, width, height));
		TestCase.assertTrue("ComponentLayouter lays out graph with separate vertices wrong", isEqualFiles(filename));
		outFile.delete();
	}
	
	public void test24() {
		String filename = "test24";
		int width = 200;
		int height = 100;
		IGraph graph = new Graph();
		IVertex vertex0 = graph.addVertex(0, 0);
		IVertex vertex1 = graph.addVertex(0, 0);
		IVertex vertex2 = graph.addVertex(0, 0);
		graph.addEdge(vertex0, vertex2);
		new LayouterFactory().getComponentLayouter().calculateCoordinates(graph, width, height);
		File outFile = printToFile(filename, graph);
		TestCase.assertTrue("ComponentLayouter lays out graph out of rectangle", isInRectangle(graph, width, height));
		TestCase.assertTrue("ComponentLayouter lays out graph with nontrivial components wrong", isEqualFiles(filename));
		outFile.delete();
	}

	public void test25() {
		String filename = "test25";
		int width = 200;
		int height = 100;
		IGraph graph = new Graph();
		IVertex vertex0 = graph.addVertex(0, 0);
		IVertex vertex1 = graph.addVertex(0, 0);
		IVertex vertex2 = graph.addVertex(0, 0);
		graph.addEdge(vertex0, vertex2);
		graph.addEdge(vertex1, vertex2);
		new LayouterFactory().getComponentLayouter().calculateCoordinates(graph, width, height);
		File outFile = printToFile(filename, graph);
		TestCase.assertTrue("ComponentLayouter lays out graph out of rectangle", isInRectangle(graph, width, height));
		TestCase.assertTrue("ComponentLayouter lays out graph with nontrivial components wrong", isEqualFiles(filename));
		outFile.delete();
	}

	public void test26() {
		String filename = "test26";
		int width = 200;
		int height = 100;
		IGraph graph = new Graph();
		IVertex vertex0 = graph.addVertex(0, 0);
		IVertex vertex1 = graph.addVertex(0, 0);
		IVertex vertex2 = graph.addVertex(0, 0);
		IVertex vertex3 = graph.addVertex(0, 0);
		IVertex vertex4 = graph.addVertex(0, 0);
		IVertex vertex5 = graph.addVertex(0, 0);
		graph.addEdge(vertex0, vertex1);
		graph.addEdge(vertex5, vertex2);
		new LayouterFactory().getComponentLayouter().calculateCoordinates(graph, width, height);
		File outFile = printToFile(filename, graph);
		TestCase.assertTrue("ComponentLayouter lays out graph out of rectangle", isInRectangle(graph, width, height));
		TestCase.assertTrue("ComponentLayouter lays out graph with nontrivial components wrong", isEqualFiles(filename));
		outFile.delete();
	}
	
	public void test27() {
		String filename = "test27";
		int width = 200;
		int height = 100;
		IGraph graph = new Graph();
		IVertex vertex0 = graph.addVertex(0, 0);
		IVertex vertex1 = graph.addVertex(0, 0);
		IVertex vertex2 = graph.addVertex(0, 0);
		IVertex vertex3 = graph.addVertex(0, 0);
		IVertex vertex4 = graph.addVertex(0, 0);
		IVertex vertex5 = graph.addVertex(0, 0);
		graph.addEdge(vertex0, vertex1);
		graph.addEdge(vertex5, vertex2);
		graph.addEdge(vertex0, vertex2);
		new LayouterFactory().getComponentLayouter().calculateCoordinates(graph, width, height);
		File outFile = printToFile(filename, graph);
		TestCase.assertTrue("ComponentLayouter lays out graph out of rectangle", isInRectangle(graph, width, height));
		TestCase.assertTrue("ComponentLayouter lays out graph with nontrivial components wrong", isEqualFiles(filename));
		outFile.delete();
	}
	
	public void test28() {
		String filename = "test28";
		int width = 20;
		int height = 10;
		IGraph graph = new Graph();
		IVertex vertex0 = graph.addVertex(0, 0);
		IVertex vertex1 = graph.addVertex(0, 0);
		IVertex vertex2 = graph.addVertex(0, 0);
		IVertex vertex3 = graph.addVertex(0, 0);
		IVertex vertex4 = graph.addVertex(0, 0);
		IVertex vertex5 = graph.addVertex(0, 0);
		graph.addEdge(vertex0, vertex1);
		graph.addEdge(vertex5, vertex2);
		boolean isOK = false;
		try {
			new LayouterFactory().getComponentLayouter().calculateCoordinates(graph, width, height);
		} catch (BadInputException ex) {
			isOK = (ex.getMessage().equals("Layouting area is too small"));
		}
		TestCase.assertTrue("ComponentLayouter doesn't throw exception when layouting area is too small", isOK);
	}


	
	private boolean isInRectangle(IGraph graph, int width, int height) {
		for (IVertex vertex : graph.vertices()) {
			if (vertex.getX() < 0 ||  vertex.getX() >= width) {
				return false;
			}
			if (vertex.getY() < 0 || vertex.getY() >= height) {
				return false;
			}
		}
		return true;
	}



}
