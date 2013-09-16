package org.amse.fedotov.graph_editor.layouters.impl;

import java.awt.geom.Point2D;
import java.util.Random;

import org.amse.fedotov.graph_editor.exception.BadInputException;
import org.amse.fedotov.graph_editor.layouters.ILayouter;
import org.amse.fedotov.graph_editor.model.IGraph;
import org.amse.fedotov.graph_editor.model.IVertex;

class AestheticLayouter implements ILayouter {
	
	private final double DELTA = 0.0001;
	private final int ITERATIONS = 400;
	private final double EPS = 0.0005;
	
	private int myLength;

	public void calculateCoordinates(IGraph graph, int width, int height) {
		width -= 2 * BORDER;
		height -= 2 * BORDER;
		if (width <= 0 ||  height <= 0) {
			throw new BadInputException("Layouting area is too small");
		}
		int v = graph.vertices().size();
		myLength = (int) (0.75 * Math.sqrt(width * height / v));
		if (graph.vertices().get(0).getX() == 0 && graph.vertices().get(0).getY() == 0) {
			new EllipseLayouter().calculateCoordinates(graph, width, height);
		}
		Point2D.Double[] forces = new Point2D.Double[v];
		for (int iteration = 0; iteration < ITERATIONS; iteration++) {
			if (iteration % 10 == 0) {
				normalization(graph, width, height);
				Random random = new Random(239111);
				for (int i = 0; i < v; i++) {
					IVertex vertex = graph.vertices().get(i);
					int shift = 20;
					vertex.moveTo(vertex.getX() + random.nextInt(shift) - shift / 2, 
							vertex.getY() + random.nextInt(shift) - shift / 2);
				}
			}
			for (int i = 0; i < v; i++) {
				IVertex vertex = graph.vertices().get(i);
				forces[i] = new Point2D.Double(0, 0);
				for (int j = 0; j < v; j++) {
					if (i != j) {
						IVertex vertex1 = graph.vertices().get(j);
						Point2D.Double curForce = coulompRepulsion(vertex1, vertex);
						forces[i].setLocation(curForce.getX() + forces[i].getX(), curForce.getY() + forces[i].getY());
					}
				}
				for (IVertex vertex1 : graph.adjacents(vertex)) {
					Point2D.Double curForce = hookeAttraction(vertex1, vertex);
					forces[i].setLocation(curForce.getX() + forces[i].getX(), curForce.getY() + forces[i].getY());
				}
			}
			for (int i = 0; i < v; i++) {
				IVertex vertex = graph.vertices().get(i);
				vertex.moveTo(vertex.getX() + (int) (forces[i].getX() * DELTA), 
						vertex.getY() + (int) (forces[i].getY() * DELTA));
			}
		}
		
		normalization(graph, width, height);
		for (int i = 0; i < v; i++) {
			IVertex vertex = graph.vertices().get(i);
			vertex.setX(BORDER + vertex.getX());
			vertex.setY(BORDER + vertex.getY());
		}
	}
	
	private final Point2D.Double O = new Point2D.Double();
	
	private Point2D.Double coulompRepulsion(IVertex u, IVertex v) {
		Point2D.Double uv = new Point2D.Double(v.getX() - u.getX(), v.getY() - u.getY());
		double k = myLength * myLength / (EPS + uv.distance(O));
		return new Point2D.Double(k * uv.getX(), k * uv.getY());
	}
	
	private Point2D.Double hookeAttraction(IVertex u, IVertex v) {
		Point2D.Double uv = new Point2D.Double(v.getX() - u.getX(), v.getY() - u.getY());
		double k = - uv.distanceSq(O) / (EPS + myLength);
		return new Point2D.Double(k * uv.getX(), k * uv.getY());
	}
	
	private void normalization(IGraph graph, int width, int height) {
		int v = graph.vertices().size();
		int minX = Integer.MAX_VALUE;
		int minY = Integer.MAX_VALUE;
		for (int i = 0; i < v; i++) {
			minX = Math.min(minX, graph.vertices().get(i).getX());
			minY = Math.min(minY, graph.vertices().get(i).getY());
		}
		for (int i = 0; i < v; i++) {
			IVertex vertex = graph.vertices().get(i);
			vertex.moveTo(vertex.getX() - minX, vertex.getY() - minY);
		}
		int maxX = Integer.MIN_VALUE;
		int maxY = Integer.MIN_VALUE;
		for (int i = 0; i < v; i++) {
			maxX = Math.max(maxX, graph.vertices().get(i).getX());
			maxY = Math.max(maxY, graph.vertices().get(i).getY());
		}
		for (int i = 0; i < v; i++) {
			IVertex vertex = graph.vertices().get(i);
			if (maxX > 0) vertex.setX(vertex.getX() * width / maxX);
			if (maxY > 0) vertex.setY(vertex.getY() * height / maxY);
		}
	}
	
//	private double fullEnergy(Point2D.Double[] forces) {
//		double energy = 0;
//		for (int i = 0; i < myVert; i++) {
//			energy += forces[i].distance(O);
//		}
//		return energy;
//	}

}
