package org.amse.fedotov.graph_editor.view;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.amse.fedotov.graph_editor.exception.BadInputException;
import org.amse.fedotov.graph_editor.model.IGraph;

@SuppressWarnings("serial")
class ImportAction extends AbstractFileAction {

	private GraphPanel myPanel;
	private List<FileFormat> myFileFormats;
	private FileFormat myDefaultFileFormat;

	ImportAction(GraphPanel panel) {
		putValue(NAME, "Import...");
		myPanel = panel;
		myFileFormats = new ArrayList<FileFormat>();
		myFileFormats.add(new TXTFileFormat());
		myDefaultFileFormat = new TXTFileFormat();		
	}

	public void actionPerformed(ActionEvent e) {
		if (myPanel.askSave()) {
			try {
				JFileChooser fc = getFileChooser(getFileFormats());
				int res = fc.showOpenDialog(null);
				if (res == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					FileFormat format = getFileFormat(fc.getFileFilter());
					myPanel.setGraph(format.getFileReader(file).read());
					myPanel.getLayouterFactory().getAestheticLayouter().calculateCoordinates(getGraph(), myPanel.getWidth(), myPanel.getHeight());
					myPanel.setSaved(false);
				} 
			} catch (BadInputException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), 
						"Bad input file", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private IGraph getGraph() {
		return myPanel.getGraph();
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
