package org.amse.fedotov.graph_editor.view;

import java.io.File;

import org.amse.fedotov.graph_editor.reader.IFileReader;
import org.amse.fedotov.graph_editor.reader.XMLFileReader;
import org.amse.fedotov.graph_editor.writer.IFileWriter;
import org.amse.fedotov.graph_editor.writer.XMLFileWriter;

class XMLFileFormat extends FileFormat {
	
	private final String xmlExtension = ".gxml";
	
	@Override
	protected String getExtension() {
		return xmlExtension;
	}

	@Override
	protected IFileWriter getFileWriter(GraphPanel panel) {
		return new XMLFileWriter(panel.getGraph());
	}

	@Override
	protected IFileReader getFileReader(File file) {
		return new XMLFileReader(file);
	}
}
