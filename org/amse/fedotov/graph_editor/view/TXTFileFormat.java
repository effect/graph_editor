package org.amse.fedotov.graph_editor.view;

import java.io.File;

import org.amse.fedotov.graph_editor.reader.IFileReader;
import org.amse.fedotov.graph_editor.reader.TXTFileReader;
import org.amse.fedotov.graph_editor.writer.IFileWriter;
import org.amse.fedotov.graph_editor.writer.TXTFileWriter;

class TXTFileFormat extends FileFormat {
	
	private final String txtExtension = ".gtxt";
	
	@Override
	protected String getExtension() {
		return txtExtension;
	}

	@Override
	protected IFileWriter getFileWriter(GraphPanel panel) {
		return new TXTFileWriter(panel.getGraph());
	}

	@Override
	protected IFileReader getFileReader(File file) {
		return new TXTFileReader(file);
	}	
}
