package org.amse.fedotov.graph_editor.view;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.amse.fedotov.graph_editor.model.IGraph;
import org.amse.fedotov.graph_editor.model.IVertex;

@SuppressWarnings("serial")
class ExportAction extends AbstractFileAction {

	private GraphPanel myPanel;
	
	private List<FileFormat> myFileFormats;
	private FileFormat myDefaultFileFormat;
	
	ExportAction(GraphPanel panel) {
		putValue(NAME, "Export...");
		myPanel = panel;
		myFileFormats = new ArrayList<FileFormat>();
		
		myFileFormats.add(new JPGFileFormat());
		myFileFormats.add(new PNGFileFormat());
		myFileFormats.add(new TXTFileFormat());
		
		myDefaultFileFormat = new TXTFileFormat();
	}

	public void actionPerformed(ActionEvent e) {
		boolean undoRenamingVertices = false;
		List<String> oldVeticesNames = null;
		try {
			JFileChooser fc = getFileChooser(getFileFormats());
			int res = fc.showSaveDialog(null);
			if (res == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				FileFormat format = getFileFormat(fc.getFileFilter());
				if (format instanceof TXTFileFormat) {
					if (!getGraph().isCorrectNumbers()) {
						Integer applyChanges = JOptionPane.showConfirmDialog(null, "Vertices will be renamed in export file. Do you want to apply changes to the graph?", 
							    "Renaming", JOptionPane.YES_NO_CANCEL_OPTION);
						if (applyChanges == JOptionPane.YES_OPTION) {
							getGraph().renameVertices();		
						} else if (applyChanges == JOptionPane.NO_OPTION) {
							oldVeticesNames = getGraph().getVerticesNames();
							undoRenamingVertices = true;
							getGraph().renameVertices();
						} else {
							return;
						}
					}
				}
				file = format.getFileWithExtension(file);
				format.getFileWriter(myPanel).write(file);
			} 
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "Can't write to this file", 
					"Bad output file", JOptionPane.ERROR_MESSAGE);
		} finally {
			if (undoRenamingVertices) {
				int i = 0;
				for (IVertex vertex : getGraph().vertices()) {
					vertex.setName(oldVeticesNames.get(i++));
				}
			}
			myPanel.repaint();
		}
	}

	@Override
	protected FileFormat getDefaultFileFormat() {
		return myDefaultFileFormat;
	}

	@Override
	protected List<FileFormat> getFileFormats() {
		return myFileFormats;
	}
	
	private IGraph getGraph() {
		return myPanel.getGraph();
	}

}
