package com.commandoby.stringCalculator.swing;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.commandoby.stringCalculator.Application;

public class ViewConsoleSwing implements Runnable {
	private JFrame frame;
	private JTextField enterField;
	private static JTextArea textArea;
	
	private int consoleListHistoryNumber = 0;

	private ActionListener actionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("enterField")) {
				Application.consoleListHistory.add(enterField.getText());
				consoleListHistoryNumber = Application.consoleListHistory.size();
				Application.print(Application.textAnalysis(enterField.getText()) + "\n");
				enterField.setText("");
			}
		}
	};
	
	private KeyAdapter keyListener = new KeyAdapter() {
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == 38) {
				if (consoleListHistoryNumber > 0) {
					enterField.setText(Application.consoleListHistory.get(--consoleListHistoryNumber));
				}
			}
			if (e.getKeyCode() == 40) {
				if (consoleListHistoryNumber >= Application.consoleListHistory.size()-1) {
					consoleListHistoryNumber = Application.consoleListHistory.size();
					enterField.setText("");
				}
				if (consoleListHistoryNumber < Application.consoleListHistory.size()-1) {
					enterField.setText(Application.consoleListHistory.get(++consoleListHistoryNumber));
				}
			}
		}
	};

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
		enterField.addActionListener(actionListener);
		enterField.addKeyListener(keyListener);
		downPanel.add(enterField);

		frame.add(downPanel, BorderLayout.SOUTH);
	}

	public static void print() {
		textArea.setText(Application.consoleText);
	}
}
