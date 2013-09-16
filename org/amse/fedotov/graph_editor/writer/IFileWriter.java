package org.amse.fedotov.graph_editor.writer;

import java.io.File;
import java.io.IOException;

public interface IFileWriter {
	
	public void write(File file) throws IOException;
}
