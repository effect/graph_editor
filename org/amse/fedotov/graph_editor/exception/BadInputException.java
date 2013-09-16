package org.amse.fedotov.graph_editor.exception;

/**
 * This exception is thrown when user enters incorrect input data. 
 * @author Pavel Fedotov
 *
 */
@SuppressWarnings("serial")
public class BadInputException extends RuntimeException {
	
	public BadInputException() {
		super();
	}
	
	public BadInputException(String message) {
		super(message);
	}
}
