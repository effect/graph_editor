package org.amse.fedotov.graph_editor.view;

@SuppressWarnings("serial")
class ComponentLayoutAction extends LayoutAction {

	ComponentLayoutAction(GraphPanel graphPanel) {
		super(graphPanel, graphPanel.getLayouterFactory().getComponentLayouter(), "Component layout", "component.gif");
	}
	
	@Override
	public boolean isEnabled() {
		return getPanel().getGraph().components().size() > 1;
	}

}
