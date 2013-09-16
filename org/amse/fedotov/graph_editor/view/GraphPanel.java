package org.amse.fedotov.graph_editor.view;

import java.util.*;
import java.util.List;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.swing.*;

import org.amse.fedotov.graph_editor.layouters.impl.LayouterFactory;
import org.amse.fedotov.graph_editor.model.*;
import org.amse.fedotov.graph_editor.model.impl.Graph;
import org.amse.fedotov.graph_editor.settings.Settings;

@SuppressWarnings("serial")
class GraphPanel extends JPanel {

	static final int RADIUS = 3;
	static final int NEAR = 15;
	
	private IGraph myGraph = new Graph();
	
	private AbstractMode myMode;
	private List<AbstractMode> myModes;
	private List<LayoutAction> myLayoutActions = new ArrayList<LayoutAction>();
	private List<List<AbstractAction>> myFileActions = new ArrayList<List<AbstractAction>>();
	private SaveAction mySaveAction;
	
	private Set<IVertex> mySelectedVertices = new HashSet<IVertex>();
	private Set<IEdge> mySelectedEdges = new HashSet<IEdge>();
	
	private LayouterFactory myLayouterFactory = new LayouterFactory();
	
	private Main myMainFrame;
	
	private String myFilename = null;

	GraphPanel(Main main) {
		myMainFrame = main;
		
		setBackground(Color.WHITE);
		
		myModes = createModes();
		myLayoutActions = createLayoutActions();
		myFileActions = createFileActions();

		updatePanel();
	}
	
	GraphPanel(Main main, String filename) {
		this(main);
		myFilename = filename;
	}
	
	private List<AbstractMode> createModes() {
		List<AbstractMode> list = new ArrayList<AbstractMode>();
		list.add(new AddVertexMode(this));
		list.add(new AddEdgeMode(this));
		list.add(new RemoveMode(this));
		return list;
	}
	
	private List<LayoutAction> createLayoutActions() {
		List<LayoutAction> list = new ArrayList<LayoutAction>();
		list.add(new LayoutAction(this, getLayouterFactory().getAestheticLayouter(), "Aesthetic layout", "aesthetic.gif"));
		list.add(new LayoutAction(this, getLayouterFactory().getEllipseLayouter(), "Ellipse layout", "ellipse.gif"));
		list.add(new ComponentLayoutAction(this));
		list.add(new LayoutAction(this, getLayouterFactory().getRandomLayouter(), "Random layout", "random.gif"));
		return list;
	}
	
	private List<List<AbstractAction>> createFileActions() {
		List<List<AbstractAction>> list = new ArrayList<List<AbstractAction>>();
		List<AbstractAction> group;
		group = new ArrayList<AbstractAction>();
		group.add(new NewAction(this));
		group.add(new LoadAction(this));
		mySaveAction = new SaveAction(this);
		group.add(mySaveAction);
		group.add(new SaveAsAction(this));
		list.add(group);
		group = new ArrayList<AbstractAction>();
		group.add(new ImportAction(this));
		group.add(new ExportAction(this));
		list.add(group);
		group = new ArrayList<AbstractAction>();
		group.add(new ExitAction(this));
		list.add(group);
		return list;
	}
	
	LayouterFactory getLayouterFactory() {
		return myLayouterFactory;
	}

	List<AbstractMode> modes() {
		return myModes;
	}
	
	List<LayoutAction> layoutActions() {
		return myLayoutActions;
	}
	
	List<List<AbstractAction>> fileActions() {
		return myFileActions;
	}
	
	private boolean mySaved = true;
	
	boolean isSaved() {
		return mySaved;
	}
	
	void setSaved(boolean saved) {
		mySaved = saved;
	}
	
	String getFilename() {
		return myFilename;
	}
	
	void setFilename(String filename) {
		myFilename = filename;
		myMainFrame.setHeader(filename);
		updatePanel();
	}

	IGraph getGraph() {
		return myGraph;
	}
	
	void setGraph(IGraph graph) {
		myGraph = graph;
		mySaved = true;
		updatePanel();
	}
	
	void updatePanel() {
		for (AbstractMode mode : modes()) {
			mode.setEnabled(mode.isEnabled());
		}
		for (LayoutAction layoutAction : layoutActions()) {
			layoutAction.setEnabled(layoutAction.isEnabled());
		}
		mySaveAction.setEnabled(mySaveAction.isEnabled());
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		for (IVertex vertex: getGraph().vertices()) {
			paintVertex(g, vertex);
		} 
		for (IEdge edge: getGraph().edges()) {
			paintEdge(g, edge);
		}
		paintSelectedElements(g);
		if (myMode != null) {
			myMode.paintFakeElements(g);
		}
		if (Settings.printVerticesNames) {
			paintNames(g);
		}
	}
	
	private void paintVertex(Graphics g, IVertex vertex) {
		g.setColor(Color.BLACK);
		g.drawOval(vertex.getX() - RADIUS, vertex.getY() - RADIUS, 2 * RADIUS + 1, 2 * RADIUS + 1);
	}

	private void paintEdge(Graphics g, IEdge edge) {
		g.setColor(Color.BLACK);
		g.drawLine(edge.getSource().getX(), edge.getSource().getY(),  
				edge.getTarget().getX(), edge.getTarget().getY());
	}
	
	private void paintNames(Graphics g) {
		g.setColor(Color.GRAY);
		for (IVertex vertex : getGraph().vertices()) {
			g.drawString(vertex.getName(), vertex.getX() + 2 * RADIUS, vertex.getY() + 2 * RADIUS);
		}
	}
	
	private void paintSelectedElements(Graphics g) {
		g.setColor(Color.BLUE);
		for (IVertex vertex : mySelectedVertices) {
			g.fillOval(vertex.getX() - RADIUS, vertex.getY() - RADIUS, 2 * RADIUS + 1, 2 * RADIUS + 1);
		}
		for (IEdge edge : mySelectedEdges) {
			g.drawLine(edge.getSource().getX(), edge.getSource().getY(), 
					edge.getTarget().getX(), edge.getTarget().getY());
		}
	}
	
	void select(IVertex vertex) {
		mySelectedVertices.add(vertex);
	}
	
	void unselect(IVertex vertex) {
		mySelectedVertices.remove(vertex);
	}

	void select(IEdge edge) {
		mySelectedEdges.add(edge);
	}
	
	void unselect(IEdge edge) {
		mySelectedEdges.remove(edge);
	}

	void changeListeners(AbstractMode mode) {
		removeMouseListener(myMode);
		removeMouseMotionListener(myMode);
		myMode = mode;
		addMouseListener(myMode);
		addMouseMotionListener(myMode);
	}
	
	int getCorrectX(int x) {
		if (x < 0) 
			return 0;
		if (x > getWidth()) 
			return getWidth();
		return x;
	}
	
	int getCorrectY(int y) {
		if (y < 0) 
			return 0;
		if (y > getHeight())
			return getHeight();
		return y;
	}
	
	
	
	private FileFormat myFileFormat = new XMLFileFormat();
	private JFileChooser mySaveAsFileChooser;
	

	/**
	 * Shows save resource dialog.
	 * @return <code>true</code> if action that invoked this method wasn't cancelled, 
	 * <code>false</code> otherwise. 
	 */
	boolean askSave() {
		if (!mySaved) {
			Integer applyChanges = JOptionPane.showConfirmDialog(null, 
					"Graph has been modified. Save changes?", 
				    "Save Resource", JOptionPane.YES_NO_CANCEL_OPTION);
			if (applyChanges == JOptionPane.YES_OPTION) {
				saveGraph();
			} else if (applyChanges == JOptionPane.CANCEL_OPTION) {
				return false;
			}
		} 
		return true;
	}
	
	void saveGraph() {
		if (myFilename == null) {
			saveAsGraph();
		} else {
			saveGraph(myFilename);
		}
	}
	
	void saveAsGraph() {
		if (mySaveAsFileChooser == null) {
			mySaveAsFileChooser = new JFileChooser(".");
			mySaveAsFileChooser.addChoosableFileFilter(myFileFormat);
		}
		try {
			int res = mySaveAsFileChooser.showSaveDialog(null);
			if (res == JFileChooser.APPROVE_OPTION) {
				File file = mySaveAsFileChooser.getSelectedFile();
				file = myFileFormat.getFileWithExtension(file);
				saveGraph(file.getCanonicalPath());
			}  
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "Can't write to this file", 
					"Bad output file", JOptionPane.ERROR_MESSAGE);
		} 
	}
	
	void saveGraph(String filename) {
		try {
			File file = new File(filename);
			myFileFormat.getFileWriter(this).write(file);
			setFilename(file.getCanonicalPath());
			setSaved(true);
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "Can't write to this file", 
					"Bad output file", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	void exit() {
		if (askSave()) {
			Settings.frameWidth = myMainFrame.getWidth();
			Settings.frameHeight = myMainFrame.getHeight();
			Settings.frameXLocation = myMainFrame.getX();
			Settings.frameYLocation = myMainFrame.getY();
			Settings.writeSettings();
			System.exit(0);
		}
	}

	
}


