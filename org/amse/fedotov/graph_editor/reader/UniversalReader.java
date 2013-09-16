package org.amse.fedotov.graph_editor.reader;

import java.io.File;

import org.amse.fedotov.graph_editor.exception.BadInputException;
import org.amse.fedotov.graph_editor.model.IGraph;


public class UniversalReader implements IFileReader {
	
	private IFileReader myReader;
	private boolean myOK = false;
	private IGraph myGraph;
	private File myFile;
	
	public UniversalReader(File file) throws BadInputException {
		myFile = file;
		try {
			myReader = new TXTFileReader(file);
			myGraph = myReader.read();
			myOK = true;
			return;
		} catch (BadInputException ex) {
			if (ex.getMessage().equals("File not found")) {
				throw ex;
			}
		}

		try {
			myReader = new XMLFileReader(file);
			myGraph = myReader.read();
			myOK = true;
			return;
		} catch (BadInputException ex) {
			if (ex.getMessage().equals("File not found")) {
				throw ex;
			}
		}
		
		// !isOK
		throw new BadInputException("Bad input file format");
	}

	public IGraph read() throws BadInputException {
		if (myOK) {
			return myGraph;
		} else {
			throw new BadInputException("Bad input file format");
		}
	}
	
	public IFileReader getReader() {
		if (myReader instanceof TXTFileReader) {
			return new TXTFileReader(myFile);
		}
		if (myReader instanceof XMLFileReader) {
			return new XMLFileReader(myFile);
		}
		return null;
	}

}
