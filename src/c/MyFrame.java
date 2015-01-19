package c;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.tree.TreePath;

/**Myframe contains a MyJEditorPane, and a JToolbarwith menu buttons*/
public class MyFrame extends JFrame implements ActionListener, KeyListener{
	
	JPanel container;
	JPanel container2;
	
	JToolBar toolbar;
	
	JButton close;
	JButton go;
	JButton forward;
	JButton back;
	JButton HISTORY;
	TextField field;
	MyJEditorPane mJEP;
	
	public MyFrame() {
		container = new JPanel();
		container.setLayout(new BorderLayout());
		add(container);
		
		toolbar = new JToolBar();		
		
		field = new TextField();
		field.addKeyListener(this);
		go = new JButton("GO!");
		go.addActionListener(this);
		close = new JButton("CLOSE");
		close.addActionListener(this);
		forward = new JButton("Forward");
		forward.addActionListener(this);
		forward.setEnabled(false);
		back = new JButton("Back");
		back.addActionListener(this);
		back.setEnabled(false);
		HISTORY =  new JButton("History");
		HISTORY.addActionListener(this);
		
		toolbar.add(back);
		toolbar.add(forward);
		toolbar.add(field);
		toolbar.add(go);
		toolbar.add(HISTORY);
		toolbar.add(close);
		container.add(toolbar, BorderLayout.NORTH);

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		try {
			mJEP = new MyJEditorPane(this);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		//lägger MyJEditorPane i en jscrollpane
		JScrollPane editorScrollPane = new JScrollPane(mJEP);
		editorScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		editorScrollPane.setMinimumSize(new Dimension(10, 10));
		
		container.add(editorScrollPane, BorderLayout.CENTER);
		setPreferredSize(new Dimension(800,800));
		pack();
		setVisible(true);
		//showHistory();
	}
	
	/**Show the web address in the textfield*/
	public void seturltext(String t) {
		field.setText(t);
	}
	/**Gör framårknappen klickbar/ inte klickbar*/
	public void enableForward(boolean b) {
		forward.setEnabled(b);
	}
	
	/**Gör bakåtknappen klickbar/ inte klickbar*/
	public void enableBack(boolean b) {
		back.setEnabled(b);
	}
	
	/**Visar en popupruta med ett valfritt fel (String)*/
	public void showError(String s) {
		JOptionPane.showMessageDialog(this,s);  
	}
	
	/**Anropas när man klickar på enter, eller trycker på GO!
	 * kollar om man skrivit http://, lägger annars till det
	 * */
	public void goButton() {
		try {
			
			String s = field.getText();
			if (!field.getText().contains("http://")) {
				s = "http://"+s;
			}
			mJEP.setUrl(s);
		} catch (MalformedURLException m) {
			showError("Fel, försök med en ny adress!");
		}catch (IOException IOe) {
			showError("Fel, försök med en ny adress!");
			System.out.println(IOe);
		}
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MyFrame myframe = new MyFrame();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "GO!") {
			goButton();
		}
		if (e.getActionCommand() == "CLOSE") {
			System.exit(0);
		}
		
		if (e.getActionCommand() == "Forward") {
			field.setText(mJEP.forward());
		}
		
		if (e.getActionCommand() == "Back") {
			field.setText(mJEP.back());
		}
		
		if (e.getActionCommand() == "History") {
			mJEP.showHistory();
		}
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == 10) { //så man kan trycka på enter
			goButton();
		}	
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
