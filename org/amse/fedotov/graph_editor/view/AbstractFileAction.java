package org.amse.fedotov.graph_editor.view;

import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

abstract class AbstractFileAction extends AbstractAction {
	
	private JFileChooser myFileChooser;
	
	protected JFileChooser getFileChooser(List<FileFormat> fileFormats) {
		if (myFileChooser == null) {
			myFileChooser = new JFileChooser(".");
			for (FileFormat fileFormat : fileFormats) {
				myFileChooser.addChoosableFileFilter(fileFormat);
			}
		}
		return myFileChooser;
	}
	
	abstract protected List<FileFormat> getFileFormats();

	abstract protected FileFormat getDefaultFileFormat();
	
	protected FileFormat getFileFormat(FileFilter f) {
		FileFormat format;
		if (f instanceof FileFormat) {
			format = (FileFormat)f;
		} else {
			format = getDefaultFileFormat();
		}
		return format;
	}

}
