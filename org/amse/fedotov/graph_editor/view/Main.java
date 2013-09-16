package org.amse.fedotov.graph_editor.view;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.*;

import org.amse.fedotov.graph_editor.exception.BadInputException;
import org.amse.fedotov.graph_editor.reader.IFileReader;
import org.amse.fedotov.graph_editor.reader.TXTFileReader;
import org.amse.fedotov.graph_editor.reader.UniversalReader;
import org.amse.fedotov.graph_editor.reader.XMLFileReader;
import org.amse.fedotov.graph_editor.settings.Settings;

@SuppressWarnings("serial")
public class Main extends JFrame {
	
	final private GraphPanel myGraphPanel;
	
	public Main() {
		super("GraphEditor");
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
 		addWindowListener(new ExitWindowAdapter());
 		Settings.readSettings();
		setSize(Settings.frameWidth, Settings.frameHeight);
		setLocation(Settings.frameXLocation, Settings.frameYLocation);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		getContentPane().add(panel);

		myGraphPanel = new GraphPanel(this);
		panel.add(myGraphPanel, BorderLayout.CENTER);
		
		JToolBar toolBar = createToolBar(myGraphPanel);
		toolBar.setFloatable(false);
		panel.add(toolBar, BorderLayout.NORTH);
		
		JMenuBar menuBar = createMenuBar(myGraphPanel);
		setJMenuBar(menuBar);

		setVisible(true);
	}
	
	public Main(String filename) {
		this();
		File file = new File(filename);
		IFileReader reader;
		try {
			reader = new UniversalReader(file).getReader();
		} catch (BadInputException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), 
					"Bad input file", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		FileFormat format;
		if (reader instanceof TXTFileReader) {
			try {
				format = new TXTFileFormat();
				myGraphPanel.setGraph(format.getFileReader(file).read());
				myGraphPanel.getLayouterFactory().getAestheticLayouter().calculateCoordinates(
						myGraphPanel.getGraph(), myGraphPanel.getWidth(), myGraphPanel.getHeight());
				myGraphPanel.setSaved(false);
				myGraphPanel.updatePanel();
				return;
			} catch (BadInputException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), 
						"Bad input file", JOptionPane.ERROR_MESSAGE);
			}
		} else if (reader instanceof XMLFileReader) {
			try {
				format = new XMLFileFormat();
				myGraphPanel.setGraph(format.getFileReader(file).read());
				myGraphPanel.setFilename(file.getCanonicalPath());
				return;
			} catch (IOException ex) {
				JOptionPane.showMessageDialog(null, "Bad input file", 
						"Bad input file", JOptionPane.ERROR_MESSAGE);
			} catch (BadInputException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), 
						"Bad input file", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			// this is unreachable code
			JOptionPane.showMessageDialog(null, "Bad input file", 
					"Bad input file", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private JToolBar createToolBar(GraphPanel graphPanel) {
		JToolBar toolBar = new JToolBar();
		ButtonGroup group = new ButtonGroup();
		for (AbstractMode mode : graphPanel.modes()) {
			JToggleButton button = new JToggleButton(mode);
			group.add(button);
			toolBar.add(button);
		}
		toolBar.addSeparator();
		for (LayoutAction layoutAction : graphPanel.layoutActions()) {
			JButton button = new JButton(layoutAction);
			toolBar.add(button);
		}
		return toolBar;
	}
	
	private JMenuBar createMenuBar(GraphPanel graphPanel) {
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(createFileMenu());
		menuBar.add(new SettingsMenu(graphPanel));
		JMenu help = new JMenu("Help");
		help.add(new JMenuItem(new AboutAction()));
		menuBar.add(help);
		return menuBar;
	}
	
	private JMenu createFileMenu() {
		JMenu fileMenu = new JMenu("File");
		for (int i = 0; i < myGraphPanel.fileActions().size(); i++) {
			if (i > 0) {
				fileMenu.addSeparator();
			}
			java.util.List<AbstractAction> group = myGraphPanel.fileActions().get(i);
			for (AbstractAction fileAction : group) {
				fileMenu.add(new JMenuItem(fileAction));
			}
		}
		return fileMenu;
	}
	
	private class ExitWindowAdapter extends WindowAdapter {
		
		@Override
		public void windowClosing(WindowEvent e){
			myGraphPanel.exit();
		}
	}
	
	void setHeader(String filename) {
		if (filename == null) {
			setTitle("GraphEditor");
		} else {
			setTitle("GraphEditor - " + filename);
		}
	}
	
}
