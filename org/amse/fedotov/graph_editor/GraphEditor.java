package org.amse.fedotov.graph_editor;

import org.amse.fedotov.graph_editor.view.Main;


/**
 * Starts GraphEditor
 *
 * @author Pavel Fedotov
 */
public class GraphEditor {
	public static void main(String[] args) {
		if (args.length == 0) {
			new Main();
		} else {
			new Main(args[0]);
		}
	}
}