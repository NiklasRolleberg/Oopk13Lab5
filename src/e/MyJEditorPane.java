package e;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JEditorPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLFrameHyperlinkEvent;

public class MyJEditorPane extends JEditorPane implements HyperlinkListener{

	URL url;
	
	public MyJEditorPane() throws Exception {
		setEditable(false);
		setPage(new URL("http://www.aftonbladet.se/"));
		addHyperlinkListener(this);
	}
	
	public void setUrl(String url) throws MalformedURLException, IOException{
		setPage(new URL(url));
	}

	@Override
	public void hyperlinkUpdate(HyperlinkEvent e) {
		if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
			try {
				setPage(e.getURL());
			} catch (Throwable t) {
				t.printStackTrace();
			}
       }
	}	
}
