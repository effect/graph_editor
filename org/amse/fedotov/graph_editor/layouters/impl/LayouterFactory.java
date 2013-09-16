package org.amse.fedotov.graph_editor.layouters.impl;

import org.amse.fedotov.graph_editor.layouters.ILayouter;

public class LayouterFactory {
	
	public ILayouter getEllipseLayouter() {
		return new EllipseLayouter();
	}
	
	public ILayouter getRandomLayouter() {
		return new RandomLayouter();
	}
	
	public ILayouter getComponentLayouter() {
		return new ComponentLayouter(); 
	}
	
	public ILayouter getAestheticLayouter() {
		return new AestheticLayouter();
	}
}
