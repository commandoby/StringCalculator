package com.commandoby.stringCalculator.swing;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
		frame.setSize(600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		addComponents();
		addConsoleLine(Application.START);
	}
	
	private void addComponents() {
		textArea = new JTextArea();
		textArea.setEditable(false);
		frame.add(new JScrollPane(textArea));

		JPanel downPanel = new JPanel(new BorderLayout());
		downPanel.add(new JLabel(" Enter the equation: "), BorderLayout.WEST);
		
		enterField = new JTextField();
		enterField.setActionCommand("enterField");
		enterField.addActionListener(this);
		downPanel.add(enterField);
		
		frame.add(downPanel, BorderLayout.SOUTH);
	}
	
	public static void addConsoleLine(String text) {
		textArea.append(text + "\n");
	}
 
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("enterField")) {
			addConsoleLine(Application.textAnalysis(enterField.getText()));
			enterField.setText("");
		}
	}

}
