package org.amse.fedotov.graph_editor.view;

import java.io.File;
import java.io.IOException;

import javax.swing.filechooser.FileFilter;

import org.amse.fedotov.graph_editor.reader.IFileReader;
import org.amse.fedotov.graph_editor.writer.IFileWriter;

abstract class FileFormat extends FileFilter {

	abstract protected String getExtension();
	
	abstract protected IFileWriter getFileWriter(GraphPanel graphPanel);
	
	abstract protected IFileReader getFileReader(File file);
	
	@Override
	public boolean accept(File f) {
		if (f.isDirectory()) {
			return true;
		}
		return (f.getName().endsWith(getExtension()));
	}
	
	@Override
	public String getDescription() {
		return getExtension().substring(1).toUpperCase() + " files (*" + getExtension() + ")";
	}
	
	protected File getFileWithExtension(File file) throws IOException {
		// there is an empty extension of file
		if (file.getName().indexOf(".") == -1) {
			// add extension
			file = new File(file.getPath() + getExtension());
		}
		return file;
	}

}
