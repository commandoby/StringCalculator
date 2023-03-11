package com.commandoby.stringCalculator.swing;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

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

	private static String consoleText = Application.START + "\n";
	public static List<String> history = new ArrayList<>();
	private int historyNumber = 0;

	private ActionListener actionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("enterField")) {
				history.add(enterField.getText());
				historyNumber = history.size();
				print("You entered: " + enterField.getText() + "\n");
				Application.print(Application.textAnalysis(enterField.getText()) + "\n");
				enterField.setText("");
			}
		}
	};

	private KeyAdapter keyListener = new KeyAdapter() {
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == 38 || e.getKeyCode() == 33) {
				if (historyNumber > 0) {
					enterField.setText(history.get(--historyNumber));
				}
			}
			if (e.getKeyCode() == 40 || e.getKeyCode() == 34) {
				if (historyNumber >= history.size() - 1) {
					historyNumber = history.size();
					enterField.setText("");
				}
				if (historyNumber < history.size() - 1) {
					enterField.setText(history.get(++historyNumber));
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
		textArea.setText(consoleText);
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

	public static void print(String text) {
		consoleText += text;
		textArea.setText(consoleText);
	}
}
