package org.amse.fedotov.graph_editor.view;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.amse.fedotov.graph_editor.exception.BadInputException;

@SuppressWarnings("serial")
class LoadAction extends AbstractFileAction {

	private GraphPanel myPanel;

	private List<FileFormat> myFileFormats;	
	private FileFormat myDefaultFileFormat;
	
	LoadAction(GraphPanel panel) {
		putValue(NAME, "Load...");
		myPanel = panel;
		myFileFormats = new ArrayList<FileFormat>();
		myFileFormats.add(new XMLFileFormat());
		myDefaultFileFormat = new XMLFileFormat(); 
	}
	
	public void actionPerformed(ActionEvent e) {
		if (myPanel.askSave()) {
			JFileChooser fc = getFileChooser(getFileFormats());
			int res = fc.showOpenDialog(null);
			if (res == JFileChooser.APPROVE_OPTION) {
				try {
					File file = fc.getSelectedFile();
					FileFormat format = getFileFormat(fc.getFileFilter());
					myPanel.setGraph(format.getFileReader(file).read());
					myPanel.setFilename(file.getCanonicalPath());

				} catch (BadInputException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), 
							"Bad input file", JOptionPane.ERROR_MESSAGE);
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), 
							"Bad input file", JOptionPane.ERROR_MESSAGE);
				}
			} 
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
	
}
