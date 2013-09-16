package org.amse.fedotov.graph_editor.view;

import java.io.File;


import org.amse.fedotov.graph_editor.exception.BadInputException;
import org.amse.fedotov.graph_editor.reader.IFileReader;
import org.amse.fedotov.graph_editor.writer.IFileWriter;
import org.amse.fedotov.graph_editor.writer.JPEGFileWriter;

class JPGFileFormat extends FileFormat {

	@Override
	protected String getExtension() {
		return ".jpg";
	}

	@Override
	protected IFileReader getFileReader(File file) {
		throw new BadInputException("Can't read graph from image");
	}

	protected IFileWriter getFileWriter(GraphPanel graphPanel) {
		return new JPEGFileWriter(graphPanel);
	}
}
