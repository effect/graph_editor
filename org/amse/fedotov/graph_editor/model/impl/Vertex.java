package org.amse.fedotov.graph_editor.model.impl;

import org.amse.fedotov.graph_editor.model.*;

final class Vertex implements IVertex {
	
	private int myX;
	private int myY;
	private String myName;

	public String getName() {
		return myName;
	}

	public int getX() {
		return myX;
	}

	public int getY() {
		return myY;
	}
	
	public void setX(int x) {
		myX = x;
	}

	public void setY(int y) {
		myY = y;
	}


	public void moveTo(int x, int y) {
		myX = x;
		myY = y;
	}

	public void setName(String name) {
		myName = name;
	}
	
	public Vertex(int x, int y) {
		myX = x;
		myY = y;
	}
	
	@Override
	public String toString() {
		String res = "Vertex";
		if (myName != null) {
			res += " " + myName;
		}
		return res + ": " + myX + " " + myY;
	}

	public double distance(int x, int y) {
		return Math.hypot(x - myX, y - myY);
	}
	
//	@Override 
//	public boolean equals(Object o) {
//		if (!(o instanceof IVertex)) {
//			return false;
//		}
//		IVertex v = (Vertex)o;
//		return getName().equals(v.getName());
//	}

}
