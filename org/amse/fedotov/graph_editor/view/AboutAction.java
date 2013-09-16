package org.amse.fedotov.graph_editor.view;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLFrameHyperlinkEvent;

@SuppressWarnings("serial")
class AboutAction extends AbstractAction {
	
	private String myName = "About...";
	
	AboutAction() {
		putValue(NAME, myName);
	}

	public void actionPerformed(ActionEvent e) {
		String about = "GraphEditor\n\n" + 
		"Author: Pavel Fedotov\n" + 
		"E-mail: fedotov@rain.ifmo.ru\n\n" +
		"Visit http://www.amse.ru"; 
//		"© 2007, Pavel Fedotov.";
		JOptionPane.showMessageDialog(null, about, "About", JOptionPane.INFORMATION_MESSAGE);
	}
	
	class Hyperactive implements HyperlinkListener {
		 
        public void hyperlinkUpdate(HyperlinkEvent e) {
            if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                JEditorPane pane = (JEditorPane) e.getSource();
                if (e instanceof HTMLFrameHyperlinkEvent) {
                    HTMLFrameHyperlinkEvent  evt = (HTMLFrameHyperlinkEvent)e;
                    HTMLDocument doc = (HTMLDocument)pane.getDocument();
                    doc.processHTMLFrameHyperlinkEvent(evt);
                } else {
                    try {
                        pane.setPage(e.getURL());
                    } catch (Throwable t) {
                        t.printStackTrace();
                    }
                }
            }
        }
    }


}
