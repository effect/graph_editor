package org.amse.fedotov.graph_editor.view;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
class SaveAsAction extends AbstractFileAction {

	private GraphPanel myPanel;
	
	private List<FileFormat> myFileFormats;	
	private FileFormat myDefaultFileFormat;
	
	SaveAsAction(GraphPanel panel) {
		putValue(NAME, "Save as...");
		myPanel = panel;
		myFileFormats = new ArrayList<FileFormat>();
		myDefaultFileFormat = new XMLFileFormat(); 
		myFileFormats.add(myDefaultFileFormat);
	}
	
	public void actionPerformed(ActionEvent e) {
		myPanel.saveAsGraph();
	}
	
	@Override
	protected FileFormat getDefaultFileFormat() {
		return myDefaultFileFormat;
	}

	@Override
	protected List<FileFormat> getFileFormats() {
		return myFileFormats;
	}

}
