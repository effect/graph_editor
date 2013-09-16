package org.amse.fedotov.graph_editor.writer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import org.amse.fedotov.graph_editor.exception.BadInputException;

public class JPEGFileWriter implements IFileWriter {
	
	private JPanel myPanel;
	
	public JPEGFileWriter(JPanel panel) {
		myPanel = panel;
	}
	
	public void write(File file) throws IOException {
		try {
			BufferedImage image = new BufferedImage(myPanel.getWidth(), myPanel.getHeight(), BufferedImage.TYPE_INT_RGB);
			Graphics g = image.getGraphics();
			myPanel.paint(g);
			ImageIO.write(image, "jpg", file);
		} catch (FileNotFoundException ex) {
			throw new BadInputException("Bad output file");
		}
	}
}
