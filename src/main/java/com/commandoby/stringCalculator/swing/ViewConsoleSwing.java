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

public class ViewConsoleSwing implements Runnable, ActionListener {
	private JFrame frame;
	private JTextField enterField;
	private static JTextArea textArea;

	@Override
	public void run() {
		frame = new JFrame("String calculator");
		frame.setSize(600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		addComponents();
	}

	private void addComponents() {
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setText(Application.consoleText);
		frame.add(new JScrollPane(textArea));

		JPanel downPanel = new JPanel(new BorderLayout());
		downPanel.add(new JLabel(" Enter the equation: "), BorderLayout.WEST);

		enterField = new JTextField();
		enterField.setActionCommand("enterField");
		enterField.addActionListener(this);
		downPanel.add(enterField);

		frame.add(downPanel, BorderLayout.SOUTH);
	}

	public static void print() {
		textArea.setText(Application.consoleText);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("enterField")) {
			Application.print(Application.textAnalysis(enterField.getText()) + "\n");
			enterField.setText("");
		}
	}

}
