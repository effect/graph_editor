package org.amse.fedotov.graph_editor.view;

import java.io.File;

import org.amse.fedotov.graph_editor.exception.BadInputException;
import org.amse.fedotov.graph_editor.reader.IFileReader;
import org.amse.fedotov.graph_editor.writer.IFileWriter;
import org.amse.fedotov.graph_editor.writer.PNGFileWriter;

public class PNGFileFormat extends FileFormat {
	
	@Override
	protected String getExtension() {
		return ".png";
	}

	@Override
	protected IFileReader getFileReader(File file) {
		throw new BadInputException("Can't read graph from image");
	}

	@Override
	protected IFileWriter getFileWriter(GraphPanel graphPanel) {
		return new PNGFileWriter(graphPanel);
	}

}
