package e;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class MyFrame extends JFrame implements ActionListener{
	
	JPanel container;
	JPanel container2;
	
	JButton close;
	JButton go;
	JButton forward;
	JButton back;
	TextField field;
	
	MyJEditorPane mJEP;
	
	public MyFrame() {
		container = new JPanel();
		container.setLayout(new BorderLayout());
		add(container);
		
		
		container2 = new JPanel();
		container2.setLayout(new BorderLayout());
		
		field = new TextField();
		go = new JButton("GO!");
		go.addActionListener(this);
		close = new JButton("CLOSE");
		close.addActionListener(this);
		forward = new JButton("Forward!");
		forward.addActionListener(this);
		back = new JButton("Back");
		back.addActionListener(this);
		
		
		container2.add(back, BorderLayout.WEST);
		container2.add(forward, BorderLayout.EAST);
		container2.add(field, BorderLayout.CENTER);
		container2.add(go, BorderLayout.SOUTH);
		container2.add(close, BorderLayout.NORTH);
		
		container.add(container2, BorderLayout.NORTH);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		try {
			mJEP = new MyJEditorPane();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		//Put the editor pane in a scroll pane.
		JScrollPane editorScrollPane = new JScrollPane(mJEP);
		editorScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		editorScrollPane.setMinimumSize(new Dimension(10, 10));
		
		container.add(editorScrollPane, BorderLayout.CENTER);
		
		setPreferredSize(new Dimension(800,800));
		pack();
		setVisible(true);
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyFrame myframe = new MyFrame();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "GO!") {
			//System.out.println(field.getText());
			try {
				mJEP.setUrl(field.getText());
			}catch (MalformedURLException m) {
				System.out.println("FEEEL");
			}catch (IOException IOe) {
				System.out.println(IOe);
			}
		}
		if (e.getActionCommand() == "CLOSE") {
			System.exit(0);
		}
	}
}
