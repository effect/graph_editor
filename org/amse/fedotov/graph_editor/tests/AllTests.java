package org.amse.fedotov.graph_editor.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {
	public static Test suite() {
		TestSuite suite = new TestSuite("Tests.");
		suite.addTestSuite(ModelTest.class);
		suite.addTestSuite(XMLWriterTest.class);
		suite.addTestSuite(XMLReaderTest.class);
		suite.addTestSuite(TXTWriterTest.class);
		suite.addTestSuite(TXTReaderTest.class);
		suite.addTestSuite(LayouterTest.class);
		return suite;
	}
}
