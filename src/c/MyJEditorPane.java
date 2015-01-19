package c;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JEditorPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLFrameHyperlinkEvent;

/** Extends from JEditorPane, shows a webbside*/
public class MyJEditorPane extends JEditorPane implements HyperlinkListener{

	MyFrame myFrame;
	
	private URL url;
	private LinkedList<URL> historyList;
	private ArrayList<URL> history;
	private int index;
	//private boolean showingHistory = false;
	
	
	public MyJEditorPane(MyFrame mfr) throws Exception {
		myFrame = mfr;
		
		historyList = new LinkedList<URL>();
		history = new ArrayList<URL>();
		
		//history.add(new URL("http://www.google.se"));
		//history.add(new URL("http://www.kth.se"));
		//history.add(new URL("http://www.csc.kth.se/utbildning/kth/kurser/DD1346/oopk13/laborationer/Lab5/labb5.html"));
		
		setEditable(false);
		url = new URL("http://www.csc.kth.se/utbildning/kth/kurser/DD1346/");
		historyList.add(url);
		setPage(url);
		index = 0;
		addHyperlinkListener(this);
	}
	
	/**go to the given web adress in the textfield
	 * @param url webadress*/
	public void setUrl(String url) throws MalformedURLException, IOException {
		this.url = new URL(url);
		setPage(this.url);
		makeHistory(this.url);
		historyList.add(this.url);
		index++;
		System.out.println(index);
		myFrame.enableBack(true);
		//skrivLista();
	}
	
	/**Create a history page in html and show it*/
	public void showHistory() {
		
		//if (showingHistory){
			//showingHistory = false;
		/*	try {
				System.out.println("går tilbaka"+url.toString());
				setPage(url);
				super.updateUI();
			} catch (IOException e) {
				System.out.println(e);
			}
		}
		else{*/
			String s = "<html><head><title> History </title></head><body bgcolor='#FFFFFF'>";
			for (Object o: history) {
				s += "<a href='"+o.toString()+"'>"+o.toString()+"</a><br/>";
			}
			s += "</body></html>";
			setText(s);
		//	showingHistory = true;
		//}
	}
	
	/**Goes forward if possible*/
	public String forward() {
		//showingHistory = false;
		try {
			url = historyList.get(index+1);
			setPage(url);
			index++;
			//System.out.println(index);
		} catch(IndexOutOfBoundsException N) {
			System.out.println(N);
		} catch (IOException e) {
			System.out.println("FEFFEFEL");
			myFrame.showError("feel");
		}
		
		myFrame.enableBack(true);
		
		if (index == historyList.size()-1) {
			myFrame.enableForward(false);
		}
		else{
			myFrame.enableForward(true);
		}
		//skrivLista();
		return url.toString();
	}
	
	/**Goes bacward if possible*/
	public String back() {
		//showingHistory = false;
		try {
			url = historyList.get(index-1);
			setPage(url);
			index--; //= historyList.indexOf(url);
		} catch (IndexOutOfBoundsException N) {
			System.out.println(N);
		} catch (IOException e) {
			myFrame.showError("feel");
		}
		myFrame.enableForward(true);
		if (index == 0) {
			myFrame.enableBack(false);
		}
		else{
			myFrame.enableBack(true);
		}
		
		//skrivLista();
		return url.toString();
	}
	
	/**Save the web adress in historyArray if doesn't already exist
	 * @param urlen is an url object*/
	public void makeHistory(URL urlen) {
		if (!history.contains(urlen)) {
			history.add(urlen);
		}
	}
	
	/**Clears from index i in historyList
	 * @param i index to clear from history list*/
	public void rensa(int i) {
		for (int j=i; j<historyList.size()-1;j++) {
			historyList.remove(i+1);
		}
	}
	
	
	/**Make it possible to click on a link and get to the link site*/
	@Override
	public void hyperlinkUpdate(HyperlinkEvent e) {
		if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
			try {
				//showingHistory = false;
				url = e.getURL();
				rensa(index);
				historyList.add(url);
				makeHistory(url);
				setPage(url);
				index++;
				myFrame.seturltext(url.toString());
				rensa(index+1);
				myFrame.enableBack(true);
				myFrame.enableForward(false);
				//skrivLista();
				//historyList.
			} catch (Throwable t) {
				System.out.println(t);
			}
       }
	}	
}
