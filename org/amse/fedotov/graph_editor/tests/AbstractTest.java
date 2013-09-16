package org.amse.fedotov.graph_editor.tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.amse.fedotov.graph_editor.model.IGraph;

import junit.framework.TestCase;

abstract class AbstractTest extends TestCase {
	
	final private String myPath = "org" + File.separatorChar + "amse" + File.separatorChar + 
		"fedotov" + File.separatorChar + "graph_editor" + File.separatorChar + 
		"tests" + File.separatorChar;
	
	final private String myExt = ".ok";
	
	protected String getPath() {
		return myPath;
	}
	
	protected String getExt() {
		return myExt;
	}

	protected boolean isEqualFiles(String name) {
		BufferedReader readerOUT;
		BufferedReader readerOK;
		try {
			readerOUT = new BufferedReader(new FileReader(getPath() + name + ".out"));
			readerOK = new BufferedReader(new FileReader(getPath() + name + getExt()));
			try {
				String s0 = "";
				String s1 = "";
				while (((s0 = readerOUT.readLine()) != null) & 
						((s1 = readerOK.readLine()) != null)) {
					if (!s0.equals(s1)) {
						return false;
					}
				}
				return s0 == s1;
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				readerOUT.close();
				readerOK.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	protected File printToFile(String name, IGraph graph) {
		File outFile = new File(getPath() + name + ".out");
		PrintWriter writer;
		try {
			writer = new PrintWriter(new FileWriter(outFile));
			try {
				writer.print(graph);
			} finally {
				writer.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return outFile;
	}
	
	protected File printToFile(String name, Object o) {
		File outFile = new File(getPath() + name + ".out");
		PrintWriter writer;
		try {
			writer = new PrintWriter(outFile);
			try {
				writer.print(o);
			} finally {
				writer.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return outFile;
	}

	
}
