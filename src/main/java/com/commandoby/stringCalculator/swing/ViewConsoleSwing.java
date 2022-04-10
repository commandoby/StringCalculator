package com.commandoby.stringCalculator.swing;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.commandoby.stringCalculator.Application;

public class ViewConsoleSwing  implements Runnable, ActionListener {
	JFrame frame;
	JTextField enterField;
	static JTextArea textArea;

	@Override
	public void run() {
		frame = new JFrame("String calculator");
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		addComponents();
		addConsoleLine(Application.START);
	}
	
	private void addComponents() {
		textArea = new JTextArea();
		textArea.setEditable(false);
		frame.add(new JScrollPane(textArea));
		
		enterField = new JTextField();
		enterField.setActionCommand("enterField");
		enterField.addActionListener(this);
		frame.add(enterField, BorderLayout.SOUTH);
	}
	
	public static void addConsoleLine(String text) {
		textArea.setText(textArea.getText() + text + "\n");
	}
 
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("enterField")) {
			addConsoleLine(Application.textAnalysis(enterField.getText()));
			enterField.setText("");
		}
	}

}
